package com.bankkata.exercise.BankKata.integration;

import com.bankkata.exercise.BankKata.controller.TransactionController;
import com.bankkata.exercise.BankKata.model.Transaction;
import com.bankkata.exercise.BankKata.model.TransactionList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bankkata.exercise.BankKata.constants.Constants.DATE_PATTERN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TransactionControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionController transactionController;

    private Transaction transaction;

    private TransactionList transactionListObject;

    private List<Transaction> transactionList;

    @Before
    public void setup() throws ParseException {
        this.mockMvc = standaloneSetup(this.transactionController).build();
        transaction = new Transaction((long) 1, "debit", 45.0, "JohnDoe", "Test1", new Date(), "07:30AM");
        transactionList = new ArrayList<>();
        transactionListObject = new TransactionList();
        transactionList.add(transaction);
        transactionListObject.setTransactions(transactionList);
    }

    @Test
    public void saveBulkTransactions_WhenSaveBulkTransactions_ThenShouldReturnOk() throws Exception {

        mockMvc.perform(post("/transaction/save_bulk_transactions")
                .content(objectMapper.writeValueAsString(transactionListObject))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllTransactions_AllTransactionsShouldBeReturned_ThenShouldReturnOk() throws Exception {

        mockMvc.perform(get("/transaction/all_transactions")
                .content(objectMapper.writeValueAsString(transactionListObject))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getBalanceResult_BalanceAmountShouldBeReturned_ThenShouldReturnOk() throws Exception {
        mockMvc.perform(get("/transaction/balance_result?date=" + getStringDate())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void findPastTransactions_BalanceAmountShouldBeReturned_ThenShouldReturnOk() throws Exception {
        mockMvc.perform(get("/transaction/past_transactions?date=" + getStringDate())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void findFutureTransactions_BalanceAmountShouldBeReturned_ThenShouldReturnOk() throws Exception {
        mockMvc.perform(get("/transaction/future_transactions?date=" + getStringDate())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    private String getStringDate() throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_PATTERN);
        Date data = fmt.parse("2021/01/10");
        String str = fmt.format(data);
        return str;
    }

}
