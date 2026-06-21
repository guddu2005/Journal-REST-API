package com.example.myFirstProject.Controller;


import com.example.myFirstProject.Service.JournalEntryService;
import com.example.myFirstProject.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMongo {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        try {
            List<JournalEntry> entries = journalEntryService.getAll();
            System.out.println("Entries fetched successfully: " + entries.size());
            return entries;
        } catch (Exception e) {
            System.out.println("Error occurred while fetching entries");
            e.printStackTrace(); // Actual error console mein print karega
            throw new RuntimeException("Failed to fetch journal entries", e);
        }
    }

    @PostMapping
    public  JournalEntry createEntry(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return  entry;
    }

    @GetMapping("id/{myId}")
    public Optional<JournalEntry> getById(@PathVariable ObjectId myId) {
        return Optional.ofNullable(journalEntryService.findById(myId).orElse(null));
    }


    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId ){
        journalEntryService.deleteById(myId);
        return  true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateEnry(@PathVariable ObjectId myId , @RequestBody JournalEntry newenrty){

        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old != null){
            if(newenrty.getTitle() != null &&
                    !newenrty.getTitle().isEmpty()) {
                old.setTitle(newenrty.getTitle());
            }

            if(newenrty.getContent() != null &&
                    !newenrty.getContent().isEmpty()) {
                old.setContent(newenrty.getContent());
            }
        }
        journalEntryService.saveEntry(old);
        return  null;
    }


}
