package actionscriptinfocollector;

public class Utilities
{
	public static String convertCarriageReturnsToLineFeeds(String source)
	{
		//convert /r only to /r/n since the parser doesn't handle /r by itself as a valid carriage return.
		//NOTE: this method should NOT change the number of characters in the input string.
		StringBuffer buffer=new StringBuffer(source.length()); //we know the result string will be same length
		for (int i=0;i<source.length();i++)
		{
			char c=source.charAt(i);
			if (c=='\r')
			{
				//if this is part of a cr/lf pair, then don't touch it
				if (i+1<source.length() && source.charAt(i+1)=='\n')
				{
					buffer.append('\r'); //keep the CR since it is still appropriate
					continue;
				}
				
				buffer.append('\n'); //convert to be a \n
			}
			else
			{
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
}
