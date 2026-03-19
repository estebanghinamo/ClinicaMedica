package com.ubp.edu.ar.tpintegrador.controladores;


import com.ubp.edu.ar.tpintegrador.App;
import com.ubp.edu.ar.tpintegrador.modelos.Medico;
import com.ubp.edu.ar.tpintegrador.modelos.OrdenConsulta;
import com.ubp.edu.ar.tpintegrador.modelos.Servicio;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class MedicoControlador implements Initializable {

    public Button BTNAtrasM;
    public TextField TFBuscadorM;
    public Button BTNBuscarA;
    public Button BTNRegDiagM;
    public TableView tableView;
    public TableColumn columnNro;
    public TableColumn columnNombre;
    public TableColumn columnDni;
    public TextField txtMatricula;
    public TextField txtDni;


    private ObservableList<OrdenConsulta> datos = null;
    private OrdenConsulta ordenConsulta = new OrdenConsulta();
    private List<OrdenConsulta> lista = new ArrayList<>();
    public ComboBox cmbServicio;
    private Servicio servicio= new Servicio();
    private ObservableList<Servicio> datoscmbServicio;
    public ComboBox cmbMedico;
    private ObservableList<Medico> datoscmbMedico;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.tableView != null) {
            this.configureTable();
            this.loadData();
        }
    }

    private void loadData() {
        List<Servicio> servicios= this.servicio.listarTodos();
        this.datoscmbServicio = FXCollections.observableList(servicios);
        this.cmbServicio.setItems(this.datoscmbServicio);
        lista = this.ordenConsulta.listarTodos();
        this.datos = FXCollections.observableList(lista);
        this.tableView.setItems(datos);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void configureTable() {
        TableColumn<OrdenConsulta, Integer> tcNro = (TableColumn<OrdenConsulta, Integer>) this.tableView.getColumns().get(0);
        tcNro.setCellValueFactory(new PropertyValueFactory<>("numeroOrden"));
        TableColumn<OrdenConsulta, String> tcNombreCliente = (TableColumn<OrdenConsulta, String>) this.tableView.getColumns().get(1);
        tcNombreCliente.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getPaciente().getNombre());
        });
        TableColumn<OrdenConsulta, Long> tcDni = (TableColumn<OrdenConsulta, Long>) this.tableView.getColumns().get(2);
        tcDni.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>( cellData.getValue().getPaciente().getIdentificacion().getNumero());
        });
    }

    @FXML
    protected void opcRegistrarDiagnostico(){
        OrdenConsulta orden = (OrdenConsulta) this.tableView.getSelectionModel().getSelectedItem();

        if (orden != null) {
            try {
                orden.buscarDetalles();
                FXMLLoader loader = App.openFXML("Diagnostico", "Registrar diagnostico", Modality.APPLICATION_MODAL);
                DiagnosticoControlador controller = loader.getController();
                controller.passData(orden, this);
            } catch (IOException ex) {
                System.out.println("catch verOrden");
            }
        } else {
            JOptionPane.showMessageDialog(null, "orden no seleccionada.", "Acción no permitida", JOptionPane.INFORMATION_MESSAGE);


        }
    }

    @FXML
    protected void opcAtras(){
        try {
            App.openFXML("Principal", " ", Modality.APPLICATION_MODAL);
        } catch (IOException ex) {

            System.out.println("catch");
        }
    }

    public void opcBuscar(ActionEvent actionEvent) {
        long dni= Long.parseLong(this.txtDni.getText());
        Medico medico= (Medico) this.cmbMedico.getValue();

        List<OrdenConsulta> ordenConsultas = new ArrayList<>();
        for (OrdenConsulta oc : lista)
        {   oc.buscarDetalles();
            if (oc.getPaciente().getIdentificacion().getNumero()==dni &&
                    Objects.equals(oc.getDetalles().get(0).getMedico().getNombre(), medico.getNombre()))
            {
                ordenConsultas.add(oc);
            }

        }
        this.datos = FXCollections.observableList(ordenConsultas);
        this.tableView.setItems(datos);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void opcServicio(ActionEvent actionEvent) {
        Servicio servicio= (Servicio) this.cmbServicio.getValue();
        Medico medico = new Medico();
        medico.setServicio(servicio);
        List<Medico> medicos= medico.ListarMedicosPorServicio(medico);
        this.datoscmbMedico = FXCollections.observableList(medicos);
        this.cmbMedico.setItems(this.datoscmbMedico);

    }
}
