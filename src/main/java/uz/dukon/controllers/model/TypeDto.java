package uz.dukon.controllers.model;

/**
 * Created by Jack on 27.02.2019.
 */
public class TypeDto {
    private String typeName;
    private Long typeId;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return  typeName;
    }
}
