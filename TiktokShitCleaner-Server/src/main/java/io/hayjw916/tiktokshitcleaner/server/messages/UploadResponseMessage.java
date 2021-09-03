package io.hayjw916.tiktokshitcleaner.server.messages;

import lombok.Getter;

public class UploadResponseMessage {

    @Getter
    public final String responseMessage;

    public UploadResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
