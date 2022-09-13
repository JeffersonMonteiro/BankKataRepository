package com.bankkata.exercise.BankKata.repository;

import com.bankkata.exercise.BankKata.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    /**
     * Repository service used to return all past transactions based on the current date
     * @param date
     * @return List<Transaction></>
     */
    @Query("select a from Transaction a where a.datetime <= :datetime")
    List<Transaction> findPastTransactions(@Param("datetime") Date date);

    /**
     * Repository service used to return all future transactions based on the current date
     * @param date
     * @return List<Transaction></>
     */
    @Query("select a from Transaction a where a.datetime >= :datetime")
    List<Transaction> findFutureTransactions(@Param("datetime") Date date);


    @Query("select a from Transaction a where a.datetime >= :dateInitial and a.datetime <= :dateFinal")
    List<Transaction> findBetweenDatesTransactions(@Param("dateInitial") Date dateInitial
            , @Param("dateFinal") Date dateFinal);
}
