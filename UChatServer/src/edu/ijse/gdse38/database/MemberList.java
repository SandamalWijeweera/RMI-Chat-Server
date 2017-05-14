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
public class MemberList {

    private File file = new File("PrivateMemberList.txt");
    private FileWriter privateListWriter;
    private FileReader privaListReader;
    private BufferedReader bufferedReader;
    private String userName;
    private String password;
    private String newDate;
    private boolean isAMember;
    private Server server = new Server();

    public MemberList() throws IOException {
        creatrMemberFile();
        String newDate = server.getDate();
    }

    /**
     * Create the File
     *
     * @throws IOException
     */
    private void creatrMemberFile() throws IOException {
        if (!file.exists()) {
            file = new File("PrivateMemberList.txt");
            file.createNewFile();
            String newDate = server.getDate();
        }
    }

    public void checkMemberForLogin(String userName, String password) throws IOException {
        creatrMemberFile();
        assignMember(userName, password);
        ifAMemberToLogIn();
    }

    public void checkMemberForRegister(String userName, String password) throws IOException {
        creatrMemberFile();
        assignMember(userName, password);
        ifAMemberToRegister();
    }

    /**
     *
     * @param Assign Values to the Variables
     */
    private void assignMember(String userName, String password) throws IOException {
        this.userName = userName;
        this.password = password;
        this.newDate = server.getDate();
    }

    /**
     *
     * @param userName
     * @param password
     * @throws java.io.FileNotFoundException
     */
    public void addMember(String userName, String password) throws FileNotFoundException, IOException {
        privateListWriter = new FileWriter(file, true);
        privateListWriter.write(userName + ",," + password + ",," + this.newDate);
        privateListWriter.write(",,,,");
        privateListWriter.write("\n");
        privateListWriter.flush();
    }

    /**
     * Read the Data From File For Login Window
     */
    private void ifAMemberToLogIn() throws FileNotFoundException, IOException {
        privaListReader = new FileReader(file);
        bufferedReader = new BufferedReader(privaListReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String details[] = line.split(",,");
            if (details[0].equalsIgnoreCase(this.userName) && details[1].equalsIgnoreCase(this.password)) {
                isAMember = true;
                break;
            } else {
                //System.out.println("Name" + details[0] + "Password" + details[1]);
                isAMember = false;
            }
        }
        bufferedReader.close();
    }

    private void ifAMemberToRegister() throws IOException {
        privaListReader = new FileReader(file);
        bufferedReader = new BufferedReader(privaListReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String details[] = line.split(",,");
            if (details[0].equalsIgnoreCase(this.userName)) {
                isAMember = true;
                break;
            } else {
                isAMember = false;
            }
        }
        bufferedReader.close();
    }

    public boolean returnIfAMember() {
        return isAMember;
    }

}
