package net.edigest.journalApp.controller;

import net.edigest.journalApp.api.response.WeatherResponse;
import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.UserRepository;
import net.edigest.journalApp.service.UserService;
import net.edigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userInDB=userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greet")
    public ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse=weatherService.getWeather("Mumbai");
        String greeting="";
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greeting = " | feels like " + weatherResponse.getCurrent().getFeelsLike() + "°C" + " | temperature " + weatherResponse.getCurrent().getTemperature() + "°C"
            +" |wind speed is "+weatherResponse.getCurrent().getWindSpeed() +" km/h | weather description is  "+weatherResponse.getCurrent().getWeatherDescriptions();}

//        "feelsLike" is an instance of "Current" class and Current is subclass of WeatherResponse class
        return ResponseEntity.ok("Hi " + authentication.getName() + greeting);
    }
}

