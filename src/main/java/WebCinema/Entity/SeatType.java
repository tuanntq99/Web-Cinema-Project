package WebCinema.Entity;

import WebCinema.Entity.Enum.ESeatType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "SeatType")
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "NameType")
    @Enumerated(EnumType.STRING)
    private ESeatType nameType;

    @OneToMany(mappedBy = "seatType")
    @JsonManagedReference("seat-seatType")
    private List<Seat> seats;
}
