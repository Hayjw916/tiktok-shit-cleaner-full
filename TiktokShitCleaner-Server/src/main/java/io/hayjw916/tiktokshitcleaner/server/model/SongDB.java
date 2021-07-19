package io.hayjw916.tiktokshitcleaner.server.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * This is the database where all of the songs are going to be stored.
 * It contains the song name, the data of the song, and a unique id.
 * */
@Entity
@Table(name = "songs")
public class SongDB {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Getter
    @Setter
    @Lob
    private byte[] data;

    public SongDB() { }

    public SongDB(String name, String id, byte[] data) {
        this.name = name;
        this.id = id;
        this.data = data;
    }

}
