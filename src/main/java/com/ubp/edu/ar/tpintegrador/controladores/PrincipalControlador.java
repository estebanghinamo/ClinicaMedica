package com.ubp.edu.ar.tpintegrador.controladores;

import com.ubp.edu.ar.tpintegrador.App;
import com.ubp.edu.ar.tpintegrador.Dao.HistorialClinicoDao;
import com.ubp.edu.ar.tpintegrador.Dao.OrdenConsultaDao;
import com.ubp.edu.ar.tpintegrador.Dao.PacienteDao;
import com.ubp.edu.ar.tpintegrador.Dto.OrdenConsultaDto;
import com.ubp.edu.ar.tpintegrador.Dto.PacienteDto;
import com.ubp.edu.ar.tpintegrador.modelos.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.List;

public class PrincipalControlador {
    public Button BtnRecepcionista;
    public Button BtnAdministracion;
    public Button BtnProfesional;
    public Button BtnMedico;
    public Button BtnTest;
    public Label welcomeText1;

    @FXML
    private Label welcomeText;
    private HistorialClinico historialClinico = new HistorialClinico();

    @FXML
    protected void initialize() {}
    @FXML
    protected void opcTest() throws ParseException {

        List<HistorialClinico> historialClinicos = this.historialClinico.listarTodos();
        for(HistorialClinico hc: historialClinicos )
        {
             hc=hc.buscar(hc);
            if (!hc.getDetallesClinicos().isEmpty()){

                System.out.println(" historial nombre paciente : "+hc.getDetallesClinicos().get(1).getOrden().getPaciente().getNombre());
            }

        }


    }
    @FXML
    protected void opcRecepcionista(){
        try {
            App.openFXML("OrdenesConsulta", "Consulta de ordenes", Modality.APPLICATION_MODAL);
        } catch (IOException ex) {
      //      showAlert(Alert.AlertType.ERROR, null, "Info", ex.toString());
            System.out.println("catch opcRecepcionista");
        }
    }
    @FXML
    protected void opcAdministracion(){
        try {
            App.openFXML("Administracion", "Admnistracion", Modality.APPLICATION_MODAL);
        } catch (IOException ex) {
            //      showAlert(Alert.AlertType.ERROR, null, "Info", ex.toString());
            System.out.println("catch administracion");
        }
    }
    @FXML
    protected void opcProfesional(){
        try {
            App.openFXML("Profesional", "Profesional", Modality.APPLICATION_MODAL);
        } catch (IOException ex) {
            //      showAlert(Alert.AlertType.ERROR, null, "Info", ex.toString());
            System.out.println("catch");
        }
    }
    @FXML
    protected void opcMedico(){
        try {
            App.openFXML("Medico", "Medico", Modality.APPLICATION_MODAL);
        } catch (IOException ex) {
            //      showAlert(Alert.AlertType.ERROR, null, "Info", ex.toString());
            System.out.println("catch");
        }
    }


}
