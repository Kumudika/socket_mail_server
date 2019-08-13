package client;

import messages.EmailReqMsg;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.UUID;

public class SocketClientExample {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
		while(args.length == 2) {
			final int noOfRequests = Integer.parseInt(args[0]);
			int noOfThreads = Integer.parseInt(args[1]);
			Runnable runnable = new Runnable()
			{
				@Override
				public void run()
				{
					int i = 0;
					while ( i < noOfRequests ){
						try
						{
							String reqId = UUID.randomUUID().toString().concat("-").concat(String.valueOf(i));
							SocketClient.sendEmailReq( getRq(reqId));
						}
						catch ( IOException e )
						{
							e.printStackTrace();
						}
						catch ( ClassNotFoundException e )
						{
							e.printStackTrace();
						}
						i++;

					}
				}
			};

			for ( int j = 0; j < noOfThreads ; j++ )
			{
				Thread emailSendingClientThread = new Thread( runnable );
				emailSendingClientThread.start();
			}
		}
	}

	public static EmailReqMsg getRq(String requestId){

		EmailReqMsg emailReqMsg = new EmailReqMsg(  );

		emailReqMsg.setRequestId( requestId);
		emailReqMsg.setBody( "Body" );
		emailReqMsg.setReceipentEmail( "janithalokuge92@gmail.com" );
		emailReqMsg.setSubject( requestId );

		return  emailReqMsg;
	}
}
