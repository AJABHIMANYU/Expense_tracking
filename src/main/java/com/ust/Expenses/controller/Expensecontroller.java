package com.ust.Expenses.controller;


import com.ust.Expenses.dto.dtoexpense;
import com.ust.Expenses.model.Expense;
import com.ust.Expenses.service.Expenseservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
public class Expensecontroller {

    @Autowired
    private Expenseservice es;

    @PostMapping("/addexpense")
    public ResponseEntity<Expense> addExpense(@RequestBody  @Valid dtoexpense dto) {
        return new ResponseEntity<>(es.addExpense(dto), HttpStatus.CREATED);
    }
    @GetMapping("/getexpense/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable UUID id)  {
        return  ResponseEntity.ok(es.getExpensebyid(id));
    }

    @GetMapping("/getallexpense")
    public ResponseEntity<List<Expense>> getExpense()  {
        return  ResponseEntity.ok(es.getallExpenses());
    }

    @GetMapping("/getexpenditure/{date}")
    public ResponseEntity<Double> getexbydate(@PathVariable  LocalDate date)  {
        Double amt=es.getexpenditure(date);
        return  ResponseEntity.ok(amt);
    }


    @GetMapping("/gettransactionmethod/{date}")
    public ResponseEntity<List<String>> gettranscationmethod(@PathVariable  LocalDate date)  {
    return ResponseEntity.ok(es.gettransactionmethod(date));
    }

    @GetMapping("/getmonthly/{month}")
    public ResponseEntity<List<Expense>> getmonthly(@PathVariable Month month)  {
        List<Expense> monthlyExpenses = es.getExpensesByMonth(month);
        return ResponseEntity.ok(monthlyExpenses);
    }

    @GetMapping("/getBalance/{date}")
    public ResponseEntity<Double> getBalance(@PathVariable LocalDate date)  {
        Double balance = es.getdaybalance(date);
        return ResponseEntity.ok(balance);
    }


}
