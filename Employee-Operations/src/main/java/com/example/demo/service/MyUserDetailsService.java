package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserDAO;
import com.example.demo.repository.UserRepository;



@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		
		UserDAO user = userrepo.findByUsername(username);
		
		if(user!=null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(username));
			return new User(user.getUsername(),user.getPassword(),roles);
		}
		
//		this is for hardcoded user
//		if (username.equals("jo")){
//		return new User("jo", "jens",new ArrayList<>());
//		}
		
		else {
		throw new UsernameNotFoundException("invalid username");
	}
		
	
}
	
	public UserDAO saveUser(UserDAO user) {
		return userrepo.save(user);
	}
}
