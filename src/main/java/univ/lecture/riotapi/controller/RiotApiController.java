package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import univ.lecture.riotapi.AppController;
import univ.lecture.riotapi.model.Summoner;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1")
@Log4j
public class RiotApiController {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${riot.api.endpoint}")
    private String riotApiEndpoint;

    @Value("${riot.api.key}")
    private String riotApiKey;

    @RequestMapping(value = "/calc/{math}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Summoner querySummoner(@RequestBody @PathVariable("math") String math) throws UnsupportedEncodingException {/*
    	final String url = riotApiEndpoint + "/summoner/by-name/" +
                math +
                "?api_key=" +
                riotApiKey;

        String response = restTemplate.getForObject(url, String.class);
        Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(response);*/
    	AppController appController = new AppController();
    	double result = appController.run(math);
    	Date time = new Date();/*
    	Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(math);
        parsedMap.forEach((key, value) -> log.info(String.format("key [%s] type [%s] value [%s]", key, value.getClass(), value)));*/
        int teamId = 5;
        long now = time.getTime();/*
        Map<String, Object> summonerDetail = (Map<String, Object>) parsedMap.values().toArray()[0];
        String queriedName = (String)summonerDetail.get("name");
        int queriedLevel = (Integer)summonerDetail.get("summonerLevel");*/
        Summoner summoner = new Summoner(teamId, now, result);

        return summoner;
    }
}
