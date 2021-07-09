package io.hayjw916.tiktokshitcleaner;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;

import android.media.MediaMuxer.OutputFormat;
import java.io.IOException;
import java.util.HashMap;

public class NewAudioExtractor {

    private static final int DEFAULT_BUFFER_SIZE = (1024 * 1024);
    private static final String TAG = NewAudioExtractor.class.getCanonicalName();

    /**
     * Extracts the audio from the video file
     * */
    public void genAudioUsingVideo(String srcPath, String dstPath) throws IOException {
        MediaExtractor extractor = new MediaExtractor();
        extractor.setDataSource(srcPath);
        int trackCount = extractor.getTrackCount();

        MediaMuxer muxer = new MediaMuxer(dstPath, OutputFormat.MUXER_OUTPUT_MPEG_4);

        /**Stores track index as well as the destination index*/
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>(trackCount);
        for (int i = 0; i < trackCount; i++) {
            MediaFormat format = extractor.getTrackFormat(i);
            String mime = format.getString(MediaFormat.KEY_MIME);
            // We are trying to extract audio from the video file
            if (mime.startsWith("audio/")) {
                extractor.selectTrack(i);
                int dstIndex = muxer.addTrack(format); // Adds
            }
        }
    }
}
