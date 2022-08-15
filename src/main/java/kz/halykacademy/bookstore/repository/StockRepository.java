package kz.halykacademy.bookstore.repository;
import kz.halykacademy.bookstore.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.UniqueConstraint;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {



    @Query(value = "select b.bookCount from Stock b where b.book.id = :id ")
    int getBookCount(Long id);

    @Transactional
    @Modifying
    @Query(value =  "update Stock  set bookCount = bookCount - :count where book.id = :id ")
    void updateCount(@Param("count") Long count,@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value =  "update Stock  set bookCount = bookCount + :count where book.id = :id ")
    void updateCountAfterDelete(@Param("count") Long count,@Param("id") Long id);
}
