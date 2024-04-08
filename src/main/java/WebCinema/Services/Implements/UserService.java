package WebCinema.Services.Implements;

import WebCinema.Config.ApplicationConfig;
import WebCinema.Entity.User;
import WebCinema.Exceptions.AppException;
import WebCinema.Exceptions.ErrorCode;
import WebCinema.Payloads.Conveters.UserConverter;
import WebCinema.Payloads.DataRequests.UserRequest.ChangePasswordRequest;
import WebCinema.Payloads.DataRequests.UserRequest.ChangeRoleRequest;
import WebCinema.Payloads.DataRequests.UserRequest.UpdateUserRequest;
import WebCinema.Repository.RoleRepository;
import WebCinema.Repository.UserRepository;
import WebCinema.Services.Interfaces.IUserService;
import WebCinema.Entity.Enum.ERole;
import WebCinema.Entity.Role;
import WebCinema.Payloads.DataResponses.DataUser.UserResponse;
import WebCinema.Payloads.Responses.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final ApplicationConfig config;

    @Override
    public MessageResponse changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            return new MessageResponse("Wrong password");
        }
        if (request.getCurrentPassword().equals(request.getNewPassword())) {
            return new MessageResponse("New password and current password must not be the same");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            return new MessageResponse("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        user.setName("ok"); // test Principal()
        // save the new password
        userRepository.save(user);
        return new MessageResponse("Change password success !");
    }

    @Override
    public MessageResponse changeRole(ChangeRoleRequest request) {
        var current = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        switch (request.getRole()) {
            case "admin" -> current.setRole(getRoleByName("ADMIN"));
            case "moderator" -> current.setRole(getRoleByName("MODERATOR"));
            default -> current.setRole(getRoleByName("USER"));
        }
        userRepository.save(current);
        return new MessageResponse("Change role of " + request.getEmail() + " success !");
    }

    private Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(ERole.valueOf("ROLE_" + roleName.toUpperCase()))
                .orElseThrow(() -> new RuntimeException("Error: Role '" + roleName + "' not found"));
    }

    @Override
    public MessageResponse updateUser(UpdateUserRequest request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTED);
        }
        var current = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        if (request.getPoint() == 0) request.setPoint(current.getPoint());
        var exists = userRepository.findAll().stream()
                .anyMatch(x ->
                        x.getUsername().equals(request.getUserName())
                                || x.getEmail().equals(request.getEmail())
                );
        if (exists) {
            return new MessageResponse("Fail");
        }
        BeanUtils.copyProperties(request, current, config.getNullPropertyNames(request));
        userRepository.save(current);
        return new MessageResponse("Update data success !");
    }

    @Override
    public MessageResponse delete(String email) {
        var current = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        current.setActive(false);
        userRepository.save(current);
        return new MessageResponse("Delete User success !");
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::EntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getById(long id) {
        return userRepository.findById(id)
                .stream()
                .map(userConverter::EntityToDTO)
                .collect(Collectors.toList());
    }

}
