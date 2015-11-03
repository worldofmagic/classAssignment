package Assignment;

public class StringPair
{
	String firstOfPair = "";
	String secondOfpair = "";
	
	public StringPair(int a)
	{
		setFirstOfPair(StringHelper.random(a));
		setSecondOfpair(StringHelper.random(a));
	}
	
	public String getFirstOfPair()
	{
		return firstOfPair;
	}
	public void setFirstOfPair(String firstOfPair)
	{
		this.firstOfPair = firstOfPair;
	}
	public String getSecondOfpair()
	{
		return secondOfpair;
	}
	public void setSecondOfpair(String secondOfpair)
	{
		this.secondOfpair = secondOfpair;
	}
	
	

}
