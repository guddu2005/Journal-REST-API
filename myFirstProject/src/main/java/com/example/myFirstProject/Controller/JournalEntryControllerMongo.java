package com.example.myFirstProject.Controller;


import com.example.myFirstProject.Service.JournalEntryService;
import com.example.myFirstProject.Service.UserService;
import com.example.myFirstProject.entity.JournalEntry;
import com.example.myFirstProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMongo {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalByUser(@PathVariable String username) {
        try {
            User user = userService.findByUserName(username);
            List<JournalEntry> entries = user.getJournalEntry();
            System.out.println("Entries fetched successfully: " + entries.size());
            if(entries != null && !entries.isEmpty()){
                return  new ResponseEntity<>(entries , HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching entries");
            e.printStackTrace(); // Actual error console mein print karega
//            throw new RuntimeException("Failed to fetch journal entries", e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{username}")
    public  ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry , @PathVariable String username){
        try{
//            User user = userService.findByUserName(username);
//            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry , username);

            return new ResponseEntity<>(entry , HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId , @PathVariable String username ){
        try{
            journalEntryService.deleteById(myId  , username);
            return new ResponseEntity<>(true , HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updateEnry(@PathVariable ObjectId myId , @RequestBody JournalEntry newentry , @PathVariable String username){
        User user = userService.findByUserName(username);
        if(user == null) return ResponseEntity.notFound().build();

        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old != null){
            boolean belongToUser = user.getJournalEntry().stream().anyMatch(entry -> entry.getId().equals(myId));

            if(!belongToUser){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            if(newentry.getTitle() != null && !newentry.getTitle().isEmpty()) {
                old.setTitle(newentry.getTitle());
            }

            if(newentry.getContent() != null && !newentry.getContent().isEmpty()) {
                old.setContent(newentry.getContent());
            }
        }
        journalEntryService.saveEntry(old , username);
        return  ResponseEntity.ok(old);
    }


}
