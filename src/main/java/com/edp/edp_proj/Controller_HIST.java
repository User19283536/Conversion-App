package com.edp.edp_proj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller_HIST {


    @FXML
    private Button returnButton3;
    @FXML
    private TableColumn<Entry,String> date;
    @FXML
    private TableColumn<Entry,String> from;
    @FXML
    private TableColumn<Entry,String> to;
    @FXML
    private TableColumn<Entry,String> amount;
    @FXML
    private TableColumn<Entry,String> rate;
    @FXML
    private TableColumn<Entry,String> basicRate;
    @FXML
    private TableView<Entry> tableView;
    @FXML
    private Label connection;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteButton(ActionEvent event)
    {
        try
        {


            int index = 0;
            try {
                ObservableList selectedCells = tableView.getSelectionModel().getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                index = tablePosition.getRow();
            }catch (Exception e)
            {
                index = -1;
            }


            deleteRecordTask dTask= new deleteRecordTask(index);
            dTask.setOnSucceeded(new EventHandler<WorkerStateEvent>()
            {
                @Override
                public void handle(WorkerStateEvent t)
                {
                    if(dTask.getValue().equals("succes"))
                    {
                        populateTableTask pTask = new populateTableTask();
                        pTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                            @Override
                            public void handle(WorkerStateEvent t)
                            {

                                try {
                                    ObservableList<Entry> tableData = pTask.getValue();

                                    date.setCellValueFactory(new PropertyValueFactory<Entry, String>("dateE"));
                                    from.setCellValueFactory(new PropertyValueFactory<Entry, String>("fromE"));
                                    to.setCellValueFactory(new PropertyValueFactory<Entry, String>("toE"));
                                    amount.setCellValueFactory(new PropertyValueFactory<Entry, String>("amountE"));
                                    rate.setCellValueFactory(new PropertyValueFactory<Entry, String>("rateE"));
                                    basicRate.setCellValueFactory(new PropertyValueFactory<Entry, String>("basicRateE"));
                                    tableView.setItems(tableData);
                                    connection.setText("Połączeno z bazą danych");
                                    connection.setTextFill(Paint.valueOf("green"));

                                }catch(Exception e)
                                {
                                    e.printStackTrace();
                                    connection.setText("Brak połączenia z bazą danych");
                                    connection.setTextFill(Paint.valueOf("red"));
                                }

                            }

                        });

                        Thread th = new Thread(pTask);
                        th.setDaemon(true);
                        th.start();

                    }
                }

            }

            );
            Thread th = new Thread(dTask);
            th.setDaemon(true);
            th.start();


        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    public void initialize() throws IOException {



        populateTableTask pTask = new populateTableTask();
        pTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t)
            {

                try {
                    ObservableList<Entry> tableData = pTask.getValue();

                    date.setCellValueFactory(new PropertyValueFactory<Entry, String>("dateE"));
                    from.setCellValueFactory(new PropertyValueFactory<Entry, String>("fromE"));
                    to.setCellValueFactory(new PropertyValueFactory<Entry, String>("toE"));
                    amount.setCellValueFactory(new PropertyValueFactory<Entry, String>("amountE"));
                    rate.setCellValueFactory(new PropertyValueFactory<Entry, String>("rateE"));
                    basicRate.setCellValueFactory(new PropertyValueFactory<Entry, String>("basicRateE"));
                    tableView.setItems(tableData);
                    connection.setText("Połączeno z bazą danych");
                    connection.setTextFill(Paint.valueOf("green"));

                }catch(Exception e)
                {
                    e.printStackTrace();
                    connection.setText("Brak połączenia z bazą danych");
                    connection.setTextFill(Paint.valueOf("red"));
                }

            }

                             });

        Thread th = new Thread(pTask);
        th.setDaemon(true);
        th.start();


    }
}
