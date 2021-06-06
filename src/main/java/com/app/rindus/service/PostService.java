package com.app.rindus.service;

import com.app.rindus.dto.PostDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class PostService extends BaseService<PostDTO> {

	private String url = "posts";

	String baseUrL() {
		return super.baseUrL() + this.url;
	}

	@Override
	public String update(PostDTO dto) {
		Gson gson = new Gson();
		PostDTO postDTO = null;
		try {
			postDTO = gson.fromJson(this.findObjectByID(dto.getId()).toString(), PostDTO.class);
			if(postDTO == null) return "Post not found";
			if(!ObjectUtils.isEmpty(dto.getDescription())) postDTO.setDescription(dto.getDescription());
			if(!ObjectUtils.isEmpty(dto.getTitle())) postDTO.setTitle(dto.getTitle());
		} catch(Exception ex) {
			System.err.println(ex);
			ex.printStackTrace();
			return "data not available";
		}

		return super.update(postDTO);
	}
}
