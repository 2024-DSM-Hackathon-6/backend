package com.hackton.backend.domain.user.presentation;

import com.hackton.backend.domain.user.presentation.dto.request.UserSignUpRequest;
import com.hackton.backend.domain.user.presentation.dto.response.UserIdentifierResponse;
import com.hackton.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserIdentifierResponse signUp(@RequestBody UserSignUpRequest request) {
        return userService.signUp(request);
    }
}
