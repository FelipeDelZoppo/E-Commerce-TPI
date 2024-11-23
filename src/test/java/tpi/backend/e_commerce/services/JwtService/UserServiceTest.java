package tpi.backend.e_commerce.services.JwtService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindValidUser() {
        String username = "arielcometto@gmail.com";
        User validUser = User.builder()
        .firstName("Ariel")
        .lastName("Cometto")
        .email(username)
        .password("test")
        .deleted(false)
        .build();

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(validUser));

        UserDetails result = userService.userDetailsService().loadUserByUsername(username);

        assertTrue(result.getUsername().equalsIgnoreCase(validUser.getEmail()));

    }

    @Test
    public void testFindInvalidUser() {
        String username = "arielcometto@gmail.com";

        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());
       
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.userDetailsService().loadUserByUsername(username);
        });

        verify(userRepository, times(1)).findByEmail(username);

    }

}