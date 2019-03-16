package uz.dukon.controllers.application.product.print;

import java.math.BigDecimal;

/**
 * Created by Jack on 21.02.2019.
 */
public class PrintModel
{

    private Long num;
    private BigDecimal paidSumma;
    private String dimension;
    private Long price;
    private BigDecimal change;
    private BigDecimal productSize;
    private BigDecimal totalMetr;
    private BigDecimal total;
    //need ones
    private String name;
    private BigDecimal jami;
    private Long id;
    private BigDecimal quantity;
    private BigDecimal summa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getJami() {
        return jami;
    }

    public void setJami(BigDecimal jami) {
        this.jami = jami;
    }

    public BigDecimal getPaidSumma() {
        return paidSumma;
    }

    public void setPaidSumma(BigDecimal paidSumma) {
        this.paidSumma = paidSumma;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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

    public BigDecimal getSumma() {
        return summa;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
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
