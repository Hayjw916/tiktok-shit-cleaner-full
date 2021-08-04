package io.hayjw916.tiktokshitcleaner.server.service;

import io.hayjw916.tiktokshitcleaner.server.model.SongDB;
import io.hayjw916.tiktokshitcleaner.server.repository.SongDBRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This service stores songs to the SongDB in order to use them for comparison*/
@Service
public class SongStorageService {

    @Autowired
    public SongDBRepo songDBRepo;

    @SuppressWarnings("UnusedReturnValue")
    public SongDB store(@NonNull MultipartFile song) throws IOException {
        String songName = StringUtils.cleanPath((Objects.requireNonNull(song.getOriginalFilename())));
        SongDB songDB = new SongDB(songName, song.getContentType(), song.getBytes());

        return songDBRepo.save(songDB);
    }

    public SongDB getSong(String id) {
        return songDBRepo.findById(id).get();
    }

    public Stream<SongDB> getAllSongs() {
        return songDBRepo.findAll().stream();
    }
}
