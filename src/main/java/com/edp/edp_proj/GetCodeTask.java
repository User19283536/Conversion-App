package com.edp.edp_proj;

import javafx.concurrent.Task;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class GetCodeTask extends Task<String> {

    @Override
    protected String call() throws Exception {

        try {

            personnalInformation person = personnalInformation.getInstance();

            URL url = new URL("https://random.justyy.workers.dev/api/random/?cached&n=10");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                //throw new RuntimeException("HttpResponseCode: " + responseCode);
                return "error";
            } else {


                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                //System.out.print(informationString);

                SendEmail sender = new SendEmail();
                sender.sendEmail(String.valueOf(informationString));

                return (String) informationString.substring(1, informationString.length() - 1);


            }

        } catch (Exception e) {
            e.printStackTrace();
            return "error";

        }

    }

}
