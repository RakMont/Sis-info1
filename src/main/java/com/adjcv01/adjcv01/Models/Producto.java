package com.adjcv01.adjcv01.Models;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name="Producto")
public class Producto {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer idProducto;
        @NotNull
        private String nombreProducto;

        private Integer precioProducto;

        private Integer cantidadProducto;
        private String foto;

    public Producto() {
    }



    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Producto that = (Producto) o;
            return Objects.equals(idProducto, that.idProducto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idProducto);
        }

        public Integer getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(Integer idProducto) {
            this.idProducto = idProducto;
        }

        public String getNombreProducto() {
            return nombreProducto;
        }

        public void setNombreProducto(String nombreProducto) {
            this.nombreProducto = nombreProducto;
        }

        public Integer getPrecioProducto() {
            return precioProducto;
        }

        public void setPrecioProducto(Integer precioProducto) {
            this.precioProducto = precioProducto;
        }

        public Integer getCantidadProducto() {
            return cantidadProducto;
        }

        public void setCantidadProducto(Integer cantidadProducto) {
            this.cantidadProducto = cantidadProducto;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        @Override
        public String toString() {
            return "Propietario{" +
                    "idProducto=" + idProducto +
                    ", nombreProducto='" + nombreProducto + '\'' +
                    ", precioProducto=" + precioProducto +
                    ", cantidadProducto=" + cantidadProducto +
                    ", foto='" + foto + '\'' +
                    '}';
        }




}
