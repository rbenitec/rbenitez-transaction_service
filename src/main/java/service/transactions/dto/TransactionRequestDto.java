package service.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import service.transactions.model.TransactionType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionRequestDto {
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe ser vacio")
    private String productId;
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe ser vacio")
    private TransactionType transactionType;
    @NotNull(message = "No debe ser nulo")
    private Double amount;
}
