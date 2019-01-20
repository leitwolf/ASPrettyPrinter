package flexprettyprint.handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import actionscriptinfocollector.AntlrUtilities;

public class ActionScriptFormatter {

	/**
	 * @param args
	 * @throws IOException
	 */
	// public static void main(String[] args) throws IOException {
	// 	File f = new File("C:\\eclipses\\workspaces\\flexPretty\\ActionScriptPrettyPrint\\src\\com\\sas\\TestData.as");
	// 	// File f=new
	// 	// File("C:\\eclipses\\workspaces\\flexPretty\\ActionScriptPrettyPrint\\src\\com\\sas\\TestData2.mxml");
	// 	BufferedReader br = new BufferedReader(new FileReader(f));
	// 	StringBuffer buffer = new StringBuffer();
	// 	while (true) {
	// 		String d = br.readLine();
	// 		if (d == null)
	// 			break;
	// 		buffer.append(d);
	// 		buffer.append("\n");
	// 	}
	// 	String data = buffer.toString();
	// 	try {
	// 		String result = null;
	// 		if (f.getName().endsWith(".as"))
	// 			result = new ASPrettyPrinter(true, data).print(0);
	// 		else
	// 			// result = new MXMLPrettyPrinter(data).print(0);
	// 		System.out.println("*****************************************************");
	// 		System.out.println(result);
	// 	} catch (Exception e) {
	// 		System.out.println("Error pretty printing");
	// 		e.printStackTrace();
	// 	}
	// }

	public static int getNumberOfEmptyLinesAtEnd(StringBuffer buffer) {
		return getNumberOfEmptyLinesAtEnd(buffer, -1);
	}

	/**
	 * @param buffer
	 * @param fromIndex index should be -1 for "at end" or the index just after the
	 *                  search start pos (so equivalent to buffer.lenght())
	 * @return
	 */
	public static int getNumberOfEmptyLinesAtEnd(StringBuffer buffer, int fromIndex) {
		if (fromIndex < 0)
			fromIndex = buffer.length();

		// don't count the last blank line, since it will probably have data on it
		int count = 0;
		boolean firstCRFound = false;
		int i = fromIndex - 1;
		while (i >= 0) {
			char c = buffer.charAt(i);
			if (!AntlrUtilities.isASWhitespace(c))
				return count;
			if (c == '\n') {
				if (!firstCRFound)
					firstCRFound = true;
				else
					count++;
				;
			}
			i--;
		}

		return count;
	}

	public static String generateSpaceString(int spaces) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < spaces; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

	// public static String generateIndent(int spaces)
	// {
	// StringBuffer buffer=new StringBuffer();
	// for (int i=0;i<spaces;i++)
	// {
	// buffer.append(' ');
	// }
	// return buffer.toString();
	// }

	public static boolean isLineEmpty(StringBuffer buffer) {
		// the line isn't empty if it has whitespace on it
		int i = buffer.length() - 1;
		if (i < 0)
			return true;

		char c = buffer.charAt(i);
		if (c == '\n')
			return true;

		return false;
	}

	public static boolean isOnlyWhitespaceOnLastLine(StringBuffer buffer) {
		int i = buffer.length() - 1;
		while (i >= 0) {
			char c = buffer.charAt(i);
			if (!AntlrUtilities.isASWhitespace(c))
				return false;
			if (c == '\n')
				return true;
			i--;
		}

		return true;
	}

	public static String generateIndent(int spaces, boolean useTabs, int tabSize) {
		if (spaces == 0)
			return "";
		StringBuffer buffer = new StringBuffer();

		if (useTabs) {
			int tabCount = spaces / tabSize;
			for (int i = 0; i < tabCount; i++) {
				buffer.append('\t');
			}
			spaces = spaces % tabSize; // change value of spaces to be left-over spaces
		}

		for (int i = 0; i < spaces; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

	/**
	 * This is a weaker validation that just checks to make sure that the number of
	 * occurrences of each character is identical.
	 * 
	 * @param buffer
	 * @param originalSource
	 * @return
	 */
	public static boolean validateNonWhitespaceCharCounts(String buffer, String originalSource) {
		// some reasonable way of validating. Just count non-whitespace and make sure
		// that we have at least as many
		// chars as before. Could improve to keep counts of each char so that ordering
		// doesn't matter.
		Map<Integer, Integer> originalCharMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> newCharMap = new HashMap<Integer, Integer>();

		int originalCharCount = 0;
		for (int i = 0; i < originalSource.length(); i++) {
			char c = originalSource.charAt(i);
			if (!AntlrUtilities.isASWhitespace(c)) {
				originalCharCount++;
				Integer count = originalCharMap.get(new Integer(c));
				if (count == null) {
					originalCharMap.put(new Integer(c), new Integer(1));
				} else {
					originalCharMap.put(new Integer(c), new Integer(count.intValue() + 1));
				}
			}
		}

		int newCharCount = 0;
		for (int i = 0; i < buffer.length(); i++) {
			char c = buffer.charAt(i);
			if (!AntlrUtilities.isASWhitespace(buffer.charAt(i))) {
				newCharCount++;
				Integer count = newCharMap.get(new Integer(c));
				if (count == null) {
					newCharMap.put(new Integer(c), new Integer(1));
				} else {
					newCharMap.put(new Integer(c), new Integer(count.intValue() + 1));
				}
			}
		}

		if (newCharMap.size() != originalCharMap.size())
			return false;

		for (Integer charAsInt : originalCharMap.keySet()) {
			Integer origCount = originalCharMap.get(charAsInt);
			Integer newCount = newCharMap.get(charAsInt);
			if (newCount == null || origCount.intValue() != newCount.intValue())
				return false;
		}

		if (newCharCount != originalCharCount)
			return false;

		return true;
	}

	public static boolean validateNonWhitespaceIdentical(String s1, String s2) {
		StringBuffer newBuffer1 = new StringBuffer();
		StringBuffer newBuffer2 = new StringBuffer();
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			if (!AntlrUtilities.isASWhitespace(c)) {
				newBuffer1.append(c);
			}
		}
		for (int i = 0; i < s2.length(); i++) {
			char c = s2.charAt(i);
			if (!AntlrUtilities.isASWhitespace(c)) {
				newBuffer2.append(c);
			}
		}
		return newBuffer1.toString().equals(newBuffer2.toString());
	}

	public static void trimAllWhitespaceOnEndOfBuffer(StringBuffer buffer) {
		// This is code to trim all trailing whitespace in buffer, including newlines.
		for (int i = buffer.length() - 1; i >= 0; i--) {
			char c = buffer.charAt(i);
			if (c == ' ' || c == '\t' || c == '\n')
				buffer.deleteCharAt(i);
			else
				break;
		}
	}

	public static void trimWhitespaceOnEndOfBuffer(StringBuffer buffer) {
		// This is code to trim trailing whitespace on lines.
		for (int i = buffer.length() - 1; i >= 0; i--) {
			char c = buffer.charAt(i);
			if (c == ' ' || c == '\t')
				buffer.deleteCharAt(i);
			else
				break;
		}
	}
}
