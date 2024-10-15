package br.ada.tech.class1171.customer;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    public static final long serialVersionUid = 1l;

    private Long id;
    private String name;
    private String document;
    private LocalDate birthDate;

    public Customer(Long id, String name, String document, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + document + ";" + birthDate;
    }
}