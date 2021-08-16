package com.capgemini.services.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.model.User;
import com.capgemini.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepository.findByLogin(username).get();
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); 
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_NORMAL"));
		
		return new org.springframework.security.core.userdetails.User( user.getLogin(), user.getPassword(), grantedAuthorities);
	}



}
