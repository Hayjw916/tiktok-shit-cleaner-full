package io.hayjw916.tiktokshitcleaner.server.message;

import lombok.Getter;
import lombok.Setter;

/**This class gets the name, url, type, and size of the song */
public class ResponseFile {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private long size;

    public ResponseFile(String name, String url, long size) {
        this.name = name;
        this.url = url;
        this.size = size;
    }
}
