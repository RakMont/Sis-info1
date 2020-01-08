package com.adjcv01.adjcv01.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;
@Entity
@Table(name="Vacuna")
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVacuna;
    @NotNull
    private String nombreVacuna;
    private String enfermedad;


    public Integer getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public String toString() {
        return "Vacuna{" +
                "idVacuna=" + idVacuna +
                ", nombreVacuna='" + nombreVacuna + '\'' +
                ", enfermedad=" + enfermedad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacuna)) return false;
        Vacuna vacuna = (Vacuna) o;
        return Objects.equals(getIdVacuna(), vacuna.getIdVacuna());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVacuna());
    }

    public Vacuna(Integer idVacuna, @NotNull String nombreVacuna, String enfermedad) {
        this.idVacuna = idVacuna;
        this.nombreVacuna = nombreVacuna;
        this.enfermedad = enfermedad;
    }

    public Vacuna() {
    }
}
