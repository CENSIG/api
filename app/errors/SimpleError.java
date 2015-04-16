package errors;

public class SimpleError 
{
	private String error;
	
	public SimpleError(String message)
	{
		this.error = message;
	}
	
	public String getError()
	{
		return this.error;
	}
}
