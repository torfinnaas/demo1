package com.torfinn.demo1.repo;

import com.torfinn.demo1.domain.TurPakke;
import org.springframework.data.repository.CrudRepository;

public interface TurPakkeRepo extends CrudRepository<TurPakke, String> {
    TurPakke findByName(String navn);
}
