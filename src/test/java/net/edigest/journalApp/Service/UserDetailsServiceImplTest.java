package net.edigest.journalApp.Service;

import net.edigest.journalApp.repository.UserRepository;
import net.edigest.journalApp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((net.edigest.journalApp.entity.User) User.builder().username("ram").password("ijbcd").roles(String.valueOf(new ArrayList<String>())).build());
        UserDetails user=userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }


}
