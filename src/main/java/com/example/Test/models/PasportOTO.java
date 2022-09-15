package com.example.Test.models;


import javax.persistence.*;

@Entity
@Table(name = "pasports")
public class PasportOTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String series;
    private String number;
    @OneToOne(optional = true, mappedBy = "pasport")
    private PersonOTO owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PersonOTO getOwner() {
        return owner;
    }

    public void setOwner(PersonOTO owner) {
        this.owner = owner;
    }
}
