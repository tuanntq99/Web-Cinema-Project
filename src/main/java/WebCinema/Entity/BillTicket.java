package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BillTicket")
public class BillTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "BillId", foreignKey = @ForeignKey(name = "fk_BillTicket_Bill"))
    @JsonBackReference("billTicket-bill")
    private Bill bills;

    @ManyToOne
    @JoinColumn(name = "TicketId", foreignKey = @ForeignKey(name = "fk_BillTicket_Ticket"))
    @JsonBackReference("billTicket-ticket")
    private Ticket tickets;

    public BillTicket(int quantity, Bill bills, Ticket tickets) {
        this.quantity = quantity;
        this.bills = bills;
        this.tickets = tickets;
    }

}
