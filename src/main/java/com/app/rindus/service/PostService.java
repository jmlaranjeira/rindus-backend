package com.app.rindus.service;

import com.app.rindus.dto.PostDTO;
import org.springframework.stereotype.Service;

@Service
public class PostService extends BaseService<PostDTO> {

	private String url = "posts";

	String baseUrL() {
		return super.baseUrL() + this.url;
	}

}
