package com.torfinn.demo1.domain;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
public class Tur {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String tittel;

    @Column(length = 2000)
    private String beskrivelse;

    @Column
    private Integer pris;

    @ManyToOne
    @JoinColumn(name="tur_pakke_kode")
    private TurPakke turPakke;

    @Column
    @Enumerated(EnumType.STRING)
    private Grad grad;

    protected Tur() {
        // used by Hibernate
    }


    public Tur(String tittel, String beskrivelse, Integer pris, TurPakke turPakke, Grad grad) {
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.turPakke = turPakke;
        this.grad = grad;
    }

    public Integer getId() {
        return id;
    }


    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public Integer getPris() {
        return pris;
    }

    public void setPris(Integer pris) {
        this.pris = pris;
    }

    public TurPakke getTurPakke() {
        return turPakke;
    }

    public void setTurPakke(TurPakke turPakke) {
        this.turPakke = turPakke;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return "Tur{" +
                "id=" + id +
                ", tittel='" + tittel + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", pris=" + pris +
                ", turPakke=" + turPakke +
                ", grad=" + grad.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tur tur = (Tur) o;
        return Objects.equals(id, tur.id) &&
                Objects.equals(tittel, tur.tittel) &&
                Objects.equals(beskrivelse, tur.beskrivelse) &&
                Objects.equals(pris, tur.pris) &&
                Objects.equals(turPakke, tur.turPakke) &&
                grad == tur.grad;
    }
}
