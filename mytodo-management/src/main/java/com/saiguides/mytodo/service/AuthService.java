package com.saiguides.mytodo.service;

import com.saiguides.mytodo.dto.LoginDto;
import com.saiguides.mytodo.dto.RegisterDto;

public interface AuthService {
    String Register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
