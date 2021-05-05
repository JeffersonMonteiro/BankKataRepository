package com.bankkata.exercise.BankKata.controller;

import com.bankkata.exercise.BankKata.model.TransactionList;
import com.bankkata.exercise.BankKata.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService service;

    /**
     *
     * @param service
     */
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    /**
     * Controller to get all transactions
     *
     * @return TransactionList
     */
    @GetMapping(path = "/all_transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionList getAllTransactions() {
        return service.getAllTransactions();
    }

    /**
     * Controller to save transactions as bulk
     *
     * @param list
     * @return String
     */
    @PostMapping(path = "/save_bulk_transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveBulkTransactions(@RequestBody TransactionList list) throws ParseException {
        return service.saveBulkTransactions(list);
    }

    /**
     * Controller to return the balance based on past transactions
     *
     * @param date
     * @return
     */
    @GetMapping(path = "/balance_result", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBalanceResult(@RequestParam("date") @DateTimeFormat(pattern = "yyyy/MM/dd") Date date) {
        return service.getBalanceResult(date);
    }

    /**
     * Controller to return past transactions based on the current date
     *
     * @param date
     * @return
     */
    @GetMapping(path = "/past_transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionList findPastTransactions(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy/MM/dd") Date date) {
        return service.findPastTransactions(date);
    }

    /**
     * Service to return future transactions based on the current date
     *
     * @param date
     * @return
     */
    @GetMapping(path = "/future_transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionList findFutureTransactions(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy/MM/dd") Date date) {
        return service.findFutureTransactions(date);
    }
}
