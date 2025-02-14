package service.transactions.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import service.transactions.entities.Transaction;
import service.transactions.model.Client;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionsRepository extends ReactiveMongoRepository<Transaction,String> {

}
