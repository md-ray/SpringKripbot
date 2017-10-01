package com.saviourcat.kripbot.market.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saviourcat.kripbot.market.KripbotException;
import com.saviourcat.kripbot.market.entity.TickUpdate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by saviourcat on 9/24/17.
 */
public class VipBitcoinConnection extends MarketProviderConnection {
    private static final String URL = "https://vip.bitcoin.co.id/api/%s/ticker";

    @Override
    public TickUpdate getTickUpdate() throws IOException, KripbotException {
        String mCode = getmCode();
        final String uri = String.format(URL, mCode);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonobj = mapper.readTree(result.getBody());
        System.out.println("httpres=" + result.getBody());
        if (jsonobj.has("ticker")) {

            JsonNode hasil = jsonobj.get("ticker");
            System.out.println("httpres=" + hasil.toString());
            TickUpdate tick = new TickUpdate();
            tick.setmCode(mCode);
            tick.setBid(Float.parseFloat(hasil.get("buy").asText()));
            tick.setLast(Float.parseFloat(hasil.get("last").asText()));
            tick.setAsk(Float.parseFloat(hasil.get("sell").asText()));
            tick.setServerTime(Long.parseLong(jsonobj.get("server_time").asText()) * 1000);
            tick.setTimestamp(new Date());
            System.out.println("hasil=" + tick.getLast());
            return tick;
        } else {
            System.out.println("tidak ketemu...");
            throw new KripbotException("VIP bitcoin response cannot be parsed");
        }
    }
}

