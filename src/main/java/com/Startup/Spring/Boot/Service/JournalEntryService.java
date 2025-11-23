package com.Startup.Spring.Boot.Service;

import com.Startup.Spring.Boot.Entity.JournalEntry;
import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;
    @Autowired UserService userService;

    public void saveEntries(JournalEntry entry, String userName) {
        User user= userService.findByUserName(userName);
        JournalEntry saved=repository.save(entry);
        user.getJournalEntries().add(saved);
        userService.saveEntries(user);

    }
    public void saveEntries(JournalEntry entry) {
        repository.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return repository.findAll();
    }

    public Optional<JournalEntry> getById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id, String userName) {
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf( x->x.getId().equals(id));
        userService.saveEntries(user);
        repository.deleteById(id);
    }

}
