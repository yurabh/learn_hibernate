package com.service;

import com.domain.Book;
import com.domain.ReaderErrata;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class ReaderErrataClient {
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private static final Session session = factory.openSession();

    public static void main(String[] args) {

        session.beginTransaction();

        Book book = new Book("Lina Kostenko");

        ReaderErrata readerErrata = new ReaderErrata("mistake in first column", book, new Date());

        book.setReaderErrata(readerErrata);

        session.save(book);

        session.save(readerErrata);

        Book bookFound = session.get(Book.class, 1);

        System.out.println(bookFound.toString());

        ReaderErrata readerErrataFound = session.get(ReaderErrata.class, 4);

        System.out.println(readerErrataFound.toString());

        readerErrataFound.setContent("mistake");

        readerErrataFound.setSubmitted(new Date());

        session.saveOrUpdate(readerErrataFound);

        session.remove(bookFound);

        session.remove(readerErrataFound);


        Query query = session.createQuery("DELETE FROM Book b where b.readerErrata.id = :id");

        query.setParameter("id", 10);

        query.executeUpdate();

        Query queryTwo = session.createQuery("DELETE FROM ReaderErrata e where e.id = :id");

        queryTwo.setParameter("id", 6);

        queryTwo.executeUpdate();


        Query queryThree = session.createQuery("SELECT b.title FROM Book b WHERE b.id = :id");

        queryThree.setParameter("id", 11);

        session.getTransaction().commit();

        session.close();

        factory.close();
    }
}
