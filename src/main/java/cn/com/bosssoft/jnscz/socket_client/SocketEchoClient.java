package cn.com.bosssoft.jnscz.socket_client;

import java.net.*;
import java.io.*;

public class SocketEchoClient {
	
	public static void main(String[] args) {
		try {
			Socket echoSocket = new Socket("localhost",8080);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			String clientMsg = "";
			System.out.println("Client ready.");
			System.out.print("The client says:");
			while ((clientMsg = stdin.readLine()) != "byebye") {
				// write into server
				out.println(clientMsg);
				//get from server
				String serverMsg = in.readLine();
				System.out.println("The server says:" + serverMsg);
				System.out.print("The client says:");
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
