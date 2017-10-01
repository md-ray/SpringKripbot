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
public class BittrexConnection extends MarketProviderConnection {
    private static final String URL = "https://bittrex.com/api/v1.1/public/getticker?market=%s";

    @Override
    public TickUpdate getTickUpdate() throws IOException, KripbotException {
        final String uri = String.format(URL, getmCode());

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonobj = mapper.readTree(result.getBody());
        if (jsonobj.has("success") && jsonobj.get("success").toString().equals("true")) {

            JsonNode hasil = jsonobj.get("result");
            TickUpdate tick = new TickUpdate();
            tick.setmCode(getmCode());
            tick.setBid(hasil.get("Bid").floatValue());
            tick.setLast(hasil.get("Last").floatValue());
            tick.setAsk(hasil.get("Ask").floatValue());
            tick.setTimestamp(new Date());
            return tick;
        } else {
            throw new KripbotException("Bittrex response cannot be parsed");
        }
    }
}

