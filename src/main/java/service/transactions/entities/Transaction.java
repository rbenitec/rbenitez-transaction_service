package service.transactions.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transaction")
public class Transaction {
    @Id
    private String idTransaction;
    private String idProduct;
    private Double amount;
    private String typeTransaction;
    private String dateTransaction;
}
