package com.nttdata.notes.controller;

import com.nttdata.notes.model.Note;
import com.nttdata.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes-app")
public class NotesRestController {

    @Autowired
    private NotesService notesService;

    @GetMapping("/all-notes")
    public @ResponseBody List<Note> getAllNotes() {
        return notesService.getAllNotes();
    }

    @GetMapping("/note/{title}")
    public Note getNoteByTitle(@PathVariable(value = "title") String title) {
        return notesService.getNoteByTitle(title);
    }

    @PostMapping("add-note")
    public int addNote(@RequestBody Note note) {
        return notesService.addNote(note);
    }
}