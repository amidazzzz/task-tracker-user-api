package org.amida.user_api.service;

import lombok.RequiredArgsConstructor;
import org.amida.user_api.model.User;
import org.amida.user_api.repository.UserRepository;
import org.amida.user_api.request.SignInRequest;
import org.amida.user_api.request.SignUpRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(SignUpRequest signUpRequest) {

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword())) // fix password encode
                .email(signUpRequest.getEmail())
                .build();

        userRepository.save(user);
    }

    public void authenticate(SignInRequest signInRequest) {
        User user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username "
                        + signInRequest.getUsername() + " not found"));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
    }

}
