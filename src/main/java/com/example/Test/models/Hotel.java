package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Hotel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите страну")
    String strana;

    @NotEmpty(message = "Введите город")
    String gorod;

    @NotEmpty(message = "Введите улицу")
    String ulica;

    @NotNull(message = "Введите номер дома")
    Integer dom ;



    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private Collection<Komnati> tenants;


    public Hotel(String strana, String gorod, String ulica, Integer dom) {
        this.strana = strana;
        this.gorod = gorod;
        this.ulica = ulica;
        this.dom = dom;
    }

    public Hotel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrana() {
        return strana;
    }

    public void setStrana(String strana) {
        this.strana = strana;
    }

    public String getGorod() {
        return gorod;
    }

    public void setGorod(String gorod) {
        this.gorod = gorod;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public Integer getDom() {
        return dom;
    }

    public void setDom(Integer dom) {
        this.dom = dom;
    }

    public Collection<Komnati> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Komnati> tenants) {
        this.tenants = tenants;
    }

}
