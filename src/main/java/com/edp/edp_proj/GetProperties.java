package com.edp.edp_proj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    String output = new String(" ");
    InputStream input;

    public String getName() throws IOException {

        try {
            Properties property = new Properties();
            String propertyFile = "app.properties";

            input = getClass().getClassLoader().getResourceAsStream(propertyFile);

            if (input != null) {
                property.load(input);
            } else {
                throw new FileNotFoundException(propertyFile + "nie znaleziony w bieżącej ścieżce");
            }

            output = property.getProperty("Name");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return output;
    }

    public String getUrl() throws IOException {

        try {
            Properties property = new Properties();
            String propertyFile = "app.properties";

            input = getClass().getClassLoader().getResourceAsStream(propertyFile);

            if (input != null) {
                property.load(input);
            } else {
                throw new FileNotFoundException(propertyFile + "nie znaleziony w bieżącej ścieżce");
            }

            output = property.getProperty("Url");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return output;
    }

    public String getUser() throws IOException {

        try {
            Properties property = new Properties();
            String propertyFile = "app.properties";

            input = getClass().getClassLoader().getResourceAsStream(propertyFile);

            if (input != null) {
                property.load(input);
            } else {
                throw new FileNotFoundException(propertyFile + "nie znaleziony w bieżącej ścieżce");
            }

            output = property.getProperty("User");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return output;
    }

    public String getPassword() throws IOException {

        try {
            Properties property = new Properties();
            String propertyFile = "app.properties";

            input = getClass().getClassLoader().getResourceAsStream(propertyFile);

            if (input != null) {
                property.load(input);
            } else {
                throw new FileNotFoundException(propertyFile + "nie znaleziony w bieżącej ścieżce");
            }

            output = property.getProperty("Password");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return output;
    }

    public String getEmail() throws IOException {

        try {
            Properties property = new Properties();
            String propertyFile = "app.properties";

            input = getClass().getClassLoader().getResourceAsStream(propertyFile);

            if (input != null) {
                property.load(input);
            } else {
                throw new FileNotFoundException(propertyFile + "nie znaleziony w bieżącej ścieżce");
            }

            output = property.getProperty("EmailUser");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return output;
    }


}