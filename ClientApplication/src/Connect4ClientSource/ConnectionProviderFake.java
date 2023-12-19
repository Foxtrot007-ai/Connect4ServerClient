package Connect4ClientSource;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ConnectionProviderFake extends ConnectionProvider{
	public Scanner myObj = new Scanner(System.in);
	
	public ConnectionProviderFake(String ip, int port) {
		super(ip, port);
		// TODO Auto-generated constructor stub
	}

	public void connectServer() throws UnknownHostException, IOException {
	}
	
	public void sendMessage(String message) {
		System.out.println(message);
	}
	
	public String getMessageFromServer() throws IOException {
		return myObj.nextLine();
	}
	
	public void stop() throws IOException {
    }
	
}
