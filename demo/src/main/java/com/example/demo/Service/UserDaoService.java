package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;

@Service
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 3;
	
	static {
		users.add(new User(1,"Kenneth", new Date(), "pass1", "701010-1111111"));
		users.add(new User(2,"Alice", new Date(),"pass2", "701010-1111111"));
		users.add(new User(3,"Elena", new Date(),"pass3", "701010-1111111"));
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getUser_id() == id) {
				return user;
			}
		}
		return null;
	}

	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getUser_id() == null) {
			user.setUser_id(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getUser_id() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
