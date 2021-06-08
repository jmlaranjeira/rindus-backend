package com.app.rindus.rest;

import com.app.rindus.dto.PostDTO;
import com.app.rindus.service.PostService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostRestServiceTest {

	@Autowired
	private PostService postService;

	/**
	 * This test method
	 * @throws JSONException
	 */
	@Test
	void findAll() throws JSONException {
		PostRestService restService = new PostRestService(postService);
		String response = restService.get();
		JSONArray jsonArray = new JSONArray(response);
		Assert.isTrue(jsonArray.length() > 0, "No data available");
	}

	@Test
	void add() {
		PostRestService restService = new PostRestService(postService);
		Gson gson = new Gson();
		PostDTO postDTO = new PostDTO();
		postDTO.setId(4);
		postDTO.setTitle("post 4");
		postDTO.setDescription("This is the post number 4");
		String response = restService.post(postDTO);
		PostDTO responseDTO = gson.fromJson(response, PostDTO.class);
		assertEquals(postDTO, responseDTO);
	}
}
