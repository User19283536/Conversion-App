package com.edp.edp_proj;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.commons.validator.EmailValidator;

import java.io.IOException;

public class ControllerREGISTER {

    @FXML
    private Button backButton;
    @FXML
    private Button registrationButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField city;
    @FXML
    private TextField country;
    @FXML
    private Label inputError;
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

    @FXML
    public void initialize() throws IOException {

        try {
            registrationButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    personnalInformation person = personnalInformation.getInstance();
                    registrationButton.fireEvent(new personEvent(personEvent.PERSON, person));

                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }

        try {
            registrationButton.addEventFilter(personEvent.ANY, this::handleConversion);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void handleConversion(personEvent event) {

        Interpreter interpreter = new Interpreter();

        try {

            if (!firstName.getText().trim().isEmpty() && !lastName.getText().trim().isEmpty() && !email.getText().trim().isEmpty() && !city.getText().trim().isEmpty() && !country.getText().trim().isEmpty()) {

                EmailValidator validator = EmailValidator.getInstance();
                if (validator.isValid(String.valueOf(email.getText()))) {

                    //System.out.print(String.valueOf(firstName.getText()));
                    //System.out.print(interpreter.checkNames(String.valueOf(firstName.getText())));
                    if(interpreter.checkNames((String) firstName.getText()) && interpreter.checkNames((String) lastName.getText()) && interpreter.checkNames((String) city.getText()) && interpreter.checkNames((String) country.getText())){

                        //setting SINGLETON class object parameters
                        event.getData().setFirstName((String) firstName.getText());
                        event.getData().setLastName((String) lastName.getText());
                        event.getData().setEmail((String) email.getText());
                        event.getData().setCity((String) city.getText());
                        event.getData().setCountry((String) country.getText());


                        Parent root = FXMLLoader.load(getClass().getResource("registered.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);


                        stage.show();
                    } else
                    {
                        inputError.setText("Błędne dane wprowadzania!");
                        inputError.setTextFill(Paint.valueOf("red"));
                    }
                } else {
                    inputError.setText("Podany adres email nie istnieje!");
                    inputError.setTextFill(Paint.valueOf("red"));
                }



            } else
            {
                inputError.setText("Uzupełnij wszystkie istotne pola!");
                inputError.setTextFill(Paint.valueOf("red"));
            }

        } catch (Exception e) {
            e.printStackTrace();


        }


    }

}
