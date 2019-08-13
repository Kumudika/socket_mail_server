package messages;

public class EmailResMsg
{
	private String requestId;
	private boolean receivedStatus;



	public String getRequestId()
	{
		return requestId;
	}

	public void setRequestId( String requestId )
	{
		this.requestId = requestId;
	}

	public boolean isReceivedStatus()
	{
		return receivedStatus;
	}

	public void setReceivedStatus( boolean receivedStatus )
	{
		this.receivedStatus = receivedStatus;
	}
}
