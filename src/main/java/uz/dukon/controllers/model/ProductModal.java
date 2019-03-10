package uz.dukon.controllers.model;

import java.math.BigDecimal;

/**
 * Created by Jack on 17.02.2019.
 */
public class ProductModal {
    private Long id;
    private String productName;
    private Long sold;
    private Long purchase;
    private String dimensionType;
    private BigDecimal defaultSize;
    private String sellType;
    private Long typeId;

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

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public Long getPurchase() {
        return purchase;
    }

    public void setPurchase(Long purchase) {
        this.purchase = purchase;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public BigDecimal getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(BigDecimal defaultSize) {
        this.defaultSize = defaultSize;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "ProductModal{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", sold=" + sold +
                ", purchase=" + purchase +
                ", dimensionType='" + dimensionType + '\'' +
                ", defaultSize=" + defaultSize +
                ", sellType='" + sellType + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
