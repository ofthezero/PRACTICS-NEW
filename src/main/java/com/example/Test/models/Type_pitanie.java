package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Type_pitanie {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите тип питание")
    String name;

       @NotNull(message = "Введите стоимость")
    Integer stoimost;

       @OneToMany(mappedBy = "type_pitanie", fetch = FetchType.EAGER)
    private Collection<Bronirovanie> tenants;

    public Type_pitanie(String name, Integer stoimost) {
        this.name = name;
        this.stoimost = stoimost;

    }

    public Type_pitanie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStoimost() { return stoimost; }

    public void setStoimost(Integer stoimost) {
        this.stoimost = stoimost;
    }

    public Collection<Bronirovanie> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Bronirovanie> tenants) {
        this.tenants = tenants;
    }



}
