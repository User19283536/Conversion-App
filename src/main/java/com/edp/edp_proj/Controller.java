package com.edp.edp_proj;


import com.google.common.eventbus.EventBus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;



    public void goToConversion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("conversion.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    EventBus eventBus = EventBusInstance.getEventBus();

    public void exitApp(ActionEvent event) throws IOException {
        ExitEvent exitApp = new ExitEvent();
        eventBus.register(new ExitEventListener());

        Label exitLabel = new Label("Czy chcesz wyjść?");
        Button yesButton = new Button("Tak");
        Button noButton = new Button("Nie");


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Pane exitLayout = new Pane();

        exitLabel.setLayoutX(70);
        exitLabel.setLayoutY(30);
        yesButton.setLayoutX(80);
        yesButton.setLayoutY(70);
        noButton.setLayoutX(130);
        noButton.setLayoutY(70);

        exitLayout.getChildren().add(exitLabel);
        exitLayout.getChildren().add(yesButton);
        exitLayout.getChildren().add(noButton);

        Scene secondScene = new Scene(exitLayout, 250, 150);


        Stage newWindow = new Stage();
        newWindow.setTitle("Exiting App...");
        newWindow.setScene(secondScene);


        newWindow.initModality(Modality.WINDOW_MODAL);


        newWindow.initOwner(stage);

        newWindow.setX(stage.getX() + 150);
        newWindow.setY(stage.getY() + 150);
        newWindow.show();

        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventBus.post(exitApp);
            }
        });

        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newWindow.close();
            }
        });
    }


    @FXML
    private Button returnButton2;
    @FXML
    private Button exitButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button conversionButton;
    @FXML
    private Label welcomeText;





    @FXML
    protected void onConversionButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}