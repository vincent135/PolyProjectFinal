package com.polytech.social.repository;

import com.polytech.social.generic.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class JpaPostRepository implements PostRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public JpaPostRepository(){
	}

	public void save(Post post) {
		entityManager.persist(post);
	}

	@Override
	public Post findById(long id) {
		return (entityManager.find(Post.class, id));
	}

	public List<Post> findAll() {
		Query query = entityManager.createQuery("SELECT p FROM Post p");
		return query.getResultList();

	}

}
