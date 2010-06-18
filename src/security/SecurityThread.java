package security;

import java.net.Socket;

import java.io.PrintWriter;
import java.io.IOException;

public class SecurityThread extends Thread {
  private Socket clientSocket = null;
  private String policy = null;

  public SecurityThread (Socket clientSocket, String policy){
    this.clientSocket = clientSocket;
    this.policy = policy;
  }

  public void run(){
    // Create an new output stream with auto-flush enabled
    PrintWriter out = null;
    try {
      out = new PrintWriter(clientSocket.getOutputStream(), true);
    } catch (IOException e) {
      System.err.println("Could not create output stream for client");
    }

    // Send our flash policy to the flash client
    out.println(policy);
    
    // Clost this client socket
    try {
      out.close();
      clientSocket.close();
    } catch (IOException e) {
      System.err.println("Could not close client socket.");
    }
  }
}
