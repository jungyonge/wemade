package app.api.wemade.module.klaytnservice.infrastructure.klaytn;

import app.api.wemade.module.klaytnservice.domain.klaytn.KlaytnService;
import com.klaytn.caver.Caver;
import com.klaytn.caver.methods.response.Transaction;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KlaytnServiceImpl implements KlaytnService {

    private final Caver caver;


    public KlaytnServiceImpl(@Value("${klaytn.rpc-url}") String rpcUrl) {
        this.caver = new Caver(rpcUrl);
    }

    @Override
    public Transaction getTransaction(String txHash) {

        Transaction tx = null;
        try {
            tx = caver.rpc.klay.getTransactionByHash(txHash).send();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return tx;
    }
}
