package io.hayjw916.tiktokshitcleaner.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * This service handles storing the submission files and converting them.
 * */
@Service
public class SubmissionService extends SongService {

    /**This value comes from whatever submission.path is in the application.properties file*/
    @Value("${submission.path}")
    private String submissionPath;
}
