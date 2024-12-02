package com.saiguides.mytodo.service;

import com.saiguides.mytodo.dto.LoginDto;
import com.saiguides.mytodo.dto.RegisterDto;
import com.saiguides.mytodo.entity.Role;
import com.saiguides.mytodo.entity.User;
import com.saiguides.mytodo.exception.TodoAPIException;
import com.saiguides.mytodo.repository.RoleRepository;
import com.saiguides.mytodo.repository.UserRepository;
import com.saiguides.mytodo.security.JwtTokenProvider;
import com.saiguides.mytodo.utils.PasswordEncoderImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String Register(RegisterDto registerDto) {
        //check user is already exists in the database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        //check email is already exists in the database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);
        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
           loginDto.getUsernameOrEmail(),
           loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}
