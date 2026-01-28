package net.edigest.journalApp.Service;

import net.edigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendMail("arpit.2428cseai2125@kiet.edu","Testing mail","Hi, App kaise hain!");
    }
}
