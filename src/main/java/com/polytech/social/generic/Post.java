package com.polytech.social.generic;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "POST")
public class Post {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "AUTHOR")
	private String author;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Like> likes;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Comment> comments;


	//Pour le reemplissage depuis une requête post
	public Post() {
	}


	public Post(String content, String author) {
		this.content = content;
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "{\'" + content + "\'}";
	}


	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Like> getLikes() {
		if (likes == null)
			return new LinkedList<>();
		return likes;
	}


	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<Comment> getComments() {
		if (comments == null)
			return new LinkedList<>();
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
