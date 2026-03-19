/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ubp.edu.ar.tpintegrador.Dao;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ubp.edu.ar.tpintegrador.Dto.*;
import com.ubp.edu.ar.tpintegrador.modelos.HistorialClinico;
import com.ubp.edu.ar.tpintegrador.modelos.Identificacion;
import com.ubp.edu.ar.tpintegrador.modelos.Localidad;
import com.ubp.edu.ar.tpintegrador.modelos.Persona;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import javax.swing.*;

/**
 *
 * @author Usuario
 */
public class PacienteDao  implements Dao<PacienteDto>{
    private ConexionSql conexion = null;

    public PacienteDao() {
        conexion = ConexionSql.getInstancia();
    }
    @Override
    public PacienteDto buscar(PacienteDto dto) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public PacienteDto buscar(int id) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        PacienteDto pacienteDto=null;
        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Paciente pa, Persona pe, HistoriaClinica hc, GrupoFamiliar gf"
                    + " where pa.Persona_idPersona=pe.idPersona and"
                    +" pa.HistoriaClinica_idHistoriaClinica=hc.idHistoriaClinica and"
                    +" pa.GrupoFamiliar_idGrupoFamiliar=gf.idGrupoFamiliar and pa.idPaciente=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setDouble(1, id);
            rs = sentencia.executeQuery();
            LocalidadDao localidadDao= new LocalidadDao();
            IdentificacionDao identificacionDao= new IdentificacionDao();
            String nombre, apellido, obraSocial;
            int  dniJefeFamiliar,idPaciente, nroHistClinica, idPersona, idHistoriaCLinica, idGrupoFamiliar, idLocalidad, idIdentificacion;
            boolean jefeFamiliar;

            if(rs.next()){

                nombre = rs.getString("pe.nombre");
                apellido = rs.getString("pe.apellido");
                obraSocial = rs.getString("pa.obraSocial");
                dniJefeFamiliar = rs.getInt("gf.dniJefeFamiliar");
                nroHistClinica = rs.getInt("hc.nro_historial");
                idPersona = rs.getInt("pe.idPersona");
                idHistoriaCLinica = rs.getInt("hc.idHistoriaClinica");
                idGrupoFamiliar = rs.getInt("gf.idGrupoFamiliar");
                idLocalidad = rs.getInt("pe.Localidad_idLocalidad");
                idIdentificacion = rs.getInt("pe.Identificacion_idIdentificacion");
                jefeFamiliar= rs.getBoolean("pa.jefeFamilia");

                pacienteDto = new PacienteDto(idPersona,nombre,apellido,localidadDao.buscar(idLocalidad),identificacionDao.buscar(idIdentificacion),obraSocial,
                        new GrupoFamiliarDto(idGrupoFamiliar,dniJefeFamiliar),
                        jefeFamiliar,
                        new HistorialClinicoDto(nroHistClinica,idHistoriaCLinica));
            }

        } catch (SQLException  e) {
            System.err.println(e);
            System.out.println("catch1");
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
                System.out.println("catch");
            }
        }
        return pacienteDto;

    }
    public PacienteDto buscarDni(int dni) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        PacienteDto pacienteDto=null;
        try {
            con = conexion.getConnection();
            String sql = "select * from paciente pa "+
                                "join persona pe on pa.Persona_idPersona = pe.idPersona "+
                                "join historiaclinica hc on pa.HistoriaClinica_idHistoriaClinica = hc.idHistoriaClinica "+
                                "join grupofamiliar gf on pa.GrupoFamiliar_idGrupoFamiliar = gf.idGrupoFamiliar "+
                                "join identificacion i on pe.Identificacion_idIdentificacion = i.idIdentificacion "+
                                "where i.nroIdentificacion=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setDouble(1, dni);
            rs = sentencia.executeQuery();
            LocalidadDao localidadDao= new LocalidadDao();
            IdentificacionDao identificacionDao= new IdentificacionDao();
            String nombre, apellido, obraSocial;
            int  dniJefeFamiliar, nroHistClinica, idPersona, idHistoriaCLinica, idGrupoFamiliar, idLocalidad, idIdentificacion;
            boolean jefeFamiliar;
            if(rs.next()){
                nombre = rs.getString("pe.nombre");
                apellido = rs.getString("pe.apellido");
                obraSocial = rs.getString("pa.obraSocial");
                dniJefeFamiliar = rs.getInt("gf.dniJefeFamiliar");
                nroHistClinica = rs.getInt("hc.nro_historial");
                idPersona = rs.getInt("pe.idPersona");
                idHistoriaCLinica = rs.getInt("hc.idHistoriaClinica");
                idGrupoFamiliar = rs.getInt("gf.idGrupoFamiliar");
                idLocalidad = rs.getInt("pe.Localidad_idLocalidad");
                idIdentificacion = rs.getInt("pe.Identificacion_idIdentificacion");
                jefeFamiliar= rs.getBoolean("pa.jefeFamilia");

                pacienteDto = new PacienteDto(idPersona,nombre,apellido,localidadDao.buscar(idLocalidad),identificacionDao.buscar(idIdentificacion),obraSocial,
                        new GrupoFamiliarDto(idGrupoFamiliar,dniJefeFamiliar),
                        jefeFamiliar,
                        new HistorialClinicoDto(nroHistClinica,idHistoriaCLinica));
            }

        } catch (SQLException  e) {
            System.err.println(e);
            System.out.println("catch1");
        } finally {
            try {
                rs.close();
                sentencia.close();
                conexion.closeConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
                System.out.println("catch");
            }
        }
        return pacienteDto;

    }

    @Override
    public List<PacienteDto> listarPorCriterio(PacienteDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PacienteDto> listarTodos() {
        Connection con;
        Statement sentencia = null;
        ResultSet rs = null;
        List<PacienteDto> lista = new ArrayList<>();
        try {
            con = conexion.getConnection();
            String sql = "select * from paciente pa "+
                    "join persona pe on pa.Persona_idPersona = pe.idPersona "+
                    "join historiaclinica hc on pa.HistoriaClinica_idHistoriaClinica = hc.idHistoriaClinica "+
                    "join grupofamiliar gf on pa.GrupoFamiliar_idGrupoFamiliar = gf.idGrupoFamiliar "+
                    "join identificacion i on pe.Identificacion_idIdentificacion = i.idIdentificacion ";
            sentencia = con.createStatement();

            rs = sentencia.executeQuery(sql);
            LocalidadDao localidadDao= new LocalidadDao();
            IdentificacionDao identificacionDao= new IdentificacionDao();
            String nombre, apellido, obraSocial;
            int  dniJefeFamiliar, nroHistClinica, idPersona, idHistoriaCLinica, idGrupoFamiliar, idLocalidad, idIdentificacion;
            boolean jefeFamiliar;
            while(rs.next()){
                nombre = rs.getString("pe.nombre");
                apellido = rs.getString("pe.apellido");
                obraSocial = rs.getString("pa.obraSocial");
                dniJefeFamiliar = rs.getInt("gf.dniJefeFamiliar");
                nroHistClinica = rs.getInt("hc.nro_historial");
                idPersona = rs.getInt("pe.idPersona");
                idHistoriaCLinica = rs.getInt("hc.idHistoriaClinica");
                idGrupoFamiliar = rs.getInt("gf.idGrupoFamiliar");
                idLocalidad = rs.getInt("pe.Localidad_idLocalidad");
                idIdentificacion = rs.getInt("pe.Identificacion_idIdentificacion");
                jefeFamiliar= rs.getBoolean("pa.jefeFamilia");

                PacienteDto pacienteDto = new PacienteDto(idPersona,nombre,apellido,localidadDao.buscar(idLocalidad),identificacionDao.buscar(idIdentificacion),obraSocial,
                        new GrupoFamiliarDto(idGrupoFamiliar,dniJefeFamiliar),
                        jefeFamiliar,
                        new HistorialClinicoDto(nroHistClinica,idHistoriaCLinica));
                lista.add(pacienteDto);
            }

        } catch (SQLException  e) {
            System.err.println(e);
            System.out.println("catch1");
        } finally {
            try {
                rs.close();
                sentencia.close();
                conexion.closeConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
                System.out.println("catch");
            }
        }
        return lista;

    }

    @Override
    public boolean insertar(PacienteDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        HistorialClinicoDao historialClinicoDao= new HistorialClinicoDao();
        GrupoFamiliarDao grupoFamiliarDao= new GrupoFamiliarDao();
        PersonaDao personaDao= new PersonaDao();
        if(!historialClinicoDao.insertar(dto.getHistorialClinico()) ||
                !grupoFamiliarDao.insertar(dto.getGrupoFamiliar()) ||
                !personaDao.insertar(dto)){
            JOptionPane.showMessageDialog(null, "error en insertar el historial o error en insertar el grupo o error en insertar la persona.", "Error ", JOptionPane.ERROR_MESSAGE);
             return false;
        }
        else {
            try {
                con = conexion.getConnection();
                con.setAutoCommit(false);

                String sql = "insert into paciente(idPaciente, obraSocial, jefeFamilia, HistoriaClinica_idHistoriaClinica, " +
                        "                     GrupoFamiliar_idGrupoFamiliar, Persona_idPersona)" +
                        "values ((select (max(p.idPaciente)+1) from paciente p),?,?, " +
                        "        (select (max(hc.idHistoriaClinica)) from historiaclinica hc), " +
                        "        (select (max(gf.idGrupoFamiliar)) from grupofamiliar gf), " +
                        "        (select (max(p.idPersona)) from persona p))";
                sentencia = con.prepareStatement(sql);

                sentencia.setString(1,dto.getObra_social());
                sentencia.setBoolean(2,dto.isJefe_familia());
                int resultado = sentencia.executeUpdate();
                if (resultado <= 0) {
                    con.rollback();
                    return false;
                }
                con.commit();
                return true;
            } catch (SQLException e) {
                System.err.println(e);
                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex) {
                    }
                }
                return false;
            } finally {
                try {
                    sentencia.close();
                    this.conexion.closeConnection();
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    @Override
    public boolean modificar(PacienteDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(PacienteDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
