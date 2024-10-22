package com.training.nf.service;

import com.training.nf.dto.AuthenticationResponseDto;
import com.training.nf.dto.LoginRequestDto;
import com.training.nf.dto.RegisterRequestDto;

public interface AuthenticationService {
    AuthenticationResponseDto register(RegisterRequestDto RequestDto);
    AuthenticationResponseDto login(LoginRequestDto RequestDto);
}
