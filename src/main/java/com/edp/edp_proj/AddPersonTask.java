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

public class AddPersonTask extends Task<String> {

    @Override
    protected String call() throws Exception {

        try {





            DbConnection connectNow = new DbConnection();
            Connection connectDB =  connectNow.getConnection();

            String connectQuerry = "SELECT id, code, fName, lName, email, city, country FROM edpdb.people";


            try
            {
                personnalInformation person = personnalInformation.getInstance();

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
                rs.updateString("code",person.getCode() );
                rs.updateString("fName",person.getFirstName() );
                rs.updateString("lName",person.getLastName());
                rs.updateString("email",person.getEmail() );
                rs.updateString("city",person.getCity() );
                rs.updateString("country",person.getCountry() );



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