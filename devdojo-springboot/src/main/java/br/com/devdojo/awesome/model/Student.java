package br.com.devdojo.awesome.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Student extends AbstractEntity{

    private String name;


    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
