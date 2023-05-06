package com.inheritance.table_per_class_with_join.client;

import com.inheritance.table_per_class_with_join.domain.BankAccountWithJoin;
import com.inheritance.table_per_class_with_join.domain.CreditCardWithJoin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

public class HibernateClientWithJoin {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final String BILLINGS_DETAILS = "SELECT bd FROM BillingDetailsWithJoin bd";

    public static void main(String[] args) {
        CreditCardWithJoin creditCard = createCreditCard();
        BankAccountWithJoin bankAccount = createBankAccount();
        persistBankAccountAndCreditCard(creditCard, bankAccount);
        List<Object> billingDetails = getBillingDetails();
        if (!billingDetails.isEmpty()) {
            billingDetails.forEach(System.out::println);
        }
    }

    private static CreditCardWithJoin createCreditCard() {
        CreditCardWithJoin creditCard = new CreditCardWithJoin();
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");
        return creditCard;
    }

    private static BankAccountWithJoin createBankAccount() {
        BankAccountWithJoin bankAccount = new BankAccountWithJoin();
        bankAccount.setAccount(111222333);
        bankAccount.setBankName("Goldman Sachs");
        bankAccount.setSwift("GOLDUS33");
        bankAccount.setOwner("Donald Trump");
        return bankAccount;
    }

    private static void persistBankAccountAndCreditCard(CreditCardWithJoin creditCard, BankAccountWithJoin bankAccount) {
        Session session;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(creditCard);
            session.persist(bankAccount);
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
