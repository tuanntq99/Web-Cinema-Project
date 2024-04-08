package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "RankCustomer")
public class RankCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Point")
    private int point;

    @Column(name = "Description")
    private String description;

    @Column(name = "Name")
    private String name;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "rankCustomer")
    @JsonManagedReference("user-rankCustomer")
    private List<User> users;

    @OneToMany(mappedBy = "rankCustomer")
    @JsonManagedReference("promotion-rankCustomer")
    private List<Promotion> promotions;
}
