package com.inheritance.table_per_class_with_join.domain;

import com.inheritance.table_per_class_with_join.BillingDetailsWithJoin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccountWithJoin extends BillingDetailsWithJoin {
    private int account;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "swift")
    private String swift;

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "account=" + account +
                ", bankName='" + bankName + '\'' +
                ", swift='" + swift + '\'' +
                '}';
    }
}
