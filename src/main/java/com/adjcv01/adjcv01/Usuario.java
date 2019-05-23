package com.adjcv01.adjcv01;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Entity
@Table(name="Usuario")
public class Usuario {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String nombre;

    public Usuario(@NotNull String nombre, Date fecnac) {

        this.nombre = nombre;
        this.fecnac = fecnac;
    }

    public Usuario() {
    }








    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecnac() {
        return fecnac;
    }

    public void setFecnac(Date fecnac) {
        this.fecnac = fecnac;
    }

    private Date fecnac;
}