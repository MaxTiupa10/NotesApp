package com.example.notesapp.Repository;

import com.example.notesapp.Entity.Note;
import com.example.notesapp.Entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note,String> {
    Page<Note> findByTags(Tag tag, Pageable pageable);
}
