package com.polytech.social.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "LIKES")
public class Like {

	private Long id;

	@JsonIgnore(value = true)
	private Post post;


	private User author;

	public Like() {
	}

	public Like(Post post, User author) {
		this.post = post;
		this.author = author;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "POST_ID")
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Like{" +
			"id=" + id +
			", post=" + post +
			", author=" + author +
			'}';
	}
}
