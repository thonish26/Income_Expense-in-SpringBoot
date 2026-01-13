package com.example.Income_and_expense.springboot.Controller;

import com.example.Income_and_expense.springboot.Model.LoginTransaction;
import com.example.Income_and_expense.springboot.Model.Signup;
import com.example.Income_and_expense.springboot.Repository.LoginRepository;
import com.example.Income_and_expense.springboot.Service.Transaction;
import com.example.Income_and_expense.springboot.Service.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class IncomeController {

    @Autowired
    private Users usr;

    @Autowired
    private Transaction trns;

    // Signup page
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new Signup());
        return "Signup";

    }

    // Signup submit
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password) {
        if(usr.getUser(username) == null){
            Signup user = new Signup();
            user.setUsername(username);
            user.setPassword(password);
            usr.signup(user);
            return "Login";
        }
        return "redirect:/signup";

    }

    // Login page
    @GetMapping("/login")
    public String showLoginForm() {
//        model.addAttribute("loginUser", new Signup());
        return "Login";
    }

    // Login submit
    @PostMapping("/login")
    public String login(@RequestParam("user") String ur,
                        @RequestParam("pwd") String pwd,
                        Model model,
                        HttpSession ses) {
        System.out.println("Connecting---");
        Signup u = usr.login(ur, pwd);

        if (u != null) {
            model.addAttribute("transactions",
                    trns.gettransaction(ur));
            model.addAttribute("name",u.getUsername());
            ses.setAttribute("loggeduser", u);
            return "Dashboard";
        }
        model.addAttribute("loginUser", new Signup());
        return "Login";
    }
//    dashboard

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession ses) {

        Signup user = (Signup) ses.getAttribute("loggeduser");

        if (user == null) {
            return "redirect:/login";
        }

        List<LoginTransaction> transactions =
                trns.gettransaction(user.getUsername());

        model.addAttribute("transactions", transactions);
        model.addAttribute("name", user.getUsername());

        return "Dashboard";
    }

// income
    @GetMapping("/income")
    public String showIncomePage() {
        return "income";
    }


    @PostMapping("/income")
    public String saveTransaction(@RequestParam("date") String date,
                                  @RequestParam("description") String description,
                                  @RequestParam("income") double income,
                                  Model model,
                                  HttpSession ses) {
        Signup user = (Signup)ses.getAttribute("loggeduser");
        LoginTransaction lgt = new LoginTransaction();
        lgt.setUsername(user.getUsername());
        lgt.setDate(date);
        lgt.setDescription(description);
        lgt.setIncome(income);
        lgt.setExpense(0);
        trns.addtransaction(lgt);

        return "redirect:/dashboard";
    }


    @GetMapping("/expense")
    public String showExpensePage() {
        return "expense";
    }

    @PostMapping("/expense")
    public String saveExpenseTransaction(@RequestParam("date") String date,
                                  @RequestParam("description") String description,
                                  @RequestParam("expense") double expense,
                                  Model model,
                                  HttpSession ses) {
        Signup user = (Signup)ses.getAttribute("loggeduser");
        LoginTransaction lgt = new LoginTransaction();
        lgt.setUsername(user.getUsername());
        lgt.setDate(date);
        lgt.setDescription(description);
        lgt.setIncome(0);
        lgt.setExpense(expense);
        trns.addtransaction(lgt);

        return "redirect:/dashboard";
    }


}



