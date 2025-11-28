package com.example.notesapp.Controller;

import com.example.notesapp.Dto.CreateNoteRequest;
import com.example.notesapp.Dto.NoteDetailDto;
import com.example.notesapp.Dto.NoteSummaryDto;
import com.example.notesapp.Entity.Tag;
import com.example.notesapp.Service.NoteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;


    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteDetailDto> createNote(@Valid @RequestBody CreateNoteRequest request) {
        NoteDetailDto createdNote = noteService.createNote(request);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }
    @GetMapping
    public Page<NoteSummaryDto> getAllNotes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Tag tag // Тег необов'язковий
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return noteService.getAllNotes(tag, pageable);
    }
    @GetMapping("/{id}")
    public NoteDetailDto getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }
    @PutMapping("/{id}")
    public NoteDetailDto updateNote(
            @PathVariable String id,
            @Valid @RequestBody CreateNoteRequest request
    ) {
        return noteService.updateNote(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
    }
    @GetMapping("/{id}/stats")
    public Map<String, Integer> getNoteStats(@PathVariable String id) {
        return noteService.getNoteStats(id);
    }
}









