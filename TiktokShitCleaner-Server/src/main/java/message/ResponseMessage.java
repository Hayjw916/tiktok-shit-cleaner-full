package message;

import lombok.Getter;
import lombok.Setter;

/**The point of this class is to send a message to the client when something happens*/
public class ResponseMessage {

    @Getter
    @Setter
    String message;

    public ResponseMessage(String message) {
        this.message = message;
    }


}
