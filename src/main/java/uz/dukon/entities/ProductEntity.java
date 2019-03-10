package uz.dukon.entities;

import javax.persistence.*;

/**
 * Created by Jack on 17.02.2019.
 */
@Entity
public class ProductEntity
{
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;

    @Column
    private String dimensionType;

    @Column
    private boolean deleted;

    @Column
    private Long sellPrice;

    @Column
    private String imagePath;




    @ManyToOne
    @JoinColumn(name = "typeId",insertable = false,updatable = false)
    private TypeProductsEntity typeProductsEntity;

    @Column(name = "typeId")
    private Long typeId;

    @Column
    private String sellType;

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

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



    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
