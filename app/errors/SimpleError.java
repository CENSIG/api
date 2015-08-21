package errors;

public class SimpleError 
{
	private String error;
	
	public SimpleError(String message)
	{
		this.error = message;
	}
	
	public SimpleError()
	{
		this(null);
	}
	
	public String getError()
	{
		return this.error;
	}
	
	public void setError(String message)
	{
		this.error = message;
	}
}
