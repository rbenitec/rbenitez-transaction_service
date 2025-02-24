package service.transactions.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountServiceResponseDto {
    private String id;
    private String typeAccount;
    private String idClient;
    private Double openingBalance;
    private String account;
    private String cci;
    private String dateCreated;
    private String dateUpdate;
}
