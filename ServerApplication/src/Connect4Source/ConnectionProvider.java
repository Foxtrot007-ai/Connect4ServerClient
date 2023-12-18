package Connect4Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionProvider {
	public int numberOfClients;
	public Socket[] client;
	public PrintWriter[] clientOut;
	public BufferedReader[] clientIn;
	 
	public ServerSocket server;
	 
	public void startServer(int port) throws IOException {
		server = new ServerSocket(port);
	}
	 
	public void connectClient(int index) throws IOException {
		client[index] = server.accept();
		clientOut[index] = new PrintWriter(client[index].getOutputStream(), true);
		clientIn[index] = new BufferedReader(new InputStreamReader(client[index].getInputStream()));
	}
	
	public void sendMessage(String message, int index) {
		clientOut[index].println(message);
	}
	
	public String getMessageFromClient(int index) throws IOException {
		return clientIn[index].readLine();
	}
	
	public void stop() throws IOException {
		for(int i = 0; i < numberOfClients; i++) {
			if(client[i] != null) {
				client[i].close();
				clientOut[i].close();
				clientIn[i].close();
			}
		}
        server.close();
        
    }
	public ConnectionProvider(int clients) {
		numberOfClients = clients;
		client = new Socket[numberOfClients];
		clientOut = new PrintWriter[numberOfClients];
		clientIn = new BufferedReader[numberOfClients];
	}
}
