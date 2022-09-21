package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class TypeKomnati {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите тип комнаты")
    String nameT;

    @NotEmpty(message = "Введите стоимость")
    String stoimost;





    @OneToMany(mappedBy = "typeKomnati", fetch = FetchType.EAGER)
    private Collection<Komnati> tenants;
    public TypeKomnati(String nameT, String stoimost) {
        this.nameT = nameT;
        this.stoimost = stoimost;

    }

    public TypeKomnati() {
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

    public String getStoimost() {
        return stoimost;
    }

    public void setStoimost(String stoimost) {
        this.stoimost = stoimost;
    }


    public Collection<Komnati> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Komnati> tenants) {
        this.tenants = tenants;
    }
}
