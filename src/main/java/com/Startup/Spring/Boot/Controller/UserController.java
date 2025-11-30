package com.Startup.Spring.Boot.Controller;

import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Repository.UserRepository;
import com.Startup.Spring.Boot.Service.UserDetailsServiceIml;
import com.Startup.Spring.Boot.Service.UserService;
import org.bson.types.ObjectId;
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
    UserRepository userRepository;


    @GetMapping("/health")
    public String healthCheck() {
        return " I am fine .....";
    }

//    @GetMapping("/getUser")
//    public List<User> getAll() {
//
//        return userService.getAllEntries();
//
//
//    }
//
//    @PostMapping("/userEntry")
//    public ResponseEntity<User> createEntry(@RequestBody User js) {
//        try{
//            User saveEntry= userService.saveEntries(js);
//            return ResponseEntity.status(HttpStatus.CREATED).body(saveEntry);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//
//    }

    //    @GetMapping("/getEntries/{myId}")
//    public ResponseEntity<User> getById(@PathVariable String myId) {
//        User user_id = userService.getById(new ObjectId(myId));
//        return new ResponseEntity<>(user_id,HttpStatus.OK);
//    }
//
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println("Deleting user with user name: " + userName);
        userRepository.deleteByUserName(userName);
        return ResponseEntity.ok("deleted successfully");

    }

    @PutMapping("/update")
    public ResponseEntity<User> updateById(@RequestBody User user) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User userInDb = userService.findByUserName(userName);

        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userInDb.setRoles(user.getRoles());

        userService.saveNewUser(userInDb);

        return ResponseEntity.ok(userInDb);
    }


}
