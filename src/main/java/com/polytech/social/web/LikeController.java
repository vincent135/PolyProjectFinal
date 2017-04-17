package com.polytech.social.web;

import com.polytech.social.repository.UserRepository;
import com.polytech.social.generic.Like;
import com.polytech.social.generic.Post;
import com.polytech.social.generic.User;
import com.polytech.social.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class LikeController {
	@Autowired
	private PublicationService publicationService;

	@Autowired
	private UserRepository userRepository;

	@ResponseBody
	@RequestMapping(value = "/likes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Like  like(@RequestParam(value="id", required=true) Long idPost, Principal principal){
		Post post = publicationService.fetchPost(idPost);
		Like like = new Like(post, userRepository.findByUsername(principal.getName()));
		publicationService.like(like);
		System.out.println(userRepository.findAll());
		System.out.println(publicationService.fetchAllLike());
		return like;
	}

	@ResponseBody
	@RequestMapping(value = "/likes/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String likeCount(@RequestParam(value="id", required=true) Long idPost){
		Post post = publicationService.fetchPost(idPost);
		List<Like> likes = publicationService.fetchAllLike(post);
		String template = "{\"Post\" : %s, \"likes_count\" : %d }";
		return String.format(template, post.toString(), likes.size());
	}

	@ResponseBody
	@RequestMapping(value = "/likes/for", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> likeFor(@RequestParam(value="id", required=true) Long idPost){
		Post post = publicationService.fetchPost(idPost);
		List<Like> likes = publicationService.fetchAllLike(post);
		List<User> likers = new LinkedList<>();
		for (Like l : likes){
			likers.add(l.getAuthor());
		}
		return likers;
	}
}
