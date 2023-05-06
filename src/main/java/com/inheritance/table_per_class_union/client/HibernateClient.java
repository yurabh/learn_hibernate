package com.inheritance.table_per_class_union.client;

import com.inheritance.table_per_class_union.domain.BankAccount;
import com.inheritance.table_per_class_union.domain.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

public class HibernateClient {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final String BILLING_DETAILS = "FROM BillingDetails";

    public static void main(String[] args) {
        CreditCard creditCard = createCreditCard();
        BankAccount bankAccount = createBankAccount();
        persistBankAccountAndCreditCard(creditCard, bankAccount);
        List<Object> billingDetails = getBillingDetails();
        if (!billingDetails.isEmpty()) {
            billingDetails.forEach(System.out::println);
        }
    }

    private static CreditCard createCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");
        return creditCard;
    }

    private static BankAccount createBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount(111222333);
        bankAccount.setBankName("Goldman Sachs");
        bankAccount.setSwift("GOLDUS33");
        bankAccount.setOwner("Donald Trump");
        return bankAccount;
    }

    private static void persistBankAccountAndCreditCard(CreditCard creditCard, BankAccount bankAccount) {
        Session session;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(creditCard);
            session.persist(bankAccount);
            transaction.commit();
        } catch (Exception e) {
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
            return session.createQuery(BILLING_DETAILS).list();
        } catch (Exception e) {
            Objects.requireNonNull(transaction).rollback();
            throw e;
        }
    }
}
