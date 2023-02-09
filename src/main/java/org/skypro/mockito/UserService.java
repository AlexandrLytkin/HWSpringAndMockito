package org.skypro.mockito;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<String> getAllLoginOfUsers() {

        try {
            Collection<User> users = this.userRepository
                    .getAllUsers();
            if (users == null) {
                return null;
            }
            return this.userRepository
                    .getAllUsers()
                    .stream()
                    .map(User::getLogin)
                    .collect(Collectors.toList());

        } catch (RuntimeException e) {
            return null;
        }
    }

    public void addUser(String login, String password) {
        User user = new User(login, password);

        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("User login should be define");
        }
        boolean userExist = this.userRepository
                .getAllUsers()
                .stream()
                .anyMatch(t -> t.equals(user));
        if (userExist) {
            throw new IllegalArgumentException("this user already exist");
        }
        this.userRepository.addUser(user);
    }

}
