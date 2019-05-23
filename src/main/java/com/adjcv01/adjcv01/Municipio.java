package com.adjcv01.adjcv01;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name="Municipio")
public class Municipio {
    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idMunicipio;
    @NotNull
    private String nombre;
    private Date fecha;

    public Municipio() {
    }

    public Municipio(@NotNull String nombre, Date fecha) {
        this.setNombre(nombre);
        this.setFecha(fecha);
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
