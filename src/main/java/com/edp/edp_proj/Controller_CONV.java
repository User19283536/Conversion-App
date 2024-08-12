package com.edp.edp_proj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller_CONV {


    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<String> avCurrencyList = FXCollections.observableArrayList("USD","GBP", "EUR","PLN","CHF","RUB");

    public void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private ChoiceBox fromCurrency = new ChoiceBox(avCurrencyList);
    @FXML
    private ChoiceBox toCurrency = new ChoiceBox(avCurrencyList);
    @FXML
    private Button convert;
    @FXML
    private NumberTextField amountInput;
    @FXML
    private Button returnButton1;
    @FXML
    private CustomLabel wrongInput;

    @FXML
    public void initialize() throws IOException {
        fromCurrency.setItems(avCurrencyList);
        toCurrency.setItems(avCurrencyList);
        fromCurrency.setValue("USD");
        toCurrency.setValue("PLN");


        try
        {
            convert.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ConversionData data = ConversionData.getInstance();
                    convert.fireEvent(new ConversionEvent(ConversionEvent.CONVERT, data));

                }
            });
        } catch (Exception e)
        {
            e.printStackTrace();

        }

        try {
            convert.addEventFilter(ConversionEvent.ANY, this::handleConversion);
        } catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    public void handleConversion(ConversionEvent event){

        try {

            Float.parseFloat((String)amountInput.getText());

            //setting SINGLETON class object parameters
            event.getData().setFrom((String)fromCurrency.getValue());
            event.getData().setTo((String)toCurrency.getValue());
            event.getData().setAmount((String)amountInput.getText());

           Parent root = FXMLLoader.load(getClass().getResource("converted.fxml"));
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);

           //System.out.print(Float.parseFloat(event.getData().getAmount()));

           stage.show();
        } catch (Exception e) {
            //e.printStackTrace();
            wrongInput.setText("Błędne dane wprowadzania");
            wrongInput.fade();


        }


    }
}


