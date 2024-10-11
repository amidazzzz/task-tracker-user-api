package org.amida.user_api.service;

import lombok.RequiredArgsConstructor;
import org.amida.user_api.model.User;
import org.amida.user_api.repository.UserRepository;
import org.amida.user_api.request.SignInRequest;
import org.amida.user_api.request.SignUpRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User register(SignUpRequest signUpRequest) {

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .build();

        return userRepository.save(user);
    }

    public User authenticate(SignInRequest signInRequest) {
        return userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + signInRequest.getUsername() + " not found"));
    }

}
