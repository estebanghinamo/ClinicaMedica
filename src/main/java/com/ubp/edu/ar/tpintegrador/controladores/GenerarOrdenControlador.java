package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.App;
import com.ubp.edu.ar.tpintegrador.Dao.Dao;
import com.ubp.edu.ar.tpintegrador.Dao.OrdenConsultaDao;
import com.ubp.edu.ar.tpintegrador.modelos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GenerarOrdenControlador implements Initializable {

    public Button btnBuscar;
    private TipoConsulta tipoConsulta;
    private Servicio servicio;
    private Turno turno;
    private Paciente paciente = new Paciente();
    private Medico medico;
    private OrdenConsulta ordenConsulta = new OrdenConsulta();
    @FXML
    public TextField txtNroOrden;
    @FXML
    public Button BTNConfirmarGO;
    @FXML
    public Button BTNCancelarGO;
    @FXML
    public TextField txtDni;
    @FXML
    public TextField txtNombre;
    @FXML
    public ComboBox<Servicio> cmbServicio;
    private ObservableList<Servicio> datoscmbServicio ;
    @FXML
    public ComboBox<Medico> cmbMedico;
    private ObservableList<Medico> datoscmbMedico = null;
    @FXML
    public ComboBox<TipoConsulta> cmbTipoConsulta;
    private ObservableList<TipoConsulta> datoscmbTipoConsulta = null;
    @FXML
    public TextField txtCosto;
    @FXML
    public TextField txtApellido;
    private OrdenConsultaDao dao = new OrdenConsultaDao();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        servicio=new Servicio();
        medico=new Medico();
        tipoConsulta=new TipoConsulta();
        this.loadData();
    }

    public void loadData(){
        List<Servicio> servicios= this.servicio.listarTodos();

        List<TipoConsulta> tipoConsultas= this.tipoConsulta.listarTodos();
        this.datoscmbServicio = FXCollections.observableList(servicios);
        this.cmbServicio.setItems(this.datoscmbServicio);
        OrdenConsulta ordenConsulta1 = new OrdenConsulta();
        this.txtNroOrden.setText(String.valueOf(ordenConsulta1.nroSigOrden()+1));

    }


    @FXML
    protected void opcRegistrarPaciente(){
        try {
            App.openFXML("RegistrarPaciente", "Consulta de ordenes", Modality.APPLICATION_MODAL);
        } catch (IOException ex) {

            System.out.println("catch");
        }
    }

    public void opcTipoConsulta(ActionEvent actionEvent) {

        this.txtCosto.setText(String.valueOf(this.cmbTipoConsulta.getValue().getCosto()));
    }
    public void opcServicio(ActionEvent actionEvent) {
        Servicio servicio= this.cmbServicio.getValue();
        TipoConsulta tipoConsulta = new TipoConsulta();
        Medico medico = new Medico();
        medico.setServicio(servicio);
        List<Medico> medicos= medico.ListarMedicosPorServicio(medico);
        this.datoscmbMedico = FXCollections.observableList(medicos);
        this.cmbMedico.setItems(this.datoscmbMedico);
        tipoConsulta.setServicio(servicio);
        List<TipoConsulta> tipoConsultas= tipoConsulta.filtrarPorServicio(tipoConsulta);
        this.datoscmbTipoConsulta = FXCollections.observableList(tipoConsultas);
        this.cmbTipoConsulta.setItems(this.datoscmbTipoConsulta);

    }


    public void opcBuscarDni(ActionEvent actionEvent) {
        int dni;
        dni= Integer.parseInt(this.txtDni.getText());
        paciente=paciente.buscarDni(dni);
        if(paciente==null)
        {
            JOptionPane.showMessageDialog(null, "No se encontró un paciente con el DNI ingresado.", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.txtNombre.setText(paciente.getNombre());
            this.txtApellido.setText(paciente.getApellido());
        }


    }



    public void opcConfirmarOrden(ActionEvent actionEvent) {
        Turno turno = new Turno();
        turno.setFecha(Date.valueOf(LocalDate.now()));
        this.ordenConsulta.setNumeroOrden(Integer.parseInt(this.txtNroOrden.getText()));
        this.ordenConsulta.setPaciente(paciente);
        this.ordenConsulta.setTipo(this.cmbTipoConsulta.getValue());
        this.ordenConsulta.setTurno(turno);
        List<DetalleOrdenConsulta> lista = new ArrayList<>();
        DetalleOrdenConsulta detalleOrdenConsulta = new DetalleOrdenConsulta(this.cmbMedico.getValue());
        lista.add(detalleOrdenConsulta);
        this.ordenConsulta.setDetalles(lista);

        this.ordenConsulta.guardar();
        Stage stage = (Stage) BTNConfirmarGO.getScene().getWindow();
        JOptionPane.showMessageDialog(null, "Orden creada.", "Acción exitosa", JOptionPane.INFORMATION_MESSAGE);
        stage.close();


    }

    public void opcCancelarOrden(ActionEvent actionEvent) {
        Stage stage = (Stage) BTNCancelarGO.getScene().getWindow();
        stage.close();
    }



    /**
     * @param url
     * @param resourceBundle
     */

}
