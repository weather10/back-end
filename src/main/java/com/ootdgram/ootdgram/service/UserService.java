package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.dto.SignupRequestDto;
import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void signup(SignupRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        // 이메일 중복 확인
        if (isEmailDuplicate(email))
            return;

        // 사용자 등록
        User user = new User(requestDto.getName(), email, password);
        userRepository.save(user);
    }

    private boolean isEmailDuplicate(String email) {
        Optional<User> checkEmail = userRepository.findByEmail(email);
        return checkEmail.isPresent();
    }
}
