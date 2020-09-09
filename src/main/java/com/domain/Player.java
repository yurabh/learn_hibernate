package com.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String userName;

    @Column
    private Integer balance;

    @Column
    private Integer lastBet;

    public Player() {
    }

    public Player(String userName, Integer balance, Integer lastBet) {
        this.userName = userName;
        this.balance = balance;
        this.lastBet = lastBet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getLastBet() {
        return lastBet;
    }

    public void setLastBet(Integer lastBet) {
        this.lastBet = lastBet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id &&
                Objects.equals(userName, player.userName) &&
                Objects.equals(balance, player.balance) &&
                Objects.equals(lastBet, player.lastBet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, balance, lastBet);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", lastBet=" + lastBet +
                '}';
    }
}
