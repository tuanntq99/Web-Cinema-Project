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
@Table(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Price")
    private double price;

    @Column(name = "StartAt", columnDefinition = "DATE")
    private Date startAt;

    @Column(name = "EndAt", columnDefinition = "DATE")
    private Date endAt;

    @Column(name = "Code")
    private String code;

    @Column(name = "Name")
    private String name;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "schedules")
    @JsonManagedReference("ticket-schedule")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "MovieId", foreignKey = @ForeignKey(name = "fk_Schedule_Movie"))
    @JsonBackReference("schedule-movie")
    private Movie movies;

    @ManyToOne
    @JoinColumn(name = "RoomId", foreignKey = @ForeignKey(name = "fk_Schedule_Room"))
    @JsonBackReference("schedule-room")
    private Room rooms;
}
