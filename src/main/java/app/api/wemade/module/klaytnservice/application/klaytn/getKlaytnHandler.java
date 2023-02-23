package app.api.wemade.module.klaytnservice.application.klaytn;

import app.api.wemade.module.klaytnservice.domain.klaytn.KlaytnService;
import com.klaytn.caver.methods.response.Transaction.TransactionData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class getKlaytnHandler {

    private final KlaytnService klaytnService;

    public getKlaytnHandler(KlaytnService klaytnService) {
        this.klaytnService = klaytnService;
    }

    @Transactional
    public TransactionData getTransaction(String txHash) {

        return klaytnService.getTransaction(txHash);
    }



}
