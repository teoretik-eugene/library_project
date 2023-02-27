package ru.eugene.SecondProjectBoot.models;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {
    private String name;

    private int age;

    private Subject subject;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Subject getSubject() {
        return subject;
    }
    @Autowired
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
