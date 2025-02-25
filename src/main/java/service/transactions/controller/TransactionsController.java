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
import service.transactions.exception.BusinessException;
import service.transactions.service.TransactionStrategyFactory;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionStrategyFactory transactionStrategyFactory;

    @PostMapping
    public Mono<ResponseEntity<TransactionResponseDto>> processTransaction(@RequestBody @Valid Mono<TransactionRequestDto> requestDtoMono) {
        return requestDtoMono
                .flatMap(request -> transactionStrategyFactory.executeStrategy(request.getTransactionType(), Mono.just(request))
                        .map(ResponseEntity::ok)
                        .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                .onErrorMap(throwable -> new BusinessException("processTransaction", throwable.getMessage()));
    }

    /*
    @PostMapping("/deposit")
    public Mono<ResponseEntity<TransactionResponseDto>> depositTransaction(@RequestBody Mono<TransactionRequestDto> requestDto) {
        transactionStrategyFactory.setTransactionsStrategyFactory(new ConcreteStrategyDeposit());
        return transactionStrategyFactory.executedStrategy(requestDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @PostMapping("/withdrawal")
    public Mono<ResponseEntity<TransactionResponseDto>> withdrawalTransaction(@RequestBody Mono<TransactionRequestDto> requestDto) {
        transactionStrategyFactory.setTransactionsStrategyFactory(new ConcreteStrategyWithdrawal());
        return transactionStrategyFactory.executedStrategy(requestDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @PostMapping("/payment")
    public Mono<ResponseEntity<TransactionResponseDto>> paymentTransaction(@RequestBody Mono<TransactionRequestDto> requestDto) {
        transactionStrategyFactory.setTransactionsStrategyFactory(new ConcreteStrategyPayment());
        return transactionStrategyFactory.executedStrategy(requestDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

     */

}
