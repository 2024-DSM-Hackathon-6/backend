package com.hackton.backend.domain.user.service;

import com.hackton.backend.domain.user.domain.UserEntity;
import com.hackton.backend.domain.user.domain.UserRepository;
import com.hackton.backend.domain.user.presentation.dto.request.UserSignUpRequest;
import com.hackton.backend.domain.user.presentation.dto.response.UserIdentifierResponse;
import com.hackton.backend.global.util.RandomStringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserIdentifierResponse signUp(UserSignUpRequest request) {
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw new RuntimeException();
        }

        String identifier = RandomStringUtil.generateRandomMixStr(10, false);

        userRepository.save(
                UserEntity.builder()
                        .accountId(request.getAccountId())
                        .password(request.getPassword())
                        .identifier(identifier)
                        .build()
        );

        return new UserIdentifierResponse(identifier);
    }
}
