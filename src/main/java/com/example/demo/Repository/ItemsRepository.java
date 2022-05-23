package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {
	List<Items> findALLByOrderByIdAsc();
	List<Items> findAllByNameContaining(@Param("namePrefix") String str);

}
