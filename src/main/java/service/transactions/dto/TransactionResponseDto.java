package service.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionResponseDto {
    private String idTransaction;
    private String idAccount;
    private Double amount;
    private String typeTransaction;
    private String dateTransaction;
}
