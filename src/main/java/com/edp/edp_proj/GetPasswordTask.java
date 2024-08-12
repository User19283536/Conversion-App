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

public class GetPasswordTask extends Task<String> {

    @Override
    protected String call() throws Exception {


        try {

            personnalInformation person = personnalInformation.getInstance();
            DbConnection connectNow = new DbConnection();
            Connection connectDB =  connectNow.getConnection();

            String connectQuerry = "SELECT code FROM edpdb.people";


            try
            {

                Statement statement = connectDB.createStatement();
                ResultSet rs = statement.executeQuery(connectQuerry);

                while(rs.next())
                {
                    if(person.getCode().equals(rs.getNString("code")))
                    {
                        return "true";
                    }


                }
                return "false";




            } catch(Exception e)
            {
                e.printStackTrace();
                return "false";
            }




        } catch (Exception e) {
            e.printStackTrace();
            return "false";

        }

    }
}