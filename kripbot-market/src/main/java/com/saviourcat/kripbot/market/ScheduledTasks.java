package com.saviourcat.kripbot.market;

import com.saviourcat.kripbot.market.dao.IMarketDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private IMarketDAO marketDAO;

    @Scheduled(fixedRate = 60000)
    public void scheduleTaskWithFixedRate() {
        System.out.println("fixed rate");
//        Tick tick = marketDAO.getTickUpdate(1, "BTC", "ETH");

//        Tick newTick = new Tick();
//        newTick.setLast(0.9f);
//        newTick.setAsk(0.9f);
//        newTick.setBid(0.9f);
//        newTick.setServerTime(System.currentTimeMillis() / 1000);
//
////        System.out.println("fixed rate = " + tick.getLast());
//        marketDAO.refreshTick(1, "BTC-ITZ", newTick);
        try {
            marketDAO.refreshAllTicks(1);
            marketDAO.refreshAllTicks(2);
            marketDAO.refreshAllTicks(3);
        } catch (KripbotException e) {
            e.printStackTrace();
        }
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
