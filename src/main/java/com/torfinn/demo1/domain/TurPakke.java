package com.torfinn.demo1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TurPakke {
    @Id
    private String kode;

    @Column
    private String navn;

    public TurPakke(String kode, String navn) {
        this.kode = kode;
        this.navn = navn;
    }
}
