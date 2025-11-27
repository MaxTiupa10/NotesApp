package com.example.notesapp.Repository;

import com.example.notesapp.Entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note,String> {




}
