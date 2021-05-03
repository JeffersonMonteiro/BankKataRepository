package com.bankkata.exercise.BankKata.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class TransactionList {

    private List<Transaction> transactions;

    /**
     * get method to return all transactions
     * @return
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * set method to all transactions
     * @param transactions
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
