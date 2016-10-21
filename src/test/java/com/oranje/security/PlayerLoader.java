package com.oranje.security;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oranje.Application;
import com.oranje.domain.Player;
import com.oranje.repository.PlayerRepository;

 

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PlayerLoader {
	
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Test
	public void loadPlayers() throws IOException, JSONException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		ClassPathResource f = new ClassPathResource("/players.json");
		StringWriter writer = new StringWriter();
		IOUtils.copy(f.getInputStream(), writer);
		String theString = writer.toString();
		org.json.JSONArray arr = new org.json.JSONArray(theString);
		
		System.out.println(arr);
		
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			System.out.println(obj.toString());
			Player p = mapper.readValue(obj.toString(), Player.class);
			playerRepository.save(p);
		}
		
		//Files.readAllLines(Paths.get("classpath:players.json"));
	}

}
