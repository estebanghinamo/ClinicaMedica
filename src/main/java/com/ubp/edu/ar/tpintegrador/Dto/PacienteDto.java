/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dto;

import com.ubp.edu.ar.tpintegrador.modelos.GrupoFamiliar;
import com.ubp.edu.ar.tpintegrador.modelos.HistorialClinico;
import com.ubp.edu.ar.tpintegrador.modelos.Identificacion;
import com.ubp.edu.ar.tpintegrador.modelos.Localidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

/**
 *
 * @author Usuario
 */
public class PacienteDto extends PersonaDto{
        public PacienteDto(int idPersona,String nombre, String apellido, LocalidadDto localidad, IdentificacionDto identificacion, String obraSocial, GrupoFamiliarDto grupoFamiliar, boolean jefe_familia, HistorialClinicoDto historialClinico) {
            super(idPersona,identificacion, nombre, apellido, localidad);
            this.obra_social = obraSocial;
            this.GrupoFamiliar = grupoFamiliar;
            this.jefe_familia = jefe_familia;
            this.historialClinico = historialClinico;
        }
     private String obra_social;

    private GrupoFamiliarDto GrupoFamiliar;



    private boolean jefe_familia;

    private HistorialClinicoDto historialClinico;

    public PacienteDto(int idPersona, String nombre, String apellido, LocalidadDto localidad, IdentificacionDto identificacion) {
        super(idPersona,identificacion, nombre, apellido, localidad);
    }
}
