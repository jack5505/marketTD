package uz.dukon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.dukon.entities.ProductEntity;

/**
 * Created by Jack on 24.08.2018.
 */
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query(value = "select count(productentity) from productentity where deleted=false",nativeQuery = true)
    int cnt();
}
