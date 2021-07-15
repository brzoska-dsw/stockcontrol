package com.pc.stockcontrol.service;

import com.pc.stockcontrol.exceptions.userNotFoundException;
import com.pc.stockcontrol.repository.UserRepository;
import com.pc.stockcontrol.utility.UserDetailsAdapter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class InitUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws userNotFoundException {
        return userRepository.findByName(userName)
                .map(UserDetailsAdapter::new)
                .orElseThrow(() -> new userNotFoundException(userName + " not found"));
    }
}