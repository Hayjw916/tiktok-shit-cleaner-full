package io.hayjw916.tiktokshitcleaner.server.service;

import io.hayjw916.tiktokshitcleaner.server.model.SongModel;
import io.hayjw916.tiktokshitcleaner.server.repo.SongDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The purpose of the SongService is to create a folder that will store
 * all of the songs on the computer as well as storing the info for the songs in
 * a database.
 *
 * It also has a convert method which converts mp3 files to wav files since we need to be using
 * .wav instead of .mp3.
 *
 *
 * */
@Service
public class SongService {

    @Autowired
    public SongDBRepo songDBRepo;

    /**Same value as song.path in the application.properties file*/
    @Value("${song.path}")
    private String songPath;

    /**This method creates a folder based on the the song.path value is in application.properties*/
    public void createSongPath() {
        try {
            Files.createDirectories(Path.of(songPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Unable to create song directory");
        }
    }

    /**This method saves the song to the folder set by song.path*/
    public void saveSong(@NonNull MultipartFile song) {
        try {
            Path dir = Paths.get(songPath);
            if (!Files.exists(dir)) {
                createSongPath();
            }
            Files.copy(song.getInputStream(), dir.resolve(Objects.requireNonNull(song.getOriginalFilename()))); // ik i already have @NonNull but it wouldn't stop bothering me so I added Objects.requireNonNull();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Unable to save song " + song.getOriginalFilename());
        }
    }


}