package com.example.Test.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bronirovanie")
public class Bronirovanie {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите дату")
    @DateTimeFormat
    String data;

    public String getFamilia()
    {
        return gosti.getFamilia();
    }
    public String getNomerKomnati()
    {
        return komnati.getNomerKomnati();
    }
    public String getType_Pitanie()
    {
        return type_pitanie.getName();
    }


    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Gosti gosti;


    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Type_pitanie type_pitanie;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Komnati komnati;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Gosti getGosti() {
        return gosti;
    }

    public void setGosti(Gosti gosti) {
        this.gosti = gosti;
    }
    public Komnati getKomnati() {
        return komnati;
    }

    public void setKomnati(Komnati komnati) {
        this.komnati = komnati;
    }

    public Type_pitanie getType_pitanie() {
        return type_pitanie;
    }

    public void setType_pitanie(Type_pitanie type_pitanie) {
        this.type_pitanie = type_pitanie;
    }

    public Bronirovanie(String data) {
        this.data = data;

    }


    public Bronirovanie() {
    }
}