package com.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "erratas")
public class ReaderErrata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private Book book;

    @Temporal(TemporalType.DATE)
    private Date submitted;

    public ReaderErrata() {
    }

    public ReaderErrata(String content, Book book, Date submitted) {
        this.content = content;
        this.book = book;
        this.submitted = submitted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderErrata that = (ReaderErrata) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(book, that.book) &&
                Objects.equals(submitted, that.submitted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, book, submitted);
    }

    @Override
    public String toString() {
        return "ReaderErrata{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", book=" + book +
                ", submitted=" + submitted +
                '}';
    }
}
