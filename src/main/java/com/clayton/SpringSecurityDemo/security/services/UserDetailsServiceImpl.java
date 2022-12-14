package com.clayton.SpringSecurityDemo.security.services;

import com.clayton.SpringSecurityDemo.model.User;
import com.clayton.SpringSecurityDemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User with name: "+ username + " NOT FOUND!"));

        return UserDetailsImpl.build(user);
    }
}
