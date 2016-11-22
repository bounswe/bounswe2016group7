package com.bounswe.group7.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bounswe.group7.security.JwtAuthenticationRequest;
import com.bounswe.group7.security.JwtTokenUtil;
import com.bounswe.group7.security.JwtUser;
import com.bounswe.group7.security.service.JwtAuthenticationResponse;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.bounswe.group7.model.security.Authority;
import com.bounswe.group7.model.security.AuthorityName;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.repository.UsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsersRepository usersRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public Users createAuthenticationToken(@RequestBody Users authenticationRequest, Device device) throws AuthenticationException {
        Users user = usersRepository.findByEmailOrUsername(authenticationRequest.getUsername());
        if (user == null) {
            return user;
        }

        authenticationRequest.setUsername(user.getUsername());
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        user.setToken(token);
        user.setPassword(null);
        // Return the token
        return user;
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody Users user) {
        final String encodedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Users userSaved = usersRepository.save(user);
        if (userSaved != null && userSaved.getId() != null) {
            //userAuthorityRepository.save(new UserAuthority(userSaved.getId(), AuthorityName.ROLE_EXPLORER.getId()));
            userSaved.setPassword(null);
            return ResponseEntity.ok(userSaved);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
