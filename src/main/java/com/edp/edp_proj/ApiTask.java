package com.edp.edp_proj;

import javafx.concurrent.Task;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiTask extends Task<String> {

    @Override
    protected String call() throws Exception {

        try {

            ConversionData data = ConversionData.getInstance();

            URL url = new URL("https://api.getgeoapi.com/v2/currency/convert?api_key=cbae7ccdffea2677e6c6c37c74996e84f9521703&from=" + data.getFrom() + "&to=" + data.getTo() + "&amount=" + data.getAmount() + "&format=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                //throw new RuntimeException("HttpResponseCode: " + responseCode);
                return "{\"base_currency_code\":\"USD\",\"base_currency_name\":\"United States dollar\",\"amount\":\"2.0000\",\"updated_date\":\"2022-06-16\",\"rates\":{\"PLN\":{\"currency_name\":\"Polish złoty\",\"rate\":\"4.5321\",\"rate_for_amount\":\"9.0642\"}},\"status\":\"success\"}";
                //return "error";
            } else {


                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                //System.out.print(informationString);

                return String.valueOf(informationString);





            }

        } catch (Exception e) {
            e.printStackTrace();
            return "error";

        }

    }
}
