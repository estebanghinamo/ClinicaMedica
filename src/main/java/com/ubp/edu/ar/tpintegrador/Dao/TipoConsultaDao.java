package com.ubp.edu.ar.tpintegrador.Dao;

import com.ubp.edu.ar.tpintegrador.Dto.TipoConsultaDto;

import java.util.List;
import com.ubp.edu.ar.tpintegrador.Dto.ServicioDto;
import com.ubp.edu.ar.tpintegrador.Dto.TipoConsultaDto;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
public class TipoConsultaDao implements Dao<TipoConsultaDto>{
    private ConexionSql conexion = null;

    public TipoConsultaDao() {

        conexion = ConexionSql.getInstancia();
    }
    @Override
    public TipoConsultaDto buscar(TipoConsultaDto dto) {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public List<TipoConsultaDto> listarPorCriterio(TipoConsultaDto dto) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List<TipoConsultaDto> tiposConsultaLista = new ArrayList<>();
        try {
            con = conexion.getConnection();
            String sql = "SELECT * " + "FROM TipoConsulta tp " +
                    "join Servicio s on tp.Servicio_idServicio=s.idServicio " +
                    " WHERE s.tipo_servicio= ? ";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, dto.getServicio().getTipo_servicio());
            rs = sentencia.executeQuery();
            while (rs.next()) {
                int idTipoConsulta = rs.getInt("tp.idTipoConsulta");
                String tipoConsulta = rs.getString("tp.tipo_consulta");
                float costo = rs.getFloat("tp.costo");
                int idServicio = rs.getInt("s.idServicio");
                String tipo_servicio = rs.getString("s.tipo_servicio");
                TipoConsultaDto tipoConsultaDto = new TipoConsultaDto(idTipoConsulta, costo, tipoConsulta, new ServicioDto(idServicio, tipo_servicio));
                tiposConsultaLista.add(tipoConsultaDto);
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
        return tiposConsultaLista;
    }
        /**
         * @return
         */
    @Override
    public List<TipoConsultaDto> listarTodos() {
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;
        List<TipoConsultaDto> tiposConsulta = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "SELECT * "+"FROM TipoConsulta tp "+
                    "join Servicio s on tp.Servicio_idServicio=s.idServicio ";
            sentencia = con.createStatement();
            rs = sentencia.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("tp.idTipoConsulta");
                String tipoConsulta = rs.getString("tp.tipo_consulta");
                float costo = rs.getFloat("tp.costo");
                //int servicioId = rs.getInt("tp.Servicio_idServicio");
                int idServicio = rs.getInt("s.idServicio");
                String tipo_servicio = rs.getString("s.tipo_servicio");
                TipoConsultaDto tipoConsultaDto = new TipoConsultaDto(id, costo,tipoConsulta, new ServicioDto(idServicio,tipo_servicio));
                tiposConsulta.add(tipoConsultaDto);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (sentencia != null) sentencia.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return tiposConsulta;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean insertar(TipoConsultaDto dto) {
        return false;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean modificar(TipoConsultaDto dto) {
        return false;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean borrar(TipoConsultaDto dto) {
        return false;
    }
}
