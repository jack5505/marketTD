package uz.dukon.controllers.model;

import java.math.BigDecimal;

/**
 * Created by Jack on 23.02.2019.
 */
public class ReportData {
    private Long id;
    private String productName;
    private BigDecimal count;
    private BigDecimal totalPrice;
    private String dimensionType;
    private String countToShow;
    private BigDecimal totalMetirP;
    private String totalMetirToShow;

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

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public String getCountToShow() {
        return countToShow;
    }

    public void setCountToShow(String countToShow) {
        this.countToShow = countToShow;
    }

    public BigDecimal getTotalMetirP() {
        return totalMetirP;
    }

    public void setTotalMetirP(BigDecimal totalMetirP) {
        this.totalMetirP = totalMetirP;
    }

    public String getTotalMetirToShow() {
        return totalMetirToShow;
    }

    public void setTotalMetirToShow(String totalMetirToShow) {
        this.totalMetirToShow = totalMetirToShow;
    }
}
