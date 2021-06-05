package com.app.rindus.rest;

import com.app.rindus.dto.PostDTO;
import com.app.rindus.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostRestService {

	private final PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	String get() {
		return this.postService.findAll();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	String delete() {
		return "Hello from delete";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	String post(@RequestBody final PostDTO request) {
		return this.postService.save(request);
	}

	@RequestMapping(method = RequestMethod.PUT)
	String put() {
		return "Hello from put";
	}
}
