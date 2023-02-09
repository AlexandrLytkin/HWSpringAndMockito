package org.skypro.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenUserInRepositoryThenUserAddedCorrectly() {
        when(userRepository.getAllUsers())
                .thenReturn(List.of(
                new User("Gabage","123"),
                new User("Trash","qwe")));
        assertThat(userService.getAllLoginOfUsers()).isNotEmpty();
    }

    @Test
    void whenUserRepositoryIsEmptyThenRepositoryShouldBeEmpty() {
        when(userRepository.getAllUsers()).thenReturn(List.of());
        assertThat(userService.getAllLoginOfUsers()).isEmpty();
    }

    @Test
    void whenUserCorrectAddThenUserIsCalledFromReport() {
        when(userRepository.getAllUsers())
                .thenReturn(List.of());
        when(userRepository.addUser(any(User.class)))
                .thenReturn(null);
        userService.addUser("Gabage","123");
        verify(userRepository)
                .addUser(any());
    }

    @Test
    void whenInvalidUserIsPassedThenServiceThrowsException() {
        assertThatThrownBy(() -> userService.addUser("", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User login should be define");
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    void whenExistUserIsPassedThenServiceTrowsException() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("Gabage", "123")));
        assertThatThrownBy(() -> userService.addUser("Gabage", "123"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this user already exist");
    }

    @Test
    void whenNetworkExceptionIsRaisedThenServiceReturnNull() {
        when(userRepository.getAllUsers()).thenThrow(new RuntimeException());
        assertThat(userService.getAllLoginOfUsers()).isEqualTo(null);
    }
}