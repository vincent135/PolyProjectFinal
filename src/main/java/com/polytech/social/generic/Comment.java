package com.polytech.social.generic;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
public class Comment {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@JsonIgnore(value = true)
	@ManyToOne
	@JoinColumn(name = "POST_ID")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private User AUTHOR;

	@Column(name = "CONTENT")
	private String CONTENT;

	public Comment() {
	}

	public Comment(Post POST, User AUTHOR, String CONTENT) {
		this.post = POST;
		this.AUTHOR = AUTHOR;
		this.CONTENT = CONTENT;
	}


	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}


	public Post getPost() {
		return post;
	}

	public void setPost(Post POST) {
		this.post = POST;
	}

	public User getAUTHOR() {
		return AUTHOR;
	}

	public void setAUTHOR(User AUTHOR) {
		this.AUTHOR = AUTHOR;
	}


	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String CONTENT) {
		this.CONTENT = CONTENT;
	}

	@Override
	public String toString() {
		return "Comment{" +
			"ID=" + ID +
			", POST=" + post +
			", AUTHOR=" + AUTHOR +
			", CONTENT='" + CONTENT + '\'' +
			'}';
	}
}
