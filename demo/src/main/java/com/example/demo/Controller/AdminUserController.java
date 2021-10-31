package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserV2;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Service.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
	@Autowired
	UserDaoService service;
	
	@GetMapping("/users")
	public MappingJacksonValue retrieveAllUsers(){
		List<User> users = service.findAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id","name","joinDate","password");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		return mapping;
	}
	
	// GET /admin/users/1 -> /admin/v1/users/1
	@GetMapping("/v1/users/{id}")
	public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not fount", id));
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id","name","joinDate","password" ,"ssn");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/v2/users/{id}")
	public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not fount", id));
		}
		
		// User -> User2 로 복사
		UserV2 userv2 = new UserV2();
		BeanUtils.copyProperties(user, userv2); // id, name, joinDate, ssn
		userv2.setGrade("VIP");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id","name","joinDate","grade" ,"ssn");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("userInfoV2",filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
	
}

