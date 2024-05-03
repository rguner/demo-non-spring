package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) {
        try {
            // Create an HTTP server listening on port 8000
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            System.out.println("Server started at http://localhost:8080");

            // Define a handler that will receive the HTTP requests
            server.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String response = "Hello, this is a simple HTTP Server!";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response.getBytes());
                    }
                }
            });

            // Start the server
            server.start();
        } catch (IOException e) {
            System.out.println("Server failed to start: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
