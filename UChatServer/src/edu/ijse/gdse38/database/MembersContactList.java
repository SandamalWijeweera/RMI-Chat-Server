/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse38.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author SW 96
 */
public class MembersContactList {

    private LinkedList<String> contactList;
    private File file = new File("PrivateContactList.txt");
    private FileWriter contactWriter;
    private FileReader contactReader;
    private BufferedReader bufferedReader;
    private String owner;
    private String contact;
    private boolean isExists;

    public MembersContactList() throws IOException {
        createContactFile();
        contactList = new LinkedList<>();
    }

    private void createContactFile() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public LinkedList<String> getContactList(String owner) throws IOException {
        contactList.clear();
        this.owner = owner;
        createContactFile();
        checkIfExists();
        if (isExists) {
            addToList();
        }
        bufferedReader.close();
        return contactList;
    }

    private void checkIfExists() throws IOException {
        contactReader = new FileReader(file);
        bufferedReader = new BufferedReader(contactReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String details[] = line.split(",,");
            if (details[0].equalsIgnoreCase(this.owner)) {
                isExists = true;
                break;
            } else {
                isExists = false;
            }
        }
        bufferedReader.close();
    }

    public void checkContact(String owner, String contact) throws IOException {
        contactReader = new FileReader(file);
        bufferedReader = new BufferedReader(contactReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String details[] = line.split(",,");
            if (details[0].equalsIgnoreCase(owner) && details[1].equals(contact)) {
                isExists = true;
                System.out.println(isExists);
                break;
            } else {
                isExists = false;
            }
        }
        bufferedReader.close();
    }

    public void storeContacts(String owner, String contact) throws IOException {
        this.owner = owner;
        this.contact = contact;
        checkContact(owner, contact);
        if (!isExists) {
            contactWriter = new FileWriter(file, true);
            contactWriter.write(owner + ",," + contact);
            contactWriter.write(",,,,");
            contactWriter.write("\n");
            contactWriter.write(contact + ",," + owner);
            contactWriter.write(",,,,");
            contactWriter.write("\n");
            contactWriter.flush();
        }
    }

    private void addToList() throws FileNotFoundException, IOException {
        if (isExists) {
            contactReader = new FileReader(file);
            bufferedReader = new BufferedReader(contactReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] details = line.split(",,");
                if (details[0].equalsIgnoreCase(this.owner)) {
                    contactList.add(details[1]);
                }
            }
        }
        bufferedReader.close();
    }

    public boolean ifHasContacts() {
        return isExists;
    }

    public void removeFriend(String name, String itemName) throws FileNotFoundException, IOException {
        contactReader = new FileReader(file);
        contactWriter = new FileWriter(file, true);
        bufferedReader = new BufferedReader(contactReader);
        PrintWriter printWriter = new PrintWriter(file);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String details[] = line.split(",,");
            if (details[0].equalsIgnoreCase(owner) && details[1].equals(contact)) {
                printWriter.println("Null Null");
            }
        }
    }

}
