package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.modelos.GrupoFamiliar;
import com.ubp.edu.ar.tpintegrador.modelos.Identificacion;
import com.ubp.edu.ar.tpintegrador.modelos.Localidad;
import com.ubp.edu.ar.tpintegrador.modelos.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.*;

import javax.lang.model.element.PackageElement;
import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class RegistrarPacienteControlador {


    public TextField txtTipoDni;
    public TextField txtNroDni;
    public Button btnConfirmar;
    public TextField txtNombre;
    public TextField txtApellido;
    public TextField txtPais;
    public TextField txtProvincia;
    public TextField txtCiudad;
    public TextField txtCalle;
    public TextField txtNro;
    public TextField txtFechaNac;
    public TextField txtDniJefeFam;
    public Button btnCancelar;
    public TextField txtObraSocial;
    public DatePicker txtFecha;
    private Paciente paciente= new Paciente();

    @FXML
    protected void opcConfirmarRegistrarPaciente(){
        Identificacion identificacion = new Identificacion(this.txtTipoDni.getText(),Long.parseLong(this.txtNroDni.getText()));
        Localidad localidad = new Localidad(this.txtPais.getText(),this.txtProvincia.getText(),
                                            this.txtCiudad.getText(),this.txtCalle.getText(),Integer.parseInt(this.txtNro.getText()));
        GrupoFamiliar grupoFamiliar= new GrupoFamiliar();
        paciente.setNombre(this.txtNombre.getText());
        paciente.setApellido(this.txtApellido.getText());
        paciente.setIdentificacion(identificacion);
        paciente.setLocalidad(localidad);
        paciente.setObra_social(this.txtObraSocial.getText());

        Period periodo = Period.between(this.txtFecha.getValue(), LocalDate.now());

        if(periodo.getYears() >= 18){

            paciente.setJefe_familia(true);
            grupoFamiliar.setDni_jefe_fam( identificacion.getNumero());
            paciente.setGrupoFamiliar(grupoFamiliar);
        }else{
            paciente.setJefe_familia(false);
            grupoFamiliar.setDni_jefe_fam(Long.parseLong(this.txtDniJefeFam.getText()));
            paciente.setGrupoFamiliar(grupoFamiliar);
        }
        this.paciente.guardar();
        Stage stage = (Stage) btnConfirmar.getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Paciente registrado", "Accion exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    public void opcCancelar(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
