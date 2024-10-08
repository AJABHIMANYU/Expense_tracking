package com.ust.Expenses.service;

import com.ust.Expenses.dto.dtoexpense;
import com.ust.Expenses.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ust.Expenses.repository.repos;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;


@Service
public class Expenseservice {

    @Autowired
    private repos repo;

    public Expense addExpense(dtoexpense ex) {
        Expense expense=new Expense();
        expense.setName(ex.getName());
        expense.setTranscationmethod(ex.getTranscationmethod());
        expense.setAmount(ex.getAmount());
        expense.setDescription(ex.getDescription());
        expense.setType(ex.getType());
        expense.setCreateDate(ex.getCreateDate());
        return repo.save(expense);

    }
    public List<Expense> getallExpenses() {
        return repo.findAll();
    }

    public Expense getExpensebyid(UUID id)  {
        Optional<Expense> op=repo.findById(id);
        Expense ex=op.get();
        return ex;

    }


    public double getexpenditure(LocalDate date) {
        List<Expense> expenseList = repo.findAll();
        double tot_exp = 0;
        for (Expense e : expenseList) {
            if (e.getCreateDate().equals(date) && e.getType().equals("Debit"))
                tot_exp = tot_exp + e.getAmount();

        }
        return tot_exp;

    }





    public List<String> gettransactionmethod(LocalDate date) {
        List<Expense> expenseList = repo.findAll();
        List<String> paymentMethods = new ArrayList<>();
        for (Expense e : expenseList) {

            if (e.getCreateDate().equals(date)) {
                paymentMethods.add(e.getTranscationmethod());
            }
        }
        return paymentMethods;
    }

    public List<Expense> getExpensesByMonth(Month month) {
        List<Expense> expenseList = repo.findAll();
        List<Expense> monthlyExpenses = new ArrayList<>();


        for (Expense e : expenseList) {

            if (e.getCreateDate().getMonth() == month ) {
                monthlyExpenses.add(e);
            }
            // Sort the list by date in ascending order (Java 8+)
            monthlyExpenses.sort(Comparator.comparing(Expense::getCreateDate));





        }
        return monthlyExpenses;
    }

    public double getdaybalance(LocalDate date) {
        double balance=0;
        List<Expense> expenseList = repo.findAll();
        List<Expense> monthlyExpenses = new ArrayList<>();
        for (Expense e : expenseList) {

        if(!e.getCreateDate().isAfter(date)  )
            {
                if(e.getType().equals("Credit")) {
                    balance=balance+e.getAmount() ;
            }
            if( e.getType().equals("Debit")) {
                balance=balance-e.getAmount() ;
            }

        }
        }
        return balance;
    }
}




