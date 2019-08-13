package messages;

public class EmailReqMsg
{

//	- RequestID - A plain text reference that can potentially be used to avoid
//	duplicate submissions
//- Sender Name - Just a plain text name
//- Recipient Email Address - a valid email address
//- Subject - Plain text subject
//- MessageText - Plain text message. No need to worry about escaping special
//	characters.


	private String requestId;
	private String senderName;
	private String receipentEmail;
	private String subject;
	private String body;

	public EmailReqMsg( String requestId, String senderName, String receipentEmail, String subject, String body )
	{
		this.requestId = requestId;
		this.senderName = senderName;
		this.receipentEmail = receipentEmail;
		this.subject = subject;
		this.body = body;
	}

	public EmailReqMsg( )
	{

	}

	public String getRequestId()
	{
		return requestId;
	}

	public void setRequestId( String requestId )
	{
		this.requestId = requestId;
	}

	public String getSenderName()
	{
		return senderName;
	}

	public void setSenderName( String senderName )
	{
		this.senderName = senderName;
	}

	public String getReceipentEmail()
	{
		return receipentEmail;
	}

	public void setReceipentEmail( String receipentEmail )
	{
		this.receipentEmail = receipentEmail;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject( String subject )
	{
		this.subject = subject;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody( String body )
	{
		this.body = body;
	}
}
