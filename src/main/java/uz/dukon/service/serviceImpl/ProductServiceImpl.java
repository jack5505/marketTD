package uz.dukon.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.dukon.controllers.model.ProductDtoList;
import uz.dukon.controllers.model.ProductModal;
import uz.dukon.controllers.model.TypeDto;
import uz.dukon.controllers.newmodel.CartTable;
import uz.dukon.entities.ProductEntity;
import uz.dukon.entities.TypeProductsEntity;
import uz.dukon.repository.ProductRepository;
import uz.dukon.repository.TypeProductRepository;
import uz.dukon.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jack on 17.02.2019.
 */
@Service
public class ProductServiceImpl  implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeProductRepository typeProductRepository;

    @Override
    public boolean create(ProductModal productModal)
    {
        int diff = productRepository.cnt();
        ProductEntity productEntity = new ProductEntity();
        if(productModal.getProductId() != null)
            productEntity.setId(productModal.getProductId());
        productEntity.setName(productModal.getProductName());
        productEntity.setTypeId(productModal.getTypeId());
        productEntity.setSellPrice(productModal.getSellPrice());
        productEntity.setDimensionType(productModal.getDimension());
        productEntity.setImagePath(productModal.getPathImage());
        productEntity.setDeleted(false);
        productRepository.save(productEntity);
        int after = productRepository.cnt();
        if(diff == after)
            return false;

        return true;


    }

    @Override
    public List<CartTable> getProducts()
    {
        List<CartTable> list = new ArrayList<>();
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<ProductEntity> productEntities = productRepository.findAll(sort);
        productEntities.forEach(productEntity ->
        {
            if(!productEntity.isDeleted())
            {
            CartTable productCombo = new CartTable();
            productCombo.setProductId(productEntity.getId());
            productCombo.setNameP(productEntity.getName());
            productCombo.setTypeDimension(productEntity.getDimensionType());
            productCombo.setSellPrice(productEntity.getSellPrice());
            productCombo.setTypeId(productEntity.getTypeId());
            productCombo.setImageUrl(productEntity.getImagePath());
            //berda barilgan tovarni defaultsizeni tekshiramiz null amaslikina olajaqda
            list.add(productCombo);
            }
        });
        return list;
    }

    @Override
    public int productCount()
    {
        int  cnt = productRepository.cnt();
        return cnt;
    }

    @Override
    public void deletProduct(Long productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(productEntity.isPresent()){
            ProductEntity productEntity1 = productEntity.get();
            productEntity1.setDeleted(true);
            productRepository.save(productEntity1);
        }
    }

    @Override
    public void createType(String type) {
        TypeProductsEntity typeProductsEntity = new TypeProductsEntity();
        typeProductsEntity.setTypeNames(type);
        typeProductsEntity.setDeleted(false);
        typeProductRepository.save(typeProductsEntity);
    }

    @Override
    public List<TypeDto> getAllType()
    {
        List<TypeProductsEntity> all = typeProductRepository.findAll();
        List<TypeDto> list = new ArrayList<>();
        all.forEach(typeProductsEntity ->
        {
           if(!typeProductsEntity.isDeleted())
           {
            TypeDto typeDto = new TypeDto();
            typeDto.setTypeId(typeProductsEntity.getId());
            typeDto.setTypeName(typeProductsEntity.getTypeNames());
            list.add(typeDto);
           }
        });
        return list;
    }

    @Override
    public void deleteType(Long typeId) {
       Optional<TypeProductsEntity> typeProductsEntity = typeProductRepository.findById(typeId);
        if(typeProductsEntity.isPresent()){
            TypeProductsEntity typeProductsEntity1 = typeProductsEntity.get();
            typeProductsEntity1.setDeleted(true);
            typeProductRepository.save(typeProductsEntity1);
        }
    }

    @Override
    public void getProductsForContent(List<ProductDtoList> productDtoListList) {
        List<ProductEntity> productEntities = productRepository.findAll();
        productEntities.forEach(productEntity ->
        {
            if(!productEntity.isDeleted()) {
                ProductDtoList productDtoList = new ProductDtoList();
                productDtoList.setId((long) (productDtoListList.size() + 1));
                productDtoList.setProductName(productEntity.getName());
                productDtoList.setDimension(productEntity.getDimensionType());
                productDtoList.setSellPrice(productEntity.getSellPrice());
                productDtoList.setPathImage(productEntity.getImagePath());
                productDtoList.setProductId(productEntity.getId());
                productDtoList.setTypeId(productEntity.getTypeId());
//            productDtoList.setSellPrice(new BigDecimal(productEntity.getSellPrice()));
                productDtoListList.add(productDtoList);
            }
        });
    }

}
