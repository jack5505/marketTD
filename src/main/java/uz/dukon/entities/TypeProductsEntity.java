package uz.dukon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jack on 26.02.2019.
 */
@Entity(name = "typesProducts")
public class TypeProductsEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String typeNames;

    @Column
    private boolean deleted;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
