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
import univ.lecture.riotapi.model.Result;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1/")
@Log4j
public class RiotApiController {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${riot.api.endpoint}")
	private String riotApiEndpoint;

	@Value("${riot.api.key}")
	private String riotApiKey;

	@RequestMapping(value = "/summoner/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String querySummoner(@PathVariable("name") @RequestBody String expression)
			throws UnsupportedEncodingException {
		Date date = new Date();
		AppController appController = new AppController();

		int queriedId = 5;
		long queriedNow = date.getTime();
		double queriedResult = appController.run(expression);

		expression = String.valueOf(queriedResult);

		final String url = riotApiEndpoint;

		// String response = restTemplate.getForObject(url, String.class);
		// Map<String, Object> parsedMap = new
		// JacksonJsonParser().parseMap(response);

		Result result = new Result(queriedId, queriedNow, queriedResult);

		Gson gson = new Gson();

		String test = gson.toJson(result);
		String res = restTemplate.postForObject(url, test, String.class);

		return res;
	}
}