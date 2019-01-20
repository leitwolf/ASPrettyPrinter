package actionscriptinfocollector;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class AntlrUtilities {
	
	private static Token getFirstTreeToken(CommonTree tree)
	{
    	List children=tree.getChildren();
    	if (children==null)
    	{
    		return (CommonToken)tree.getToken();
    	}
    	
    	return getFirstTreeToken((CommonTree)children.get(0));
	}

	/**
	 * return whether char is whitespace, treating non-breaking space as whitespace as well
	 * @param ch
	 * @return
	 */
	public static boolean isASWhitespace(char ch)
	{
		if (Character.isWhitespace(ch))
			return true;
		if (ch=='\u00A0')
			return true;
		
		return false;
	}
	/**
	 * trim the string, treating non-breaking space as whitespace as well
	 * @param input
	 * @return
	 */
	public static String asTrim(String input)
	{
		input=input.replace('\u00a0', ' ');
		return input.trim();
	}
	
	
	private static Token getLastTreeToken(CommonTree tree)
	{
		if (tree==null)
			return null;
    	List children=tree.getChildren();
    	if (children==null)
    	{
    		return (CommonToken)tree.getToken();
    	}
    	
    	return getLastTreeToken((CommonTree)children.get(children.size()-1));
	}
	
    private static int getFirstTreePosition(CommonTree tree)
    {
    	if (tree==null)
    		return -1;
    	List children=tree.getChildren();
    	if (children==null)
    	{
    		return ((CommonToken)tree.getToken()).getStartIndex();
    	}
    	
    	return getFirstTreePosition((CommonTree)children.get(0));
    }
    
    public static int getFirstTreePosition(ParserRuleReturnScope tree)
    {
    	return getFirstTreePosition((CommonTree)tree.getTree());
    }
    
    private static int getLastTreePosition(CommonTree tree)
    {
    	List children=tree.getChildren();
    	if (children==null)
    	{
    		return ((CommonToken)tree.getToken()).getStopIndex()+1;
    	}
    	
    	return getLastTreePosition((CommonTree)children.get(children.size()-1));
    }
    
    public static int getLastTreePosition(ParserRuleReturnScope tree)
    {
    	if (tree.getTree()==null)
    	{
    		if (tree.getStop()!=null && tree.getStop() instanceof CommonToken)
    		{
    			return ((CommonToken)tree.getStop()).getStopIndex()+1; //this is necessary for implicit semicolon cases.  You will get an extra CR here
    		}
			return -1;
    	}
    	return getLastTreePosition((CommonTree)tree.getTree());
    }
    
//    public static List<String> getTreeListText(ParserRuleReturnScope tree, String delimiter)
//    {
//    	String allText=getTreeText(tree);
//    	String[] items=allText.split(delimiter);
//    	List<String> results=new ArrayList<String>();
//    	for (String item:items)
//    	{
//    		if (item.length()>0)
//    		{
//    			results.add(item);
//    		}
//    	}
//    	
//    	return results;
//    }
    
    private static String getCommonTreeText(CommonTree tree)
    {
    	if (tree==null)
    		return "";
    	List children=tree.getChildren();
    	if (children==null)
    	{
    		return tree.getToken().getText();
    	}
    	StringBuffer buffer=new StringBuffer();
    	for (Object obj:children)
    	{
    		if (obj instanceof CommonTree)
    		{
    			buffer.append(getCommonTreeText((CommonTree)obj));
    		}
    	}
    	return buffer.toString();
    }
    
    public static String getTreeText(ParserRuleReturnScope tree)
    {
    	return getCommonTreeText((CommonTree)tree.getTree());
    }

//	public static ASDocComment findPreviousComment(ParserRuleReturnScope t, CommonTokenStream rawTokens) {
//		
//		return findPreviousComment(getFirstTreeToken((CommonTree)t.getTree()), rawTokens);
//	}
//
//	public static ASDocComment findPreviousComment(Token tok, CommonTokenStream rawTokens)
//	{
//		int currentTokenIndex=((CommonToken)tok).getTokenIndex()-1;
////		List<Token> hiddenTokens=new ArrayList<Token>();
//		
//		//collect all of the hidden tokens since the last non-whitespace token
//		while (currentTokenIndex>=0)
//		{
//			Token t=rawTokens.get(currentTokenIndex);
//			if (t.getChannel()==Token.DEFAULT_CHANNEL)
//				break; 
//			
//			if (t.getType()==ASCollectorLexer.COMMENT_MULTILINE && t.getText().startsWith("/**"))
//			{
//				return new ASDocComment(t);
//			}
////			hiddenTokens.add(t);
//			currentTokenIndex--;
//		}
////		Collections.reverse(hiddenTokens);
//		return null;
//	}

	// public static ASDocComment findCommentReverse(List<Token> hiddenTokens)
	// {
	// 	int currentTokenIndex=hiddenTokens.size()-1;
		
	// 	//collect all of the hidden tokens since the last non-whitespace token
	// 	loop: while (currentTokenIndex>=0)
	// 	{
	// 		Token t=hiddenTokens.get(currentTokenIndex);
	// 		switch (t.getChannel())
	// 		{
	// 		case ASCollectorParser.CHANNEL_MLCOMMENT:
	// 			if (t.getText().startsWith("/**"))
	// 				return new ASDocComment(t);
	// 			break loop;
	// 		case ASCollectorParser.CHANNEL_WHITESPACE:
	// 		case ASCollectorParser.CHANNEL_EOL:
	// 			currentTokenIndex--;
	// 			break;
	// 		default:
	// 			break loop;
	// 		}
	// 	}
	// 	return null;
	// }
	
	public static List<Token> getPostHiddenTokens(Token tok, CommonTokenStream rawTokens)
	{
		List<Token> results=new ArrayList<Token>();
		if (tok==null)
			return results;
		int currentTokenIndex=((CommonToken)tok).getTokenIndex()+1;
		while (currentTokenIndex<rawTokens.size())
		{
			Token t=rawTokens.get(currentTokenIndex);
			if (t.getChannel()==Token.DEFAULT_CHANNEL)
				break;
			
			if (t.getChannel()==ASCollectorParser.CHANNEL_EOL)
				break;
			
			if (t.getChannel()==ASCollectorParser.CHANNEL_SLCOMMENT)
			{
				results.add(t);
				break;
			}
			
			results.add(t);
			currentTokenIndex++;
		}
		
		//walk backwards to remove whitespace tokens at the end of list
		for (int i=results.size()-1;i>=0;i--)
		{
			Token t=results.get(i);
			if (t.getChannel()==ASCollectorParser.CHANNEL_WHITESPACE)
				results.remove(i);
		}
		
		return results;
	}

	public static List<Token> getPostHiddenTokens(ParserRuleReturnScope tree, CommonTokenStream rawTokens)
	{
		if (tree.getTree()==null)
		{
			//I think this only happens with implied semicolons
			if (tree.getStart() instanceof CommonToken && tree.getStart()!=null)
			{
				//I think we should always be on at least token 1.  
				Token currentTok=rawTokens.get(((CommonToken)tree.getStart()).getTokenIndex());
				//I go back one token if I am on a non-default channel token so that I can search forward for hidden tokens.
				if (currentTok.getChannel()!=Token.DEFAULT_CHANNEL)
					currentTok=rawTokens.get(((CommonToken)tree.getStart()).getTokenIndex()-1);
				return getPostHiddenTokens(currentTok, rawTokens);
			}
			return null;
		}
		return getPostHiddenTokens(getLastTreeToken((CommonTree)tree.getTree()), rawTokens);
	}
	
	public static List<Token> getHiddenTokens(Token tok, CommonTokenStream rawTokens, boolean crossLineBoundaries, boolean filterNone)
	{
		List<Token> results=new ArrayList<Token>();
		if (tok==null)
			return results;
		int currentTokenIndex=((CommonToken)tok).getTokenIndex()-1;
		boolean seenCR=false;
		int tokensSinceLastCR=0;
		while (currentTokenIndex>=0)
		{
			Token t=rawTokens.get(currentTokenIndex);
			if (t.getChannel()==Token.DEFAULT_CHANNEL)
				break;
			
			if (t.getChannel()==ASCollectorParser.CHANNEL_EOL || t.getChannel()==ASCollectorParser.CHANNEL_SLCOMMENT)
			{
				if (!crossLineBoundaries)
					break;
				tokensSinceLastCR=0;
//				if (t.getChannel()==ASCollectorParser.CHANNEL_SLCOMMENT)
//					tokensSinceLastCR++;
				seenCR=true;
			}
			else
			{
				tokensSinceLastCR++;
			}
			
			results.add(0, t);
			currentTokenIndex--;
		}
		
		//if we want all the hidden tokens (without any post-processing), then just return here
		if (filterNone)
			return results;
		
		//strip off tokens from previous line that had code on it
		if (seenCR && currentTokenIndex>=0)
		{
			for (int i=0;i<tokensSinceLastCR;i++)
			{
				results.remove(0);
			}
		}
		
		if (results.size()>0 && currentTokenIndex>=0)
		{
			//remove the first token if it contained a carriage return.  The idea here is to not include the token that should
			//go with the previous line.  Special case for first token of file
			if (results.get(0).getChannel()==ASCollectorParser.CHANNEL_EOL || results.get(0).getChannel()==ASCollectorParser.CHANNEL_SLCOMMENT)
			{
				results.remove(0);
			}
			else if (crossLineBoundaries)
			{
				//otherwise, remove whitespace tokens up to the first carriage return or all tokens
				while (results.size()>0)
				{
					Token t=results.get(0);
					if (t.getChannel()==ASCollectorParser.CHANNEL_EOL)
						break;
					results.remove(0);
				}
			}
		}
		
		//leave leading whitespace associated with the element
//		//now, strip off the leading whitespace
//		while (results.size()>0)
//		{
//			Token t=results.get(0);
//			if (t.getChannel()==ASCollectorParser.CHANNEL_EOL || t.getChannel()==ASCollectorParser.CHANNEL_WHITESPACE)
//			{
//				results.remove(0);
//				continue;
//			}
//			break;
//		}
		return results;
	}

	public static List<Token> getHiddenTokens(ParserRuleReturnScope tree, CommonTokenStream rawTokens, boolean crossLineBoundaries)
	{
		return getHiddenTokens(getFirstTreeToken((CommonTree)tree.getTree()), rawTokens, crossLineBoundaries, false);
	}

	public static String findPreWhitespace(String text) 
	{
		StringBuffer buffer=new StringBuffer();
		for (int i=0;i<text.length();i++)
		{
			char ch=text.charAt(i);
			if (isASWhitespace(ch))
				buffer.append(ch);
			else
				break;
		}
		return buffer.toString();
	}
	// -------------
	
}
