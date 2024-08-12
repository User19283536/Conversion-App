package com.edp.edp_proj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private Connection link;

    public Connection getConnection() throws IOException {
        //String Name = "edpdb";
        //String User="root";
        //String Password="EDP2022WATproj";
        //String url = "jdbc:mysql://localhost/" + Name;

        GetProperties properties = new GetProperties();
        String url = properties.getUrl();
        String User = properties.getUser();
        String Password = properties.getPassword();


        try
        {

            Class.forName("com.mysql.cj.jdbc.Driver");
            link = DriverManager.getConnection(url, User, Password);

        } catch(Exception e)
        {
            e.printStackTrace();
        }

        return link;



    }
}
