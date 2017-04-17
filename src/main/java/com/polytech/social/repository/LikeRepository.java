package com.polytech.social.repository;

import com.polytech.social.generic.Like;
import com.polytech.social.generic.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;



@Repository
@Transactional
public interface LikeRepository extends JpaRepository<Like, Long> {
	List<Like> findByPost(Post post);
}
