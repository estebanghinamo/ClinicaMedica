package com.ubp.edu.ar.tpintegrador.Dao;

import com.ubp.edu.ar.tpintegrador.Dto.IdentificacionDto;
import com.ubp.edu.ar.tpintegrador.Dto.PacienteDto;
import com.ubp.edu.ar.tpintegrador.modelos.HistorialClinico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class IdentificacionDao implements Dao<IdentificacionDto>{
    private ConexionSql conexion = null;

    public IdentificacionDao() {
        conexion = ConexionSql.getInstancia();
    }
    /**
     * @param dto
     * @return
     */
    @Override
    public IdentificacionDto buscar(IdentificacionDto dto) {
        return null;
    }


    public IdentificacionDto buscar(int id) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        IdentificacionDto identificacion=null;
        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from  Identificacion i"
                    + " where i.idIdentificacion= ? ";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);
            rs = sentencia.executeQuery();

            String  tipo;
            double nroIdentificacion;
            int   idIdentificacion;
            rs.next();
            tipo = rs.getString("i.tipo");
            nroIdentificacion = rs.getDouble("i.nroIdentificacion");
            idIdentificacion = rs.getInt("i.idIdentificacion");
            identificacion = new IdentificacionDto(idIdentificacion,tipo,nroIdentificacion);

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return identificacion;
    }
    /**
     * @param dto
     * @return
     */
    @Override
    public List<IdentificacionDto> listarPorCriterio(IdentificacionDto dto) {
        return List.of();
    }

    /**
     * @return
     */
    @Override
    public List<IdentificacionDto> listarTodos() {
        return List.of();
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean insertar(IdentificacionDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            con.setAutoCommit(false);

            String sql = "insert into identificacion(idIdentificacion, tipo, nroIdentificacion) " +
                    "values ((select (max(i.idIdentificacion)+1) from identificacion i),?,?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1,dto.getTipo());
            sentencia.setDouble(2,dto.getNumero());
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

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean modificar(IdentificacionDto dto) {
        return false;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean borrar(IdentificacionDto dto) {
        return false;
    }
}