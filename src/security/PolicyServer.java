package security;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;

public class PolicyServer {
  public static void main (String args[]) {
    final int DEFAULT_PORT = 1234;

    // Set server port
    int port = DEFAULT_PORT;
    try {
      port = Integer.parseInt(args[0]);
    } catch (Exception e){
      port = DEFAULT_PORT;
    }

    // Start server socket
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
      System.out.println("Server listening on port: " + port);
    } catch (IOException e) {
      System.err.println("Could not listen on port: " + port);
      System.exit(1);
    }

    while (true) {
      // Accept a client and spawn a thread
      Socket clientSocket = null;
      try {
        clientSocket = serverSocket.accept();
        System.out.println("Connection accepted, sending XML...");
      } catch (IOException e) {
        System.out.println("Accept failed: " + port);
      }

      SecurityThread thread = new SecurityThread(clientSocket);
      thread.run();
    }
  }
}
