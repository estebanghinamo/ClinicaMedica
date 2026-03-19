package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class EstadoOrden {

    private EstadoOrdenEnum nombre;
    private Date fechaDesde;
    private Date fechaHasta;
    private Dao dao= FabricaDAO.getFactory("EstadoOrdenDao");
}