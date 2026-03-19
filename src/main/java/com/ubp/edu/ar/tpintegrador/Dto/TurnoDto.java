package com.ubp.edu.ar.tpintegrador.Dto;

import com.ubp.edu.ar.tpintegrador.modelos.FranjaHoraria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TurnoDto {
    private int id;
    private int numTurno;
    private java.sql.Date fecha;
    private Time hora;
    private String franja_horaria;
    public TurnoDto(int id,int numTurno,java.sql.Date fecha,Time hora){
        this.id=id;
        this.numTurno=numTurno;
        this.fecha=fecha;
        this.hora=hora;
    }
}
