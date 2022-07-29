package com.nttdata.notes.utils;

import com.nttdata.notes.exception.DuplicateNameException;
import com.nttdata.notes.model.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    private final static String PATH = "C:/Users/luncani/Desktop/notes/notes-server/src/main/resources/notes.dat";

    public static List<Note> readFromFile() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(FileUtils.PATH);
        List<Note> noteList = new ArrayList<>();
        boolean cont = true;
        while (cont) {
            try {
                ObjectInputStream ois = new ObjectInputStream(fis);
                Note note = (Note) ois.readObject();
                if (note != null) {
                    noteList.add(note);
                } else {
                    cont = false;
                }
            } catch (EOFException e) {
                break;
            }
        }
        fis.close();
        return noteList;
    }

    public static void writeToFile(Note note) throws IOException, ClassNotFoundException, DuplicateNameException {

        List<Note> notesDuplicateList = FileUtils.readFromFile().stream().filter(n -> n.getTitle().equals(note.getTitle())).collect(Collectors.toList());
        if (notesDuplicateList.isEmpty()) {
            FileOutputStream fos = new FileOutputStream(FileUtils.PATH, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(note);

            oos.close();
            fos.close();
            System.out.println("Write successful.");
        } else {
            throw new DuplicateNameException("Note with title " + note.getTitle() + " already exists");
        }
    }
}
