package com.testecerveja.TesteCerveja.Repository;

import com.testecerveja.TesteCerveja.Entity.Cerveja;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CervejaRepository extends CrudRepository<Cerveja, Integer> {

    List<Cerveja> findCervejaByEstilo(String estilo);

    @Query(value = "SELECT * FROM cerveja where templ <= ?1 and temph >= ?1 order by abs((temph+templ)/2 - ?1), estilo limit 1",
            nativeQuery = true)
    List<Cerveja> findCervejaByTemp(int Temp);
}
