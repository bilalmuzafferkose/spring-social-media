package com.bilalkose.spring_social_media.service;

import com.bilalkose.spring_social_media.dto.request.CreateUserRequest;
import com.bilalkose.spring_social_media.dto.UserDto;
import com.bilalkose.spring_social_media.exception.AlreadyCreatedUserException;
import com.bilalkose.spring_social_media.exception.UserNotFoundException;
import com.bilalkose.spring_social_media.model.Follow;
import com.bilalkose.spring_social_media.model.User;
import com.bilalkose.spring_social_media.model.mapper.UserMapper;
import com.bilalkose.spring_social_media.repository.FollowRepository;
import com.bilalkose.spring_social_media.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, FollowRepository followRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User could not find by id: " + id)
                );
        return this.userMapper.toDto(user);
    }

    public User getUserEntityById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User could not find by id: " + id)
                );
        return user;
    }

    public UserDto save(CreateUserRequest createUserRequest) {
        this.checkIfUserIsExistsByUsernameOrEmail(createUserRequest.getUsername(), createUserRequest.getEmail());
        User user = userMapper.toModel(createUserRequest);
        User savedUser = userRepository.save(user);
        return this.userMapper.toDto(savedUser);

    }

    public boolean isFollowing(Long userId, Long followingId) {
        Optional<Follow> follow = followRepository.findByUserIdAndFollowingId(userId, followingId);
        return follow.isPresent();
    }

    private void checkIfUserIsExistsByUsernameOrEmail(String username, String email) {
        if (this.userRepository.existsByUsername(username)) {
            throw new AlreadyCreatedUserException("Already created for this username : " + username);
        }

        if (this.userRepository.existsByEmail(email)) {
            throw new AlreadyCreatedUserException("Already created for this email : " + email);
        }
    }
}
