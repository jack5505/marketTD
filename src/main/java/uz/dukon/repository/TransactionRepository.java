package uz.dukon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.dukon.entities.TransactionEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Jack on 17.02.2019.
 */
public interface TransactionRepository  extends JpaRepository<TransactionEntity,Long>
{
    @Query(value = "select productid, name,dimensiontype,sum(productcount) as totalcount,\n" +
            "            sum(producttotalsoldprice) as totalSum from transactionss left join productentity \n" +
            "            on productentity.id = transactionss.productid\n" +
            "             where transactionss.create_date >= (:startDate) and transactionss.create_date <= (:endDate) and transactionss.deleted = false group by productid,name,dimensiontype",nativeQuery = true)
    List<Object[]> reportForTable (@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);



    @Modifying
    @Transactional
    @Query(value = "update transactionss set deleted = true where deleted = false",nativeQuery = true)
    void updateDelete();
}
