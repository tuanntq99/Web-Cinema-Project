package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "MovieDuration")
    private int movieDuration;

    @Column(name = "EndTime", columnDefinition = "DATE")
    private Date endTime;

    @Column(name = "PremiereDate", columnDefinition = "DATE")
    private Date premiereDate;

    @Column(name = "Description")
    private String description;

    @Column(name = "Director")
    private String director;

    @Column(name = "Image")
    private String image;

    @Column(name = "HeroImage")
    private String heroImage;

    @Column(name = "Language")
    private String language;

    @Column(name = "Name")
    private String name;

    @Column(name = "Trailer")
    private String trailer;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "movies")
    @JsonManagedReference("schedule-movie")
    private List<Schedule> schedules;

    @ManyToOne
    @JoinColumn(name = "MovieTypeId", foreignKey = @ForeignKey(name = "fk_Movie_MovieType"))
    @JsonBackReference("movie-movieType")
    private MovieType movieType;

    @ManyToOne
    @JoinColumn(name = "RateId", foreignKey = @ForeignKey(name = "fk_Movie_Rate"))
    @JsonBackReference("movie-rate")
    private Rate rate;
}
