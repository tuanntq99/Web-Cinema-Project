package WebCinema.Entity;

//import com.example.webcinema.Entity.Enum.ERoom;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Capacity")
    private int capacity;

    @Column(name = "Type")
//    @Enumerated(EnumType.STRING)
    private int Type;

    @Column(name = "Description")
    private String description;

    @Column(name = "Code")
    private String code;

    @Column(name = "Name")
    private String name;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "CinemaId", foreignKey = @ForeignKey(name = "fk_Room_Cinema"))
    @JsonBackReference("room-cinema")
    private Cinema cinema;

    @OneToMany(mappedBy = "rooms")
    @JsonManagedReference("schedule-room")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "rooms")
    @JsonManagedReference("seat-room")
    private List<Seat> seats;
}
