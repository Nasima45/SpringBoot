//package com.Startup.Spring.Boot.Controller;
//
//import com.Startup.Spring.Boot.Entity.JournalEntry;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//    Map<String, JournalEntry> entries=new HashMap<>();
//    @GetMapping("/health")
//    public String healthCheck(){
//        return " I am fine .....";
//    }
//    @GetMapping("/getEntries")
//    public List<JournalEntry> getAll(){
//        return entries.values().stream().toList();
//        //return new ArrayList<>(entries.values());
//    }
//    @PostMapping("/createEntry")
//    public List<JournalEntry> createEntry(@RequestBody JournalEntry js){
//        entries.put(js.getId(),js);
//        return new ArrayList<>(entries.values());
//    }
//    @PostMapping("/createEntries")
//    public ResponseEntity<JournalEntry> createEntries(@RequestBody JournalEntry js){
//        entries.put(js.getId(),js);
//        return ResponseEntity.status(HttpStatus.CREATED).body(js);
//    }
//    @GetMapping("/getEntries/{myId}")
//    public JournalEntry getById(@PathVariable Long myId){
//        return entries.get(myId);
//    }
//    @DeleteMapping("/deleteEntries/{myId}")
//    public JournalEntry deleteById(@PathVariable Long myId){
//        return entries.remove(myId);
//    }
//    @PutMapping("/getId/{myId}")
//    public JournalEntry updateById(@PathVariable String myId, @RequestBody JournalEntry js){
//        entries.put(myId,js);
//        return js;
//    }
//    @PutMapping("/getId/{myId}")
//    public ResponseEntity<JournalEntry> updateEntry(@PathVariable String myId, @RequestBody JournalEntry js){
//        entries.put(myId,js);
//        return ResponseEntity.ok(js);
//    }
//
//}
