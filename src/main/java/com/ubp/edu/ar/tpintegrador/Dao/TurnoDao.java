package com.ubp.edu.ar.tpintegrador.Dao;

import com.ubp.edu.ar.tpintegrador.Dto.TurnoDto;

import java.sql.*;
import java.time.LocalTime;
import java.util.List;

public class TurnoDao implements Dao<TurnoDto> {
    private ConexionSql conexion = null;

    public TurnoDao() {

        conexion = ConexionSql.getInstancia();
    }
    @Override
    public TurnoDto buscar(TurnoDto dto) {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public List<TurnoDto> listarPorCriterio(TurnoDto dto) {
        return List.of();
    }

    /**
     * @return
     */
    @Override
    public List<TurnoDto> listarTodos() {
        return List.of();
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean insertar(TurnoDto dto) {
        Connection con= null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        LocalTime now = LocalTime.now();
        Time hora = Time.valueOf(now);

        try {
            con = conexion.getConnection();
            con.setAutoCommit(false);

            String sql = "insert into turno (idTurno, nro_turno, fecha, hora, FranjaHoraria_idFranjaHoraria) "
                    + "values((select IF((max(t.idTurno) + 1) is null, 1, (max(t.idTurno) + 1)) " +
                    "    from turno t)," +
                    "(select IF((max(t.nro_turno) + 1) is null, 1, (max(t.nro_turno) + 1)) " +
                    "     from turno t)," +
                    "?,?,?)";
            sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setDate(1,  dto.getFecha());
            sentencia.setTime(2,hora);
            //Franja Horaria es un String
            sentencia.setInt(3,1);
            int resultado = sentencia.executeUpdate();
            if (resultado <= 0) {
                con.rollback();
                return false;
            }
            con.commit();
            return true;
        } catch (SQLException e) {
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
    public boolean modificar(TurnoDto dto) {
        return false;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean borrar(TurnoDto dto) {
        return false;
    }
}
