/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse38.connector;

import edu.ijse.gdse38.controller.ChatController;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author SW96
 */
public class ChatConnector {

    private static ChatConnector chatConnector;
    private ChatController chatController;

    private ChatConnector() throws NotBoundException, MalformedURLException, RemoteException {
        chatController = (ChatController) Naming.lookup("rmi://localhost:5050/SW");
    }

    public static ChatConnector getChatConnector() throws NotBoundException, MalformedURLException, RemoteException {
        if (chatConnector == null) {
            chatConnector = new ChatConnector();
        }
        return chatConnector;
    }

    public ChatController getChatController() {
        return chatController;
    }
}
