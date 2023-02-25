package app.api.wemade.module.klaytnservice.domain.klaytn;

import com.klaytn.caver.methods.response.Transaction;

public interface KlaytnService {

    Transaction getTransaction(String txHash);

}
