package service.transactions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;
import service.transactions.model.Client;
import service.transactions.service.TransactionsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;


    @PostMapping("/deposit")
    public Mono<ResponseEntity<TransactionResponseDto>> depositTransaction (@RequestBody TransactionRequestDto requestDto){
        return null;
    }



    @PostMapping("/withdrawal")
    public Mono<ResponseEntity<TransactionResponseDto>> withdrawalTransaction(@RequestBody TransactionRequestDto requestDto){
        return null;
    }



    @PostMapping("/payment")
    public Mono<ResponseEntity<TransactionResponseDto>> paymentTransaction (@RequestBody TransactionRequestDto requestDto){
        return null;
    }

}
