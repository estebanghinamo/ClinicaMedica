/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dto;

import com.ubp.edu.ar.tpintegrador.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
/**
 *
 * @author Usuario
 */
public class LocalidadDto {
    private int id;
    private String pais;
    private String provincia;
    private String ciudad;
    private String calle;
    private int numero;

    public LocalidadDto(int id, String pais, String provincia, String ciudad, String calle, int numero) {
        this.id = id;
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;
    }
    /**
     *
     */


}