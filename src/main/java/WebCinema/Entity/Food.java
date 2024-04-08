package WebCinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Price")
    private double price;

    @Column(name = "Description")
    private String description;

    @Column(name = "Image")
    private String image;

    @Column(name = "NameOfFood")
    private String nameOfFood;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "food")
    @JsonManagedReference("billFood-food")
    private List<BillFood> billFoods;

}
