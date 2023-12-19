package Connect4Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionProviderFake extends ConnectionProvider{
	public ConnectionProviderFake(int clients) {
		super(clients);
	}
	Scanner myObj = new Scanner(System.in);
	
	public void startServer(int port) throws IOException {
	}
	 
	public void connectClient(int index) throws IOException {
	}
	
	public void sendMessage(String message, int index) {
		System.out.println("To player" +index +": " +message);
	}
	
	public String getMessageFromClient(int index) throws IOException {
		return myObj.nextLine(); 
	}
	
	public void stop() throws IOException {
        
    }
}
