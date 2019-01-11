package com.torfinn.demo1.services;

import com.torfinn.demo1.domain.TurPakke;
import com.torfinn.demo1.repo.TurPakkeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurPakkeSrv {
    private TurPakkeRepo turPakkeRepo;

    @Autowired
    public TurPakkeSrv(TurPakkeRepo turPakkeRepo) {
        this.turPakkeRepo = turPakkeRepo;
    }

    public TurPakke createTurPakke(String kode, String navn) {
        if(!turPakkeRepo.existsById(kode)) {
            return turPakkeRepo.save(new TurPakke(kode, navn));
        }

        return null;
    }

    public Iterable<TurPakke> lookup() {
        return turPakkeRepo.findAll();
    }

    public long antallTurer() {
        return turPakkeRepo.count();
    }
}
