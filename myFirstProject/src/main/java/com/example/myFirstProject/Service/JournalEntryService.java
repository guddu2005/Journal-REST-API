package com.example.myFirstProject.Service;

import com.example.myFirstProject.entity.JournalEntry;
import com.example.myFirstProject.entity.User;
import com.example.myFirstProject.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.myFirstProject.repository.JournalEntryRepository.*;


@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;


    public void saveEntry(JournalEntry journalEntry , String username){
        User user  = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);

        boolean exists = user.getJournalEntry().stream().anyMatch(entry -> entry.getId().equals(saved.getId()));

        if(!exists){
            user.getJournalEntry().add(saved);
            userService.saveEntry(user);

        }
    }
    
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myId) {

        return journalEntryRepository.findById(myId);
    }


    public void deleteById(ObjectId myId , String username){
        User user = userService.findByUserName(username);
        user.getJournalEntry().removeIf(x -> x.getId().equals(myId));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
    }
}


// controller --> service --> repository
