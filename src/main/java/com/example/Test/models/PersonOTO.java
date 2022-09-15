package com.example.Test.models;


import javax.persistence.*;

@Entity
@Table (name = "users")
public class PersonOTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="pasport_id")
    private PasportOTO pasport;

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

    public PasportOTO getPasport() {
        return pasport;
    }

    public void setPasport(PasportOTO pasport) {
        this.pasport = pasport;
    }

    public PersonOTO(String name, PasportOTO pasport) {
        this.name = name;
        this.pasport = pasport;
    }

    public PersonOTO() {
    }
}
