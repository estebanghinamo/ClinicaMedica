package com.ubp.edu.ar.tpintegrador;

import com.ubp.edu.ar.tpintegrador.Dao.ConexionSql;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//
import javafx.scene.Parent;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
//
public class App extends Application {
    private static Scene scene ;
    @Override
    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("Principal"), 600, 400);
//        stage.setScene(scene);
//        //stage.setMaximized(true);
//        stage.setTitle("Hello!");
//        stage.show();
        try {
            openFXML("Principal","Hello!",Modality.APPLICATION_MODAL);
        }catch (IOException ex)
        {
            System.out.println("Catch");
        }

    }

    static public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static FXMLLoader openFXML(String fxml, String title, Modality modality) throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle(title);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.setResizable(false);
        newWindow.initModality(modality);
        newWindow.show();
        return loader;
    }
    public static void main(String[] args) {
        launch();


    }
}