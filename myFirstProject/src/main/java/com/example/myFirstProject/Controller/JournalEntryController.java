//package com.example.myFirstProject.Controller;
//
//
//import com.example.myFirstProject.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("journal")
//public class JournalEntryController {
//
//    private Map<Long , JournalEntry> journalEntries = new HashMap<>();
//
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public  boolean createJournal(@RequestBody JournalEntry myEntry){
////        journalEntries.put(myEntry.getId() , myEntry);
//        return true;
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public  boolean deleteById(@PathVariable Long myId){
//        JournalEntry removed =  journalEntries.remove(myId);
//        if(removed != null) return true;
//        return false;
//    }
//
//    @PutMapping("id/{myId}")
//    public  JournalEntry updateEntry(@PathVariable Long myId , @RequestBody JournalEntry entry){
//        return journalEntries.put(myId ,entry);
//    }
//
//
//}
