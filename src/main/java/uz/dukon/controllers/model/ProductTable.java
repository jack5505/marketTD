package uz.dukon.controllers.model;

import javafx.scene.control.Button;

import java.math.BigDecimal;

/**
 * Created by Jack on 17.02.2019.
 */
public class ProductTable {
    private Long id;
    private String productName;
    private Button plus;
    private BigDecimal count;
    private Button minus;
    private Long getPrice;
    private Long sellPrice;
    private BigDecimal totalSellPrice;
    private Button delete;
    private Long productId;

    private BigDecimal totalSummDec;

    public BigDecimal getTotalSummDec() {
        return totalSummDec;
    }

    public void setTotalSummDec(BigDecimal totalSummDec) {
        this.totalSummDec = totalSummDec;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Button getPlus() {
        return plus;
    }

    public void setPlus(Button plus) {
        this.plus = plus;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Button getMinus() {
        return minus;
    }

    public void setMinus(Button minus) {
        this.minus = minus;
    }

    public Long getGetPrice() {
        return getPrice;
    }

    public void setGetPrice(Long getPrice) {
        this.getPrice = getPrice;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(BigDecimal totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
