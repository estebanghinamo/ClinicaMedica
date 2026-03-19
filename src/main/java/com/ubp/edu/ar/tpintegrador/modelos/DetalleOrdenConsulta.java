package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

/**
 * 
 */
public class DetalleOrdenConsulta {
    private Practica practica;
    private Medico medico;
    private String diagnostico;
    private String observacion;
    private Dao dao= FabricaDAO.getFactory("DetalleOrdenConsultaDao");

    public DetalleOrdenConsulta(Medico medico) {
        this.medico = medico;
    }
}
