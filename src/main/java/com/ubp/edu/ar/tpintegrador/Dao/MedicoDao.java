/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dao;
import com.ubp.edu.ar.tpintegrador.Dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class MedicoDao implements Dao<MedicoDto> {
    private ConexionSql conexion = null;

    public MedicoDao() {

        conexion = ConexionSql.getInstancia();
    }
    @Override
    public MedicoDto buscar(MedicoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public MedicoDto buscar(int id) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        MedicoDto medicoDto=null;
        try {
            con = conexion.getConnection();
            String sql = "select * from medico m "+
                    "join profesional p on m.Profesional_idProfesional=p.idProfesional "+
                    "join personal pr on p.Personal_idPersonal=pr.idPersonal " +
                    "join persona pe on pr.Persona_idPersona = pe.idPersona " +
                    "where m.idMedico=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setDouble(1, id);
            rs = sentencia.executeQuery();
            LocalidadDao localidadDao= new LocalidadDao();
            IdentificacionDao identificacionDao= new IdentificacionDao();
            String nombre, apellido, matricula;
            int   idPersona, idLocalidad, idIdentificacion;
            if(rs.next()){

                nombre = rs.getString("pe.nombre");
                apellido = rs.getString("pe.apellido");
                idPersona = rs.getInt("pe.idPersona");
                idLocalidad = rs.getInt("pe.Localidad_idLocalidad");
                idIdentificacion = rs.getInt("pe.Identificacion_idIdentificacion");
                matricula = rs.getString("p.matricula");
                medicoDto = new MedicoDto(idPersona,identificacionDao.buscar(idIdentificacion),nombre,apellido,localidadDao.buscar(idLocalidad),matricula);
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
        return medicoDto;

    }

    @Override
    public List<MedicoDto> listarPorCriterio(MedicoDto dto) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List<MedicoDto> lista = new ArrayList<>();
        try {
            con = conexion.getConnection();
            String sql = "select * from medico m "+
                    "join profesional p on m.Profesional_idProfesional=p.idProfesional "+
                    "join servicio s on p.Servicio_idServicio=s.idServicio " +
                    "join personal pr on p.Personal_idPersonal=pr.idPersonal " +
                    "join persona pe on pr.Persona_idPersona = pe.idPersona " +
                    "where s.tipo_servicio=?";

            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, dto.getServicio().getTipo_servicio());
            rs = sentencia.executeQuery();
            LocalidadDao localidadDao= new LocalidadDao();
            IdentificacionDao identificacionDao= new IdentificacionDao();
            String nombre, apellido, matricula;
            int   idPersona, idLocalidad, idIdentificacion;

            while (rs.next()){
                nombre = rs.getString("pe.nombre");
                apellido = rs.getString("pe.apellido");
                matricula = rs.getString("p.matricula");
                idPersona = rs.getInt("pe.idPersona");
                idLocalidad = rs.getInt("pe.Localidad_idLocalidad");
                idIdentificacion = rs.getInt("pe.Identificacion_idIdentificacion");


               MedicoDto medicoDto = new MedicoDto(idPersona,identificacionDao.buscar(idIdentificacion),nombre,apellido,localidadDao.buscar(idLocalidad),matricula);
               lista.add(medicoDto);

            }

        } catch (SQLException e) {
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
    public List<MedicoDto> listarTodos() {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertar(MedicoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar(MedicoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(MedicoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
