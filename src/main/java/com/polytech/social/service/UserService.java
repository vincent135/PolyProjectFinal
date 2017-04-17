package com.polytech.social.service;


import com.polytech.social.generic.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);
}
