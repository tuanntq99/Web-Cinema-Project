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
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Code")
    private String code;

    @Column(name = "PriceTicket")
    private double priceTicket;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "tickets")
    @JsonManagedReference("billTicket-ticket")
    private List<BillTicket> billTickets;

    @ManyToOne
    @JoinColumn(name = "ScheduleId", foreignKey = @ForeignKey(name = "fk_Ticket_Schedule"))
    @JsonBackReference("ticket-schedule")
    private Schedule schedules;

    @ManyToOne
    @JoinColumn(name = "SeatId", foreignKey = @ForeignKey(name = "fk_Ticket_Seat"))
    @JsonBackReference("ticket-seat")
    private Seat seats;
}
