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

    
}
