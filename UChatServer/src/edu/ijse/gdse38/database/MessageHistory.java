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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *
 * @author SW 96
 */
public class MessageHistory {

    private File file = new File("MessageHistory.txt");

    private BufferedReader bufferedReader;
    private FileReader messageReader;
    private FileWriter messageWriter;
    private String newDate;
    private String sender;
    private String reciever;
    private ArrayList<String> messageArray = new ArrayList<>();
    private String newTime;
    private SimpleDateFormat sdf;
    private String hisDate;

    public MessageHistory() throws IOException {
        createFile();
    }

    public MessageHistory(Server server) throws IOException {
        createFile();
    }

    private void createFile() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void storeHistory(String sender, String reciever, String message) throws IOException {
        date();
        time();
        this.sender = sender;
        this.reciever = reciever;
        messageWriter = new FileWriter(file, true);
        messageWriter.write(sender + "\t" + reciever + "\t" + sender + " : " + message + "\t" + newDate + "\t" + newTime + "\n");
        messageWriter.flush();

    }

    public ArrayList<String> getHistory(String sender, String reciever) {
        try {
            date();
            ceckPreDate();
            this.sender = sender;
            this.reciever = reciever;
            messageReader = new FileReader(file);
            bufferedReader = new BufferedReader(messageReader);

            String line;
            int countYesterday = 0;
            int countToday = 0;

            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split("\t");
                if ((temp[0].equals(sender) && temp[1].equals(reciever)) || (temp[0].equals(reciever) && temp[1].equals(sender))) {
                    if (hisDate.equals(temp[3])) {
                        countYesterday++;
                        if (countYesterday == 1) {
                            messageArray.add("\t  -- " + temp[3] + " --\n");
                        }
                        messageArray.add(temp[2] + "\n" + "\t\t\t" + temp[4]);
                        messageArray.add("\n");
                    }
                    if (newDate.equals(temp[3])) {
                        countToday++;
                        if (countToday == 1) {
                            messageArray.add("\t  -- " + temp[3] + " --\n");
                        }
                        messageArray.add(temp[2] + "\n" + "\t\t\t" + temp[4]);
                        messageArray.add("\n");
                    }
                }
            }
            // bufferedReader.close();
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] temp = line.split("\t");
//                if ((temp[0].equals(sender) && temp[1].equals(reciever)) || (temp[0].equals(reciever) && temp[1].equals(sender))) {
//                    if (newDate.equals(temp[3])) {
//                        messageArray.add(temp[2] + "\n" + "\t\t\t" + temp[4]);
//                        messageArray.add("\n");
//                    }
//                }
//            }
            bufferedReader.close();
           // if (messageArray.size() != 0) {

            //  }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MessageHistory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MessageHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageArray;
    }

    private void date() {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        newDate = sdf.format(date);
    }

    private void time() {
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        String newDate = time.format(date);
        newTime = time.format(date);
    }

    private void ceckPreDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date preDate = calendar.getTime();
        hisDate = sdf.format(preDate);
    }

}
