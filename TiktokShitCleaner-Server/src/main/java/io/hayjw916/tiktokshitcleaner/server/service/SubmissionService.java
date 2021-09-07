package io.hayjw916.tiktokshitcleaner.server.service;

import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.wave.Wave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    public boolean compareSongs(String submissionFile) {
        try {
            Path submissionFilePath = Paths.get(submissionPath).resolve(submissionFile);

            for (Path song : loadAllSongs()) {
                FingerprintSimilarity fingerprintSimilarity = new Wave(submissionFilePath.toString()).getFingerprintSimilarity(new Wave(song.toString()));
                if (fingerprintSimilarity.getScore() >= .8) {
                    return true;
                }
            }


        } catch (Exception e) {
            logger.error(String.format("There was an error trying to compare the files: %s", e.getMessage()));
        }
        return false;
    }
}
