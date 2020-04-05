package ru.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;

import javax.mail.Session;
import javax.mail.Store;
import java.io.InputStream;
import java.io.PrintStream;

public class JamesHelper {

    private Session mailSession;
    private TelnetClient telnet;
    private ApplicationManager app;
    private String mailServer;
    private InputStream in;
    private PrintStream out;
    private Store store;

    public JamesHelper(ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());
    }

    public boolean doesUserExist(String name) {
        initTelnetSession();
        write("verify " + name);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User " + name + " exist");
    }

    public void createUser(String name, String pass) {
        initTelnetSession();
        write("adduser " + name + " " + pass);
        String result = readUntil("User " + name + " added");
        closeTelnetSession();
    }

    public void deleteUser(String name) {
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + " deleted");
        closeTelnetSession();
    }

    private void initTelnetSession() {
        mailServer = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("mailserver.adminlogin");
        String password = app.getProperty("mailserver.adminpassword");
        
        try {
            telnet.connect(mailServer, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        readUntil("Login id:");
        write("");
        readUntil("Password:");
        write("");

        readUntil("Login id:");
        write(login);
        readUntil("Password:");
        write(password);

        readUntil("Welcome " + login + ". HELP for a list of commands");

    }

    private String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeTelnetSession() {
        write("quit");
    }
}
