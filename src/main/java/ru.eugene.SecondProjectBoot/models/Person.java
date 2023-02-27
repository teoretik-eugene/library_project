package ru.eugene.SecondProjectBoot.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Not valid name")
    @Column(name = "name")
    private String name;
    @Min(value = 100, message = "min year is 100")
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)    // Подумать и сделать через N+1
    private List<Book> bookList;

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBookList() {
        if(this.bookList == null){
            bookList = new ArrayList<>();
        }
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return id + " - " + name + ", " + year;
    }
}
