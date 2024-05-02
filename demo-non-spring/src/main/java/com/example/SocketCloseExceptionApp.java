package com.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SocketCloseExceptionApp {
    public static void main(String[] args) {
        try {
            // Create a new socket
            Socket socket = new Socket();

            // Connect to the server
            socket.connect(new InetSocketAddress("localhost", 8080));

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.print("POST /api/validate HTTP/1.1\\n\\r\\n");
            pw.print("Host: localhost\\n\\r\\n");
            pw.flush();
            // Immediately close the socket
            socket.close();

            // Try to perform an operation on the closed socket
            // socket.getInputStream(); // client side socket close exception.

        } catch (SocketException e) {
            // This will catch the "Socket closed" exception
            System.out.println("Caught SocketException: " + e.getMessage());
        } catch (IOException e) {
            // This will catch any other IOExceptions
            System.out.println("Caught IOException: " + e.getMessage());
        }
    }
}