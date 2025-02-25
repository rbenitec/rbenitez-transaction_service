package service.transactions.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;
import service.transactions.exception.BusinessException;
import service.transactions.model.TransactionType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TransactionStrategyFactory {

    private final Map<TransactionType, StrategyTransactionsService> strategies;

    /**
     * A nivel del constructor se defini el tipo de transaccion.
     *
     * @param strategyList
     */
    public TransactionStrategyFactory(List<StrategyTransactionsService> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(StrategyTransactionsService::getTransactionType, Function.identity()));
    }

    /**
     * Método para obtener la estrategia y ejecutarla directamente.
     */
    public Mono<TransactionResponseDto> executeStrategy(TransactionType transactionType, Mono<TransactionRequestDto> transactionRequest) {
        StrategyTransactionsService strategy = strategies.get(transactionType);
        if (strategy == null) {
            return Mono.error(new BusinessException("ERROR", "Tipo de transacción no soportado"));
        }
        return strategy.executedTransaction(transactionRequest);
    }

}
