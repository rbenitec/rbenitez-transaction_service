package service.transactions.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreditServiceResponseDto {
    private String id;
    private String creditType;
    private String customerId;
    private Double amount;
    private Double interestRate;
    private Integer termMonths;
    private String createdAt;
    private String updateAt;
}
