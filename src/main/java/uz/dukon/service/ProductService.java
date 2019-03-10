package uz.dukon.service;

import uz.dukon.controllers.model.ProductModal;
import uz.dukon.controllers.model.TypeDto;
import uz.dukon.controllers.newmodel.CartTable;

import java.util.List;

/**
 * Created by Jack on 17.02.2019.
 */
public interface ProductService
{
    boolean create(ProductModal productModal);

    List<CartTable> getProducts();

    int productCount();

    void deletProduct(Long productId);

    void createType(String type);

    List<TypeDto> getAllType();

    void deleteType(Long typeId);
}
