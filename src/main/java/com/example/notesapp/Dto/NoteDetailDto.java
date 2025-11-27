package com.example.notesapp.Dto;

import com.example.notesapp.Entity.Tag;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;
@Data
public class NoteDetailDto {

    private String id;

    private String title;

    private String text;

    private LocalDateTime createdAt;

    private Set<Tag> tags;
}