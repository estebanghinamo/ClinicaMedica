module com.ubp.edu.ar.tpintegrador {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires modelmapper;
    requires java.sql;
    requires java.compiler;
    requires java.desktop;


    opens com.ubp.edu.ar.tpintegrador.modelos to modelmapper;
    opens com.ubp.edu.ar.tpintegrador.Dto to modelmapper;
    opens com.ubp.edu.ar.tpintegrador to javafx.fxml;
    opens com.ubp.edu.ar.tpintegrador.controladores to javafx.fxml;

    exports com.ubp.edu.ar.tpintegrador;
    exports com.ubp.edu.ar.tpintegrador.controladores;
    exports com.ubp.edu.ar.tpintegrador.modelos;
    exports com.ubp.edu.ar.tpintegrador.Dao;
    exports com.ubp.edu.ar.tpintegrador.Dto;

}