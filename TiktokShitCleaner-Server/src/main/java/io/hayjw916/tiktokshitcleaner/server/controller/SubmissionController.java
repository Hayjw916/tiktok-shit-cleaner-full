package io.hayjw916.tiktokshitcleaner.server.controller;

import io.hayjw916.tiktokshitcleaner.server.messages.UploadResponseMessage;
import io.hayjw916.tiktokshitcleaner.server.model.SubmissionModel;
import io.hayjw916.tiktokshitcleaner.server.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<UploadResponseMessage> uploadSubmission(@RequestParam("submission") MultipartFile submission) {
        try {
            submissionService.saveSong(submission);
            return ResponseEntity.status(HttpStatus.OK).body(new UploadResponseMessage("Uploaded and & saved submission"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new UploadResponseMessage("Unable to upload submission " + submission.getOriginalFilename()));
        }
    }

    /*
    @GetMapping
    public ResponseEntity<List<SubmissionModel>> listAllSubmissions() {

    }*/
}