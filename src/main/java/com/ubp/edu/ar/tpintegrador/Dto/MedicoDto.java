/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dto;

import com.ubp.edu.ar.tpintegrador.Dao.MedicoDao;
import com.ubp.edu.ar.tpintegrador.modelos.Servicio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor

/**
 *
 * @author Usuario
 */
public class MedicoDto extends ProfesionalDto{


    public MedicoDto(int idPersona, IdentificacionDto identificacion,String nombre, String apellido, LocalidadDto localidad, String matricula){
        super(idPersona,identificacion,nombre,apellido,localidad,matricula);

    }

    /**
     * 
     */


}
