package net.edigest.journalApp.Service;

import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.UserRepository;
import net.edigest.journalApp.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled //ignored this one
    @Test
    public void testing(){
        assertNotNull(userRepository.findByUserName("ram"));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings ={  //we also use @CsvSource OR CsvFile etc also..
            "ram",
            "shyam",
            "Ghanshyam"
    })
    public void anyTest(String userName){
        assertNotNull(userService.findByUserName(userName));
    }

}
