package com.hackton.backend.domain.user.service;

import com.hackton.backend.domain.user.domain.UserEntity;
import com.hackton.backend.domain.user.domain.UserRepository;
import com.hackton.backend.domain.user.presentation.dto.request.UserSignUpRequest;
import com.hackton.backend.domain.user.presentation.dto.response.UserIdentifierResponse;
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

        userRepository.save(
                UserEntity.builder()
                        .accountId(request.getAccountId())
                        .password(request.getPassword())
                        .identifier("")
                        .build()
        );

        return new UserIdentifierResponse("adl;fkj");
    }
}
