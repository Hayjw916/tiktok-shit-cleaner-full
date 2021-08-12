package io.hayjw916.tiktokshitcleaner.server.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The SubmissionModel is a type of song (which is why it extends the SongModel class).
 *
 * It has one more field
 * <ul><li>isSongGood - Stores if the submission is good or not</li></ul>
 *
 * @author Hayden Webber
 * */
public class SubmissionModel extends SongModel {

    @Getter
    @Setter
    private boolean isSongGood;


    public SubmissionModel(String fileName, long fileSize, String fileType, String fileUrl, boolean isSongGood) {
        super(fileName, fileSize, fileType, fileUrl);
        this.isSongGood = isSongGood;
    }
}
