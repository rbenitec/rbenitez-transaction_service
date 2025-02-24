package service.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionResponseDto {
    private String transactionId;
    private String productId;
    private Double amount;
    private String typeTransaction;
    private String dateTransaction;
}
