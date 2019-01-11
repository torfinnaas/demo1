package com.torfinn.demo1.domain;

import javax.persistence.*;

@Entity
public class Tur {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String tittel;

    @Column
    private String beskrivelse;

    @Column
    private Integer pris;

    @ManyToOne
    private TurPakke turPakke;

    public Tur(String tittel, String beskrivelse, Integer pris, TurPakke turPakke) {
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.turPakke = turPakke;
    }
}
