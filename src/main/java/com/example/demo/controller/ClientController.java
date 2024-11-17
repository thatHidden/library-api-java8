package com.example.demo.controller;

import com.example.demo.dto.request.CommandClientDto;
import com.example.demo.dto.response.ResponseClientDto;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping("/{id}")
    private ResponseClientDto getClient(@PathVariable("id") UUID id) {
        return clientMapper.toDto(clientService.findOne(id));
    }

    @GetMapping()
    private Page<ResponseClientDto> getAllClients(@PageableDefault Pageable pageable) {
        return clientService.findAll(pageable).map(clientMapper::toDto);
    }

    @PostMapping()
    public ResponseClientDto createClient(@RequestBody CommandClientDto dto) {
        return clientMapper.toDto(clientService.create(clientMapper.toModel(dto)));
    }

    @PutMapping("/{id}")
    public ResponseClientDto updateClient(@RequestBody CommandClientDto dto,
                                          @PathVariable UUID id) {
        return clientMapper.toDto(clientService.update(clientMapper.toModel(dto), id));
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.delete(id);
    }
}
