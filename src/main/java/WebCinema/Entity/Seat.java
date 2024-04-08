package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Number")
    private int Number;

    @Column(name = "Line")
    private String line;

    @Column(name = "IsActive")
    private boolean isActive;

    @OneToMany(mappedBy = "seats")
    @JsonManagedReference("ticket-seat")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "SeatStatusId", foreignKey = @ForeignKey(name = "fk_Seat_SeatStatus"))
    @JsonBackReference("seat-seatStatus")
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "RoomId", foreignKey = @ForeignKey(name = "fk_Seat_Room"))
    @JsonBackReference("seat-room")
    private Room rooms;

    @ManyToOne
    @JoinColumn(name = "SeatTypeId", foreignKey = @ForeignKey(name = "fk_Seat_SeatTypeId"))
    @JsonBackReference("seat-seatType")
    private SeatType seatType;
}
