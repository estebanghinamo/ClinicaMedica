package com.ubp.edu.ar.tpintegrador.Dao;

import com.ubp.edu.ar.tpintegrador.Dto.IdentificacionDto;
import com.ubp.edu.ar.tpintegrador.Dto.LocalidadDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalidadDao implements Dao<LocalidadDto>{
    private ConexionSql conexion = null;

    public LocalidadDao() {
        conexion = ConexionSql.getInstancia();
    }
    /**
     * @param dto
     * @return
     */
    @Override
    public LocalidadDto buscar(LocalidadDto dto) {
        return null;
    }


    public LocalidadDto buscar(int id) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        LocalidadDto localidad=null;
        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Localidad l"
                    + " where l.idLocalidad= ? ";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);
            rs = sentencia.executeQuery();

            String pais,provincia,ciudad,calle;
            int   idLocalidad,numero;
            rs.next();
            pais = rs.getString("l.pais");
            provincia = rs.getString("l.provincia");
            ciudad = rs.getString("l.ciudad");
            calle = rs.getString("l.calle");
            numero = rs.getInt("l.numero");
            idLocalidad = rs.getInt("l.idLocalidad");
            localidad = new LocalidadDto(idLocalidad,pais,provincia,ciudad,calle,numero);

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
        return localidad;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public List<LocalidadDto> listarPorCriterio(LocalidadDto dto) {
        return List.of();
    }

    /**
     * @return
     */
    @Override
    public List<LocalidadDto> listarTodos() {
        return List.of();
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean insertar(LocalidadDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            con.setAutoCommit(false);

            String sql = "insert into localidad(idLocalidad, pais, provincia, ciudad, calle, numero)" +
                    "values ((select (max(l.idLocalidad)+1) from localidad l),?,?,?,?,?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1,dto.getPais());
            sentencia.setString(2,dto.getProvincia());
            sentencia.setString(3,dto.getCiudad());
            sentencia.setString(4,dto.getCalle());
            sentencia.setInt(5,dto.getNumero());
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
    public boolean modificar(LocalidadDto dto) {
        return false;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean borrar(LocalidadDto dto) {
        return false;
    }
}