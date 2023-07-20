package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.repository.UserRepository;
import com.ootdgram.ootdgram.util.AwsS3Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.ootdgram.ootdgram.domain.dto.UserDto.SignupRequest;
import static com.ootdgram.ootdgram.domain.dto.UserDto.UpdateRequest;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AwsS3Util awsS3Util;

    @Value("${user.default.image}")
    private String defaultUserImageUrl;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       AwsS3Util awsS3Util) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.awsS3Util = awsS3Util;
    }

    public void signup(SignupRequest requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        // 이메일 중복 확인
        if (isEmailDuplicate(email))
            return;

        // 사용자 등록
        User user = new User(requestDto.getNickname(), email, password, defaultUserImageUrl);
        userRepository.save(user);
    }

    public void update(UpdateRequest requestDto, MultipartFile multipartFile, User user) {
        String dirName = "user";
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Illegal")
        );

        String updateImageURL = awsS3Util.update(multipartFile, findUser.getImage(), dirName);
        findUser.update(requestDto, updateImageURL);
    }
    private boolean isEmailDuplicate(String email) {
        Optional<User> checkEmail = userRepository.findByEmail(email);
        return checkEmail.isPresent();
    }
}
