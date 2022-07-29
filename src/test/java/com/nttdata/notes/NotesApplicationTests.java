package com.nttdata.notes;

import com.nttdata.notes.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Random;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NotesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotesApplicationTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void retrieveNotes() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/notes-app/all-notes"),
                HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void retrieveOneNote() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/notes-app/note/title1"),
                HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void addNote() {

        Random rand = new Random();
        Note note = Note.builder().title("titleForTest" + rand.nextInt(100)).content("contentForTest").build();

        HttpEntity<Note> entity = new HttpEntity<>(note, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/notes-app/add-note"),
                HttpMethod.POST, entity, String.class);

        String status = response.getBody();
        Assertions.assertEquals(status,"202");
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
