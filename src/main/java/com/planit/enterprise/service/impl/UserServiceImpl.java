package com.planit.enterprise.service.impl;

import com.planit.enterprise.dto.UserDTO;
import com.planit.enterprise.entity.User;
import com.planit.enterprise.repository.UserRepository;
import com.planit.enterprise.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO fetchUserByID(int id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        }
        return null; // Handle the case where the user is not found
    }

    @Override
    public UserDTO fetchUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        }
        return null; // Return null if no user found
    }

    @Override
    public Boolean doesEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public int registerUser(User user) {
        User newUser = user;
        userRepository.save(newUser);
        return newUser.getId(); // Return the user ID after saving
    }
}
