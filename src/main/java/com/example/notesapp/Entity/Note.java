package com.example.notesapp.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Data
@Document(collection = "notes")
public class Note {

    @Id
    private String id;
    private String title;
    private String text;
    private LocalDateTime createdAt;
    private Set<Tag> tags = new HashSet<>();
}