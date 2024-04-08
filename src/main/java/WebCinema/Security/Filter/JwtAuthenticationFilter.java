package WebCinema.Security.Filter;

import WebCinema.Repository.UserRepository;
import WebCinema.Security.Service.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            UserDetailsService userDetailsService,
            UserRepository userRepository,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Skip filter for register/sign-in/request otp/refresh token
        if (request.getServletPath().contains("/api/v1/auth")
//                || request.getServletPath().contains("/api/v1/payment")
//                || request.getServletPath().contains("/api/v1/")
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            //lay jwt tu request
            String jwt = authHeader.substring(7);
            //Lay uesrName tu chuoi jwt (da build trong JwtTokenProvider)
            String userName = jwtTokenProvider.extractUserName(jwt);
            //Kiem tra user da duoc authenticate chua
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //Lay thong tin nguoi dung tu userId // default is load email
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
                var user = userRepository.findByUserName(userDetails.getUsername())
                        .orElseGet(() -> userRepository.findByEmail(userDetails.getUsername())
                                .orElseThrow());
                //Check valid token and active userStatus and active account by confirm email

                if (jwtTokenProvider.isTokenValid(jwt, userDetails)
                        && user.getUserStatus().getCode().equals("True") // active token
                        && user.isActive() // active account by email
                ) {
                    //Neu nguoi dung hop le set thong tin cho security context
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    // Set authentication for user
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException ex) {
            exceptionResolver.resolveException(request, response, null, ex);
        }
    }

}
