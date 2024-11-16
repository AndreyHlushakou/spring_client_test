package org.example.spring_client_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final Map<Integer, EchoClient> clients = new HashMap<>();

    public void connectClient(int client){
        try {
            EchoClient echoClient = new EchoClient();
            clients.put(client, echoClient);
        } catch (Exception ex) {
            log.info("ERROR CONNECTION CLIENT");
        }
        log.info("Client connected");
    }

    public void sendMessage(int client, String message){
        EchoClient echoClient = clients.get(client);
        echoClient.sendMessage(message);
    }

    public void disconnectClient(int client){
        EchoClient echoClient = clients.get(client);
        try {
            echoClient.stop();
        } catch (IOException e) {
            log.info("ERROR DISCONNECTING CLIENT");
        }
        log.info("Client disconnected");
    }

    public void disconnectAllClients(){
        for (Integer i : clients.keySet()) {
            disconnectClient(i);
        }
    }
}
