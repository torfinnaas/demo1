package com.torfinn.demo1.repo;

import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurPakke;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "turer", path = "turer")
public interface TurRepo extends PagingAndSortingRepository<Tur, Integer> {
    public Tur findByTittel(String tittel);

    public List<Tur> findByPrisLessThanAndTurPakkeKode(@Param("pris") Integer pris, @Param("turPakkeKode") String turPakkeKode);

    Page<Tur> findByTurPakkeKode(@Param("kode") String turPakkeKode, Pageable pageable);

    //@Query("select t from t Tur, tp TurPakke where t.tur.turPakke = tp.turPakke and tp.turPakke.kode = ?1")
    //public List<Tur> lookupTur(String turPakkeKode);




    @Override
    @RestResource(exported = false)
    <S extends Tur> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Tur> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Tur entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Tur> entities);

}
