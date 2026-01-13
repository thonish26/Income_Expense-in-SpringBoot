package com.example.Income_and_expense.springboot.Repository;

import com.example.Income_and_expense.springboot.Model.LoginTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<LoginTransaction,Long> {
    List<LoginTransaction> findByusername(String username);
}
