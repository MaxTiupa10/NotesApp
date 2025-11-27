package com.example.notesapp.Service;


import com.example.notesapp.Dto.CreateNoteRequest;
import com.example.notesapp.Dto.NoteDetailDto;
import com.example.notesapp.Entity.Note;
import com.example.notesapp.Repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;


    public NoteDetailDto createNote(CreateNoteRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setCreatedAt(LocalDateTime.now());

        if (request.getTags() == null) {
            note.setTags(new HashSet<>());
        } else {
            note.setTags(request.getTags());
        }

        // Зберігаємо (Mongo сама згенерує String ID)
        Note savedNote = noteRepository.save(note);

        NoteDetailDto dto = new NoteDetailDto();
        dto.setId(savedNote.getId());
        dto.setTitle(savedNote.getTitle());
        dto.setText(savedNote.getText());
        dto.setCreatedAt(savedNote.getCreatedAt());
        dto.setTags(savedNote.getTags());

        return dto;
    }
}
