package com.ubp.edu.ar.tpintegrador.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PersonalDto extends PersonaDto {

    private float horas_trabajadas;

    private float sueldo;

    private Date fecha_ingreso;

    private String departamento;
    public PersonalDto(int idPersona, IdentificacionDto identificacion,String nombre, String apellido, LocalidadDto localidad){
        super(idPersona,identificacion,nombre,apellido,localidad);

    }
}
