package WebCinema.Entity;

import WebCinema.Entity.Enum.ERate;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Description")
    private String description;

    @Column(name = "Code")
    @Enumerated(EnumType.STRING)
    private ERate code;

    @OneToMany(mappedBy = "rate")
    @JsonManagedReference("movie-rate")
    private List<Movie> movies;

}
