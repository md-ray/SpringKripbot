package com.saviourcat.kripbot.market.provider;

import com.saviourcat.kripbot.market.KripbotException;

/**
 * Created by saviourcat on 9/24/17.
 */
public class MarketProviderConnectionFactory {
    public static MarketProviderConnection getMarketProviderConnection(int eid) throws KripbotException {
        if (eid == 1) {
            MarketProviderConnection mpc = new BittrexConnection();
            mpc.setEid(eid);
            return mpc;
        } else if (eid == 2) {
            MarketProviderConnection mpc = new VipBitcoinConnection();
            mpc.setEid(eid);
            return mpc;
        } else if (eid == 3) {
            MarketProviderConnection mpc = new LunoConnection();
            mpc.setEid(eid);
            return mpc;
        } else {
            throw new KripbotException("Market EID not defined in factory");
        }
    }
}
