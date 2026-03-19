package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.App;
import com.ubp.edu.ar.tpintegrador.modelos.OrdenConsulta;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ProfesionalControlador implements Initializable {

    public Button BTNAtrasP;
    public Button BTNBuscarP;
    public TextField TFBuscadorP;
    public Button BTNRegPracticaP;
    public TableView tableView;
    public TableColumn columnNro;
    public TableColumn columnNombre;
    public TableColumn columnDni;
    private ObservableList<OrdenConsulta> datos = null;
    private OrdenConsulta ordenConsulta = new OrdenConsulta();
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
        List<OrdenConsulta> ordenConsultas = this.ordenConsulta.listarTodos();
        this.datos = FXCollections.observableList(ordenConsultas);
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
    protected void opcRegistrarPractica(){
        OrdenConsulta orden = (OrdenConsulta) this.tableView.getSelectionModel().getSelectedItem();
        if (orden != null) {
            try {
                App.openFXML("Practica", "Registrar practica", Modality.APPLICATION_MODAL);
            } catch (IOException ex) {
                System.out.println("catch verOrden");
            }
        } else {
            JOptionPane.showMessageDialog(null, "orden no seleccionada.", "Error ", JOptionPane.ERROR_MESSAGE);

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


}
