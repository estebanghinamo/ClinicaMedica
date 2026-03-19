package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Turno {


    private String numero_turno;
    private java.sql.Date fecha;
    private Time Hora;
    private String franja_horaria;
    private Dao dao= FabricaDAO.getFactory("TurnoDao");

}