package service.transactions.mapper;

import service.transactions.dto.TransactionResponseDto;
import service.transactions.entities.Transaction;

import java.util.function.Function;

public class MapperToTransactionResponse implements Function<Transaction, TransactionResponseDto> {
    @Override
    public TransactionResponseDto apply(Transaction transaction) {
        return TransactionResponseDto.builder()
                .transactionId(transaction.getIdTransaction())
                .amount(transaction.getAmount())
                .dateTransaction(transaction.getDateTransaction())
                .productId(transaction.getIdProduct())
                .typeTransaction(transaction.getTypeTransaction())
                .build();
    }
}
