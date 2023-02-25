package app.api.wemade.api.klaytn.response;

import com.klaytn.caver.utils.Utils;
import com.klaytn.caver.utils.Utils.KlayUnit;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.web3j.utils.Numeric;

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

    public String getBlockNumber() {
        return String.valueOf(Numeric.toBigInt(this.blockNumber));
    }

    public String getValue() {
        return Utils.convertFromPeb(new BigDecimal(
            Numeric.toBigInt(this.value)), KlayUnit.KLAY);
    }
}
