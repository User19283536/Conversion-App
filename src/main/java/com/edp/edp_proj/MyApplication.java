package com.edp.edp_proj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("$$ Conversion App $$");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e)
        {
            //e.printStackTrace();
            System.out.print("exception handled1!");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}