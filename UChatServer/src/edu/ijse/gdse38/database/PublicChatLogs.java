/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse38.database;

import edu.ijse.gdse38.main.Server;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SW 96
 */
public class PublicChatLogs {

    private File file = new File("PublicLogFile.txt");
    private FileWriter logFile;
    private String newDate;
    private FileReader logReader;
    private BufferedReader bufferedReader;
    private Server server = new Server();

    public PublicChatLogs(String log) throws IOException {
        String newDate = server.getDate();
        createLogFile();
        writeLogs(log);
    }

    public PublicChatLogs() throws IOException {
//        createLogFile();
        readLogs();
    }

    /**
     * Write Logs
     */
    private void createLogFile() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private void writeLogs(String log) throws IOException {
        logFile = new FileWriter(file, true);
        logFile.write("\t\t\t\t" + newDate + " Logs \n");
        logFile.write(log + "\n");
        logFile.write("\t\t----------------------------------------\n");
        logFile.flush();
    }

//    public static void main(String[] args) {
//        try {
//            new PublicChatLogs();
//        } catch (IOException ex) {
//            Logger.getLogger(PublicChatLogs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public String getDate() {
        return newDate;
    }

    /**
     * ReadLogs
     */
    private void readLogs() throws FileNotFoundException, IOException {
        logReader = new FileReader(file);
        bufferedReader = new BufferedReader(logReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
        Runtime.getRuntime().exec("notePad PublicLogFile.txt");

    }
}
