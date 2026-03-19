package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import com.ubp.edu.ar.tpintegrador.Dao.MedicoDao;
import com.ubp.edu.ar.tpintegrador.Dto.MedicoDto;
import com.ubp.edu.ar.tpintegrador.Dto.OrdenConsultaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

/**
 * 
 */
public class Medico extends Profesional {
    private Dao dao= FabricaDAO.getFactory("MedicoDao");
    public List<Medico> ListarMedicosPorServicio(Medico medico){
        ModelMapper mapper = new ModelMapper();
        MedicoDto dto = mapper.map(medico,MedicoDto.class);
        List<MedicoDto> medicoDtos = this.dao.listarPorCriterio(dto);
        List<Medico> medicos = Arrays.asList(mapper.map(medicoDtos, Medico[].class));
        return medicos;
    }
    @Override
    public String toString() {
        return getNombre().toUpperCase()+" "+getApellido().toUpperCase();
    }



}