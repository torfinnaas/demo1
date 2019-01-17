package com.torfinn.demo1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TurPakke {
    @Id
    private String kode;

    @Column
    private String navn;

    protected TurPakke() {
        // used by Hibernate
    }

    public TurPakke(String kode, String navn) {
        this.kode = kode;
        this.navn = navn;
    }

    public String getKode() {
        return kode;
    }


    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return "TurPakke{" +
                "kode='" + kode + '\'' +
                ", navn='" + navn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurPakke that = (TurPakke) o;
        return Objects.equals(kode, that.kode) &&
                Objects.equals(navn, that.navn);
    }
}
