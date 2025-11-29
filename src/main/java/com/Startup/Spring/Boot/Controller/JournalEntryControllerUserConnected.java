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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userJournal")
public class JournalEntryControllerUserConnected {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String healthCheck() {
        return " I am fine .....";
    }

    @GetMapping("/getUserJournal")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println("Controller reached for userName: " + userName);
        User user = userService. findByUserName(userName);
        try {
            List<JournalEntry> entries = user.getJournalEntries();
            return ResponseEntity.status(HttpStatus.OK).body(entries);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/createEntry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry js) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        try {
            journalEntryService.saveEntries(js, userName);
            return new ResponseEntity<>(js, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/getEntries/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String myId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findByUserName(userName);
        System.out.println(myId);
        boolean exists = user.getJournalEntries().stream().anyMatch(x -> x.getId().toHexString().equals(myId));
        if (exists) {
            Optional<JournalEntry> id = journalEntryService.getById(myId);
            if (id.isPresent()) {
                return new ResponseEntity<>(id.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteEntries/{myId}/")
    public ResponseEntity<?> deleteById(@PathVariable String myId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        try {

            journalEntryService.deleteById(myId, userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("/getId/{myId}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable String myId,
                                                   @RequestBody JournalEntry newEntry
                                                   ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        try {
            User user=userService.findByUserName(userName);
            boolean exists=user.getJournalEntries().stream().anyMatch(entry->entry.getId().toHexString().equals(myId));
            if(!exists){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            JournalEntry old = journalEntryService.getById(myId).orElse(null);
            if (old != null) {
                old.setTitle((newEntry.getTitle()) != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());

            }
            journalEntryService.saveEntries(old);
            return ResponseEntity.status(HttpStatus.OK).body(old);

        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
