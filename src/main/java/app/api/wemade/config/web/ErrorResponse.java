package app.api.wemade.config.web;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private final int errorCode;
    private final String errorMessage;

    public static ErrorResponse ERROR(int errorCode, String errorMessage) {
        return ErrorResponse
            .builder()
            .errorCode(errorCode)
            .errorMessage(errorMessage)
            .build();
    }
}
