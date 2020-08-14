package own.stu.highConcurrence.cacheconsistence_bak.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import own.stu.highConcurrence.cacheconsistence_bak.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query("update Product p set p.store = p.store - :num where p.id = :productId and p.store >= :num")
    int updateStore(@NonNull @Param("productId") Integer productId,
                    @NonNull @Param("num") Integer num);

}
