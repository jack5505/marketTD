package uz.dukon.controllers.newmodel;

import javafx.scene.layout.HBox;

import java.math.BigDecimal;

/**
 * Created by Jack on 19.02.2019.
 */
public class CartTable {
    private Long id;
    private String nameP;
    private BigDecimal countP;
    private Long sellPrice;
    private BigDecimal totalSumm;
    private String typeDimension;
    private Long productId;
    private Boolean change = false;
    private String sellType;
    private Long typeId;
    private int orderedId;
    private HBox boxes;



    public String getTypeDimension() {
        return typeDimension;
    }

    public void setTypeDimension(String typeDimension) {
        this.typeDimension = typeDimension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public BigDecimal getCountP() {
        return countP;
    }

    public void setCountP(BigDecimal countP) {
        this.countP = countP;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getTotalSumm() {
        return totalSumm;
    }

    public void setTotalSumm(BigDecimal totalSumm) {
        this.totalSumm = totalSumm;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean getChange() {
        return change;
    }

    public void setChange(Boolean change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return nameP+" "+typeDimension;
    }

    public int getOrderedId() {
        return orderedId;
    }

    public void setOrderedId(int orderedId) {
        this.orderedId = orderedId;
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

    public HBox getBoxes() {
        return boxes;
    }

    public void setBoxes(HBox boxes) {
        this.boxes = boxes;
    }

}
