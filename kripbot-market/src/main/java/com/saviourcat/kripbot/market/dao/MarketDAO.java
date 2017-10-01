package com.saviourcat.kripbot.market.dao;

import com.saviourcat.kripbot.market.KripbotException;
import com.saviourcat.kripbot.market.entity.MarketList;
import com.saviourcat.kripbot.market.entity.Tick;
import com.saviourcat.kripbot.market.entity.TickHistory;
import com.saviourcat.kripbot.market.entity.TickUpdate;
import com.saviourcat.kripbot.market.provider.MarketProviderConnection;
import com.saviourcat.kripbot.market.provider.MarketProviderConnectionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

/**
 * Created by saviourcat on 9/21/17.
 */
@Transactional
@Repository
public class MarketDAO implements IMarketDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Tick getCurrentTick(int eid, String c1, String c2) {
        String q = "SELECT ml FROM MarketList ml WHERE eid=:eid AND c1Code=:c1 AND c2Code=:c2";
        MarketList ml = null;
        try {
            ml = entityManager.createQuery(q, MarketList.class)
                    .setParameter("eid", eid)
                    .setParameter("c1", c1)
                    .setParameter("c2", c2)
                    .getSingleResult();

            String mCode = ml.getmCode();
            return entityManager.createQuery(
                    "SELECT t FROM Tick t WHERE eid=:eid AND mCode=:mCode", Tick.class)
                    .setParameter("eid", eid)
                    .setParameter("mCode", mCode)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Tick getCurrentTick(int eid, String mCode) {
        try {
            return entityManager.createQuery(
                    "SELECT t FROM Tick t WHERE eid=:eid AND mCode=:mCode", Tick.class)
                    .setParameter("eid", eid)
                    .setParameter("mCode", mCode)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void refreshTick(int eid, String mCode, TickUpdate newTick) {
        Tick oldTick = getCurrentTick(eid, mCode);
        if (oldTick != null) {
//            entityManager.getTransaction().begin();
            TickHistory th = new TickHistory();
            th.populateTick(oldTick);
            oldTick.update(newTick);
//            entityManager.
//            entityManager.merge(oldTick);
            entityManager.persist(th);
//            entityManager.getTransaction().commit();
        } else {
            Tick brandNewTick = new Tick();
            brandNewTick.setEid(eid);
            brandNewTick.setmCode(mCode);
            brandNewTick.update(newTick);
            entityManager.persist(brandNewTick);
        }
    }

    @Override
    public void refreshAllTicks(int eid) throws KripbotException {
        try {
            List<MarketList> list = entityManager.createQuery(
                    "SELECT ml FROM MarketList ml WHERE eid=:eid", MarketList.class)
                    .setParameter("eid", eid)
                    .getResultList();
            for (MarketList ml : list) {
                MarketProviderConnection mpc = MarketProviderConnectionFactory.getMarketProviderConnection(eid);
                mpc.setmCode(ml.getmCode());
                TickUpdate updatedtick = mpc.getTickUpdate();
                System.out.println("GREP " + updatedtick.getmCode() + " = " + updatedtick.getLast());
                refreshTick(eid, ml.getmCode(), updatedtick);
            }


        } catch (NoResultException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
