package com.bankkata.exercise.BankKata.constants;

public class Constants {

    private Constants() {
    }

    //Paths urls
    public static final String TRANSACTION_PATH = "/transaction";
    public static final String ALL_TRANSACTIONS_PATH = "/all_transactions";
    public static final String SAVE_BULK_TRANSACTIONS_PATH = "/save_bulk_transactions";
    public static final String BALANCE_RESULT_PATH = "/balance_result";
    public static final String PAST_TRANSACTIONS_PATH = "/past_transactions";
    public static final String FUTURE_TRANSACTIONS_PATH = "/future_transactions";

    //Params
    public static final String DATE_PARAM = "date";
    public static final String DATE_PATTERN = "yyyy/MM/dd";

    //SQL Queries
    public static final String PAST_TRANSACTIONS_SQL_QUERY = "select a from Transaction a where a.date <= :date";
    public static final String FUTURE_TRANSACTIONS_SQL_QUERY = "select a from Transaction a where a.date >= :date";

    //Messages
    public static final String EMPTY_LIST_MESSAGE = "There aren't items to be stored";
    public static final String NUMBER_ITEMS_MESSAGE = "Number of item(s) stored: ";
    public static final String DEBIT = "debit";
    public static final String BALANCE_MESSAGE = "Your Balance is: ";

}
