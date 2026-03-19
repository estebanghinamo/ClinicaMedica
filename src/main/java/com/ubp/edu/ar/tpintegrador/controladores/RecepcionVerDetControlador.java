package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.modelos.DetalleOrdenConsulta;
import com.ubp.edu.ar.tpintegrador.modelos.OrdenConsulta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecepcionVerDetControlador implements Initializable {

    public TableColumn columnaServico;
    public TableColumn ColumnaDiagnostico;
    public TableColumn ColumnaPractica;
    public TableColumn ColumnaObservacion;
    public TableColumn ColumnaMedProf;
    public TextField TFNroOrden;
    public TextField TFNombre;
    public TextField TFDni;
    public Button BTNAgregarDetalle;
    public Button BTNEliminarDetalle;
    private OrdenesConsultaControlador otherCtrl;
    private OrdenConsulta ordenConsulta = new OrdenConsulta();
    @FXML
    private TableView<DetalleOrdenConsulta> tableView;
    private ObservableList<DetalleOrdenConsulta> datos = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.tableView != null) {
            this.configureTable();

        }
    }



    private void configureTable() {
        TableColumn<DetalleOrdenConsulta, String> tcServicio = (TableColumn<DetalleOrdenConsulta, String>) this.tableView.getColumns().get(0);
        tcServicio.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getPractica().getServicio().toString());
        });
        TableColumn<DetalleOrdenConsulta, String> tcDiagnostico = (TableColumn<DetalleOrdenConsulta, String>) this.tableView.getColumns().get(1);
        tcDiagnostico.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDiagnostico());
        });
        TableColumn<DetalleOrdenConsulta, String> tcPractica = (TableColumn<DetalleOrdenConsulta, String>) this.tableView.getColumns().get(2);
        tcPractica.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getPractica().getNombrePractica());
        });

        TableColumn<DetalleOrdenConsulta, String> tcObservacion = (TableColumn<DetalleOrdenConsulta, String>) this.tableView.getColumns().get(3);
        tcObservacion.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getObservacion());
        });
        TableColumn<DetalleOrdenConsulta, String> tcMedico = (TableColumn<DetalleOrdenConsulta, String>) this.tableView.getColumns().get(4);
        tcMedico.setCellValueFactory(cellData -> {
            String nombreCompleto = cellData.getValue().getMedico().getNombre() + " " + cellData.getValue().getMedico().getApellido();
            return new SimpleStringProperty(nombreCompleto);
        });
    }

    public void passData(OrdenConsulta ordenConsulta, OrdenesConsultaControlador otherCtrl) {
        this.otherCtrl = otherCtrl;
        this.TFNroOrden.setText(Integer.toString(ordenConsulta.getNumeroOrden()));
        this.TFNombre.setText((ordenConsulta.getPaciente().getNombre()));
        this.TFDni.setText(Long.toString(ordenConsulta.getPaciente().getIdentificacion().getNumero()));
        this.ordenConsulta = ordenConsulta;
        this.ordenConsulta.buscarDetalles();
        this.datos = FXCollections.observableList(this.ordenConsulta.getDetalles());
        this.tableView.setItems(this.datos);

    }

    /**
     * @param url
     * @param resourceBundle
     */

}
