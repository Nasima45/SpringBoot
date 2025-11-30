package com.Startup.Spring.Boot.Controller;

import com.Startup.Spring.Boot.Entity.User;
import com.Startup.Spring.Boot.Exception.UserNotFoundException;
import com.Startup.Spring.Boot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired

   private UserService userService;
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> user=userService.getAllEntries();
        if(user==null || user.isEmpty()){
            return ResponseEntity.noContent().build();
        }
       return ResponseEntity.ok(user);
    }

}
