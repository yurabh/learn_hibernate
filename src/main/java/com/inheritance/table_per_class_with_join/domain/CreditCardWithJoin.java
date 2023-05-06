package com.inheritance.table_per_class_with_join.domain;

import com.inheritance.table_per_class_with_join.BillingDetailsWithJoin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCardWithJoin extends BillingDetailsWithJoin {
    @Column(name = "card_number")
    private int cardNumber;
    @Column(name = "exp_month")
    private String expMonth;
    @Column(name = "exp_year")
    private String expYear;

    public int getCardNumber() {
        return cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", expMonth='" + expMonth + '\'' +
                ", expYear='" + expYear + '\'' +
                '}';
    }
}
