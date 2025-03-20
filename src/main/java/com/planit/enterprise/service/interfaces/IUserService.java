package com.planit.enterprise.service.interfaces;

import com.planit.enterprise.dto.UserDTO;
import com.planit.enterprise.entity.User;

public interface IUserService {
    /**
     * Fetch single object of class UserDTO given ID.
     * @param id unique identifier for user.
     * @return the matching user, or null if no matches found.
     */
    UserDTO fetchUserByID(int id);

    /**
     * Fetch single object of class UserDTO given email.
     * @param email unique identifier for user.
     * @return the matching user, or null if no matches found.
     */
    UserDTO fetchUserByEmail(String email);

    /**
     * Returns result of checking for user with given email.
     * @param email email to search for in data.
     * @return true if user found, false if no match.
     */
    Boolean doesEmailExist(String email);

    /**
     * Adds additional user to database
     * @param user the new user
     * @return created user's ID upon successful creation, -1 otherwise
     */
    int registerUser(User user);
}
