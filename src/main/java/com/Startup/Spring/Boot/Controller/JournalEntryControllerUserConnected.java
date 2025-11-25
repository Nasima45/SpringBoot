package com.Startup.Spring.Boot.Controller;

import com.Startup.Spring.Boot.Entity.JournalEntry;
import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Service.JournalEntryService;
import com.Startup.Spring.Boot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userJournal")
public class JournalEntryControllerUserConnected {

    @Autowired
    private JournalEntryService service;
    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String healthCheck() {
        return " I am fine .....";
    }

    @GetMapping("/getUserJounrnal/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName) {
        System.out.println("Controller reached for userName: " + userName);
        User user = userService.findByUserName(userName);
        try {
            List<JournalEntry> entries = user.getJournalEntries();
            return ResponseEntity.status(HttpStatus.OK).body(entries);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/createEntry/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry js, @PathVariable String userName) {

        try {

              service.saveEntries(js, userName);
            return new ResponseEntity<>(js,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/getEntries/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String myId) {
        Optional<JournalEntry> id = service.getById(myId);
        if (id.isPresent()) {
            return new ResponseEntity<>(id.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteEntries/{myId}/{userName}")
    public ResponseEntity<?> deleteById(@PathVariable String myId,@PathVariable String userName) {

        try {

            service.deleteById(myId,userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("/getId/{myId}/{userName}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable String myId,
                                                   @RequestBody JournalEntry newEntry,
                                                   @PathVariable String userName) {
        try {
            JournalEntry old = service.getById(myId).orElse(null);
            if (old != null) {
                old.setTitle((newEntry.getTitle()) != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());

            }
            service.saveEntries(old);
            return ResponseEntity.status(HttpStatus.OK).body(old);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
