package com.Startup.Spring.Boot.Controller;

import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Service.UserDetailsServiceIml;
import com.Startup.Spring.Boot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    UserDetailsServiceIml userDetailsServiceIml;

    @PostMapping("/userEntry")
    public ResponseEntity<User> createEntry(@RequestBody User js) {
        try{
            User saveEntry= userService.saveNewUser(js);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveEntry);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}
