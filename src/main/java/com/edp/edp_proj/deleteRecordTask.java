package com.edp.edp_proj;

import javafx.concurrent.Task;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class deleteRecordTask extends Task<String> {

    private int index = 0;

    public deleteRecordTask(int row) {
        this.index = row;
    }

    @Override
    protected String call() throws Exception {

        try {


            DbConnection connectNow = new DbConnection();
            Connection connectDB =  connectNow.getConnection();

            String Querry = "SELECT id FROM edpdb.records";


            try
            {

                Statement statement = connectDB.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = statement.executeQuery(Querry);
                int id = 0;
                if (index == -1) {

                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                } else
                {
                    index++;
                    while (rs.next() && index > 0) {
                        id = rs.getInt("id");
                        index--;
                    }


                }

                //System.out.print(id);

                if(id!=0)
                {
                    String deleteQuerry = String.format("delete FROM edpdb.records where id=%d",id);

                    statement.executeUpdate(deleteQuerry);
                }


                return "succes";

            } catch(Exception e)
            {
                e.printStackTrace();
                return "error";
            }




        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
}