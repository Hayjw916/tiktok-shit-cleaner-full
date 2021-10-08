package io.hayjw916.tiktokshitcleaner.server.controller;

import io.hayjw916.tiktokshitcleaner.server.messages.UploadResponseMessage;
import io.hayjw916.tiktokshitcleaner.server.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<UploadResponseMessage> uploadSubmission(@RequestParam("submission") MultipartFile submission) {
        try {
            submissionService.saveSubmission(submission);
            String response;
            if (submissionService.compareSongs(submission.getOriginalFilename())) {
                response = "Song is like another song";
            } else {
                response = "Song is not like another song";
            }
            return ResponseEntity.status(HttpStatus.OK).body(new UploadResponseMessage("Uploaded and & saved submission: " + response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new UploadResponseMessage("Unable to upload submission " + submission.getOriginalFilename()));
        }
    }

    @GetMapping("{submission:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getSubmission(@PathVariable String submissionName) {
        Resource submission = submissionService.loadSong(submissionName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; submissionName=\"" + submission.getFilename() + "\"")
                .body(submission);
    }

    @DeleteMapping
    public void deleteSongs() {
        submissionService.deleteAllSongs();
    }
}