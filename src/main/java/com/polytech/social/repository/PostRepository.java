package com.polytech.social.repository;


import com.polytech.social.generic.Post;

import java.util.List;

public interface PostRepository {
	void save(Post post);

	Post findById(long id);

	List<Post> findAll();
}
