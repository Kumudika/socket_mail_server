package server;

import messages.EmailReqMsg;

import java.util.Vector;

public class MailHandler
{
	//from,password,to,subject,message
	private String from = "janithalokuge@gmail.com";
	private String password = "bahxfrsxhpuvjbhz";


	public void addToEmailDispatchQueue(EmailReqMsg emailReqMsg)
	{
		QueueService.getInstance().putRequestInQueue(emailReqMsg);
	}
}
