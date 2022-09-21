package com.example.Test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Dolznost {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите наименование должности")
    String nameDolznosti;

    @NotNull(message = "Введите оклад")
    Integer oklad ;


    public Dolznost(String nameDolznosti, Integer oklad) {
        this.nameDolznosti = nameDolznosti;
        this.oklad = oklad;

    }

    public Dolznost() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDolznosti() {
        return nameDolznosti;
    }
    public void setNameDolznosti(String nameDolznosti) {
        this.nameDolznosti = nameDolznosti;
    }

    public Integer getOklad() {
        return oklad;
    }
    public void setOklad(Integer oklad) {
            this.oklad = oklad;
    }
}
