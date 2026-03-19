package com.ubp.edu.ar.tpintegrador.Dto;

import com.ubp.edu.ar.tpintegrador.modelos.DetalleOrdenConsulta;
import com.ubp.edu.ar.tpintegrador.modelos.EstadoOrden;
import com.ubp.edu.ar.tpintegrador.modelos.TipoConsulta;
import com.ubp.edu.ar.tpintegrador.modelos.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrdenConsultaDto {

    private int id;
    private int numeroOrden;
    private TipoConsultaDto tipo;
    private EstadoOrdenDto estado;
    private TurnoDto turno;
    private List<DetalleOrdenConsultaDto> detalle;
    private PacienteDto paciente;

    public OrdenConsultaDto(int id, int numeroOrden, TurnoDto turno, TipoConsultaDto tipo, PacienteDto paciente) {
        this.id = id;
        this.numeroOrden = numeroOrden;
        this.turno = turno;

        this.tipo = tipo;
        this.paciente = paciente;
    }

    public OrdenConsultaDto(int idOrden, PacienteDto pacienteDTO, int nroOrden, TipoConsultaDto tipo) {
        this.id = idOrden;
        this.paciente = pacienteDTO;
        this.numeroOrden = nroOrden;
        this.tipo=tipo;
    }
}
