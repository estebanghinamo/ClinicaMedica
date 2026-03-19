package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.FabricaDAO;
import com.ubp.edu.ar.tpintegrador.Dao.HistorialClinicoDao;
import com.ubp.edu.ar.tpintegrador.Dto.HistorialClinicoDto;
import com.ubp.edu.ar.tpintegrador.Dto.OrdenConsultaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor

public class HistorialClinico {
    private Dao dao = FabricaDAO.getFactory("HistorialClinicoDao");
    private HistorialClinicoDao dao1 = new HistorialClinicoDao();
    private int numero_historial;
    private List<DetallesClinico> detallesClinicos;


    public HistorialClinico() {
        this.detallesClinicos = new ArrayList<>();
    }

    public HistorialClinico buscar(HistorialClinico hc) {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(OrdenConsulta.class, OrdenConsultaDto.class).addMappings(m -> {
            m.map(OrdenConsulta::getDetalles, OrdenConsultaDto::setDetalle);
        });

        mapper.typeMap(OrdenConsultaDto.class, OrdenConsulta.class).addMappings(m -> {
            m.map(OrdenConsultaDto::getDetalle, OrdenConsulta::setDetalles);
        });
        mapper.typeMap(HistorialClinico.class, HistorialClinicoDto.class).addMappings(m -> {
            m.map(HistorialClinico::getDetallesClinicos, HistorialClinicoDto::setDetallesClinicosDto);
        });

        mapper.typeMap(HistorialClinicoDto.class, HistorialClinico.class).addMappings(m -> {
            m.map(HistorialClinicoDto::getDetallesClinicosDto, HistorialClinico::setDetallesClinicos);
        });
        HistorialClinicoDto dto = mapper.map(hc, HistorialClinicoDto.class);
        HistorialClinicoDto historialClinicoDto = (HistorialClinicoDto) this.dao.buscar(dto);

        HistorialClinico historialClinico = mapper.map(historialClinicoDto, HistorialClinico.class);

        return historialClinico;
    }

    public List<HistorialClinico> listarTodos() {
        List<HistorialClinicoDto> historialClinicoDto = this.dao.listarTodos();
        ModelMapper mapper = new ModelMapper();
        List<HistorialClinico> historialClinicos = Arrays.asList(mapper.map(historialClinicoDto, HistorialClinico[].class));
        return historialClinicos;
    }

    public List<HistorialClinico> listarPorCriterio(HistorialClinico historialClinico) {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(HistorialClinico.class, HistorialClinicoDto.class).addMappings(m -> {
            m.map(HistorialClinico::getDetallesClinicos, HistorialClinicoDto::setDetallesClinicosDto);
        });

        mapper.typeMap(HistorialClinicoDto.class, HistorialClinico.class).addMappings(m -> {
            m.map(HistorialClinicoDto::getDetallesClinicosDto, HistorialClinico::setDetallesClinicos);
        });
        HistorialClinicoDto dto = mapper.map(historialClinico, HistorialClinicoDto.class);
        List<HistorialClinicoDto> hcDto = this.dao.listarPorCriterio(dto);
        List<HistorialClinico> hc = Arrays.asList(mapper.map(hcDto, HistorialClinico[].class));
        return hc;
    }
    public boolean guardarDetalle() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(OrdenConsulta.class, OrdenConsultaDto.class).addMappings(m -> {
            m.map(OrdenConsulta::getDetalles, OrdenConsultaDto::setDetalle);
        });

        mapper.typeMap(OrdenConsultaDto.class, OrdenConsulta.class).addMappings(m -> {
            m.map(OrdenConsultaDto::getDetalle, OrdenConsulta::setDetalles);
        });
        mapper.typeMap(HistorialClinico.class, HistorialClinicoDto.class).addMappings(m -> {
            m.map(HistorialClinico::getDetallesClinicos, HistorialClinicoDto::setDetallesClinicosDto);
        });

        mapper.typeMap(HistorialClinicoDto.class, HistorialClinico.class).addMappings(m -> {
            m.map(HistorialClinicoDto::getDetallesClinicosDto, HistorialClinico::setDetallesClinicos);
        });
        HistorialClinicoDto historialClinicoDto = mapper.map(this, HistorialClinicoDto.class);
        return this.dao1.insertarDetalles(historialClinicoDto);
    }

}