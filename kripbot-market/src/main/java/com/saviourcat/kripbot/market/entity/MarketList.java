package com.saviourcat.kripbot.market.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by saviourcat on 9/21/17.
 */
@Entity
@Table(name="market_list")
public class MarketList {
    @Id
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

    @Column(name="c1_code")
    private String c1Code;

    @Column(name="c2_code")
    private String c2Code;

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

    public String getC1Code() {
        return c1Code;
    }

    public void setC1Code(String c1Code) {
        this.c1Code = c1Code;
    }

    public String getC2Code() {
        return c2Code;
    }

    public void setC2Code(String c2Code) {
        this.c2Code = c2Code;
    }
}
