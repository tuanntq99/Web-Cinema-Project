package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Address")
    private String address;

    @Column(name = "Description")
    private String description;

    @Column(name = "Code")
    private String code;

    @Column(name = "NameOfCinema")
    private String nameOfCinema;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "cinema")
    @JsonManagedReference("room-cinema")
    private List<Room> rooms;
}
