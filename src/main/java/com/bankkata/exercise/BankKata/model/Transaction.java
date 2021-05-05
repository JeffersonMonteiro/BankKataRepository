package com.bankkata.exercise.BankKata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "DATE")
    private String date;

    @Column(name = "TIME")
    private String time;

    @Column(name = "TO")
    private String to;

    @Column(name = "COMMENT")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mma")
    @Column(name = "DATETIME")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date datetime;

    /**
     * Constructor to Transaction object
     * @param id
     * @param type
     * @param amount
     * @param date
     * @param time
     * @param to
     * @param comment
     * @param datetime
     */
    public Transaction(long id, String type, Double amount, String date, String time, String to, String comment, Date datetime) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.to = to;
        this.comment = comment;
        this.datetime = datetime;
    }


    public Transaction() {
    }

    /**
     * get method to return id
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * set method to id value
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * get method to return type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * set method to type value
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get method to return amount balance
     * @return
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * set method to amount balance
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * get method to return target costumer
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     * set method to target consumer value
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * get method to return the comments
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * set method to comments
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * get method to return date
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * set method to date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * get method to return time
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * set method to time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
