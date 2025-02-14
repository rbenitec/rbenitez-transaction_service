package service.transactions.service;

import service.transactions.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionsService {
	Flux<Client> findAll();
	Mono<Client> save(Client client);

	Flux<Client> findClientByAge(int age);

	Mono<Client> update(Client client);

	Mono<Void> delete(String id);
}
