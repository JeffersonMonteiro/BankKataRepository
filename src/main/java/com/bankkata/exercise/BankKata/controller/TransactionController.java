package com.bankkata.exercise.BankKata.controller;

import com.bankkata.exercise.BankKata.model.TransactionList;
import com.bankkata.exercise.BankKata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.bankkata.exercise.BankKata.constants.Constants.ALL_TRANSACTIONS_PATH;
import static com.bankkata.exercise.BankKata.constants.Constants.BALANCE_RESULT_PATH;
import static com.bankkata.exercise.BankKata.constants.Constants.DATE_PARAM;
import static com.bankkata.exercise.BankKata.constants.Constants.DATE_PATTERN;
import static com.bankkata.exercise.BankKata.constants.Constants.FUTURE_TRANSACTIONS_PATH;
import static com.bankkata.exercise.BankKata.constants.Constants.PAST_TRANSACTIONS_PATH;
import static com.bankkata.exercise.BankKata.constants.Constants.SAVE_BULK_TRANSACTIONS_PATH;
import static com.bankkata.exercise.BankKata.constants.Constants.TRANSACTION_PATH;

@RestController
@RequestMapping(TRANSACTION_PATH)
public class TransactionController {

    @Autowired
    private TransactionService service;

    /**
     * Controller to get all transactions
     * @return TransactionList
     */
    @GetMapping(path = ALL_TRANSACTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionList getAllTransactions() {
        return service.getAllTransactions();
    }

    /**
     * Controller to save transactions as bulk
     * @param list
     * @return String
     */
    @PostMapping(path = SAVE_BULK_TRANSACTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveBulkTransactions(@RequestBody TransactionList list) {
        return service.saveBulkTransactions(list);
    }

    /**
     * Controller to return the balance based on past transactions
     * @param date
     * @return
     */
    @GetMapping(path = BALANCE_RESULT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBalanceResult(@RequestParam(DATE_PARAM) @DateTimeFormat(pattern= DATE_PATTERN) Date date) {
       return service.getBalanceResult(date);
    }

    /**
     * Controller to return past transactions based on the current date
     * @param date
     * @return
     */
    @GetMapping(path = PAST_TRANSACTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionList findPastTransactions(
            @RequestParam(DATE_PARAM) @DateTimeFormat(pattern=DATE_PATTERN) Date date){
    return service.findPastTransactions(date);
    }

    /**
     * Service to return future transactions based on the current date
     * @param date
     * @return
     */
    @GetMapping(path = FUTURE_TRANSACTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionList findFutureTransactions(
            @RequestParam(DATE_PARAM) @DateTimeFormat(pattern=DATE_PATTERN) Date date){
        return service.findFutureTransactions(date);
    }
}
