package com.edp.edp_proj;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerREGISTERED {
    @FXML
    private Label email;
    @FXML
    private Label emailSent;
    @FXML
    private Label result;
    @FXML
    private Label status;
    @FXML
    private TextField returnCode;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws IOException {

        try {

            personnalInformation person = personnalInformation.getInstance();

            GetCodeTask activationCode = new GetCodeTask();

            activationCode.setOnSucceeded(new EventHandler<WorkerStateEvent>()
            {
                @Override
                public void handle(WorkerStateEvent t) {
                    String connection = activationCode.getValue();

                    try {

                        if (!connection.equals("error"))
                        {
                            status.setText("Połączeno z API");
                            status.setTextFill(Paint.valueOf("green"));
                            emailSent.setText("Wysłano potwierdzenie na adres:");
                            emailSent.setTextFill(Paint.valueOf("green"));
                            email.setText(person.getEmail());
                            email.setTextFill(Paint.valueOf("green"));
                            person.setCode(connection);

                            returnCode.setText(connection);

                            AddPersonTask pTask = new AddPersonTask();

                            Thread th = new Thread(pTask);
                            th.setDaemon(true);
                            th.start();



                        }else
                        {
                            status.setText("Brak połączenia z API");
                            status.setTextFill(Paint.valueOf("red"));
                        }

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }

            }

            );

            Thread th = new Thread(activationCode);
            th.setDaemon(true);
            th.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
