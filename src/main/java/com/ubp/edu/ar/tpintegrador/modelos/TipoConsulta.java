package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import com.ubp.edu.ar.tpintegrador.Dao.TipoConsultaDao;
import com.ubp.edu.ar.tpintegrador.Dto.MedicoDto;
import com.ubp.edu.ar.tpintegrador.Dto.TipoConsultaDto;
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
public class TipoConsulta {


    private float costo;
    private String tipo;
    private Servicio servicio;
    private Dao dao= FabricaDAO.getFactory("TipoConsultaDao");


    public List<TipoConsulta> listarTodos() {
        List<TipoConsultaDto> tiposDto = this.dao.listarTodos();
        ModelMapper mapper = new ModelMapper();
        List<TipoConsulta> tipos = Arrays.asList(mapper.map(tiposDto, TipoConsulta[].class));
        return tipos;
    }
    public List<TipoConsulta> filtrarPorServicio(TipoConsulta tipoConsulta) {
        ModelMapper mapper = new ModelMapper();

        TipoConsultaDto dto = mapper.map(tipoConsulta,TipoConsultaDto.class);

        List<TipoConsultaDto> tiposDto = this.dao.listarPorCriterio(dto);


        List<TipoConsulta> tiposConsultaList = Arrays.asList(mapper.map(tiposDto, TipoConsulta[].class));

        return tiposConsultaList;
    }
    @Override
    public String toString() {
        return tipo.toUpperCase();
    }


}