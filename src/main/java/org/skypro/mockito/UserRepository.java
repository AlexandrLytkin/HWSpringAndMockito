package org.skypro.mockito;

import java.util.*;

public class UserRepository {
    private final List<User> userList = new ArrayList<>();

    public User addUser(User user) {
        this.userList.add(user);
        return user;
    }

    public Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(userList);
    }

    public Optional<User> findUserByLogin(String login) {
        for (User user : userList) {
            if (user.getLogin().contains(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        for (User user : userList) {
            if (user.getLogin().contains(login) && user.getPassword().contains(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
