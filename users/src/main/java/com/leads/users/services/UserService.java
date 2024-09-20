package com.leads.users.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.leads.users.controllers.UserController;
import com.leads.users.dtos.UserDto;
import com.leads.users.exceptions.UserNotFoundException;
import com.leads.users.models.User;
import com.leads.users.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		if (!userList.isEmpty()) {
			userList.forEach(this::addLinks);
		}
		return userList;
	}

	public User saveUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return userRepository.save(user);
	}

	public User findById(UUID id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado, id: " + id));
	}

	public User updateUser(UUID id, UserDto userDto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado, id: " + id));

		BeanUtils.copyProperties(userDto, user);
		return userRepository.save(user);
	}

	public void deleteUser(UUID id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado, id: " + id));

		userRepository.delete(user);
	}

	private void addLinks(User user) {
		UUID id = user.getId();
		Link selfLink = linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel();
		user.add(selfLink);

	}

}
