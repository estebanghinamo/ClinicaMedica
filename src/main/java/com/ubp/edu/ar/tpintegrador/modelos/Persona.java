package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 
 */
public abstract class Persona {



    private String nombre;
    private Dao dao= FabricaDAO.getFactory("PersonaDao");
    private String apellido;
    private Identificacion identificacion;
    private Localidad localidad;
    public Persona(long numero){ new Identificacion(numero);}
}