package example.brief.MyRh.exceptions.costumeHeaders;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime now, String message) {
    }
}
