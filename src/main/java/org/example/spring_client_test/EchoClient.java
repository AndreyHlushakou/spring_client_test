package org.example.spring_client_test;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class EchoClient {
    /*Value("${tspServer.port}")*/ int port = 700;

    private final SocketChannel client;
    private ByteBuffer buffer;

    public void stop() throws IOException {
        client.close();
        buffer = null;
    }

    public EchoClient() throws IOException {
        client = SocketChannel.open(new InetSocketAddress("localhost", port));
        buffer = ByteBuffer.allocate(256);
    }

    public String sendMessage(String msg) {
        String[] arrStr = msg.split(" ");
        byte[] msgBytes = new byte[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            msgBytes[i] = (byte) Integer.parseInt(arrStr[i], 16);
        }

        buffer = ByteBuffer.wrap(msgBytes);
        String response = null;
        try {
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            response = new String(buffer.array()).trim();
            System.out.println("response=" + response);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }
}
