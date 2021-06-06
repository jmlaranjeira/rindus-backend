package com.app.rindus.service;

import com.app.rindus.dto.AuthorDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends BaseService<AuthorDTO> {

	private String url = "authors";

	String baseUrL() {
		return super.baseUrL() + this.url;
	}
}
