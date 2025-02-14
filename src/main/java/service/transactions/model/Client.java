package service.transactions.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Document(collection = "Client")
public class Client {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;

}
