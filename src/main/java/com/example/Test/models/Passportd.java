package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Passportd {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите серия пасспорта")
    String seriaPassporta;

    @NotNull(message = "Введите номер пасспорта")
    Integer nomerPassporta ;



    @OneToMany(mappedBy = "passportd", fetch = FetchType.EAGER)
    private Collection<Gosti> tenants;

    public Passportd(String seriaPassporta,Integer nomerPassporta) {
        this.seriaPassporta = seriaPassporta;
        this.nomerPassporta = nomerPassporta;
         }

    public Passportd() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeriaPassporta() {
        return seriaPassporta;
    }

    public void setSeriaPassporta(String seriaPassporta) {
        this.seriaPassporta = seriaPassporta;
    }

    public Integer getNomerPassporta() {
        return nomerPassporta;
    }

    public void setNomerPassporta(Integer nomerPassporta) {
        this.nomerPassporta = nomerPassporta;
    }

    public Collection<Gosti> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Gosti> tenants) {
        this.tenants = tenants;
    }
}
