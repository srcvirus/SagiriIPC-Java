/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uwaterloo.cs.sagiriclient;

import java.io.EOFException;
import java.io.Serializable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
/**
 *
 * @author ShihaburRahman
 */
public class SagiriDaemonGreeting implements Serializable {
    public SagiriDaemonGreeting() {
        this.majorVersion = Byte.MIN_VALUE;
        this.minorVersion = Byte.MIN_VALUE;
        this.patch = Byte.MIN_VALUE;
        socks5Port = Short.MIN_VALUE;
    }
    
    public SagiriDaemonGreeting(
            byte majorVersion, byte minorVersion, byte patch, short socks5Port) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patch = patch;
        this.socks5Port = socks5Port;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeByte(this.majorVersion);
        out.writeByte(this.minorVersion);
        out.writeByte(this.patch);
        out.writeShort(this.socks5Port);
    }
    
    private void readObject(ObjectInputStream in) 
            throws IOException, InvalidClassException {
        try {
            this.majorVersion = in.readByte();
            this.minorVersion = in.readByte();
            this.patch = in.readByte();
            this.socks5Port = in.readShort();
        } catch (EOFException eofException) {
            throw new InvalidClassException("Too few fields");
        } catch(IOException ioException) {
            throw ioException;
        } 
    }
    
    public byte getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(byte majorVersion) {
        this.majorVersion = majorVersion;
    }

    public byte getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(byte minorVersion) {
        this.minorVersion = minorVersion;
    }

    public byte getPatch() {
        return patch;
    }

    public void setPatch(byte patch) {
        this.patch = patch;
    }

    public short getSocks5Port() {
        return socks5Port;
    }

    public void setSocks5Port(short socks5Port) {
        this.socks5Port = socks5Port;
    }
    
    private byte majorVersion;
    private byte minorVersion;
    private byte patch;
    private short socks5Port;
}
