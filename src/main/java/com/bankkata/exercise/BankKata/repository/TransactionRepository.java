package com.bankkata.exercise.BankKata.repository;

import com.bankkata.exercise.BankKata.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

import static com.bankkata.exercise.BankKata.constants.Constants.DATE_PARAM;
import static com.bankkata.exercise.BankKata.constants.Constants.FUTURE_TRANSACTIONS_SQL_QUERY;
import static com.bankkata.exercise.BankKata.constants.Constants.PAST_TRANSACTIONS_SQL_QUERY;


public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    /**
     * Repository service used to return all past transactions based on the current date
     * @param date
     * @return List<Transaction></>
     */
    @Query(PAST_TRANSACTIONS_SQL_QUERY)
    List<Transaction> findPastTransactions(@Param(DATE_PARAM) Date date);

    /**
     * Repository service used to return all future transactions based on the current date
     * @param date
     * @return List<Transaction></>
     */
    @Query(FUTURE_TRANSACTIONS_SQL_QUERY)
    List<Transaction> findFutureTransactions(@Param(DATE_PARAM) Date date);
}
