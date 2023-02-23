package app.api.wemade.api.klaytn.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Todo List response")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String blockHash;
    private String blockNumber;
    private String from;
    private String to;
    private String type;
    private String value;

}
