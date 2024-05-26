package com.example.identity_service.service;

import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.request.UserUpdateRequest;
import com.example.identity_service.entity.User;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username has already existed !!");
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    // get all users in database
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // get user by Id
    public User getUser(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!!!!"));
    }

    // update user's information
    public User updateUser(String userId, UserUpdateRequest request) {
        User userToBeUpdated = getUser(userId);

        userToBeUpdated.setPassword(request.getPassword());
        userToBeUpdated.setFirstName(request.getFirstName());
        userToBeUpdated.setLastName(request.getLastName());
        userToBeUpdated.setDob(request.getDob());

        return userRepository.save(userToBeUpdated);
    }

    // delete a user
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public void testAntonationBuilder(UserCreationRequest request) {
        UserCreationRequest request1 = UserCreationRequest.builder()
                .username("Kikika")
                .password("123abc")
                .firstName("Tiến")
                .lastName("Đạt")
                .dob(LocalDate.of(2002,12,27))
                .build();
    }

}
