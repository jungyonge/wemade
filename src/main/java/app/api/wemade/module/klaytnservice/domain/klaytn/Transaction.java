package app.api.wemade.module.klaytnservice.domain.klaytn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private String blockHash;
    private String blockNumber;
    private String from;
    private String to;
    private String type;
    private String value;
}
