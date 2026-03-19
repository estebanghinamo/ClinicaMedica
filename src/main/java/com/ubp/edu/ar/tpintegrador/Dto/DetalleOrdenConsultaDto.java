package com.ubp.edu.ar.tpintegrador.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DetalleOrdenConsultaDto {
    private int id;
    private PracticaDto practica;
    private MedicoDto medico;
    private String diagnostico;
    private String observacion;

}
