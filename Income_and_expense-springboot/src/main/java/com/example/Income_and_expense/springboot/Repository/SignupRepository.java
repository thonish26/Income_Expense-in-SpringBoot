package com.example.Income_and_expense.springboot.Repository;

import com.example.Income_and_expense.springboot.Model.Signup;
import com.example.Income_and_expense.springboot.Service.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignupRepository extends JpaRepository<Signup,Long> {

    Signup findByusername(String username);

    User findByUsername(String username);
}
