package com.example.myFirstProject.entity;


import com.example.myFirstProject.enums.Sentiment;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
//@Getter
//@Setter
@Data
public class JournalEntry {

    @Id  // for unique key
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

}
