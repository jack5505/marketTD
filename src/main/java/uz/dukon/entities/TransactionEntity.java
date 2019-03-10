package uz.dukon.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jack on 17.02.2019.
 */
@Entity(name = "transactionss")
public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "orderId")
    private Long orderId;

    @Column
    private BigDecimal productCount;

    @Column
    private Long productPrice;

    @Column
    private BigDecimal productTotalSoldPrice;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Column
    private boolean deleted;



    @ManyToOne
    @JoinColumn(name = "orderId",insertable = false,updatable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "productId",insertable = false,updatable = false)
    private ProductEntity productEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductTotalSoldPrice() {
        return productTotalSoldPrice;
    }

    public void setProductTotalSoldPrice(BigDecimal productTotalSoldPrice) {
        this.productTotalSoldPrice = productTotalSoldPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
