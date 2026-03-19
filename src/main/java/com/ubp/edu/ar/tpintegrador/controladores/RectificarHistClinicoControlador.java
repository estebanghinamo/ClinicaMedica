package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.Dto.HistorialClinicoDto;
import com.ubp.edu.ar.tpintegrador.Dto.OrdenConsultaDto;
import com.ubp.edu.ar.tpintegrador.modelos.DetallesClinico;
import com.ubp.edu.ar.tpintegrador.modelos.HistorialClinico;
import com.ubp.edu.ar.tpintegrador.modelos.OrdenConsulta;
import com.ubp.edu.ar.tpintegrador.modelos.Paciente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;


public class RectificarHistClinicoControlador {

    public Button BTNAgregarRHC;
    public TextField TFNroOrdenRHC;
    private DetHistoriaClinicaControlador otherCtrl;
    private HistorialClinico historialClinico= new HistorialClinico();
    private OrdenConsulta ordenConsulta = new OrdenConsulta();

    public void opcAgregar(ActionEvent actionEvent) {
        OrdenConsulta ordenConsulta = new OrdenConsulta();
        ordenConsulta.setNumeroOrden(Integer.parseInt(this.TFNroOrdenRHC.getText()));
        this.ordenConsulta= ordenConsulta.buscarOrden(ordenConsulta);
        this.ordenConsulta.buscarDetalles();
        DetallesClinico detallesClinico = new DetallesClinico(this.ordenConsulta,historialClinico.getDetallesClinicos().size());
        historialClinico.getDetallesClinicos().add(detallesClinico);
        historialClinico.guardarDetalle();
        JOptionPane.showMessageDialog(null, "se ha cargado con éxito.", "Acción exitoda ", JOptionPane.INFORMATION_MESSAGE);
        Stage stage = (Stage) BTNAgregarRHC.getScene().getWindow();
        stage.close();
    }
    public void passData(HistorialClinico historialClinico, DetHistoriaClinicaControlador otherCtrl) {
        this.otherCtrl = otherCtrl;
        this.historialClinico=historialClinico;
    }
}
