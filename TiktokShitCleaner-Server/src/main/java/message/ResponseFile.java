package message;

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
    private String type;

    @Getter
    @Setter
    private long size;

    public ResponseFile(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
}
