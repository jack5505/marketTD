package uz.dukon.controllers.model;

/**
 * Created by Jack on 17.02.2019.
 */
public class ProductCombo {
    private Long productId;
    private Long sellProduct;
    private Long getProduct;
    private String name;
    private String typeDimension;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellProduct() {
        return sellProduct;
    }

    public void setSellProduct(Long sellProduct) {
        this.sellProduct = sellProduct;
    }

    public Long getGetProduct() {
        return getProduct;
    }

    public void setGetProduct(Long getProduct) {
        this.getProduct = getProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeDimension() {
        return typeDimension;
    }

    public void setTypeDimension(String typeDimension) {
        this.typeDimension = typeDimension;
    }

    @Override
    public String toString() {
        return  name;
    }
}
