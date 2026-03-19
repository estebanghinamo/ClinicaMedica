package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import com.ubp.edu.ar.tpintegrador.Dao.ServicioDao;
import com.ubp.edu.ar.tpintegrador.Dto.ServicioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

/**
 * 
 */
public class Servicio {


    private String tipo_servicio;
    private Dao dao = FabricaDAO.getFactory("ServicioDao");

    public List<Servicio> listarTodos() {
        List<ServicioDto> serviciosDto = this.dao.listarTodos();
        ModelMapper mapper = new ModelMapper();
        List<Servicio> servicios = Arrays.asList(mapper.map(serviciosDto, Servicio[].class));
        return servicios;
    }
    @Override
    public String toString() {
        return tipo_servicio.toUpperCase();
    }



}