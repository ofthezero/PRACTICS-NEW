package com.example.Test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class DateRaboti {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите день работы")
    String daysRaboti;

    @NotEmpty(message = "Введите часы работы")
    String timesRaboti;



    public DateRaboti(String daysRaboti, String timesRaboti) {
        this.daysRaboti = daysRaboti;
        this.timesRaboti = timesRaboti;

    }

    public DateRaboti() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDaysRaboti() {
        return daysRaboti;
    }

    public void setDaysRaboti(String daysRaboti) {
        this.daysRaboti = daysRaboti;
    }

    public String getTimesRaboti() {
        return timesRaboti;
    }

    public void setTimesRaboti(String timesRaboti) {
        this.timesRaboti = timesRaboti;
    }


}
