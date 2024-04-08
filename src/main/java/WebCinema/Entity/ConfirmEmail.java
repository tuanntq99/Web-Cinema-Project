package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ConfirmEmail")
public class ConfirmEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "RequiredTime", updatable = false)
    private String requiredTime;

    @Column(name = "ExpiredTime", columnDefinition = "DATETIME")
    private Date expiredTime;

    @Column(name = "ConfirmCode")
    private String confirmCode;

    @Column(name = "IsConfirm")
    private boolean isConfirm = false;

    @ManyToOne
    @JoinColumn(name = "UserId", foreignKey = @ForeignKey(name = "fk_ConfirmEmail_User"))
    @JsonBackReference("confirmEmail-user")
    private User users;
}
