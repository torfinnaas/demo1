package com.torfinn.demo1.services;

import com.torfinn.demo1.domain.Grad;
import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurPakke;
import com.torfinn.demo1.repo.TurPakkeRepo;
import com.torfinn.demo1.repo.TurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class TurSrv {
    private TurPakkeRepo turPakkeRepo;
    private TurRepo turRepo;

    @Autowired
    public TurSrv(TurPakkeRepo turPakkeRepo, TurRepo turRepo) {
        this.turPakkeRepo = turPakkeRepo;
        this.turRepo = turRepo;
    }

    public Tur createTur(String tittel, String beskrivelse, Integer pris, String turPakkeKode, Grad grad) {
        Optional<TurPakke> turPakke = turPakkeRepo.findById(turPakkeKode);
        if (!turPakke.isPresent())
            throw new RuntimeException("Tur-pakke eksisterer ikke: " + turPakkeKode);

        return turRepo.save(new Tur(tittel, beskrivelse, pris, turPakke.get(), grad));
    }

    public Iterable<Tur> lookup() {
        return turRepo.findAll();
        //return turRepo.lookupTur("CC");
    }

    public long total() {
        return turRepo.count();
    }

    public Iterable<Tur> listBillige() {
        Optional<TurPakke> turPakke = turPakkeRepo.findById("BC");
        return turRepo.findByPrisLessThanAndTurPakke(120, turPakke.get());
    }

}
