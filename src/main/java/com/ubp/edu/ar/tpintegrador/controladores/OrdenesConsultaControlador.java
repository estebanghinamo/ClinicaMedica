package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.App;
import com.ubp.edu.ar.tpintegrador.Dao.OrdenConsultaDao;
import com.ubp.edu.ar.tpintegrador.modelos.Identificacion;
import com.ubp.edu.ar.tpintegrador.modelos.OrdenConsulta;
import com.ubp.edu.ar.tpintegrador.modelos.Paciente;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.LongStream;

public class OrdenesConsultaControlador implements Initializable {
    public Button BtnEliminarOrdenOC1;
    public TextField TFBuscarOC;
    public Button BTNGenerarOrdenOC;
    public TextField txtBuscar;
    public Button btnBuscar;
    public Button BTNModificarOrdenOC;
    public Button btnAtras;
    public Button btnVerOrden;
    private OrdenConsulta ordenConsulta = new OrdenConsulta();
    @FXML
    private TableView<OrdenConsulta> tableView;
    @FXML
    private TableColumn<OrdenConsulta,Integer> columnNro;
    @FXML
    private TableColumn<OrdenConsulta,String> columnNombre;
    @FXML
    private TableColumn<OrdenConsulta,Integer> columnDni;

    private ObservableList<OrdenConsulta> datos = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (this.tableView != null) {
            this.configureTable();
            this.loadData();
        }

    }

    public void loadData() {
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
    protected void opcGenerarOrden(){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("GenerarOrden.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();  // Espera a que la ventana modal se cierre

            // Recargar los datos de la tabla después de cerrar la ventana modal
            loadData();
        } catch (IOException ex) {
              System.out.println("catch opcGenerarOrden");
        }
    }

    public void opcEliminarOrden(ActionEvent actionEvent) {
        this.ordenConsulta = this.tableView.getSelectionModel().getSelectedItem();
        if(ordenConsulta.eliminar()){
            JOptionPane.showMessageDialog(null, "Orden eliminada.", "Acción exitosa", JOptionPane.INFORMATION_MESSAGE);

            this.loadData();
        }
        else {
            JOptionPane.showMessageDialog(null, "La orden no se puede borrar porque ya ha sido cargada en el historial del paciente.", "Acción no permitida", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAlertDialog(String s, Alert.AlertType alertType) {
    }

    public void opcBuscar(ActionEvent actionEvent) {
        Identificacion identificacion = new Identificacion();
        identificacion.setNumero( Integer.parseInt(this.txtBuscar.getText()));
        Paciente paciente = new Paciente();
        paciente.setIdentificacion(identificacion);
        ordenConsulta.setPaciente(paciente);
        List<OrdenConsulta> ordenConsultas = ordenConsulta.listarPorCriterio(ordenConsulta);
        this.datos = FXCollections.observableList(ordenConsultas);
        this.tableView.setItems(datos);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void opcAtras(ActionEvent actionEvent) {
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
    }

    public void opcVerOrden(ActionEvent actionEvent) {
        OrdenConsulta orden = this.tableView.getSelectionModel().getSelectedItem();
        if (orden != null) {
            try {
                FXMLLoader loader = App.openFXML("RecepcionVerDet", "Detalles de la orden", Modality.APPLICATION_MODAL);
                RecepcionVerDetControlador controller = loader.getController();
                controller.passData(orden, this);
            } catch (IOException ex) {
                System.out.println("catch verOrden");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Orden no seleccionada", "Error ", JOptionPane.ERROR_MESSAGE);

        }
    }
}
