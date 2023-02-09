package org.skypro.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @BeforeEach
    void setUp() {
        user1 = new User("Maria", "zayka123");
        user2 = new User(null,null);
        user3 = new User("Varvara", "zayka123");
        user4 = new User("Maria", "321War123");
    }
    private List<User> getAllUsers() {
        User user1 = new User("Maria", "zayka123");
        User user2 = new User("Varvara", "321War123");
        return new ArrayList<>(List.of(user1, user2));
    }

    private List<User> getAllUsersBlankList() {
        return new ArrayList<>();
    }

//1
    @Test
    void getBlankListUsers() {
        assertTrue(getAllUsersBlankList().isEmpty());
    }
//2
    @Test
    void getCompleteListUsers() {
        assertTrue(!getAllUsers().isEmpty());
    }
//3
    @Test
    void findUserByLoginIfUserExist() {
        assertTrue(user1.getLogin() != null);
    }
//4
    @Test
    void findUserByLoginIfUserNotExist() {
        assertTrue(user2.getLogin() == null);
    }
//5
    @Test
    void findUserByLoginAndPasswordIfUserExistByLoginAndPassword() {
        assertTrue(user1.getLogin() != null && user1.getPassword() != null);
    }
//6
    @Test
    void findUserByLoginAndPasswordWithPasswordCoincidenceWithExistAndLoginNoCoincidence() {
        assertTrue(user1.getLogin() != null && user1.getPassword() != null);
        assertTrue(!user1.getLogin().equals(user3.getLogin()) && user1.getPassword().equals(user3.getPassword()));
    }
//7
    @Test
    void findUserByLoginAndPasswordWithLoginCoincidenceWithExistAndPasswordNoCoincidence() {
        assertTrue(user1.getLogin() != null && user1.getPassword() != null);
        assertTrue(user1.getLogin().equals(user4.getLogin()) && !user1.getPassword().equals(user4.getPassword()));

    }
}