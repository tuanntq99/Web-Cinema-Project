package WebCinema.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "GeneralSetting")
public class GeneralSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "BreakTime", columnDefinition = "DATETIME")
    private Date breakTime;

    @Column(name = "BusinessHours")
    private int businessHours;

    @Column(name = "CloseTime", columnDefinition = "DATETIME")
    private Date closeTime;

    @Column(name = "FixedTicketPrice")
    private double fixedTicketPrice;

    @Column(name = "PercentDay")
    private int perCentDay;

    @Column(name = "PercentWeek")
    private int percentWeek;

    @Column(name = "TimeBeginToChange", columnDefinition = "DATETIME")
    private Date timeBeginToChange;
}
