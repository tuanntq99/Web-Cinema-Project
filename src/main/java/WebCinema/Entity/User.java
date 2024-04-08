package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Point")
    private int point;

    @Column(name = "UserName", unique = true, nullable = false)
    private String userName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Name")
    private String name;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @JsonIgnore
    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "IsActive")
    private boolean isActive = false;

    @OneToMany(mappedBy = "users")
    @JsonManagedReference("confirmEmail-user")
    private List<ConfirmEmail> confirmEmails;

    @OneToMany(mappedBy = "users")
    @JsonManagedReference("refreshToken-user")
    private List<RefreshToken> refreshTokens;

    @OneToMany(mappedBy = "users")
    @JsonManagedReference("bill-user")
    private List<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "RoleId", foreignKey = @ForeignKey(name = "fk_User_Role"))
    @JsonBackReference("user-role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "UserStatusId", foreignKey = @ForeignKey(name = "fk_User_UserStatus"))
    @JsonBackReference("user-userStatus")
    private UserStatus userStatus;

    @ManyToOne
    @JoinColumn(name = "RankCustomerId", foreignKey = @ForeignKey(name = "fk_User_RankCustomer"))
    @JsonBackReference("user-rankCustomer")
    private RankCustomer rankCustomer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(getRole().getRoleName().name())
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
