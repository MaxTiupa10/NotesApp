package com.example.notesapp.Service;

import com.example.notesapp.Dto.CreateNoteRequest;
import com.example.notesapp.Dto.NoteDetailDto;
import com.example.notesapp.Dto.NoteSummaryDto;
import com.example.notesapp.Entity.Note;
import com.example.notesapp.Entity.Tag;
import com.example.notesapp.Repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteDetailDto createNote(CreateNoteRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setCreatedAt(LocalDateTime.now());
        note.setTags(request.getTags() == null ? new HashSet<>() : request.getTags());

        Note savedNote = noteRepository.save(note);
        return mapToDetailDto(savedNote);
    }
    public Page<NoteSummaryDto> getAllNotes(Tag tag, Pageable pageable) {
        Page<Note> notePage;
        if (tag != null) {
            notePage = noteRepository.findByTags(tag, pageable);
        } else {
            notePage = noteRepository.findAll(pageable);
        }
        return notePage.map(this::mapToSummaryDto);
    }
    public NoteDetailDto getNote(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found: " + id));
        return mapToDetailDto(note);
    }
    public NoteDetailDto updateNote(String id, CreateNoteRequest request) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found: " + id));

        note.setTitle(request.getTitle());
        note.setText(request.getText());
        if (request.getTags() != null) {
            note.setTags(request.getTags());
        }

        return mapToDetailDto(noteRepository.save(note));
    }
    public void deleteNote(String id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found: " + id);
        }
        noteRepository.deleteById(id);
    }
    public Map<String, Integer> getNoteStats(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found: " + id));

        String text = note.getText();
        if (text == null || text.isBlank()) {
            return Collections.emptyMap();
        }


        String cleanText = text.toLowerCase().replaceAll("[^\\p{L}0-9\\s]", "");
        String[] words = cleanText.split("\\s+");

        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            if (!word.isBlank()) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        return wordCounts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
    private NoteDetailDto mapToDetailDto(Note note) {
        NoteDetailDto dto = new NoteDetailDto();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setText(note.getText());
        dto.setCreatedAt(note.getCreatedAt());
        dto.setTags(note.getTags());
        return dto;
    }
    private NoteSummaryDto mapToSummaryDto(Note note) {
        NoteSummaryDto dto = new NoteSummaryDto();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setCreatedAt(note.getCreatedAt());
        return dto;
    }
}