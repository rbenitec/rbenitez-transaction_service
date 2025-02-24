package service.transactions.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;
import service.transactions.service.ContextTransaction;
import service.transactions.service.impl.ConcreteStrategyDeposit;
import service.transactions.service.impl.ConcreteStrategyPayment;
import service.transactions.service.impl.ConcreteStrategyWithdrawal;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionsController {

    private final ContextTransaction contextTransaction;


    @PostMapping("/deposit")
    public Mono<ResponseEntity<TransactionResponseDto>> depositTransaction(@RequestBody Mono<TransactionRequestDto> requestDto) {
        contextTransaction.setStrategyTransactionsService(new ConcreteStrategyDeposit());
        return contextTransaction.executedStrategy(requestDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @PostMapping("/withdrawal")
    public Mono<ResponseEntity<TransactionResponseDto>> withdrawalTransaction(@RequestBody Mono<TransactionRequestDto> requestDto) {
        contextTransaction.setStrategyTransactionsService(new ConcreteStrategyWithdrawal());
        return contextTransaction.executedStrategy(requestDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @PostMapping("/payment")
    public Mono<ResponseEntity<TransactionResponseDto>> paymentTransaction(@RequestBody Mono<TransactionRequestDto> requestDto) {
        contextTransaction.setStrategyTransactionsService(new ConcreteStrategyPayment());
        return contextTransaction.executedStrategy(requestDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
