/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse38.chatControllerImpl;

import edu.ijse.gdse38.chatObserverble.ChatObserverble;
import edu.ijse.gdse38.controller.ChatController;
import edu.ijse.gdse38.database.MemberList;
import edu.ijse.gdse38.database.MembersContactList;
import edu.ijse.gdse38.database.MessageHistory;
import edu.ijse.gdse38.main.Server;
import edu.ijse.gdse38.observer.ChatObserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author SW 96
 */
public class ChatControlerImpl extends UnicastRemoteObject implements ChatController {

    private ChatObserverble chatObserverble = new ChatObserverble();
    private MemberList memberList;
    private MembersContactList membersContactList;
    private MessageHistory messageHistory;
    private Server server;

    public ChatControlerImpl() throws RemoteException, IOException {
        this.memberList = new MemberList();
        this.membersContactList = new MembersContactList();
        this.messageHistory = new MessageHistory();
    }

    public ChatControlerImpl(Server server) throws RemoteException, IOException {
        this.memberList = new MemberList();
        this.membersContactList = new MembersContactList();
        this.messageHistory = new MessageHistory();
        this.server = server;
    }

    @Override
    public void addChatObserver(ChatObserver chatObserver, String userName) throws RemoteException {
        chatObserverble.addChatobserver(chatObserver);
        chatObserverble.notifyOnlineList(server, userName, "+");
    }

    @Override
    public void removeChatObserver(ChatObserver chatObserver, String userName) throws RemoteException {
        chatObserverble.removeChatobserver(chatObserver);
        chatObserverble.notifyOnlineList(server, userName, "-");
    }

    @Override
    public void sendMessage(String msgType, String message, String sender, String reciepent) throws RemoteException, IOException {
        if (msgType.equals("PRIVATE")) {
            chatObserverble.noytifyPrivateObserver(msgType, message, sender, reciepent);
        } else {
            chatObserverble.noytifyObserver(msgType, message, sender, reciepent);
        }
    }

    @Override
    public void addPrivateChatObserver(ChatObserver chatObserver, String userName) throws RemoteException {
        chatObserverble.addPrivateChatobserver(chatObserver);
    }

    @Override
    public void remPrivateChatObserver(ChatObserver chatObserver, String userName) throws RemoteException {
        chatObserverble.removePrivateChatobserver(chatObserver);
    }

    @Override
    public void checkMemberForLogin(String userName, String password) throws RemoteException, IOException {
        memberList.checkMemberForLogin(userName, password);

    }

    @Override
    public boolean returnIfAMember(String userName, String password) throws RemoteException {
        boolean returnIfAMember = memberList.returnIfAMember();
        return returnIfAMember;
    }

    @Override
    public void addMember(String userName, String password) throws RemoteException, IOException {
        memberList.addMember(userName, password);
    }

    @Override
    public void checkMemberForRegister(String userName, String password) throws RemoteException, IOException {
        memberList.checkMemberForRegister(userName, password);
    }

    @Override
    public LinkedList<String> getContactList(String owner) throws RemoteException, IOException {
        LinkedList<String> contactList = membersContactList.getContactList(owner);
        return contactList;
    }

    @Override
    public void storeContacts(String owner, String contact) throws RemoteException, IOException {
        membersContactList.storeContacts(owner, contact);
    }

    @Override
    public String[] getOnlineList() throws RemoteException, IOException {
        String[] onlinelist = chatObserverble.getOnlineList();
        return onlinelist;
    }

    @Override
    public void storeHistory(String sender, String reciepent, String message) throws RemoteException, IOException {
        messageHistory.storeHistory(sender, reciepent, message);
    }

    @Override
    public boolean ifHasContacts() throws RemoteException, IOException {
        boolean ifHasContacts = membersContactList.ifHasContacts();
        return ifHasContacts;
    }

    @Override
    public void checkContact(String owner, String contact) throws RemoteException, IOException {
        membersContactList.checkContact(owner, contact);
    }

    @Override
    public void sendRequest(String requester, String confirmer) throws RemoteException, IOException {
        chatObserverble.sendRequest(requester, confirmer);
    }

    @Override
    public void requestConfirmed(String confirmer, String requester) throws RemoteException, IOException {
        chatObserverble.requestConfirmed(requester, confirmer);
    }

    @Override
    public boolean sendDocument(String sender, String reciepent, String name, byte[] mydata, int mylen) throws RemoteException, IOException {
        boolean accept = chatObserverble.sendFile(sender, reciepent, name, mydata, mylen);
        return accept;
    }

    @Override
    public ArrayList<String> getHistory(String sender, String reciepent) throws RemoteException, IOException {
        ArrayList<String> history = messageHistory.getHistory(sender, reciepent);
        return history;
    }

    @Override
    public void removeFriend(String name, String itemName) throws RemoteException, IOException {
        membersContactList.removeFriend(name, itemName);
    }

    @Override
    public void requestIgnored(String sender, String reciepent) throws RemoteException, IOException {
        chatObserverble.requestIgnored(sender, reciepent);
    }
}
