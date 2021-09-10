package io.hayjw916.tiktokshitcleaner.server.controller;

import io.hayjw916.tiktokshitcleaner.server.messages.UploadResponseMessage;
import io.hayjw916.tiktokshitcleaner.server.model.SongModel;
import io.hayjw916.tiktokshitcleaner.server.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("song")
public class SongController {

    @Autowired
    private SongService songService;

    protected final Logger logger = LoggerFactory.getLogger(SongController.class);

    @PostMapping
    public ResponseEntity<UploadResponseMessage> uploadSong(@RequestParam("song")MultipartFile song) {
        try {
            songService.saveSong(song);

            return ResponseEntity.status(HttpStatus.OK).body(new UploadResponseMessage("Uploaded & Saved file successfully"));
        } catch (Exception e) {
            logger.error("Error uploading file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new UploadResponseMessage(String.format("Unable to upload file: %s", song.getOriginalFilename())));
        }
    }

    @GetMapping
    public ResponseEntity<List<SongModel>> listAllSongs() {
        List<SongModel> songInfo = songService.loadAllSongs()
                .stream().map(this::pathToSongData).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(songInfo);
    }

    private SongModel pathToSongData(Path path) {
        SongModel songModel = new SongModel();
        String songName = path.getFileName().toString();
        songModel.setFileName(songName);
        songModel.setFileUrl(MvcUriComponentsBuilder.fromMethodName(SongController.class, "getSong", songName)
                .build().toString());

        try {
            songModel.setFileSize(Files.size(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }

        return songModel;
    }

    @GetMapping("{songName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getSong(@PathVariable String songName) {
        Resource song = songService.loadSong(songName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; songNam=\"" + song.getFilename() + "\"")
                .body(song);
    }

    @GetMapping("{uuid:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getSongByUUID(@PathVariable String uuid) {
        Resource song = songService.loadSong(uuid);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; songUUID=\"" + uuid + "\"")
                .body(song);
    }

    @DeleteMapping
    public void deleteSongs() {
        songService.deleteAllSongs();
    }
}
