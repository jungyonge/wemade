package app.api.wemade.api.klaytn;

import app.api.wemade.api.klaytn.response.TransactionDto;
import app.api.wemade.module.klaytnservice.application.klaytn.getKlaytnHandler;
import com.klaytn.caver.utils.Utils;
import com.klaytn.caver.utils.Utils.KlayUnit;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.utils.Numeric;

@Tag(name = "Klaytn API", description = "Klaytn API")
@RequestMapping("/api/v1/klaytn")
@RestController
public class KlaytnController {

    private final getKlaytnHandler klaytnHandler;

    private final ModelMapper modelMapper;

    public KlaytnController(getKlaytnHandler klaytnHandler, ModelMapper modelMapper) {
        this.klaytnHandler = klaytnHandler;
        this.modelMapper = modelMapper;
    }

    @Tag(name = "Klaytn API", description = "Klaytn API")
    @ApiOperation(value = "트랜잭션 조회 요청")
    @Secured({"ROLE_NORMAL_USER"})
    @GetMapping
    public ResponseEntity<TransactionDto> getTransaction(@RequestParam String txHash) {

        var tx = klaytnHandler.getTransaction(txHash);
        TransactionDto txDto = modelMapper.map(tx, TransactionDto.class);

        txDto.setValue(Utils.convertFromPeb(new BigDecimal(Numeric.toBigInt(txDto.getValue())), KlayUnit.KLAY));
        txDto.setBlockNumber(String.valueOf(Numeric.toBigInt(txDto.getBlockNumber())));
        return ResponseEntity.ok(txDto);
    }


}
