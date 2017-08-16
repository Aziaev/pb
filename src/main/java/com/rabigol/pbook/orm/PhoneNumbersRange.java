package com.rabigol.pbook.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ranges")
public class PhoneNumbersRange implements IRange, Serializable {
    private static final long serialVersionUID = -5910818471336303026L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "startNumber")
    private long startNumber;
    @Column(name = "endNumber")
    private long endNumber;

    public PhoneNumbersRange(long startNumber, long endNumber) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    private PhoneNumbersRange() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(long startNumber) {
        this.startNumber = startNumber;
    }

    public long getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(long endNumber) {
        this.endNumber = endNumber;
    }

    @Override
    public String toString() {
        return "Phone numbers range [ " + id + " : " + startNumber + " - " + endNumber + " ]";
    }
}
