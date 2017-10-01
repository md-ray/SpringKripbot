package com.saviourcat.kripbot.market;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MarketController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String status() {
        return "Kripbot Market Service API Service is running!";
    }

    @RequestMapping(value = "/market/tick/{tickName}", method = RequestMethod.GET)
    @ResponseBody
    public String getMarketTick(@PathVariable("tickName") String tickName) {
        return "Current Tick=" + tickName;
    }
}
