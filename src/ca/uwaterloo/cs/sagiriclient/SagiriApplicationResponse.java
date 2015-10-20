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

/**
 *
 * @author ShihaburRahman
 */
public class SagiriApplicationResponse {

    public SagiriApplicationResponse() {
        this.responseCode = Byte.MIN_VALUE;
        this.message = "";
    }
    
    public SagiriApplicationResponse(
            byte requestCode, short messageLength, String message) {
        this.responseCode = requestCode;        
        this.message = message;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeByte(this.responseCode);
        out.writeShort(this.message.length());
        out.writeBytes(this.message);
    }
    
    private void readObject(ObjectInputStream in) 
            throws IOException, InvalidClassException {
        try {
            this.responseCode = in.readByte();
            short messageLength = in.readShort();
            for (int i = 0; i < messageLength; ++i)
                this.message += in.readChar();
        } catch (EOFException eofException) {
            throw new InvalidClassException("Too few fields");
        } catch (IOException ioException) {
            throw ioException;
        }
    }

    public byte getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(byte requestCode) {
        this.responseCode = requestCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
       
    private byte responseCode;
    private String message;
}
