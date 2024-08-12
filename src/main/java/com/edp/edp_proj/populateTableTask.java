package com.edp.edp_proj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class populateTableTask extends Task<ObservableList<Entry>> {

    @Override
    protected ObservableList<Entry> call() throws Exception {

        ObservableList<Entry> tableData = FXCollections.observableArrayList();

        try {


            DbConnection connectNow = new DbConnection();
            Connection connectDB =  connectNow.getConnection();

            String connectQuerry = "SELECT id, frC, toC, amount, rate, rate_amount, date FROM edpdb.records";


            try
            {

                Statement statement = connectDB.createStatement();
                ResultSet rs = statement.executeQuery(connectQuerry);

                while(rs.next())
                {
                    String date = rs.getNString("date");
                    String from = rs.getNString("frC");
                    String to = rs.getNString("toC");
                    String amount = rs.getNString("amount");
                    String rate = rs.getNString("rate");
                    String rate_amount = rs.getNString("rate_amount");


                    tableData.add(new Entry(date,from,to,amount,rate,rate_amount));
                }



                return tableData;

            } catch(Exception e)
            {
                e.printStackTrace();
                return tableData;
            }




        } catch (Exception e) {
            e.printStackTrace();
            return tableData;

        }

    }
}