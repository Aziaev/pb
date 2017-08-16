package com.rabigol.pbook.orm;

import java.io.Serializable;

public class Range implements Serializable {
    private static final long serialVersionUID = -4865934402613314900L;
    private String fromNumber;
    private String toNumber;

    public Range(String fromNumber, String toNumber) {
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
    }

    public Range() {
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }
}
