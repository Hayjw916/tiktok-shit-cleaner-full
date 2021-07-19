package io.hayjw916.tiktokshitcleaner.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This is the database where all of the songs are going to be stored.
 * It contains the song name, the data of the song, and a unique id.
 * */
@Entity
@Table(name = "songs")
public class SongDB {
}
