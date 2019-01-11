package com.torfinn.demo1.repo;

import com.torfinn.demo1.domain.Tur;
import org.springframework.data.repository.CrudRepository;

public interface TurRepo extends CrudRepository<Tur, Integer> {

}
