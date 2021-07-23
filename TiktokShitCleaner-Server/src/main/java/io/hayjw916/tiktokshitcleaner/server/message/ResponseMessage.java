package io.hayjw916.tiktokshitcleaner.server.message;

import lombok.Getter;
import lombok.Setter;

/**The point of this class is to send a io.hayjw916.tiktokshitcleaner.server.message to the client when something happens*/
public class ResponseMessage {

    @Getter
    @Setter
    String message;

    public ResponseMessage(String message) {
        this.message = message;
    }


}
