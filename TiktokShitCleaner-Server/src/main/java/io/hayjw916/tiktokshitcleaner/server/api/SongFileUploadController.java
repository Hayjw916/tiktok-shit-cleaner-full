package io.hayjw916.tiktokshitcleaner.server.api;

import io.hayjw916.tiktokshitcleaner.server.message.ResponseFile;
import io.hayjw916.tiktokshitcleaner.server.model.SongDB;
import io.hayjw916.tiktokshitcleaner.server.service.SongStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.hayjw916.tiktokshitcleaner.server.message.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8081")
public class SongFileUploadController {

    @Autowired
    public SongStorageService songStorageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadSong(@RequestParam("song")MultipartFile song) {
        String message = "";
        try {
            songStorageService.store(song);

            message = "Successfully stored song into database";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch(Exception e) {
            message = "Failed to store message";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/songs")
    public ResponseEntity<List<ResponseFile>> getSongs() {
        List<ResponseFile> songs = songStorageService.getAllSongs().map(dbFile -> {
            String songDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/songs/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    songDownloadUri,
                    dbFile.getData().length
            );
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(songs);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<byte[]> getSongById(@PathVariable String id) {
        SongDB songDB = songStorageService.getSong(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + songDB.getName() + "\"")
                .body(songDB.getData());
    }
}
