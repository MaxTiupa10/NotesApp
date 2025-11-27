package com.example.notesapp.Controller;

import com.example.notesapp.Dto.CreateNoteRequest;
import com.example.notesapp.Dto.NoteDetailDto;
import com.example.notesapp.Service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteDetailDto> createNote(@Valid @RequestBody CreateNoteRequest request) {
        NoteDetailDto createdNote = noteService.createNote(request);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }


}









