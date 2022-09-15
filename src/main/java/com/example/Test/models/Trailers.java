package com.example.Test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Trailers {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите название сериала")
    String nameT;

    @NotEmpty(message = "Введите рейтинг КП")
    String raiting1;

    @NotEmpty(message = "Введите рейтинг U")
    String raiting2;

    @NotNull(message = "Введите количество серий")
    Integer date ;
    @NotNull(message = "Введите количество сезонов")
    Integer season;

    public Trailers(String nameT, String raiting1, String raiting2, Integer date, Integer season) {
        this.nameT = nameT;
        this.raiting1 = raiting1;
        this.raiting2 = raiting2;
        this.date = date;
        this.season = season;
    }

    public Trailers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameT() {
        return nameT;
    }

    public void setNameT(String nameT) {
        this.nameT = nameT;
    }

    public String getRaiting1() {
        return raiting1;
    }

    public void setRaiting1(String raiting1) {
        this.raiting1 = raiting1;
    }

    public String getRaiting2() {
        return raiting2;
    }

    public void setRaiting2(String raiting2) {
        this.raiting2 = raiting2;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }
}
