package com.testecerveja.TesteCerveja.Repository;

import com.testecerveja.TesteCerveja.Entity.Cerveja;
import org.springframework.data.repository.CrudRepository;

public interface CervejaRepository extends CrudRepository<Cerveja, Integer> {
}
