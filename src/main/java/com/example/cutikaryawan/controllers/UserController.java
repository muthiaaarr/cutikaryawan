package com.example.cutikaryawan.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cutikaryawan.models.User;
import com.example.cutikaryawan.models.dtos.UserDTO;
import com.example.cutikaryawan.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	ModelMapper mapper = new ModelMapper();
	
	// CREATE USER
	@PostMapping("/create")
	public Map<String, Object> createUser(@RequestBody UserDTO userDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		User user = new User();
		user = mapper.map(userDTO, User.class);
		userRepository.save(user);
		
		userDTO.setUserId(user.getUserId());
		result.put("Message", "Create user success!");
		result.put("Data", userDTO);
		
		return result;
	}
	
	// READ ALL USER
	@GetMapping("/readAll")
	public Map<String, Object> readAllUser() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		
		for (User u : userList) {
			UserDTO userDTO = mapper.map(u, UserDTO.class);
			userDTOList.add(userDTO);
		}
		
		result.put("Message", "Read all users success!");
		result.put("Data", userDTOList);
		result.put("Total items", userDTOList.size());
		
		return result;
	}
	
	// READ USER BY ID
	@GetMapping("/read")
	public Map<String, Object> readUserById(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		User user = userRepository.findById(id).get(); // bikin exception
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		
		result.put("Message", "Read user by id success!");
		result.put("Data", userDTO);
		
		return result;
	}
	
	// UPDATE USER
	@PutMapping("/update")
	public Map<String, Object> updateUser(@RequestParam("id") Long id, @RequestBody UserDTO userDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		User user = userRepository.findById(id).get(); // bikin exception
		user = mapper.map(userDTO, User.class);
		user.setUserId(id);
		userRepository.save(user);
		
		userDTO.setUserId(id);
		result.put("Message", "Update user success!");
		result.put("Data", userDTO);
		
		return result;
	}
	
	// DELETE USER
	@DeleteMapping("/delete")
	public Map<String, Object> deleteUser(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		User user = userRepository.findById(id).get(); // bikin exception
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		
		userRepository.delete(user);
		
		userDTO.setUserId(id);
		result.put("Message", "Delete user success!");
		result.put("Data", userDTO);
		
		return result;
	}
	
}
