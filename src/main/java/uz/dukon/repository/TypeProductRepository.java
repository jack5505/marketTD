package uz.dukon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dukon.entities.TypeProductsEntity;

/**
 * Created by Jack on 26.02.2019.
 */
public interface TypeProductRepository extends JpaRepository<TypeProductsEntity,Long> {
}
