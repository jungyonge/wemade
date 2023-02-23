package app.api.wemade.module.klaytnservice.domain.klaytn;

import com.klaytn.caver.methods.response.Transaction.TransactionData;

public interface KlaytnService {

    TransactionData getTransaction(String txHash);

}
