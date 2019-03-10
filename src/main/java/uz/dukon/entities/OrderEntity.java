package uz.dukon.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jack on 17.02.2019.
 */
@Entity(name = "orderss")
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "paymentPlastic")
    private BigDecimal plastic;

    @Column(name = "paymentCash")
    private BigDecimal cash;

    @Column(name = "totalSumm")
    private BigDecimal totalSumm;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPlastic() {
        return plastic;
    }

    public void setPlastic(BigDecimal plastic) {
        this.plastic = plastic;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getTotalSumm() {
        return totalSumm;
    }

    public void setTotalSumm(BigDecimal totalSumm) {
        this.totalSumm = totalSumm;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
