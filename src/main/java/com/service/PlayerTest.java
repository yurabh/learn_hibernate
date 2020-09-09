package com.service;

import com.domain.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class PlayerTest {

    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    private static Session session = factory.openSession();

    public static void main(String[] args) {

        session.beginTransaction();

        Player player = new Player("Yura", 120, 10);

        session.save(player);


        Player playerFound = session.get(Player.class, 14);

        System.out.println(playerFound.toString());

        playerFound.setBalance(1);

        playerFound.setLastBet(1);

        playerFound.setUserName("ooo");

        session.update(playerFound);

        session.remove(player);


        System.out.println("Show all Players \n");

        session.createQuery("SELECT p FROM Player p")
                .getResultStream()
                .forEach(p -> System.out.println(p));


        System.out.println("Select all name of Players \n");

        session.createQuery("SELECT p.userName FROM Player p")
                .getResultStream().forEach(p -> System.out.println(p.toString()));


        System.out.println("Select some player by id");

        session.createQuery("SELECT p.lastBet  FROM Player p WHERE p.id = 14")
                .stream()
                .forEach((p -> System.out.println(p.toString())));


        System.out.println("Select all players order by ask");

        session.createQuery("SELECT p FROM Player p WHERE p.id < 15 " +
                " ORDER BY p.userName desc")
                .stream()
                .forEach((p -> System.out.println(p.toString())));


        Query query = session.createQuery("UPDATE Player p set p.balance = :balance WHERE p.id = :id");

        query.setParameter("id", 14);

        query.setParameter("balance", 10000);

        int result = query.executeUpdate();

        System.out.println("Row updated: " + result);


        Query queryDelete = session.createQuery("DELETE FROM Player p WHERE p.id = :id");

        queryDelete.setParameter("id", 14);

        int resultDelete = queryDelete.executeUpdate();

        System.out.println("Rows affected: " + resultDelete);


        session.getTransaction().commit();

        session.close();

        factory.close();
    }
}
