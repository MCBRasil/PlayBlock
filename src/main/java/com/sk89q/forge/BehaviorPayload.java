package com.sk89q.forge;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BehaviorPayload implements Payload {
    
    private int type;
    private Payload payload;

    public BehaviorPayload() {
    }

    public BehaviorPayload(int type, Payload payload) {
        setType(type);
        setPayload(payload);
    }

    public BehaviorPayload(Enum<?> type, Payload payload) {
        setType(type);
        setPayload(payload);
    }

    public int getType() {
        return type;
    }
    
    public boolean isType(Enum<?> e) {
        return e.ordinal() == getType();
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setType(Enum<?> type) {
        this.type = type.ordinal();
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        setType(in.readByte() & 0xff);
        if (payload != null) {
            payload.read(in);
        }
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.write(type);
        if (payload != null) {
            payload.write(out);
        }
    }

}