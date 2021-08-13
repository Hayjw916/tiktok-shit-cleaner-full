package io.hayjw916.tiktokshitcleaner.server.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The SongModel class contains info such as
 * <ul>
 *     <li>fileName - Stores the name of the file</li>
 *     <li>fileSize - Stores the size of the file</li>
 *     <li>fileType - Stores the type of the file</li>
 *     <li>fileUrl - Stores the url for the file on the system, only to be used by the server however</li>
 * </ul>
 *
 * @author Hayden Webber
 * */
public class SongModel {

    @Getter
    @Setter
    private String fileName;

    @Getter
    @Setter
    private long fileSize;

    @Getter
    @Setter
    private String fileType;

    @Getter
    @Setter
    private String fileUrl;

    public SongModel(String fileName, long fileSize, String fileType, String fileUrl) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
    }
}