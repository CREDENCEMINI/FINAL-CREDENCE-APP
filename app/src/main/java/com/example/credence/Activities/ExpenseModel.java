package com.example.credence.Activities;

import java.io.Serializable;

public class ExpenseModel implements Serializable {
    private String expenseId;
    private String expense_note;
    private String expense_category;
    private long expense_amount;
    private String time; // Change time to String
    private String type;
    private String uid;

    ExpenseModel() {
    }

    public ExpenseModel(String expenseId, String expense_note, String expense_category, long expense_amount, String time, String type, String uid) {
        this.expenseId = expenseId;
        this.expense_note = expense_note;
        this.expense_category = expense_category;
        this.expense_amount = expense_amount;
        this.time = time;
        this.type = type;
        this.uid = uid;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpense_note() {
        return expense_note;
    }

    public void setExpense_note(String expense_note) {
        this.expense_note = expense_note;
    }

    public String getExpense_category() {
        return expense_category;
    }

    public void setExpense_category(String expense_category) {
        this.expense_category = expense_category;
    }

    public long getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(long expense_amount) {
        this.expense_amount = expense_amount;
    }

    public String getTime() { // Change getter for time
        return time;
    }

    public void setTime(String time) { // Change setter for time
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
