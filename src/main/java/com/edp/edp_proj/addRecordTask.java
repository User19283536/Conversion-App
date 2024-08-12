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

public class addRecordTask extends Task<String> {

    @Override
    protected String call() throws Exception {

        try {





            DbConnection connectNow = new DbConnection();
            Connection connectDB =  connectNow.getConnection();

            String connectQuerry = "SELECT id, frC, toC, amount, rate, rate_amount, date FROM edpdb.records";


            try
            {
                ConversionData data = ConversionData.getInstance();
                String informationStg = data.getResult();

                //System.out.print(informationStg);

                JSONParser parser = new JSONParser();
                JSONObject obj1 = (JSONObject) parser.parse(String.valueOf(informationStg));
                JSONObject obj2 = (JSONObject) parser.parse(String.valueOf(obj1.get("rates")));
                JSONObject obj3 = (JSONObject) parser.parse(String.valueOf(obj2.get(data.getTo())));

                int newIndex = 0;
                Statement statement = connectDB.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = statement.executeQuery(connectQuerry);

                while(rs.next())
                {
                    newIndex = (int) rs.getInt("id");
                }

                newIndex = newIndex + 1;

                rs.moveToInsertRow();
                rs.updateInt("id", newIndex);
                rs.updateString("frC", data.getFrom());
                rs.updateString("toC", data.getTo());
                rs.updateString("amount", data.getAmount());
                rs.updateString("rate", String.valueOf(obj3.get("rate")));
                rs.updateString("rate_amount", String.valueOf(obj3.get("rate_for_amount")));
                rs.updateString("date", String.valueOf(obj1.get("updated_date")));

                rs.insertRow();

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