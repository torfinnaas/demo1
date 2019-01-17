package com.torfinn.demo1.repo;

import com.torfinn.demo1.domain.TurPakke;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "pakker", path = "pakker")
public interface TurPakkeRepo extends CrudRepository<TurPakke, String> {
    Optional<TurPakke> findByNavn(@Param("name")String navn);

    @Override
    @RestResource(exported = false)
    <S extends TurPakke> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends TurPakke> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(TurPakke entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends TurPakke> entities);

}
