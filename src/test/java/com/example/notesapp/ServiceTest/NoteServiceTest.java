package com.example.notesapp.ServiceTest;

import com.example.notesapp.Entity.Note;
import com.example.notesapp.Repository.NoteRepository;
import com.example.notesapp.Service.NoteService;
import org.junit.jupiter.api.Test; // Це JUnit 5
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void getNoteStats_shouldReturnSortedWordCount() {

        String noteId = "test-id";
        String text = "Note is just a note";

        Note note = new Note();
        note.setId(noteId);
        note.setText(text);


        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));

        Map<String, Integer> stats = noteService.getNoteStats(noteId);

        assertEquals(4, stats.size(), "Має бути 4 унікальних слова");
        assertEquals(2, stats.get("note"), "Слово 'note' має зустрічатися 2 рази");
        assertEquals(1, stats.get("is"));
    }
}