/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dao;
import com.ubp.edu.ar.tpintegrador.Dto.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class ServicioDao implements Dao<ServicioDto> {
    private ConexionSql conexion = null;

    public ServicioDao() {

        conexion = ConexionSql.getInstancia();
    }
    @Override
    public ServicioDto buscar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ServicioDto> listarPorCriterio(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ServicioDto> listarTodos() {
        Connection con=null;
        Statement sentencia = null;
        ResultSet rs = null;
        List<ServicioDto> lista = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "SELECT * "+ "FROM servicio s";

            sentencia = con.createStatement();

            rs = sentencia.executeQuery(sql);

            int id;
            String nombre;
            while (rs.next()) {

                id=rs.getInt("s.idServicio");
                nombre=rs.getString("s.tipo_servicio");
                ServicioDto servicioDto = new ServicioDto(id, nombre);
                lista.add(servicioDto);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (Exception ex) {

            }
        }
        return lista;
    }

    @Override
    public boolean insertar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}