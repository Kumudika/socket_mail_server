package server;

import com.google.gson.Gson;
import messages.EmailReqMsg;
import messages.EmailResMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
	//static ServerSocket variable
	private static ServerSocket server;
	//socket server port on which it will listen
	private static int port = 9800;

	public void start() throws IOException, ClassNotFoundException
	{
		//create the socket server object
		server = new ServerSocket(port);
		MailHandler mailHandler = new MailHandler();
//		mailHandler.init();
		Gson gson = new Gson();

		//keep listens indefinitely until receives 'exit' call or program terminates
		while(true){
			System.out.println("Waiting for the client request");
			//creating socket and waiting for client connection
			Socket socket = server.accept();
			//read from socket to ObjectInputStream object
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			//convert ObjectInputStream object to String
			String message = (String) ois.readObject();
			System.out.println("Message Received: " + message);
			EmailReqMsg emailReqMsg = gson.fromJson( message, EmailReqMsg.class );

			EmailResMsg emailResMsg = new EmailResMsg();

			emailResMsg.setReceivedStatus( true );
			emailResMsg.setRequestId( emailReqMsg.getRequestId() );




			//create ObjectOutputStream object
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			//write object to Socket
			oos.writeObject(gson.toJson( emailResMsg ));
			//close resources

			mailHandler.addToEmailDispatchQueue( emailReqMsg );

			ois.close();
			oos.close();
			socket.close();
			//terminate the server if client sends exit request
			if(message.equalsIgnoreCase("exit")) break;
		}
		System.out.println("Shutting down Socket server!!");
		//close the ServerSocket object
		server.close();

	}
}
