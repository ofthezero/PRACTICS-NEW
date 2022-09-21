package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Komnati {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введите номер комнаты")
    String nomerKomnati;

    @NotEmpty(message = "Введите статус")
    String status;

    public String getNameT()
    {
        return typeKomnati.getNameT();
    }



    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private TypeKomnati typeKomnati;



    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Hotel hotel;

    public Komnati(String nomerKomnati, String status) {
        this.nomerKomnati = nomerKomnati;
        this.status = status;

    }

    public Komnati() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomerKomnati() {
        return nomerKomnati;
    }

    public void setNomerKomnati(String nomerKomnati) {
        this.nomerKomnati = nomerKomnati;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TypeKomnati getTypeKomnati() {
        return typeKomnati;
    }

    public void setTypeKomnati(TypeKomnati typeKomnati) {
        this.typeKomnati = typeKomnati;
    }
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
