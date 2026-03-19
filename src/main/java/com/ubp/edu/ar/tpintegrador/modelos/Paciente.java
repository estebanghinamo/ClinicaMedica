package com.ubp.edu.ar.tpintegrador.modelos;

import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.HistorialClinicoDao;
import com.ubp.edu.ar.tpintegrador.Dao.IdentificacionDao;
import com.ubp.edu.ar.tpintegrador.Dao.PacienteDao;
import com.ubp.edu.ar.tpintegrador.Dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente extends Persona {

    private PacienteDao dao = new PacienteDao();
    private String obra_social;

    private GrupoFamiliar GrupoFamiliar;

    private boolean jefe_familia;

    private HistorialClinico historialClinico;

    public Paciente(long numero) {
        super(numero);
    }
    public Paciente buscarDni(int dni){
        ModelMapper mapper = new ModelMapper();
        Paciente paciente = new Paciente();
        PacienteDto pacienteDto = this.dao.buscarDni(dni);
        if(pacienteDto==null)
        {
            paciente=null;
        }
        else{
            paciente = mapper.map(pacienteDto,Paciente.class);
        }
        return paciente;
    }
    public boolean guardar() {
        ModelMapper mapper = new ModelMapper();
        LocalidadDto localidadDto= mapper.map(this.getLocalidad(),LocalidadDto.class);
        IdentificacionDto identificacionDto= mapper.map(this.getIdentificacion(),IdentificacionDto.class);
        PacienteDto pacienteDto = mapper.map(this, PacienteDto.class);
        pacienteDto.setLocalidadDto(localidadDto);
        pacienteDto.setIdentificacion(identificacionDto);


        return this.dao.insertar(pacienteDto);
    }

    public List<Paciente> listarTodos() {
        List<PacienteDto> pacienteDtos = this.dao.listarTodos();
        ModelMapper mapper = new ModelMapper();
        List<Paciente> pacientes = Arrays.asList(mapper.map(pacienteDtos, Paciente[].class));
        return pacientes;
    }


}