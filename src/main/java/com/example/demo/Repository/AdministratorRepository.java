package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
	List<Administrator> findByEmailAndPassword(String email, String password);
}
