package com.ubp.edu.ar.tpintegrador.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoOrdenDto {
    private String nombre;
    private Date fechaDesde;
    private Date fechaHasta;
}
