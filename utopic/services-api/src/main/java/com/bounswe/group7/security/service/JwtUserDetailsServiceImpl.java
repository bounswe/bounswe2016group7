package com.bounswe.group7.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.security.JwtUserFactory;
import com.bounswe.group7.repository.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    public final Logger logger = LogManager.getLogger(JwtUserDetailsServiceImpl.class);
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = null;
        try {
            user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return JwtUserFactory.create(user);
            }
        } catch (Exception ex) {
            logger.error(ex, ex);
        }
        return null;
    }
}
