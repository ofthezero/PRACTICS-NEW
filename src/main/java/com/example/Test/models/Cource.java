package com.example.Test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Cource {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите название курса")
    String nameCource;

    @NotEmpty(message = "Введите текст курса")
    String cource;

    String cources;


    public Cource(String nameCource, String cource, String cources) {
        this.nameCource = nameCource;
        this.cource = cource;
        this.cources = cources;
          }

    public Cource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCource() {
        return nameCource;
    }

    public void setNameCource(String nameCource) {
        this.nameCource = nameCource;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public String getCources() {
        return cources;
    }
    public void setCources(String cource) {
            this.cources = cources;
    }



  }
