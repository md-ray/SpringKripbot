package com.saviourcat.kripbot.market.provider;

import com.saviourcat.kripbot.market.KripbotException;
import com.saviourcat.kripbot.market.entity.TickUpdate;

import java.io.IOException;

/**
 * Created by saviourcat on 9/24/17.
 */
public abstract class MarketProviderConnection {
    private String mCode;

    private int eid;

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

    public abstract TickUpdate getTickUpdate() throws IOException, KripbotException;
}
