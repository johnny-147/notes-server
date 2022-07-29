package com.nttdata.notes.service;

import com.nttdata.notes.exception.DuplicateNameException;
import com.nttdata.notes.model.Note;
import com.nttdata.notes.utils.FileUtils;
import com.nttdata.notes.model.CommandLineOptions;
import com.nttdata.notes.utils.PrintUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineService {

    private final String[] args;

    public CommandLineService(String[] args) {
        this.args = args;
    }

    public void doAction() {
        if(args.length > 3) {
            throw new IllegalArgumentException("Maximum 3 parameters required !");
        }

        CommandLineOptions type = CommandLineOptions.valueOf(args[0]);
        switch (type) {
            case add:
                try {
                    Note note = Note.builder().title(args[1]).content(args[2]).build();
                    FileUtils.writeToFile(note);
                } catch (IOException | ClassNotFoundException | DuplicateNameException exception) {
                    System.out.println("Error writing to file");
                }
                break;
            case list:
                if(args.length == 1) {
                    try {
                        List<String> notesTitles = FileUtils.readFromFile().stream()
                                .map(Note::getTitle).collect(Collectors.toList());
                        PrintUtils.printNotesTitle(notesTitles);
                    } catch (IOException | ClassNotFoundException exception) {
                        System.out.println("Error reading from file");
                    }
                } else if(args.length == 2) {
                    try {
                        List<Note> note = FileUtils.readFromFile().stream().filter(n -> n.getTitle().equals(args[1])).collect(Collectors.toList());
                        PrintUtils.printNotes(note);
                    } catch (IOException | ClassNotFoundException exception) {
                        System.out.println("Error reading from file");
                    }
                }
                break;
            default:
                System.out.println("Not a valid command entered! Valid commands are add, list, list <title>");
        }
    }
}
