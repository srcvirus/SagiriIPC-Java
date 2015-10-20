/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uwaterloo.cs.sagiriclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author ShihaburRahman
 */
public class SagiriConnection {

    public SagiriConnection() {
        this.socket = null;
        this.hostname = "";
        this.port = Short.MIN_VALUE;
    }
    
    public SagiriConnection(String hostname, short port) {
        this.socket = null;
        this.hostname = hostname;
        this.port = port;
    }
    
    public SagiriDaemonGreeting connect(String hostname, short port)
        throws IOException, ClassNotFoundException {
        SagiriDaemonGreeting greeting = null;
        try {
            socket = new Socket(hostname, port);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            greeting = (SagiriDaemonGreeting) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw ex;
        }
        return greeting;
    }
    
    public String startForward(int privateKey, short internalPort, short externalPort) 
            throws Exception {
        String retMessage = null;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            SagiriApplicationRequest request = new SagiriApplicationRequest(
                    SagiriApplicationRequestCodes.HOST, privateKey, internalPort, 
                    externalPort);
            oos.writeObject(request);
            SagiriApplicationResponse response = 
                    (SagiriApplicationResponse) ois.readObject();
            retMessage = response.getMessage();
            socket.close();
        } catch (Exception ex) {
            throw ex;
        }
        return retMessage;
    }
    
    public String stopForward(int privateKey, short externalPort) throws Exception {
        String retMessage = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            SagiriApplicationRequest request = new SagiriApplicationRequest(
                    SagiriApplicationRequestCodes.STOP, privateKey, Short.MIN_VALUE, 
                    externalPort);
            oos.writeObject(request);
            SagiriApplicationResponse response = 
                    (SagiriApplicationResponse) ois.readObject();
            retMessage = response.getMessage();
            socket.close();
        } catch (Exception ex) {
            throw ex;
        }
        return retMessage;
    }
    
    public void close() throws Exception {
        try {
            socket.close();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    private Socket socket;
    private String hostname;
    private short port;
}
