package com.Startup.Spring.Boot.Controller;

import com.Startup.Spring.Boot.Entity.JournalEntry;
import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Service.JournalEntryService;
import com.Startup.Spring.Boot.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService  service;

    @GetMapping("/health")
    public String healthCheck() {
        return " I am fine .....";
    }

    @GetMapping("/getUser")
    public List<User> getAll() {

        return service.getAllEntries();


    }

    @PostMapping("/userEntry")
    public ResponseEntity<User> createEntry(@RequestBody User js) {
        try{
            User saveEntry=service.saveEntries(js);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveEntry);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/getEntries/{myId}")
    public ResponseEntity<User> getById(@PathVariable ObjectId myId) {
        Optional<User> id = service.getById(myId);
        if (id.isPresent()) {
            return new ResponseEntity<>(id.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteEntries/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {
        if(myId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{

            service.deleteById(myId);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }
    @PutMapping("/getId/{userName}")
    public ResponseEntity<User> updateById(@RequestBody User user , @PathVariable String userName) {
        User userInDb=service.findByUserName(userName);
        if(userInDb!=null && !userInDb.getUserName().isEmpty()){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            service.saveEntries(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
