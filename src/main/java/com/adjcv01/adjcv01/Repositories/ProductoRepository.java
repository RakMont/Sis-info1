package com.adjcv01.adjcv01.Repositories;

import com.adjcv01.adjcv01.Models.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto, Integer>{
    List<Producto> findByPrecioProducto(Integer precio);

}
