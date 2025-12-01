package com.Startup.Spring.Boot.Service;

import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Exception.UserNotFoundException;
import com.Startup.Spring.Boot.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void saveUserEntries(User entry) {
        repository.save(entry);
    }
    public User saveNewUser(User entry){
        entry.setPassword(passwordEncoder.encode(entry.getPassword()));
        return repository.save(entry );
    }

    public List<User> getAllEntries() {
        return repository.findAll();
    }

    public User getById(ObjectId id)  {
        return repository.findById(id)
                .orElseThrow(()->new UserNotFoundException("user not found with this id : "+ id));
    }

    public void deleteById(ObjectId id) {
        User user_id=  repository.findById(id).
                orElseThrow(()->new UserNotFoundException("user not exist with this id: " + id));
        logger.info("user id is {} :" ,String.valueOf(user_id));
        repository.delete(user_id);
    }
    public User findByUserName(String name){
       return repository.findByUserName(name).orElseThrow(()->new UserNotFoundException("user not found" + name));
    }

}
