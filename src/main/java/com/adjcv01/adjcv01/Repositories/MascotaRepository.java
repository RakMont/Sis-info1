
package com.adjcv01.adjcv01.Repositories;

import com.adjcv01.adjcv01.Models.Mascota;
import com.adjcv01.adjcv01.Models.Propietario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface MascotaRepository extends CrudRepository<Mascota, Integer> {
    List<Mascota> findByEspecie(String especie);
    List<Mascota> findByPropietario(Propietario propietario);


    @Query("SELECT DISTINCT especie FROM Mascota")
    public List<String> findDistinctMascotaFromDb();
}
