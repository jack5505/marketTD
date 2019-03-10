package uz.dukon.controllers.application.product.content;

import javafx.scene.control.Button;

import java.math.BigDecimal;

/**
 * Created by Jack on 17.02.2019.
 */
public class PurchasesTableViewData
{
    private String name;
    private int quantity;
    private String sellPrice;
    private String sellPriceCur;
    private String purchasePrice;

    private String purchasePriceCur;
    private String totalSumm;

    private BigDecimal quantityAmount;
    private BigDecimal sellPriceDec;
    private BigDecimal purchasePriceDec;
    private BigDecimal totalSummDec;

    private Button plusButton;
    private Button minusButton;
    private Button delete;

    private Long priceId;
    private Long productId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getQuantityAmount() {
        return quantityAmount;
    }

    public void setQuantityAmount(BigDecimal quantityAmount) {
        this.quantityAmount = quantityAmount;
    }

    public BigDecimal getSellPriceDec() {
        return sellPriceDec;
    }

    public void setSellPriceDec(BigDecimal sellPriceDec) {
        this.sellPriceDec = sellPriceDec;
    }

    public String getSellPriceCur() {
        return sellPriceCur;
    }

    public void setSellPriceCur(String sellPriceCur) {
        this.sellPriceCur = sellPriceCur;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchasePriceDec() {
        return purchasePriceDec;
    }

    public void setPurchasePriceDec(BigDecimal purchasePriceDec) {
        this.purchasePriceDec = purchasePriceDec;
    }

    public String getPurchasePriceCur() {
        return purchasePriceCur;
    }

    public void setPurchasePriceCur(String purchasePriceCur) {
        this.purchasePriceCur = purchasePriceCur;
    }

    public String getTotalSumm() {
        return totalSumm;
    }

    public void setTotalSumm(String totalSumm) {
        this.totalSumm = totalSumm;
    }

    public BigDecimal getTotalSummDec() {
        return totalSummDec;
    }

    public void setTotalSummDec(BigDecimal totalSummDec) {
        this.totalSummDec = totalSummDec;
    }

    public Button getPlusButton() {
        return plusButton;
    }

    public void setPlusButton(Button plusButton) {
        this.plusButton = plusButton;
    }

    public Button getMinusButton() {
        return minusButton;
    }

    public void setMinusButton(Button minusButton) {
        this.minusButton = minusButton;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public void calcTotalSumm() {
        totalSummDec = purchasePriceDec.multiply(new BigDecimal(quantity));
        totalSumm = totalSummDec.toString();
    }
}
