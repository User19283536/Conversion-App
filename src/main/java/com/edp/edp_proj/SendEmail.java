package com.edp.edp_proj;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import java.nio.file.*;;


import java.io.IOException;

public class SendEmail {

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final boolean SSL_FLAG = true;


    public void sendEmail(String code) throws IOException {

        GetProperties properties = new GetProperties();
        personnalInformation person = personnalInformation.getInstance();
        String user = properties.getEmail();
        String password = new String(Files.readAllBytes(Paths.get("passwords.txt")));

        String to = person.getEmail();
        String subject = "Rejestracja w aplikacji Conversion App!";
        String message = String.format("Dziękujemy, że jesteś z nami %s %s!\n Twój kod aktywacyjny: \n%s",person.getFirstName(), person.getLastName(),code);

        try {
            Email email = new SimpleEmail();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(user, password));
            email.setSSLOnConnect(SSL_FLAG);
            email.setFrom(user);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(to);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}