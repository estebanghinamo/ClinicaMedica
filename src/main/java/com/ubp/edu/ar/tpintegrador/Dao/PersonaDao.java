/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dao;
import com.ubp.edu.ar.tpintegrador.Dto.*;
import com.ubp.edu.ar.tpintegrador.modelos.Identificacion;
import com.ubp.edu.ar.tpintegrador.modelos.Localidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class PersonaDao implements Dao<PersonaDto> {
    private ConexionSql conexion = null;

    public PersonaDao() {

        conexion = ConexionSql.getInstancia();
    }
    @Override
    public PersonaDto buscar(PersonaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PersonaDto> listarPorCriterio(PersonaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PersonaDto> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertar(PersonaDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        LocalidadDao localidadDao= new LocalidadDao();
        IdentificacionDao identificacionDao= new IdentificacionDao();
        if(!localidadDao.insertar(dto.getLocalidadDto()) ||
                !identificacionDao.insertar(dto.getIdentificacion())){
            JOptionPane.showMessageDialog(null, "error en insertar la localidad o error en insertar la identificacion.", "Error ", JOptionPane.ERROR_MESSAGE);

           return false;
        }else{
            try {
                con = conexion.getConnection();
                con.setAutoCommit(false);

                String sql = "insert into persona(idPersona, nombre, apellido, Localidad_idLocalidad, Identificacion_idIdentificacion) " +
                        "values ((select (max(p.idPersona)+1) from persona p),?,?, " +
                        "        (select (max(l.idLocalidad)) from localidad l), " +
                        "        (select (max(i.idIdentificacion)) from identificacion i))";
                sentencia = con.prepareStatement(sql);
                sentencia.setString(1,dto.getNombre());
                sentencia.setString(2,dto.getApellido());
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
    public boolean modificar(PersonaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(PersonaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
