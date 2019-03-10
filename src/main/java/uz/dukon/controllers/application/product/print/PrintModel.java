package uz.dukon.controllers.application.product.print;

import java.math.BigDecimal;

/**
 * Created by Jack on 21.02.2019.
 */
public class PrintModel
{
    private Long id;
    private Long num;
    private String productName;
    private BigDecimal totalSumma;
    private BigDecimal paidSumma;
    private BigDecimal productCount;
    private String dimension;
    private Long price;
    private BigDecimal totalPrice;
    private BigDecimal change;
    private BigDecimal productSize;
    private BigDecimal totalMetr;
    private BigDecimal total;


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

    public BigDecimal getTotalSumma() {
        return totalSumma;
    }

    public void setTotalSumma(BigDecimal totalSumma) {
        this.totalSumma = totalSumma;
    }

    public BigDecimal getPaidSumma() {
        return paidSumma;
    }

    public void setPaidSumma(BigDecimal paidSumma) {
        this.paidSumma = paidSumma;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public BigDecimal getProductSize() {
        return productSize;
    }

    public void setProductSize(BigDecimal productSize) {
        this.productSize = productSize;
    }

    public BigDecimal getTotalMetr() {
        return totalMetr;
    }

    public void setTotalMetr(BigDecimal totalMetr) {
        this.totalMetr = totalMetr;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
