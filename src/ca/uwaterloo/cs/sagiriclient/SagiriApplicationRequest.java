/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uwaterloo.cs.sagiriclient;

import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author ShihaburRahman
 */
public class SagiriApplicationRequest implements Serializable{
    public SagiriApplicationRequest() {
        this.requestCode = Byte.MIN_VALUE;
        this.privateKey = Integer.MIN_VALUE;
        this.internalPort = Short.MIN_VALUE;
        this.externalPort = Short.MIN_VALUE;
    }
    
    public SagiriApplicationRequest(
            byte requestCode, int privateKey, short internalPort, short externalPort) {
        this.requestCode = requestCode;
        this.privateKey = privateKey;
        this.internalPort = internalPort;
        this.externalPort = externalPort;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeByte(requestCode);
        out.writeInt(privateKey);
        out.writeShort(internalPort);
        out.writeShort(externalPort);
    }
    
    private void readObject(ObjectInputStream in) 
            throws IOException, InvalidClassException {
        try {
            this.requestCode = in.readByte();
            this.privateKey = in.readInt();
            this.internalPort = in.readShort();
            this.externalPort = in.readShort();
        } catch (EOFException eofException) {
            throw new InvalidClassException("Too few fields");
        } catch (IOException ioException) {
            throw ioException;
        }
    }
    
    public byte getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(byte requestCode) {
        this.requestCode = requestCode;
    }

    public int getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(int privateKey) {
        this.privateKey = privateKey;
    }

    public short getInternalPort() {
        return internalPort;
    }

    public void setInternalPort(short internalPort) {
        this.internalPort = internalPort;
    }

    public short getExternalPort() {
        return externalPort;
    }

    public void setExternalPort(short externalPort) {
        this.externalPort = externalPort;
    }
    
    // Sagiri application request message fields
    private byte requestCode;
    private int privateKey;
    private short internalPort;
    private short externalPort;

}
