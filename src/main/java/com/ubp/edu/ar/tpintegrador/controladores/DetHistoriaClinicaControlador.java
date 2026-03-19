package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.App;
import com.ubp.edu.ar.tpintegrador.Dto.HistorialClinicoDto;
import com.ubp.edu.ar.tpintegrador.modelos.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetHistoriaClinicaControlador implements Initializable {

    public TableColumn ColumnaNroOrden;
    public TableColumn ColumnaServicio;
    public TableColumn ColumnaDiagnostico;
    public TableColumn ColumnaPractica;
    public TableColumn ColumnaObservacion;
    public TableColumn ColumnaMEDPROF;
    public Button BTNRectificarHC;
    public TextField txtNro;
    public TextField txtNombre;
    public TextField txtDni;
    @FXML
    private TableView<DetallesClinico> tableView;
    private ObservableList<DetallesClinico> datos = null;
    private AdministracionControlador otherCtrl;
    private HistorialClinico historialClinico1 = new HistorialClinico();

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.tableView != null) {
            this.configureTable();

        }

    }

    private void configureTable() {
        TableColumn<DetallesClinico, Integer> tcNro = (TableColumn<DetallesClinico, Integer>) this.tableView.getColumns().get(0);
        tcNro.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getOrden().getNumeroOrden());
        });
        TableColumn<DetallesClinico, String> tcServicio = (TableColumn<DetallesClinico, String>) this.tableView.getColumns().get(1);
        tcServicio.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getOrden().getTipo().getServicio().getTipo_servicio());
        });
        TableColumn<DetallesClinico, String> tcMedico = (TableColumn<DetallesClinico, String>) this.tableView.getColumns().get(2);
        tcMedico.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getOrden().getDetalles().get(0).getMedico().getNombre());
        });

    }
    public void passData(Paciente paciente, AdministracionControlador otherCtrl) {
        HistorialClinico historialClinico = new HistorialClinico();
        this.otherCtrl = otherCtrl;
        this.txtNro.setText(Integer.toString(paciente.getHistorialClinico().getNumero_historial()));
        this.txtNombre.setText((paciente.getNombre()));
        this.txtDni.setText(Long.toString(paciente.getIdentificacion().getNumero()));
        paciente.setHistorialClinico(historialClinico.buscar(paciente.getHistorialClinico()));
        this.datos = FXCollections.observableList(paciente.getHistorialClinico().getDetallesClinicos());
        this.tableView.setItems(this.datos);
        this.historialClinico1=paciente.getHistorialClinico();
    }

    public void opcRectificar(ActionEvent actionEvent) {
        if (historialClinico1 != null) {
            try {
                FXMLLoader loader = App.openFXML("RectificarHistClinico", "Rectificar", Modality.APPLICATION_MODAL);
                RectificarHistClinicoControlador controller = loader.getController();
                controller.passData(historialClinico1, this);

            } catch (IOException ex) {
                System.out.println("catch verhist");
            }
        } else {
            JOptionPane.showMessageDialog(null, " no hay historial.", "Acción no permitida", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
