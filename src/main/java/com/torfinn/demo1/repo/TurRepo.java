package com.torfinn.demo1.repo;

import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurPakke;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TurRepo extends CrudRepository<Tur, Integer> {
    public Tur findByTittel(String tittel);

    public List<Tur> findByPrisLessThanAndTurPakke(Integer pris, TurPakke turPakke);

    //@Query("select t from t Tur, tp TurPakke where t.tur.turPakke = tp.turPakke and tp.turPakke.kode = ?1")
    //public List<Tur> lookupTur(String turPakkeKode);


}
