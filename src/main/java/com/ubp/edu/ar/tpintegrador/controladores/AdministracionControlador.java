package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.App;
import com.ubp.edu.ar.tpintegrador.modelos.*;
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
import java.util.ResourceBundle;

public class AdministracionControlador implements Initializable {
    public Button BTNRatificarA;
    public Button BTNBuscarA;
    public TextField TFBuscadorA;
    public TableColumn columnNro;
    public TableColumn columnNombre;
    public TableColumn columnDni;
    @FXML
    private TableView<Paciente> tableView;
    private Paciente paciente = new Paciente();
    List<Paciente> pacientes = new ArrayList<>();
    private ObservableList<Paciente> datos = null;




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
        pacientes = this.paciente.listarTodos();
        this.datos = FXCollections.observableList(pacientes);
        this.tableView.setItems(datos);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void configureTable() {
        TableColumn<Paciente, Integer> tcNro = (TableColumn<Paciente, Integer>) this.tableView.getColumns().get(0);
        tcNro.setCellValueFactory(cellData -> {
            if (!pacientes.isEmpty()) {
                return new SimpleObjectProperty<>(cellData.getValue().getHistorialClinico().getNumero_historial());
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });
        TableColumn<Paciente, String> tcNombre = (TableColumn<Paciente, String>) this.tableView.getColumns().get(1);
        tcNombre.setCellValueFactory(cellData -> {
            if (!pacientes.isEmpty()) {
                return new SimpleStringProperty(cellData.getValue().getNombre());
            } else {
                return new SimpleStringProperty("");
            }
        });
        TableColumn<Paciente, Long> tcDni = (TableColumn<Paciente, Long>) this.tableView.getColumns().get(2);
        tcDni.setCellValueFactory(cellData -> {
            if (!pacientes.isEmpty()) {
                return new SimpleObjectProperty<>(cellData.getValue().getIdentificacion().getNumero());
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });
    }

    @FXML
    public void opcVerHistClinico(ActionEvent actionEvent) {
        Paciente paciente1 = this.tableView.getSelectionModel().getSelectedItem();
        if (paciente1 != null) {
            try {
                FXMLLoader loader = App.openFXML("DetHistoriaClinica", "Detalles de la historia clinica", Modality.APPLICATION_MODAL);
                DetHistoriaClinicaControlador controller = loader.getController();
                controller.passData(paciente1, this);
            } catch (IOException ex) {
                System.out.println("catch verhist");
            }
        } else {
            JOptionPane.showMessageDialog(null, "historial no seleccionado.", "Error ", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void opcBuscar(ActionEvent actionEvent) {
        int dni = Integer.parseInt(this.TFBuscadorA.getText());
        Paciente paciente1 = new Paciente();
        this.paciente= paciente1.buscarDni(dni);
        List<Paciente> lista = new ArrayList<>();
        lista.add(this.paciente);
        this.datos = FXCollections.observableList(lista);
        this.tableView.setItems(datos);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
