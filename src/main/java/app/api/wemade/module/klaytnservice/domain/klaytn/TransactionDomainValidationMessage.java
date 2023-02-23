package app.api.wemade.module.klaytnservice.domain.klaytn;


import app.api.wemade.domain.ExplainableMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TransactionDomainValidationMessage implements ExplainableMessage {

    TODO_LIST_NOT_FOUND(1_0001, "사용자가 존재하지 않습니다."),

    ;

    private final int code;
    private final String message;
    private final HttpStatus status;

    TransactionDomainValidationMessage(int code, String message) {
        this.code = code;
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public int getStatus() {
        return status.value();
    }
}
