/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dto;

import com.ubp.edu.ar.tpintegrador.modelos.DetallesClinico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor




/**
 *
 * @author Usuario
 */
public class HistorialClinicoDto {
    private int numero_historial;
    private int id;
    private List<DetallesClinicosDto> detallesClinicosDto = new ArrayList<>();

    public HistorialClinicoDto(int nroHistClinica, int idHistoriaCLinica) {
        this.numero_historial = nroHistClinica;
        this.id = idHistoriaCLinica;
    }
    public HistorialClinicoDto() {}
}
