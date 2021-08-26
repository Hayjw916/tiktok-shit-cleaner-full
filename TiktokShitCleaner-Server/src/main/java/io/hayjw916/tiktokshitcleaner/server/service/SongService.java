package io.hayjw916.tiktokshitcleaner.server.service;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The purpose of the SongService is to create a folder that will store
 * all the songs on the computer as well as storing the info for the songs in
 * a database.
 *
 * It also has a convert method which converts mp3 files to wav files since we need to be using
 * .wav instead of .mp3.
 *
 *
 * */
@Service
public class SongService {

    /**Same value as song.path in the application.properties file*/
    @Value("${song.path}")
    protected String songPath;

    protected final Logger logger = LoggerFactory.getLogger(SongService.class);

    /**This method creates a folder based on the song.path value is in application.properties*/
    public void createSongPath() {
        try {
            Files.createDirectories(Path.of(songPath));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Unable to create a directory");
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
            logger.error("Unable to save song: {}", song.getOriginalFilename());
        }
    }

    public Resource loadSong(String fileName) {
        try {
            Path file = Paths.get(songPath).resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File does not exist");
            }
        } catch (Exception e) {
            throw new RuntimeException("There was an error: " + e.getMessage());
        }
    }

    public List<Path> loadAllSongs() {
        try {
            Path root = Paths.get(songPath);
            if (Files.exists(root)) {
                return Files.walk(root, 1)
                        .filter(path -> !path.equals(root))
                        .collect(Collectors.toList());
            }

            return Collections.emptyList();
        } catch(IOException e) {
            throw new RuntimeException("Unable to list the files");
        }
    }

    public void deleteAllSongs() {
        FileSystemUtils.deleteRecursively(Paths.get(songPath).toFile());
    }

    public void convertToWav(String fileName) {
        try {
            if (fileName.contains(".mp4")) {
                Converter converter = new Converter();
                converter.convert(fileName, songPath);
            } else if (fileName.contains(".wav")) {
                logger.info("File contains .wav, not needed for conversion");
            }
        } catch (JavaLayerException e) {
            logger.error("Unable to convert File: {}" , e.getMessage());
        }

    }

}