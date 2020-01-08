package com.adjcv01.adjcv01.Repositories;
import com.adjcv01.adjcv01.Models.Vacuna;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface VacunaRepository extends CrudRepository<Vacuna, Integer> {

    List<Vacuna> findByIdVacunaNotIn(List<Integer> vacunas);


}
