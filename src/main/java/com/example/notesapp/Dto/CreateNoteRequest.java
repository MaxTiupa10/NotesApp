package com.example.notesapp.Dto;

import com.example.notesapp.Entity.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;
@Data
public class CreateNoteRequest {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Text cannot be empty")
    private String text;

    private Set<Tag> tags;
}