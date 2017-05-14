/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse38.chatObserverImpl;

import edu.ijse.gdse38.observer.ChatObserver;
import edu.ijse.gdse38.view.MainWindow;
import edu.ijse.gdse38.view.PrivateChatWindow;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author SW 96
 */
public class ChatObserverImpl extends UnicastRemoteObject implements ChatObserver {

    private MainWindow mainWindow;
    private PrivateChatWindow privateChatWindow;
    private String[] onlineList;

    public ChatObserverImpl(MainWindow mainWindow) throws RemoteException {
        this.mainWindow = mainWindow;
    }

    public ChatObserverImpl(PrivateChatWindow privateChatWindow) throws RemoteException {
        this.privateChatWindow = privateChatWindow;
    }

    public ChatObserverImpl() throws RemoteException {
    }

    @Override
    public void update(String msgType, String message, String sender, String reciepent) throws RemoteException {
        if (reciepent.equalsIgnoreCase("All")) {
            mainWindow.setMessage(msgType, message, sender, reciepent);
        }
        if (msgType.equals("PRIVATE")) {
            privateChatWindow.setMessage(msgType, message, sender, reciepent);
        }
    }

    @Override
    public void sendRequest(String requester, String confirmer) throws RemoteException {
        mainWindow.requests(requester, confirmer);
    }

    @Override
    public void requestConfirmed(String requester, String confirmer) throws RemoteException {
        mainWindow.requestConfirmed(requester, confirmer);
    }

    @Override
    public void nameList(java.util.List<String> listSet) throws RemoteException {
        int memberCount = listSet.size();
        onlineList = new String[listSet.size()];
        listSet.toArray(onlineList);
        mainWindow.countpublicUserLabel.setText("Public(Online " + Integer.toString(memberCount) + ")");
        mainWindow.publicChatList.setListData(onlineList);
    }

    @Override
    public String[] getOnlineList() {
        return onlineList;
    }

    @Override
    public boolean sendFile(String sender, String reciepent, String name, byte[] mydata, int mylen) {
        boolean recieveFile = privateChatWindow.recieveFile(sender, reciepent, name, mydata, mylen);
        return recieveFile;
    }

    @Override
    public void requestIgnored(String sender, String reciepent) throws RemoteException {
        mainWindow.requestIgnored(sender, reciepent);
    }
}
