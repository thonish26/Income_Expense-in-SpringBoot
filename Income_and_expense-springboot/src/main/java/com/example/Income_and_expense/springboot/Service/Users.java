package com.example.Income_and_expense.springboot.Service;

import com.example.Income_and_expense.springboot.Model.Signup;
import com.example.Income_and_expense.springboot.Repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Users {
    @Autowired
   private SignupRepository signupRepository;

    public void signup(Signup username) {

        signupRepository.save(username);
    }

    public Signup login(String username, String password) {
        Signup u = signupRepository.findByusername(username);

        if (u!= null && u.getPassword().equals(password)){
            return u;
        }
        return null;
    }
    public Signup getUser(String username){
        return signupRepository.findByusername(username);
    }
}

