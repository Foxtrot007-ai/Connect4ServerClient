package Connect4ClientSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionProvider {
	public int numberOfClients;
	public String ip;
	public int port;
	
	public Socket server;
	public PrintWriter serverOut;
	public BufferedReader serverIn;
	 
	public void connectServer() throws UnknownHostException, IOException {
		server = new Socket(ip, port);
		serverOut = new PrintWriter(server.getOutputStream(), true);
		serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
	}
	
	public void sendMessage(String message) {
		serverOut.println(message);
	}
	
	public String getMessageFromServer() throws IOException {
		return serverIn.readLine();
	}
	
	public void stop() throws IOException {
		if(server != null) {
			server.close();
			serverOut.close();
			serverIn.close();
		}
    }
	
	public ConnectionProvider(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
}
