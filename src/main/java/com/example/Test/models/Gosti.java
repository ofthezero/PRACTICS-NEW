package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Gosti {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите фамилию")
    String familia;

    @NotEmpty(message = "Введите имя")
    String imya;

    @NotEmpty(message = "Введите отчество")
    String otchestvo;



    public String getSeriaPassporta()
    {
        return passportd.getSeriaPassporta();
    }
    @OneToMany(mappedBy = "gosti", fetch = FetchType.EAGER)
    private Collection<Bronirovanie> tenants;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Passportd passportd;

    public Gosti(String familia, String imya, String otchestvo) {
        this.familia = familia;
        this.imya = imya;
        this.otchestvo = otchestvo;

    }

    public Gosti() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getImya() {
        return imya;
    }

    public void setImya(String imya) {
        this.imya = imya;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }



    public Passportd getPassportd() {
        return passportd;
    }

    public void setPassportd(Passportd passportd) {
        this.passportd = passportd;
    }

    public Collection<Bronirovanie> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Bronirovanie> tenants) {
        this.tenants = tenants;
    }

}
