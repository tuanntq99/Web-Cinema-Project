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
@Table(name = "Promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Percent")
    private int percent;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Type")
    private String type;

    @Column(name = "StartTime", columnDefinition = "DATE")
    private Date startTime;

    @Column(name = "EndTime", columnDefinition = "DATE")
    private Date endTime;

    @Column(name = "Description")
    private String description;

    @Column(name = "Name")
    private String name;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "promotions")
    @JsonManagedReference("bill-promotion")
    private List<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "RankCustomerId", foreignKey = @ForeignKey(name = "fk_Promotion_RankCustomer"))
    @JsonBackReference("promotion-rankCustomer")
    private RankCustomer rankCustomer;
}
