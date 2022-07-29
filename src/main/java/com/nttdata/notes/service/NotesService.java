package com.nttdata.notes.service;

import com.nttdata.notes.exception.DuplicateNameException;
import com.nttdata.notes.model.Note;
import com.nttdata.notes.utils.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesService {

    public List<Note> getAllNotes() {
        try {
            return FileUtils.readFromFile();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Error reading from file");
        }
        return null;
    }

    public Note getNoteByTitle(String title) {
        try {
            List<Note> notes = FileUtils.readFromFile().stream().filter(n -> n.getTitle().equals(title)).collect(Collectors.toList());
            return notes.size() > 0 ? notes.get(0) : null;
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Error reading from file");
        }
        return null;
    }

    public int addNote(Note note) {
        try {
            FileUtils.writeToFile(note);
        } catch (IOException | ClassNotFoundException | DuplicateNameException exception) {
            System.out.println("Error writing to file");
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        return HttpStatus.ACCEPTED.value();
    }
}
