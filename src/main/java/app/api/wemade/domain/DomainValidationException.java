package app.api.wemade.domain;

public class DomainValidationException extends RuntimeException {

    private int code;
    private int httpStatus;
    private String originalMessage;

    public DomainValidationException(ExplainableMessage explainableMessage) {
        super(explainableMessage.getMessage());
        this.code = explainableMessage.getCode();
        this.httpStatus = explainableMessage.getStatus();
    }

    public DomainValidationException(ExplainableMessage explainableMessage,
        String originalMessage) {
        super(explainableMessage.getMessage());
        this.code = code;
        this.httpStatus = httpStatus;
        this.originalMessage = originalMessage;
    }

    public int getCode() {
        return this.code;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }
}
