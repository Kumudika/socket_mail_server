package client;

import com.google.gson.Gson;
import messages.EmailReqMsg;
import messages.EmailResMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient
{

	public SocketClient( int port, InetAddress host )
	{
	}


	public static EmailResMsg sendEmailReq( EmailReqMsg emailReqMsg ) throws IOException, ClassNotFoundException
	{
		//get the localhost IP address, if server is running on some other IP, you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Gson gson = new Gson();

		int i = 0;
		//establish socket connection to server
		socket = new Socket(host.getHostName(), 9876);
		//write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("Message RQ: " + gson.toJson( emailReqMsg ));
		oos.writeObject(gson.toJson( emailReqMsg ));
		//read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		String message = (String) ois.readObject();
		System.out.println("Message RS: " + message);

		EmailResMsg resMsg = gson.fromJson( message, EmailResMsg.class );

		ois.close();
		oos.close();
		return resMsg;
	}
}
