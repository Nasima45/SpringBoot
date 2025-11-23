package com.Startup.Spring.Boot.Controller;

import com.Startup.Spring.Boot.Entity.JournalEntry;
import com.Startup.Spring.Boot.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService service;

    @GetMapping("/health")
    public String healthCheck() {
        return " I am fine .....";
    }

    @GetMapping("/getEntries")
    public ResponseEntity<List<JournalEntry>> getAll() {
        try{
        List<JournalEntry> entries=service.getAllEntries();
        return  ResponseEntity.status(HttpStatus.OK).body(entries);}
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

//    @PostMapping("/createEntry")
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry js) {
//        try{
//            JournalEntry saveEntry=service.saveEntries(js, user);
//            return ResponseEntity.status(HttpStatus.CREATED).body(saveEntry);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//
//    }

    @GetMapping("/getEntries/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String myId) {
        Optional<JournalEntry> id = service.getById(myId);
        if (id.isPresent()) {
            return new ResponseEntity<>(id.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/deleteEntries/{myId}")
//    public ResponseEntity<?> deleteById(@PathVariable String myId) {
//        if(myId==null ||myId.isEmpty() ){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        try{
//
//            service.deleteById(myId, userName);
//            return  new ResponseEntity<>(HttpStatus.OK);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//
//    }

//    @PutMapping("/getId/{myId}")
//    public ResponseEntity<JournalEntry> updateById(@PathVariable String myId, @RequestBody JournalEntry newEntry) {
//        try{
//        JournalEntry old = service.getById(myId).orElse(null);
//        if (old != null) {
//            old.setTitle((newEntry.getTitle()) != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
//            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
//
//        }
//            service.saveEntries(old, user);
//            return ResponseEntity.status(HttpStatus.OK).body(old);
//
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


}
