package com.example.XyhubHelpdesk.IdGenerator;

import java.io.Serializable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {

    private static long sequence = 5;
    private static final int WIDTH = 4; // Set the desired width for the sequential number

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        synchronized (CustomIdGenerator.class) {
            sequence++;
            String sequenceStr = String.format("%0" + WIDTH + "d", sequence);
            return "PO-" + sequenceStr;
        }
    }
}