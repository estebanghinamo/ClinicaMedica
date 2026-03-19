package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GrupoFamiliar {

    private long dni_jefe_fam;
    private Dao dao= FabricaDAO.getFactory("GrupoFamiliarDao");
    private List <Paciente> integrantes;


    /**
     * @param id 
     * @return
     */


}