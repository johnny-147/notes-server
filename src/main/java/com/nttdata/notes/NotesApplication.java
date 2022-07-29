package com.nttdata.notes;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NotesApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(NotesApplication.class);
        if (args.length == 0) {
            app.web(WebApplicationType.SERVLET);
            app.run(args);
        } else {
            NotesApplicationConsole.main(args);
        }

    }
}
