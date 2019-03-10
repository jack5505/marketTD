package uz.dukon.controllers.model;

import java.math.BigDecimal;

/**
 * Created by Jack on 17.02.2019.
 */
public class TransactionDto {
    private Long id;
    private Long productId;
    private BigDecimal productCount;
    private Long productPrice;
    private BigDecimal productTotalSellPrice;
    private String productName;
    private String dimension;
    private BigDecimal defaultSize;
    private BigDecimal totalMetirP;

    public BigDecimal getProductTotalSellPrice() {
        return productTotalSellPrice;
    }

    public void setProductTotalSellPrice(BigDecimal productTotalSellPrice) {
        this.productTotalSellPrice = productTotalSellPrice;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

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

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(BigDecimal defaultSize) {
        this.defaultSize = defaultSize;
    }

    public BigDecimal getTotalMetirP() {
        return totalMetirP;
    }

    public void setTotalMetirP(BigDecimal totalMetirP) {
        this.totalMetirP = totalMetirP;
    }
}
