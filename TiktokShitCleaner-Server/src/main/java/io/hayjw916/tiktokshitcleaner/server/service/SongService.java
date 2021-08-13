package io.hayjw916.tiktokshitcleaner.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Value("${song.path}")
    private String songPath;


}
