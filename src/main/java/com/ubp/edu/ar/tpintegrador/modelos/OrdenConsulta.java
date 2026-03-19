package com.ubp.edu.ar.tpintegrador.modelos;
import com.ubp.edu.ar.tpintegrador.Dao.*;
import com.ubp.edu.ar.tpintegrador.Dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor

public class OrdenConsulta {

    private Turno turno;
    private List<DetalleOrdenConsulta> detalles;
    private List<EstadoOrden> estado;
    private int numeroOrden;
    private TipoConsulta tipo;
    private Paciente paciente;
    private Dao dao = FabricaDAO.getFactory("OrdenConsultaDao");

    private OrdenConsultaDao dao1 = new OrdenConsultaDao();

    public OrdenConsulta(){dao=new OrdenConsultaDao();}
    public OrdenConsulta(int numeroOrden,TipoConsulta tipo,Paciente paciente,Turno turno){
        this.numeroOrden=numeroOrden;
        this.tipo=tipo;
        this.paciente=paciente;
        this.turno=turno;
    }
    public List<OrdenConsulta> listarPorCriterio(OrdenConsulta ordenConsulta) {
        ModelMapper mapper = new ModelMapper();
        OrdenConsultaDto dto = mapper.map(ordenConsulta,OrdenConsultaDto.class);
        List<OrdenConsultaDto> ordenesDto = this.dao.listarPorCriterio(dto);
        List<OrdenConsulta> ordenes = Arrays.asList(mapper.map(ordenesDto, OrdenConsulta[].class));
        return ordenes;
    }
    public OrdenConsulta buscarOrden(OrdenConsulta ordenConsulta) {
        ModelMapper mapper = new ModelMapper();
        OrdenConsultaDto dto = mapper.map(ordenConsulta,OrdenConsultaDto.class);
       OrdenConsultaDto ordeneDto = this.dao1.buscarOrden(dto);
        OrdenConsulta orden = mapper.map(ordeneDto, OrdenConsulta.class);
        return orden;
    }

public List<OrdenConsulta> listarTodos() {
    List<OrdenConsultaDto> ordenesDto = this.dao.listarTodos();
    ModelMapper mapper = new ModelMapper();
    List<OrdenConsulta> ordenes = Arrays.asList(mapper.map(ordenesDto, OrdenConsulta[].class));
    return ordenes;
}
    public boolean guardar() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(OrdenConsulta.class, OrdenConsultaDto.class).addMappings(m -> {
            m.map(OrdenConsulta::getDetalles, OrdenConsultaDto::setDetalle);
        });

        mapper.typeMap(OrdenConsultaDto.class, OrdenConsulta.class).addMappings(m -> {
            m.map(OrdenConsultaDto::getDetalle, OrdenConsulta::setDetalles);
        });
        OrdenConsultaDto ordenConsultaDto = mapper.map(this, OrdenConsultaDto.class);
        return this.dao.insertar(ordenConsultaDto);
    }
    public boolean guardarNuevoDetalle() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(OrdenConsulta.class, OrdenConsultaDto.class).addMappings(m -> {
            m.map(OrdenConsulta::getDetalles, OrdenConsultaDto::setDetalle);
        });

        mapper.typeMap(OrdenConsultaDto.class, OrdenConsulta.class).addMappings(m -> {
            m.map(OrdenConsultaDto::getDetalle, OrdenConsulta::setDetalles);
        });
        OrdenConsultaDto ordenConsultaDto = mapper.map(this, OrdenConsultaDto.class);
        return this.dao1.insertarNuevoDetalle(ordenConsultaDto);
    }
    public boolean eliminar() {
        ModelMapper mapper = new ModelMapper();
        OrdenConsultaDto ordenConsultaDto = mapper.map(this, OrdenConsultaDto.class);
        return this.dao.borrar(ordenConsultaDto);
    }

    public void buscarDetalles() {
        ModelMapper mapper = new ModelMapper();
        OrdenConsultaDto ordenConsultaDto = mapper.map(this, OrdenConsultaDto.class);
        ordenConsultaDto = (OrdenConsultaDto) this.dao.buscar(ordenConsultaDto);
        List<DetalleOrdenConsulta> dets = new ArrayList<>(Arrays.asList(mapper.map(ordenConsultaDto.getDetalle(), DetalleOrdenConsulta[].class)));
        this.detalles = dets;
    }
    public int nroSigOrden(){
        int nroSiguiente;
        nroSiguiente= dao1.obtenerUltimoNumeroOrden();

        return nroSiguiente;
    }


}
