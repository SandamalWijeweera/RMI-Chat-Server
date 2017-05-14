/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse38.chatObserverble;

import edu.ijse.gdse38.main.Server;
import edu.ijse.gdse38.observer.ChatObserver;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SW 96
 */
public class ChatObserverble {

    private LinkedList<ChatObserver> observerList = new LinkedList<>();
    private LinkedList<ChatObserver> prilist = new LinkedList<>();
    private List<String> onlineList = new ArrayList<>();
    private int countOfClients = 0;
    private String newDate;
//    private Server server;
//    private String name;

    public ChatObserverble() {

    }

    public void addChatobserver(ChatObserver chatObserver) throws RemoteException {
        observerList.add(chatObserver);
    }

    public void removeChatobserver(ChatObserver chatObserver) throws RemoteException {
        observerList.remove(chatObserver);
    }

    public void addPrivateChatobserver(ChatObserver chatObserver) throws RemoteException {
        prilist.add(chatObserver);
    }

    public void removePrivateChatobserver(ChatObserver chatObserver) throws RemoteException {
        prilist.remove(chatObserver);
    }

    public void noytifyObserver(String msgType, String message, String sender, String reciepent) throws RemoteException {
        for (ChatObserver chatObserver : observerList) {
            chatObserver.update(msgType, message, sender, reciepent);
        }
    }

    public void noytifyPrivateObserver(String msgType, String message, String sender, String reciepent) throws RemoteException {
        for (ChatObserver chatObserver : prilist) {
            chatObserver.update(msgType, message, sender, reciepent);
        }
    }

    public void sendRequest(String requester, String confirmer) throws RemoteException {
        for (ChatObserver chatObserver : observerList) {
            chatObserver.sendRequest(requester, confirmer);
        }
    }

    public void requestConfirmed(String requester, String confirmer) throws RemoteException {
        for (ChatObserver chatObserver : observerList) {
            chatObserver.requestConfirmed(requester, confirmer);
        }
    }

    public void requestIgnored(String sender, String reciepent) throws RemoteException {
        for (ChatObserver chatObserver : observerList) {
            chatObserver.requestIgnored(sender, reciepent);
        }
    }

    public void notifyOnlineList(Server server, String name, String mark) throws RemoteException {
//        this.server = server;
//        this.name = name;
        setDate();
        if (mark.equals("+")) {
            server.serverStatus.append(name + " Connected to Server @ " + this.newDate + " ...\n");
            onlineList.add(name);
            countOfClients = onlineList.size();
            for (ChatObserver ob : observerList) {
                ob.nameList(onlineList);
            }

            server.noOfUsers.setText(Integer.toString(countOfClients));
        } else {
            onlineList.remove(name);
            countOfClients = onlineList.size();
            server.serverStatus.append(name + " Leave Server @ " + this.newDate + " ...\n");
            for (ChatObserver ob : observerList) {
                ob.nameList(onlineList);
            }
            server.noOfUsers.setText(Integer.toString(countOfClients));
        }

    }

    public String[] getOnlineList() {
        String[] online = new String[countOfClients];
        onlineList.toArray(online);
        return online;
    }

    private void setDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        newDate = sdf.format(date);
    }

    public boolean sendFile(String sender, String reciepent, String name, byte[] mydata, int mylen) throws RemoteException {
        boolean sent = false;
        for (ChatObserver chatObserver : prilist) {
            chatObserver.sendFile(sender, reciepent, name, mydata, mylen);
            sent = true;
        }
        return sent;
    }

}
