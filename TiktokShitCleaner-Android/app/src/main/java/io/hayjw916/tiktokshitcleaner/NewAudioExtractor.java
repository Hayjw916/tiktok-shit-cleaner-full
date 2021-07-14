package io.hayjw916.tiktokshitcleaner;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;

import android.media.MediaMuxer.OutputFormat;
import android.util.Log;

import java.io.IOException;
import java.nio.ByteBuffer;
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
        int bufferSize = -1; // Amount of time it takes to process incoming audio signals
        for (int i = 0; i < trackCount; i++) {
            MediaFormat format = extractor.getTrackFormat(i);
            String mime = format.getString(MediaFormat.KEY_MIME);
            // We are trying to extract audio from the video file
            if (mime.startsWith("audio/")) {
                extractor.selectTrack(i);
                int dstIndex = muxer.addTrack(format); // Adds the audio track to the muxer
                indexMap.put(i, dstIndex);
                if (format.containsKey(MediaFormat.KEY_MAX_INPUT_SIZE)) {
                    int newSize = format.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE);
                    bufferSize = Math.max(newSize, bufferSize);
                }
                if (bufferSize < 0) {
                    bufferSize = DEFAULT_BUFFER_SIZE; // Just resetting the buffer size to 1048576
                }

                muxer.setOrientationHint(0); // For some reason, I need to set the orientation of the file since it is outputting to MPEG-4

                // This copies samples from MediaExtractor to the muxer, will loop for copying each sample until it gets to the end of the src file
                int offset = 0;
                int trackIndex = -1;
                ByteBuffer dstBuf = ByteBuffer.allocate(bufferSize);
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                muxer.start();
                while (true) {
                    bufferInfo.offset = offset;
                    bufferInfo.size = extractor.readSampleData(dstBuf, offset);
                    if (bufferInfo.size < 0) {
                        Log.d(TAG, "Saw input EOS");
                        bufferInfo.size = 0;
                        break;
                    } else {
                        bufferInfo.presentationTimeUs = extractor.getSampleTime(); // Gets the time of the sample in Miroseconds.
                        bufferInfo.flags = extractor.getSampleFlags();
                        trackIndex = extractor.getSampleTrackIndex();
                        muxer.writeSampleData(indexMap.get(trackIndex), dstBuf, bufferInfo);
                        extractor.advance();
                    }
                }

                muxer.stop();
                muxer.release();
            }
        }
    }
}
