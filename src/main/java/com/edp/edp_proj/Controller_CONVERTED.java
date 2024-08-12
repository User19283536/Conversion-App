package com.edp.edp_proj;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;


//to move later to separate class
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Controller_CONVERTED {



    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label Input;
    @FXML
    private Button returnButton2;
    @FXML
    private Label Output;
    @FXML
    private Label info;
    @FXML
    private Label status;
    @FXML
    private Label APIconnection;
    @FXML
    private Label equals;


    public void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("conversion.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws IOException {

        try {

            ConversionData data = ConversionData.getInstance();


                ApiTask apiTask = new ApiTask();



                apiTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                    //data.setResult(apiTask.getValue());
                    String informationString = apiTask.getValue();

                    try {
                        if(!informationString.equals("error")) {

                            JSONParser parser = new JSONParser();
                            JSONObject obj1 = (JSONObject) parser.parse(String.valueOf(informationString));
                            JSONObject obj2 = (JSONObject) parser.parse(String.valueOf(obj1.get("rates")));
                            JSONObject obj3 = (JSONObject) parser.parse(String.valueOf(obj2.get(data.getTo())));
                            //System.out.print(obj3.get("rate_for_amount"));

                            data.setResult(informationString);

                            equals.setText("równa się");
                            Input.setText(data.getAmount() + " " + data.getFrom());
                            //System.out.print(data.getResult());
                            Output.setText(obj3.get("rate_for_amount") + " " + data.getTo());
                            info.setText("Kurs jednostkowy: " + obj3.get("rate") + " z dnia " + obj1.get("updated_date"));
                            APIconnection.setText("Połączono z API");
                            APIconnection.setTextFill(Paint.valueOf("green"));

                            addRecordTask rTask = new addRecordTask();
                            rTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                                @Override
                                public void handle(WorkerStateEvent t) {
                                    String connection = rTask.getValue();

                                    try {

                                        if (!connection.equals("error"))
                                        {
                                            status.setText("Połączeno z bazą danych");
                                            status.setTextFill(Paint.valueOf("green"));
                                        }else
                                        {
                                            status.setText("Brak połączenia z bazą danych");
                                            status.setTextFill(Paint.valueOf("red"));
                                        }

                                    }catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }

                                }


                            } );


                            Thread th = new Thread(rTask);
                            th.setDaemon(true);
                            th.start();

                        } else
                        {
                            info.setText("Przepraszamy, niestety wystąpił problem.");
                            APIconnection.setText("Brak połączenia z API");
                            APIconnection.setTextFill(Paint.valueOf("red"));
                            status.setText("Łączenie z bazą danych przerwane");
                            status.setTextFill(Paint.valueOf("red"));
                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }

                }
                );

                Thread th = new Thread(apiTask);
                th.setDaemon(true);
                th.start();









        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
