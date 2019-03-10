package uz.dukon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.dukon.entities.OrderEntity;

/**
 * Created by Jack on 17.02.2019.
 */
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query("select max(id) from orderss")
    Long getLastCreated();
}
