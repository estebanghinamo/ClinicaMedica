package com.ubp.edu.ar.tpintegrador.Dto;

import com.ubp.edu.ar.tpintegrador.modelos.Servicio;
import com.ubp.edu.ar.tpintegrador.modelos.TipoConsulta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TipoConsultaDto {
    private int id;
    private float costo;
    private String tipo;
    private ServicioDto servicio;
    public TipoConsultaDto(int id,float costo,String tipo){
        this.id=id;
        this.costo=costo;
        this.tipo=tipo;
    }
}
