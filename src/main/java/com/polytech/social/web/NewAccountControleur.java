package com.polytech.social.web;

import com.polytech.social.repository.RoleRepository;
import com.polytech.social.repository.UserRepository;
import com.polytech.social.service.SecurityService;
import com.polytech.social.service.UserService;
import com.polytech.social.service.CheckForm;
import com.polytech.social.generic.Role;
import com.polytech.social.generic.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;

@Controller
public class NewAccountControleur {


	UserRepository userRepository;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserService userService;
	@Autowired
	private CheckForm userValidator;
	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(value = "/new_account", method = RequestMethod.GET)
	public String createAccountForm(){
		return "new_account";
	}


	@RequestMapping(value = "/new_account", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		System.out.println(bindingResult);

		if (bindingResult.hasErrors()) {
			return "new_account";
		}

		userForm.setRoles(new HashSet<Role>());
		userForm.getRoles().add(roleRepository.findAll().get(0));

		userService.save(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/";
	}

}
