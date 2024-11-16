package org.example.spring_client_test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class MainController {
    private final ClientService clientService;

    //echo//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/echo/{echo}")
    public <T> T getEcho(@PathVariable T echo) {
        return echo;
    }
    @GetMapping("/echo")
    public String getEcho() {
        return "echo";
    }


    @GetMapping("/connectClient/{client}")
    public void connectClient(@PathVariable int client) {
        clientService.connectClient(client);
    }


    @GetMapping("/sendMessage/{client}/{message}")
    public void sendMessage(@PathVariable int client, @PathVariable String message) {
        clientService.sendMessage(client, message);
    }


    @GetMapping("/disconnectClient/{client}")
    public void disconnectClient(@PathVariable int client) {
        clientService.disconnectClient(client);
    }
    @GetMapping("/disconnectClient")
    public void disconnectClient() {
        clientService.disconnectAllClients();
    }

}
