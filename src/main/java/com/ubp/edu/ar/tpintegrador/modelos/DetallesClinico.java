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
public class DetallesClinico {
    private Dao dao;
    private int nro;
    private OrdenConsulta orden;

    public DetallesClinico(OrdenConsulta orden, int nro) {
        this.orden = orden;
        this.nro = nro;
    }


}