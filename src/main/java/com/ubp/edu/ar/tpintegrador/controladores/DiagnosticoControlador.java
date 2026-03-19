package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.modelos.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class DiagnosticoControlador {

    public TextArea TADiagnosticoD;
    public TextField TFHoraD;
    public Button BTNSalirD;
    public Button btnGuardar;
    public TextField txtMedico;
    private MedicoControlador otherCtrl;
    DetalleOrdenConsulta detalleOrdenConsulta = new DetalleOrdenConsulta();
    OrdenConsulta ordenConsulta= new OrdenConsulta();
    Medico medico = new Medico();

    public void opcSalir(ActionEvent actionEvent) {
    }

    public void opcGuardar(ActionEvent actionEvent) {
        detalleOrdenConsulta.setDiagnostico(this.TADiagnosticoD.getText());
        detalleOrdenConsulta.setMedico(medico);
        ordenConsulta.getDetalles().add(detalleOrdenConsulta);

        this.ordenConsulta.guardarNuevoDetalle();
        JOptionPane.showMessageDialog(null, "se ha cargado con éxito.", "Acción exitosa ", JOptionPane.INFORMATION_MESSAGE);
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }
    public void passData(OrdenConsulta ordenConsulta, MedicoControlador otherCtrl) {
        this.otherCtrl = otherCtrl;
        this.ordenConsulta = ordenConsulta;
        this.medico= this.ordenConsulta.getDetalles().get(0).getMedico();

    }
}
