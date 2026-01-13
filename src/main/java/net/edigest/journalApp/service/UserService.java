package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public  class UserService  {

    @Autowired
    private UserRepository userRepository;  // auto object creation by Spring IOC

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // save in encoded form in mongoDB Atlas, only new user
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // save in encoded form in mongoDB Atlas, only new user
        user.setRoles(List.of("USER","ADMIN"));
        userRepository.save(user);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }//for existing user

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
