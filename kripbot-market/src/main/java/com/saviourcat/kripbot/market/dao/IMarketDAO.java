package com.saviourcat.kripbot.market.dao;

import com.saviourcat.kripbot.market.KripbotException;
import com.saviourcat.kripbot.market.entity.Tick;
import com.saviourcat.kripbot.market.entity.TickUpdate;

/**
 * Created by saviourcat on 9/21/17.
 */
public interface IMarketDAO {
    Tick getCurrentTick(int eid, String c1, String c2);
    Tick getCurrentTick(int eid, String mCode);
    void refreshTick(int eid, String mCode, TickUpdate tick);
    void refreshAllTicks(int eid) throws KripbotException;

}
