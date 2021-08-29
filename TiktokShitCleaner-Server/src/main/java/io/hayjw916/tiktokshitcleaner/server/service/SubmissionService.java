package io.hayjw916.tiktokshitcleaner.server.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This service handles storing the submission files and converting them.
 * */
@Service
public class SubmissionService extends SongService {

    /**This value comes from whatever submission.path is in the application.properties file*/
    @Value("${submission.path}")
    private String submissionPath;

    @Override
    public void createSongPath() {
        try {
            Files.createDirectories(Path.of(submissionPath));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Unable to create a directory!");
        }
    }

    /**This method needs to search through all songs in song.path and compare them*/
    public void compareSongs(String fileName) { // Might change to MutlipartFile but not sure yet
        try {
            Path file = Paths.get(submissionPath).resolve(fileName);
            Path songFiles = Paths.get(songPath);
             
        } catch (Exception e) {

        }
    }
}
