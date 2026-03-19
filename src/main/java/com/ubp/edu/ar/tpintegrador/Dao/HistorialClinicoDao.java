/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubp.edu.ar.tpintegrador.Dao;
import com.ubp.edu.ar.tpintegrador.Dto.*;
import com.ubp.edu.ar.tpintegrador.modelos.DetallesClinico;
import com.ubp.edu.ar.tpintegrador.modelos.HistorialClinico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class HistorialClinicoDao implements Dao<HistorialClinicoDto> {
    private ConexionSql conexion = null;

    public HistorialClinicoDao() {

        conexion = ConexionSql.getInstancia();
    }
    @Override
    public HistorialClinicoDto buscar(HistorialClinicoDto dto) {
        Connection con;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List<DetallesClinicosDto> lista = new ArrayList<>();
        LocalidadDao localidadDao=new LocalidadDao();
        IdentificacionDao identificacionDao=new IdentificacionDao();
        try {
            con = conexion.getConnection();
            String sql = "select * "+" from detallesclinicos dc " +
                    "join historiaclinica hc on dc.HistoriaClinica_idHistoriaClinica = hc.idHistoriaClinica " +
                    "join ordenconsulta oc on dc.OrdenConsulta_idOrdenConsulta = oc.idOrdenConsulta "+
                    "join paciente p on oc.Paciente_idPaciente=p.idPaciente "+
                    "join persona per on p.Persona_idPersona=per.idPersona "+
                    "join localidad l on per.Localidad_idLocalidad=l.idLocalidad "+
                    "join identificacion i on per.Identificacion_idIdentificacion=i.idIdentificacion " +
                    "join TipoConsulta tp on oc.TipoConsulta_idTipoConsulta= tp.idTipoConsulta " +
                    "join servicio s on tp.Servicio_idServicio = s.idServicio "+
                    "where hc.nro_historial=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1,dto.getNumero_historial());
            rs = sentencia.executeQuery();
            while (rs.next()) {
                int idOrden=rs.getInt("oc.idOrdenConsulta");
                int nroOrden=rs.getInt("oc.nro_orden");
                String nombre=rs.getString("per.nombre");
                String apellido=rs.getString("per.apellido");
                int idLocalidad = rs.getInt("per.Localidad_idLocalidad");
                int  idIdentificacion = rs.getInt("per.Identificacion_idIdentificacion");
                int idPersona = rs.getInt("per.idPersona");
                int idDetalle = rs.getInt("dc.idDetallesClinicos");
                int nroDetalle=rs.getInt("dc.nroDetalleClinico");
                int idTipoConsulta=rs.getInt("tp.idTipoConsulta");
                int costo=rs.getInt("tp.costo");
                String tipoConsulta=rs.getString("tp.tipo_consulta");
                int idServicio= rs.getInt("s.idServicio");
                String nombreServicio= rs.getString("s.tipo_servicio");
                PacienteDto pacienteDTO=new PacienteDto(idPersona,nombre,apellido,localidadDao.buscar(idLocalidad),identificacionDao.buscar(idIdentificacion));

                OrdenConsultaDto ordenConsultaDto=new OrdenConsultaDto(idOrden,pacienteDTO,nroOrden, new TipoConsultaDto(idTipoConsulta,costo,tipoConsulta,new ServicioDto(idServicio,nombreServicio)));

                OrdenConsultaDao dao = new OrdenConsultaDao();
                OrdenConsultaDto ordenConsultaDto1 = new OrdenConsultaDto();
                ordenConsultaDto1.setNumeroOrden(ordenConsultaDto.getNumeroOrden());
                ordenConsultaDto1=dao.buscar(ordenConsultaDto1);
                ordenConsultaDto.setDetalle(ordenConsultaDto1.getDetalle());
               DetallesClinicosDto detallesClinicosDtos =new DetallesClinicosDto(idDetalle,nroDetalle,ordenConsultaDto);

                lista.add(detallesClinicosDtos);
            }
                dto.setDetallesClinicosDto(lista);


        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
                this.conexion.closeConnection();
            } catch (Exception ex) {

            }
        }
        return dto;
    }

    @Override
    public List<HistorialClinicoDto> listarPorCriterio(HistorialClinicoDto dto) {

        List<HistorialClinicoDto> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        try {
            con = this.conexion.getConnection();
            String sql = "SELECT * FROM historiaclinica hc " +
                    "join detallesclinicos dc on hc.idHistoriaClinica=dc.HistoriaClinica_idHistoriaClinica " +
                    "join ordenconsulta oc on dc.OrdenConsulta_idOrdenConsulta=oc.idOrdenConsulta "+
                    "JOIN Paciente p ON oc.Paciente_idPaciente = p.idPaciente " +
                    "JOIN persona per ON p.Persona_idPersona=per.idPersona "+
                    "JOIN identificacion i ON per.Identificacion_idIdentificacion = i.idIdentificacion " +
                    "WHERE i.nroIdentificacion = ? limit 1";
            sentencia = con.prepareStatement(sql);
            sentencia.setDouble(1, dto.getDetallesClinicosDto().get(0).getOrden().getPaciente().getIdentificacion().getNumero());
            rs = sentencia.executeQuery();


            while (rs.next()) {

                int NroHistorial=rs.getInt("hc.nro_historial");
                int idhc=rs.getInt("hc.idHistoriaClinica");

                HistorialClinicoDto historialClinicoDto = new HistorialClinicoDto(NroHistorial,idhc);
                lista.add(historialClinicoDto);
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

    @Override
    public List<HistorialClinicoDto> listarTodos() {

        Connection con;
        Statement sentencia = null;
        ResultSet rs = null;
        List<HistorialClinicoDto> lista = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "select * "+" from historiaclinica hc ";
            sentencia = con.createStatement();
            rs = sentencia.executeQuery(sql);
            while (rs.next()) {

                int NroHistorial=rs.getInt("hc.nro_historial");
                int idhc=rs.getInt("hc.idHistoriaClinica");

                HistorialClinicoDto historialClinicoDto = new HistorialClinicoDto(NroHistorial,idhc);
                lista.add(historialClinicoDto);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
                this.conexion.closeConnection();
            } catch (Exception ex) {

            }
        }
        return lista;

    }

    @Override
    public boolean insertar(HistorialClinicoDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            con.setAutoCommit(false);

            String sql = "insert into historiaclinica(idHistoriaClinica, nro_historial) " +
                    "values((select IF((max(hc.idHistoriaClinica) + 1) is null, 1, (max(hc.idHistoriaClinica) + 1)) " +
                    "from historiaclinica hc), " +
                    "       (select IF((max(hc.nro_historial) + 1) is null, 1, (max(hc.nro_historial) + 1)) " +
                    "from historiaclinica hc))";
            sentencia = con.prepareStatement(sql);
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
    public boolean insertarDetalles(HistorialClinicoDto dto) {
        Connection con=null;
        PreparedStatement sentencia = null;
        try {
            con = conexion.getConnection();
            con.setAutoCommit(false);
            String sql ="insert into detallesclinicos" +
                    "(idDetallesClinicos, HistoriaClinica_idHistoriaClinica, OrdenConsulta_idOrdenConsulta, nroDetalleClinico) " +
                    "values ((select IF((max(dc.idDetallesClinicos) + 1) is null, 1, (max(dc.idDetallesClinicos) + 1)) " +
                    "         from detallesclinicos dc)," +
                    "       (select idHistoriaClinica from historiaclinica hc where hc.nro_historial=?)," +
                    "       (select idOrdenConsulta from ordenconsulta oc where oc.nro_orden=?)," +
                    "       (select IF((max(dc.nroDetalleClinico) + 1) is null, 1, (max(dc.nroDetalleClinico) + 1)) " +
                    "         from detallesclinicos dc))";

            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1,dto.getNumero_historial());
            sentencia.setInt(2,dto.getDetallesClinicosDto().get((dto.getDetallesClinicosDto().size())-1).getOrden().getNumeroOrden());
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
    public boolean modificar(HistorialClinicoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(HistorialClinicoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
