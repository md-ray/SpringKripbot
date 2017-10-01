package com.saviourcat.kripbot.market.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by saviourcat on 9/21/17.
 */
@Entity
@Table(name="current_tick")
public class Tick {
    @Id @GeneratedValue
    @Column(name="id")
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="eid")
    private int eid;

    @Column(name="m_code")
    private String mCode;

    @Column(name="last")
    private float last;

    @Column(name="ask")
    private float ask;

    @Column(name="bid")
    private float bid;

    @Column(name="server_time")
    private long serverTime;

    @Column(name="ts", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;



    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public float getLast() {
        return last;
    }

    public void setLast(float last) {
        this.last = last;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public void update(TickUpdate newTick) {
        last = newTick.getLast();
        bid = newTick.getBid();
        ask = newTick.getAsk();
        serverTime = newTick.getServerTime();
        timestamp = new Date();
    }
}
