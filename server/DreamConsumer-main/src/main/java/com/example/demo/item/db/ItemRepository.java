package com.example.item.db;

import com.example.prac.db.PracticeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Page<Item> findAll(Pageable pageable);

    @Query("select c from Item c where c.deleted = true and (:nowTime - c.deletedTime) >= 4L * 60 * 1000")
    List<Item> findItemByDeletedIsTrue(@Param("nowTime") Long nowTime);


}
