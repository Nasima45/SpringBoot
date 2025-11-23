package com.Startup.Spring.Boot.Service;

import com.Startup.Spring.Boot.Entity.JournalEntry;
import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveEntries(User entry) {
       return repository.save(entry);

    }

    public List<User> getAllEntries() {
        return repository.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return repository.findById(id);
    }

    public void deleteById(ObjectId id) {
        repository.deleteById(id);
    }
    public User findByUserName(String name){
       return repository.findByUserName(name);
    }

}
