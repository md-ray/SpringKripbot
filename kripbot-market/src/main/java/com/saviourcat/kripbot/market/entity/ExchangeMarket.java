package com.saviourcat.kripbot.market.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by saviourcat on 9/21/17.
 */
@Entity
@Table(name="exchange_market")
public class ExchangeMarket {
    @Id
    @Column(name="eid")
    private int eid;

    @Column(name="title")
    private String title;

    @Column(name="status")
    private int status;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
