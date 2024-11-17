package com.example.demo.service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final Validator<Client> clientValidator;

    @Transactional(readOnly = true)
    public Client findOne(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
    }

    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Transactional
    public Client create(Client client) {
        clientValidator.validate(client);
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Client client, UUID id) {
        clientValidator.validate(client);
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        existingClient.setBirthDate(client.getBirthDate());
        existingClient.setFullName(client.getFullName());
        return clientRepository.save(existingClient);
    }

    @Transactional
    public void delete(UUID id) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        clientRepository.delete(existingClient);
    }
}
