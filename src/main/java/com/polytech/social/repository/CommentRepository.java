package com.polytech.social.repository;

import com.polytech.social.generic.Comment;
import com.polytech.social.generic.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPost(Post post);
}
