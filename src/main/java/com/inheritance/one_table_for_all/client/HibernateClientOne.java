package com.inheritance.one_table_for_all.client;

import com.inheritance.one_table_for_all.domain.BankAccountOneTable;
import com.inheritance.one_table_for_all.domain.CreditCardOneTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

public class HibernateClientOne {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final String BILLINGS_DETAILS = "SELECT bd FROM BillingDetailsOneTable bd";

    public static void main(String[] args) {
        CreditCardOneTable creditCardOneTable = createCreditCard();
        BankAccountOneTable bankAccountOneTable = createBankAccount();
        persistBankAccountAndCreditCard(creditCardOneTable, bankAccountOneTable);
        List<Object> billingDetails = getBillingDetails();
        if (!billingDetails.isEmpty()) {
            billingDetails.forEach(System.out::println);
        }
    }

    private static CreditCardOneTable createCreditCard() {
        CreditCardOneTable creditCardOneTable = new CreditCardOneTable();
        creditCardOneTable.setCardNumber(44411111);
        creditCardOneTable.setExpMonth("Jan");
        creditCardOneTable.setExpYear("2017");
        creditCardOneTable.setOwner("Bill Gates");
        return creditCardOneTable;
    }

    private static BankAccountOneTable createBankAccount() {
        BankAccountOneTable bankAccountOneTable = new BankAccountOneTable();
        bankAccountOneTable.setAccount(111222333);
        bankAccountOneTable.setBankName("Goldman Sachs");
        bankAccountOneTable.setSwift("GOLDUS33");
        bankAccountOneTable.setOwner("Donald Trump");
        return bankAccountOneTable;
    }

    private static void persistBankAccountAndCreditCard(CreditCardOneTable creditCardOneTable, BankAccountOneTable bankAccountOneTable) {
        Session session;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(creditCardOneTable);
            session.persist(bankAccountOneTable);
            transaction.commit();
        } catch (NullPointerException e) {
            Objects.requireNonNull(transaction).rollback();
            throw e;
        }
    }

    private static List<Object> getBillingDetails() {
        Session session;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            return session.createQuery(BILLINGS_DETAILS).list();
        } catch (Exception e) {
            Objects.requireNonNull(transaction).rollback();
            throw e;
        }
    }
}
