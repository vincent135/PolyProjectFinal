package com.polytech.social.service;

import com.polytech.social.generic.Comment;
import com.polytech.social.generic.Like;
import com.polytech.social.generic.Post;

import java.util.List;

public interface PublicationService {
	void post(Post post);

	void like(Like like);

	List<Like> fetchAllLike();

	List<Like> fetchAllLike(Post post);

	void comment(Comment comment);

	List<Comment> fetchAllComment();

	Post fetchPost(Long id);

	List<Post> fetchAll();

	List<Comment> fetchAllComment(Post post);
}
