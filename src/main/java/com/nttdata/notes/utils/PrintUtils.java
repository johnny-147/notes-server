package com.nttdata.notes.utils;

import com.nttdata.notes.model.Note;

import java.util.List;

public class PrintUtils {

    public static void printNotes(List<Note> notes) {
        for(Note note: notes) {
            System.out.println("Title: " + note.getTitle() + " Content: " + note.getContent());
        }
    }

    public static void printNotesTitle(List<String> notesTitles) {
        for(String noteTitle: notesTitles) {
            System.out.println("Title: " + noteTitle);
        }
    }
}
