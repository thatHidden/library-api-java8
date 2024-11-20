package com.example.demo.controller;

import com.example.demo.dto.request.CommandClientDto;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientPageController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public String getGroupPage(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int clientPage) {
        final int PAGE_SIZE = 5;

        Pageable clientPageable = PageRequest.of(clientPage, PAGE_SIZE);

        Page<Client> clientPageResult = clientService.findAll(clientPageable);

        model.addAttribute("clients", clientPageResult.getContent());
        model.addAttribute("clientsCurrentPage", clientPageResult.getNumber());
        model.addAttribute("clientsTotalPages", clientPageResult.getTotalPages());

        if (!model.containsAttribute("commandClientDto")) {
            model.addAttribute("commandClientDto", CommandClientDto.builder().build());
        }

        return "clients";
    }

    @GetMapping("/update/{id}")
    public String updateClientForm(Model model, @PathVariable("id") UUID id) {
        Client existingClient = clientService.findOne(id);

        model.addAttribute("clientId", existingClient.getId());

        if (!model.containsAttribute("commandClientDto")) {
            model.addAttribute("commandClientDto",
                    CommandClientDto.builder()
                            .fullName(existingClient.getFullName())
                            .birthDate(existingClient.getBirthDate())
                            .build()
            );
        }

        return "clients_update";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@Valid @ModelAttribute("commandClientDTO") CommandClientDto commandClientDto,
                               BindingResult bindingResult,
                               @PathVariable("id") UUID id,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commandClientDto", bindingResult);
            redirectAttributes.addFlashAttribute("commandClientDto", commandClientDto);
            return "redirect:/client/update/" + id;
        }

        clientService.update(clientMapper.toModel(commandClientDto), id);

        return "redirect:/client";
    }

    @PostMapping("/add")
    public String addBorrow(
            @Valid @ModelAttribute("commandClientDto") CommandClientDto commandClientDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commandClientDto", bindingResult);
            redirectAttributes.addFlashAttribute("commandClientDto", commandClientDto);
            return "redirect:/client";
        }

        clientService.create(clientMapper.toModel(commandClientDto));

        return "redirect:/client";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") UUID id) {
        clientService.delete(id);
        return "redirect:/client";
    }
}
