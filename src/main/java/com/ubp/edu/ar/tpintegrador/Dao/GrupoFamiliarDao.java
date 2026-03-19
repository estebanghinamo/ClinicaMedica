package com.ubp.edu.ar.tpintegrador.Dao;

import com.ubp.edu.ar.tpintegrador.Dto.GrupoFamiliarDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



/**
 *
 */
public class GrupoFamiliarDao implements Dao<GrupoFamiliarDto> {
    private ConexionSql conexion = null;

    public GrupoFamiliarDao() {

        conexion = ConexionSql.getInstancia();
    }

    @Override
    public GrupoFamiliarDto buscar(GrupoFamiliarDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GrupoFamiliarDto> listarPorCriterio(GrupoFamiliarDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GrupoFamiliarDto> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertar(GrupoFamiliarDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            con.setAutoCommit(false);

            String sql = "insert into grupofamiliar(idGrupoFamiliar, dniJefeFamiliar)" +
                    "values ((select (max(gf.idGrupoFamiliar)+1) from grupofamiliar gf),?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setDouble(1,dto.getDni_jefe_fam());
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

    @Override
    public boolean modificar(GrupoFamiliarDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(GrupoFamiliarDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}