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
public class Identificacion {

    private String tipo;
    private long numero;
    private Dao dao= FabricaDAO.getFactory("LocalidadDao");
    public Identificacion(long numero){this.numero=numero;}

    public Identificacion(String tipo, long numero) {
        this.tipo = tipo;
        this.numero = numero;
    }


}