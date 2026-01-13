package com.example.Income_and_expense.springboot.Service;

import com.example.Income_and_expense.springboot.Model.LoginTransaction;
import com.example.Income_and_expense.springboot.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Transaction {
    @Autowired
    private LoginRepository loginRepository;

    public LoginTransaction addtransaction(LoginTransaction l){
        return loginRepository.save(l);
    }
    public List<LoginTransaction> gettransaction(String username){
        return loginRepository.findByusername(username);
    }
}
