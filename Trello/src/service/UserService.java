package service;

import entity.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String userId, String name, String email) {
        if (userRepository.findById(userId) != null) {
            System.out.println("User ID already exists. Please choose another.");
            return null;
        }
        User user = new User(userId, name, email);
        userRepository.save(user);
        return user;
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId);
    }
}
