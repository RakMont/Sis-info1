package com.adjcv01.adjcv01.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="Mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMascota;
    @NotNull
    private String nombreMascota;
    private Integer edadMascota;
    private String especie;


    @ManyToOne
    @JoinColumn(name = "idPropietario")
    private Propietario propietario;

    public String getEspecie() {
        return especie;
    }


    @ManyToMany
    @JoinTable(name="vacuna_mascotas"
            ,joinColumns=@JoinColumn(name="mascota_id")
            ,inverseJoinColumns=@JoinColumn(name="vacuna_id")
    )
    private List<Vacuna> vacunas;


    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public Integer getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(Integer edadMascota) {
        this.edadMascota = edadMascota;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mascota)) return false;
        Mascota mascota = (Mascota) o;
        return Objects.equals(getIdMascota(), mascota.getIdMascota());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMascota());
    }



    public Mascota() {
    }
}
