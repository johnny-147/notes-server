package com.nttdata.notes;

import com.nttdata.notes.service.CommandLineService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

public class NotesApplicationConsole {

   public static void main(String args[]) {
       if(args.length > 0) {
           new CommandLineService(args).doAction();
       }
   }
}
