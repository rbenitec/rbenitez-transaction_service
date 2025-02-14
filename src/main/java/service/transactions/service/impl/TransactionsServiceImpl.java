package service.transactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.transactions.model.Client;
import service.transactions.repository.TransactionsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.transactions.service.TransactionsService;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    @Override
    public Flux<Client> findAll() {
        return transactionsRepository.findAll();
    }

    @Override
    public Mono<Client> save(Client client) {
        return transactionsRepository.save(client);
    }

    @Override
    public Flux<Client> findClientByAge(int age) {
        return transactionsRepository.findAll().filter(k -> k.getAge() == age);
    }

    @Override
    public Mono<Client> update(Client client) {
        return transactionsRepository.save(client);
    }

    @Override
    public Mono<Void> delete(String id) {
        return transactionsRepository.deleteById(id);
    }


}
