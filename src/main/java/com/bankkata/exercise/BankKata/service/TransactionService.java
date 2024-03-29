package com.bankkata.exercise.BankKata.service;

import com.bankkata.exercise.BankKata.model.Transaction;
import com.bankkata.exercise.BankKata.model.TransactionList;
import com.bankkata.exercise.BankKata.repository.TransactionRepository;
import com.bankkata.exercise.BankKata.util.Util;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.bankkata.exercise.BankKata.constants.Constants.BALANCE_MESSAGE;
import static com.bankkata.exercise.BankKata.constants.Constants.DATE_TIME_PATTERN;
import static com.bankkata.exercise.BankKata.constants.Constants.DEBIT;
import static com.bankkata.exercise.BankKata.constants.Constants.EMPTY_LIST_MESSAGE;
import static com.bankkata.exercise.BankKata.constants.Constants.NUMBER_ITEMS_MESSAGE;


@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    private TransactionList transactionList;

    private StringBuffer resultMessage;

    /**
     * @param transactionRepository
     */
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /***
     * Service to get all transactions
     * @return TransactionList
     */
    public TransactionList getAllTransactions() {
        transactionList = new TransactionList();
        transactionList.setTransactions(transactionRepository.findAll());
        return transactionList;
    }

    /**
     * * Service to return past transactions based on the date which you input. it will return all transactions
     * that are equal or less than current date
     *
     * @param date the
     * @return TransactionList the payload type display the messages
     */
    public TransactionList findPastTransactions(Date date) {
        transactionList = new TransactionList();
        transactionList.setTransactions(transactionRepository.findPastTransactions(date));
        return transactionList;
    }

    /**
     * * Service to return future transactions based on the date which you input. it will return all transactions
     * that are equal or greater than current date
     *
     * @param date
     * @return
     */
    public TransactionList findFutureTransactions(Date date) {
        transactionList = new TransactionList();
        transactionList.setTransactions(transactionRepository.findFutureTransactions(date));
        return transactionList;
    }

    /**
     * Service to save transactions as bulk
     *
     * @param transactionList
     * @return
     */
    public String saveBulkTransactions(TransactionList transactionList) {
        List<Transaction> listTransactions = transactionList.getTransactions();
        setDatetime(listTransactions);
        List<Transaction> resultList = transactionRepository.saveAll(listTransactions);
        return getResultMessage(resultList);
    }

    public TransactionList findBetweenDatesTransactions(Date dateInitial, Date dateFinal) {
        List<Transaction> resultList = transactionRepository.findBetweenDatesTransactions(dateInitial, dateFinal);
        transactionList = new TransactionList();
        transactionList.setTransactions(resultList);
        return transactionList;
    }


    /**
     *
     * @param listTransactions
     */
    private void setDatetime(List<Transaction> listTransactions) {
        try {
            for (Transaction item : listTransactions) {
                item.setDatetime(new SimpleDateFormat(DATE_TIME_PATTERN).parse(item.getDate()
                        + " " + item.getTime()));
            }
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method to build a message based on the quantity of messages that were stored into the database
     *
     * @param resultList
     * @return
     */
    private String getResultMessage(List<Transaction> resultList) {
        resultMessage = new StringBuffer();
        if (Objects.isNull(resultList)) {
            resultMessage.append(EMPTY_LIST_MESSAGE);
        } else {
            resultMessage.append(NUMBER_ITEMS_MESSAGE + resultList.size());
        }
        return resultMessage.toString();
    }

    /**
     * Service to return the balance based on past transactions
     *
     * @param date
     * @return
     */
    public String getBalanceResult(Date date) {
        List<Transaction> transactions = findPastTransactions(date).getTransactions();
        return getBalanceValue(transactions);
    }

    /**
     * Method to calculate the amount based on DEBIT/CREDIT types
     *
     * @param transactions
     * @return
     */
    private String getBalanceValue(List<Transaction> transactions) {
        Double resultValue = 0d;
        resultMessage = new StringBuffer();

        for (Transaction item : transactions) {
            if (item.getType().equals(DEBIT)) {
                resultValue = resultValue - item.getAmount();
            } else {
                resultValue = resultValue + item.getAmount();
            }
        }
        return resultMessage.append(BALANCE_MESSAGE + Util.roundDouble(resultValue, 2)).toString();
    }
}
