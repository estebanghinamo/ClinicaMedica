package com.ubp.edu.ar.tpintegrador.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.ubp.edu.ar.tpintegrador.Dto.*;
import com.ubp.edu.ar.tpintegrador.modelos.DetalleOrdenConsulta;

public class OrdenConsultaDao implements Dao<OrdenConsultaDto> {
    private ConexionSql conexion = null;

    public OrdenConsultaDao() {

        conexion = ConexionSql.getInstancia();
    }


    /**
     * @param dto
     * @return
     */
    @Override
    public OrdenConsultaDto buscar(OrdenConsultaDto dto) {
        Connection con;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        OrdenConsultaDto ordenConsultaDto = null;
        MedicoDao medicoDao = new MedicoDao();
        try {
            List<DetalleOrdenConsultaDto> lista = new ArrayList<>();
            con = conexion.getConnection();
            String sql = "select * " +
                    "from OrdenConsulta oc " +
                    "join detalleordenconsulta do on oc.idOrdenConsulta = do.OrdenConsulta_idOrdenConsulta " +
                    "LEFT JOIN practica pra on do.Practica_id_practica = pra.id_practica " +
                    "join medico m on do.Medico_idMedico = m.idMedico " +
                    "join profesional pr on m.Profesional_idProfesional = pr.idProfesional " +
                    "join personal pe on pr.Personal_idPersonal = pe.idPersonal " +
                    "join persona p on pe.Persona_idPersona = p.idPersona " +
                    "join servicio s on pr.Servicio_idServicio = s.idServicio"+
                    " where oc.nro_orden=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, dto.getNumeroOrden());
            rs = sentencia.executeQuery();

            String diagnostico, observacion, tipoServicio,nombrePractica;
            int idDetalleOrdenConsulta, idMedico, idServicio;
            float costo;
            while (rs.next()) {
            diagnostico = rs.getString("do.diagnostico");
                observacion = rs.getString("do.observacion");
                costo = rs.getFloat("pra.costo");
                idMedico = rs.getInt("m.idMedico");
                idDetalleOrdenConsulta = rs.getInt("do.idDetalleOrdenConsulta");
                idServicio = rs.getInt("s.idServicio");
                tipoServicio = rs.getString("s.tipo_Servicio");
                nombrePractica = rs.getString("pra.nombrePractica");

                DetalleOrdenConsultaDto detalleOrdenConsultaDto = new DetalleOrdenConsultaDto(idDetalleOrdenConsulta,new PracticaDto(costo,new ServicioDto(idServicio,tipoServicio),nombrePractica),medicoDao.buscar(idMedico),diagnostico,observacion);
                lista.add(detalleOrdenConsultaDto);
            }
            dto.setDetalle(lista);

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (Exception ex) {

            }
        }
        return dto;
    }
    public OrdenConsultaDto buscarOrden(OrdenConsultaDto dto) {
        Connection con;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        try {
            con = this.conexion.getConnection();
            String sql = "SELECT * FROM OrdenConsulta oc " +
                    "JOIN Paciente p ON oc.Paciente_idPaciente = p.idPaciente " +
                    "JOIN persona per ON p.Persona_idPersona=per.idPersona "+
                    "JOIN identificacion i ON per.Identificacion_idIdentificacion = i.idIdentificacion " +
                    "join turno t on oc.Turno_idTurno=t.idTurno "+
                    "join TipoConsulta tp on oc.TipoConsulta_idTipoConsulta= tp.idTipoConsulta "
                    +"WHERE oc.nro_orden=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setDouble(1,dto.getNumeroOrden());
            rs = sentencia.executeQuery();

            while (rs.next()) {
                PacienteDao pacienteDao= new PacienteDao();
                int idOrden=rs.getInt("oc.idOrdenConsulta");
                int numeroOrden=rs.getInt("oc.nro_orden");
                int idTurno=rs.getInt("t.idTurno");
                int numTurno=rs.getInt("t.nro_turno");
                Date fecha=rs.getDate("t.fecha");
                Time hora=rs.getTime("t.hora");
                int idTipoConsulta=rs.getInt("tp.idTipoConsulta");
                int costo=rs.getInt("tp.costo");
                String tipoConsulta=rs.getString("tp.tipo_consulta");
                int idPaciente=rs.getInt("p.idPaciente");

                dto.setId(idOrden);
                dto.setNumeroOrden(numeroOrden);
                dto.setTurno(new TurnoDto(idTurno,numTurno,fecha,hora));
                dto.setTipo(new TipoConsultaDto(idTipoConsulta,costo,tipoConsulta));
                dto.setPaciente(pacienteDao.buscar(idPaciente));
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
        return dto;
    }


    /**
     * @param dto
     * @return
     */
    @Override
    public List<OrdenConsultaDto> listarPorCriterio(OrdenConsultaDto dto) {
        List<OrdenConsultaDto> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        try {
            con = this.conexion.getConnection();
            String sql = "SELECT * FROM OrdenConsulta oc " +
                    "JOIN Paciente p ON oc.Paciente_idPaciente = p.idPaciente " +
                    "JOIN persona per ON p.Persona_idPersona=per.idPersona "+
                    "JOIN identificacion i ON per.Identificacion_idIdentificacion = i.idIdentificacion " +
                    "join turno t on oc.Turno_idTurno=t.idTurno "+
                    "join TipoConsulta tp on oc.TipoConsulta_idTipoConsulta= tp.idTipoConsulta "
                    +"WHERE i.nroIdentificacion = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setDouble(1, dto.getPaciente().getIdentificacion().getNumero());
            rs = sentencia.executeQuery();

            while (rs.next()) {
                PacienteDao pacienteDao= new PacienteDao();
                int idOrden=rs.getInt("oc.idOrdenConsulta");
                int numeroOrden=rs.getInt("oc.nro_orden");
                int idTurno=rs.getInt("t.idTurno");
                int numTurno=rs.getInt("t.nro_turno");
                Date fecha=rs.getDate("t.fecha");
                Time hora=rs.getTime("t.hora");
                int idTipoConsulta=rs.getInt("tp.idTipoConsulta");
                int costo=rs.getInt("tp.costo");
                String tipoConsulta=rs.getString("tp.tipo_consulta");
                int idPaciente=rs.getInt("p.idPaciente");

                OrdenConsultaDto ordenConsultaDto = new OrdenConsultaDto(idOrden, numeroOrden,
                        new TurnoDto(idTurno,numTurno,fecha,hora),
                        new TipoConsultaDto(idTipoConsulta,costo,tipoConsulta),
                        pacienteDao.buscar(idPaciente));
                lista.add(ordenConsultaDto);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
                conexion.closeConnection();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return lista;
    }
    /**
     * @return
     */
    @Override
    public List<OrdenConsultaDto> listarTodos() {
        Connection con;
        Statement sentencia = null;
        ResultSet rs = null;
        List<OrdenConsultaDto> lista = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "select * " +
                    "from OrdenConsulta oc " +
                    "join Turno t on Turno_idTurno=idTurno "+
                    "join TipoConsulta tc on TipoConsulta_idTipoConsulta=idTipoConsulta ";
            sentencia = con.createStatement();

            rs = sentencia.executeQuery(sql);


            int idOrden, numeroOrden, idPaciente,idTurno,idTipoConsulta,numTurno;
            Date fecha;
            Time hora;
            float costo;
            String franjaHoraria, tipoConsulta;
            while (rs.next()) {
                PacienteDao pacienteDao= new PacienteDao();
                idOrden=rs.getInt("oc.idOrdenConsulta");
                numeroOrden=rs.getInt("oc.nro_orden");
                idPaciente=rs.getInt("oc.Paciente_idPaciente");
                idTurno=rs.getInt("t.idTurno");
                numTurno=rs.getInt("t.nro_turno");
                fecha=rs.getDate("t.fecha");
                hora=rs.getTime("t.hora");
                idTipoConsulta=rs.getInt("tc.idTipoConsulta");
                tipoConsulta=rs.getString("tc.tipo_consulta");
                costo=rs.getFloat("tc.costo");

               OrdenConsultaDto ordenConsultaDto = new OrdenConsultaDto(idOrden, numeroOrden, new TurnoDto(idTurno,numTurno,fecha,hora), new TipoConsultaDto(idTipoConsulta,costo,tipoConsulta),pacienteDao.buscar(idPaciente));
                lista.add(ordenConsultaDto);

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

    /**
     * @return
     */

    @Override
    public boolean insertar(OrdenConsultaDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        try {
            TurnoDao turnoDao = new TurnoDao();
            if(!turnoDao.insertar(dto.getTurno())){
                System.out.println("error en la creacion del turno");

            }


            con = conexion.getConnection();
            con.setAutoCommit(false);

            String sql = "insert into ordenconsulta (idOrdenConsulta, nro_orden, Turno_idTurno, TipoConsulta_idTipoConsulta, Paciente_idPaciente, Caja_idCaja) "
                    + "values((select (max(o.idOrdenConsulta)+1) from ordenconsulta o)," +
                    "?,(select (max(t.idTurno)) from turno t)," +
                    "(select ti.idTipoConsulta from tipoconsulta ti where ti.tipo_consulta like ?),(select p.idPaciente from paciente p " +
                        "join persona pe on p.Persona_idPersona = pe.idPersona " +
                        "join identificacion i on pe.Identificacion_idIdentificacion = i.idIdentificacion " +
                            "where i.nroIdentificacion like ?)," +
                    "?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1,dto.getNumeroOrden());
            sentencia.setString(2,dto.getTipo().getTipo());
            sentencia.setDouble(3,dto.getPaciente().getIdentificacion().getNumero());
            //numero 1 por defecto para la caja
            sentencia.setInt(4,1);
            int resultado = sentencia.executeUpdate();
            if (resultado <= 0) {
                System.out.println("malio sal");
                con.rollback();
                return false;
            }
            String sql1="insert into detalleordenconsulta (idDetalleOrdenConsulta, OrdenConsulta_idOrdenConsulta, Practica_id_practica, " +
                    "diagnostico, Medico_idMedico, observacion) values" +
                    "((select IF((max(doc.idDetalleOrdenConsulta) + 1) is null, 0, (max(doc.idDetalleOrdenConsulta) + 1)) " +
                    "from detalleordenconsulta doc)," +
                    "(select max(oc.idOrdenConsulta) from ordenconsulta oc),null,null," +
                    "(select m.idMedico from medico m " +
                    "join profesional pr on m.Profesional_idProfesional = pr.idProfesional " +
                    "join personal pl on pr.Personal_idPersonal = pl.idPersonal " +
                    "join persona p on pl.Persona_idPersona = p.idPersona " +
                    "where p.nombre = ?)" +
                    ",null)";
            sentencia = con.prepareStatement(sql1);
            sentencia.setString(1,dto.getDetalle().get(0).getMedico().getNombre());
            int resultado1 = sentencia.executeUpdate();
            if (resultado1 <= 0) {
                System.out.println("malio sal");
                con.rollback();
                return false;
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("este catch");
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
    public boolean insertarNuevoDetalle(OrdenConsultaDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        try {


            con = conexion.getConnection();
            con.setAutoCommit(false);

            String sql="insert into detalleordenconsulta (idDetalleOrdenConsulta, OrdenConsulta_idOrdenConsulta, Practica_id_practica, " +
                    "diagnostico, Medico_idMedico, observacion) values" +
                    "((select IF((max(doc.idDetalleOrdenConsulta) + 1) is null, 1, (max(doc.idDetalleOrdenConsulta) + 1)) " +
                    "from detalleordenconsulta doc)," +
                    "(select oc.idOrdenConsulta from ordenconsulta oc where nro_orden = ?),null,?," +
                    "(select m.idMedico from medico m " +
                    "join profesional pr on m.Profesional_idProfesional = pr.idProfesional " +
                    "join personal pl on pr.Personal_idPersonal = pl.idPersonal " +
                    "join persona p on pl.Persona_idPersona = p.idPersona " +
                    "where p.nombre = ?)" +
                    ",null)";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1,dto.getNumeroOrden());
            sentencia.setString(2,dto.getDetalle().get((dto.getDetalle().size()-1)).getDiagnostico());
            sentencia.setString(3,dto.getDetalle().get((dto.getDetalle().size()-1)).getMedico().getNombre());
            int resultado = sentencia.executeUpdate();
            if (resultado <= 0) {
                System.out.println("malio sal");
                con.rollback();
                return false;
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("este catch");
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
    public int obtenerUltimoNumeroOrden() {
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;
        int ultimoNumero = 0;

        try {
            con = conexion.getConnection();
            String sql = "SELECT MAX(oc.nro_Orden) AS maxNro " + " FROM ordenConsulta oc";
            sentencia = con.createStatement();
            rs = sentencia.executeQuery(sql);

            if (rs.next()) {
                ultimoNumero = rs.getInt("maxNro");
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
        return ultimoNumero;
    }
    /**
     * @param dto
     * @return
     */
    @Override
    public boolean modificar(OrdenConsultaDto dto) {
        return false;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public boolean borrar(OrdenConsultaDto dto) {
        Connection con = null;
        PreparedStatement sentencia = null;
        PreparedStatement sentenciaDetalle = null;

        try {
            con = this.conexion.getConnection();
            con.setAutoCommit(false);

            // Primero, borramos los detalles asociados a la orden de consulta
            String sqlDetalle = "DELETE FROM detalleOrdenConsulta " +
                    "WHERE OrdenConsulta_idOrdenConsulta = (SELECT idOrdenConsulta FROM ordenconsulta WHERE nro_orden = ?)";
            sentenciaDetalle = con.prepareStatement(sqlDetalle);
            sentenciaDetalle.setInt(1, dto.getNumeroOrden());
            int resultadoDetalle = sentenciaDetalle.executeUpdate();

            // Si no hay errores, procedemos a borrar la orden de consulta
            String sql = "DELETE FROM ordenconsulta WHERE nro_orden = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, dto.getNumeroOrden());
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

                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            try {
                if (sentencia != null) sentencia.close();
                if (sentenciaDetalle != null) sentenciaDetalle.close();
                this.conexion.closeConnection();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }
}
