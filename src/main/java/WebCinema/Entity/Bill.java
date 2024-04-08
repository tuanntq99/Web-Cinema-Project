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
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long Id;

    @Column(name = "TotalMoney")
    private double totalMoney;

    @Column(name = "TradingCode", unique = true)
    private String tradingCode;

    @Column(name = "CreateTime", columnDefinition = "DATETIME")
    private Date createTime;

    @Column(name = "Name")
    private String name;

    @Column(name = "UpdateTime", columnDefinition = "DATETIME")
    private Date updateTime;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "bills")
    @JsonManagedReference("billFood-bill")
    private List<BillFood> billFoods;

    @OneToMany(mappedBy = "bills")
    @JsonManagedReference("billTicket-bill")
    private List<BillTicket> billTickets;

    @ManyToOne
    @JoinColumn(name = "CustomerId", foreignKey = @ForeignKey(name = "fk_Bill_User"))
    @JsonBackReference("bill-user")
    private User users;

    @ManyToOne
    @JoinColumn(name = "PromotionId", foreignKey = @ForeignKey(name = "fk_Bill_Promotion"))
    @JsonBackReference("bill-promotion")
    private Promotion promotions;

    @ManyToOne
    @JoinColumn(name = "BillStatusId", foreignKey = @ForeignKey(name = "fk_Bill_BillStatus"))
    @JsonBackReference("bill-billStatus")
    private BillStatus billStatus;
}
