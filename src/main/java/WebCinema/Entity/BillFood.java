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
@Table(name = "BillFood")
public class BillFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "BillId",foreignKey = @ForeignKey(name = "fk_BillFood_Bill"))
    @JsonBackReference("billFood-bill")
    private Bill bills;

    @ManyToOne
    @JoinColumn(name = "FoodId",foreignKey = @ForeignKey(name = "fk_BillFood_Food"))
    @JsonBackReference("billFood-food")
    private Food food;

    public BillFood(int quantity, Bill bills, Food food) {
        this.quantity = quantity;
        this.bills = bills;
        this.food = food;
    }
}
