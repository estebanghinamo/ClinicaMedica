package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 
 */
public class Localidad {


    private String pais;
    private String provincia;
    private Dao dao= FabricaDAO.getFactory("LocalidadDao");
    private String ciudad;
    private String calle;
    private int numero;

    public Localidad(String pais, String provincia, String ciudad, String calle, int numero) {
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;

    }

}