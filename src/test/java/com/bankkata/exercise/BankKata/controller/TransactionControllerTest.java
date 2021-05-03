package com.bankkata.exercise.BankKata.controller;

import com.bankkata.exercise.BankKata.model.Transaction;
import com.bankkata.exercise.BankKata.model.TransactionList;
import com.bankkata.exercise.BankKata.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bankkata.exercise.BankKata.constants.Constants.DATE_PATTERN;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerTest {

    private Transaction transaction;

    private TransactionList transactionListObject;

    private List<Transaction> transactionList;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Before
    public void setUp() throws ParseException {
        transaction = new Transaction((long) 1, "debit", 45.0, "JohnDoe", "Test1", new Date(), "07:30AM");
        transactionList = new ArrayList<>();
        transactionListObject = new TransactionList();
        transactionList.add(transaction);
        transactionListObject.setTransactions(transactionList);
    }

    @Test
    public void saveBulkTransactions_transactionShouldBeSaved_Success() throws ParseException {
        when(transactionService.saveBulkTransactions(transactionListObject)).thenReturn("test");
        Assert.assertEquals("test", transactionController.saveBulkTransactions(transactionListObject));
        verify(transactionService).saveBulkTransactions(transactionListObject);
    }

    @Test
    public void getAllTransactions_allTransactionShouldBeListed_Success() {
        when(transactionService.getAllTransactions()).thenReturn(transactionListObject);
        Assert.assertEquals(transactionListObject, transactionController.getAllTransactions());
        verify(transactionService).getAllTransactions();
    }

    @Test
    public void findPastTransactions_allPastedTransactionsShouldBeListed_Success() throws ParseException {
        when(transactionService.findPastTransactions(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10"))).thenReturn(transactionListObject);
        Assert.assertEquals(transactionListObject, transactionController.findPastTransactions(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10")));
        verify(transactionService).findPastTransactions(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10"));
    }

    @Test
    public void findFutureTransactions_allFutureTransactionsShouldBeListed_Success() throws ParseException {
        when(transactionService.findFutureTransactions(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10"))).thenReturn(transactionListObject);
        Assert.assertEquals(transactionListObject, transactionController.findFutureTransactions(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10")));
        verify(transactionService).findFutureTransactions(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10"));
    }

    @Test
    public void getBalance_balanceValueShouldBeReturned_Success() throws ParseException {
        when(transactionService.getBalanceResult(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10"))).thenReturn("Balance amount");
        Assert.assertEquals("Balance amount", transactionController.getBalanceResult(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10")));
        verify(transactionService).getBalanceResult(new SimpleDateFormat(DATE_PATTERN).parse("2021/01/10"));
    }

}
