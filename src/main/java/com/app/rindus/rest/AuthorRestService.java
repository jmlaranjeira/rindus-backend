package com.app.rindus.rest;

import com.app.rindus.dto.AuthorDTO;
import com.app.rindus.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthorRestService {

	private final AuthorService authorService;

	@RequestMapping(method = RequestMethod.GET)
	String get() {
		return this.authorService.findAll();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	String delete() {
		return "Hello from delete";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	String post(@RequestBody final AuthorDTO request) {
		request.setDateRegistry(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		return this.authorService.add(request);
	}

	@RequestMapping(method = RequestMethod.PUT)
	String put(@RequestBody final AuthorDTO request){
		return this.authorService.update(request);
	}
}
