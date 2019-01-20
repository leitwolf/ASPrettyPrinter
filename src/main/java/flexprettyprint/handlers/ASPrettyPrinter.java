package flexprettyprint.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.swt.graphics.Point;

import actionscriptinfocollector.AntlrUtilities;
import actionscriptinfocollector.Utilities;
import flexprettyprint.handlers.AS3_exParser.fileContents_return;

public class ASPrettyPrinter
{
	private static final int AddCRIfBeyondMaxCol=8;
	private static final int AddCR=9;
	private static final int AddCR_BlankLine=10;
	private static final int ADD_WS=11;
	
	public static final int INDENT_LIKE_SUBITEM=16;
	public static final int INDENT_TO_WRAP_ELEMENT=32;
	
	public static final int FORMAT_ALL=100;
	public static final int FORMAT_INDENT=101;
	public static final int FORMAT_NOCRs=102;
	public static final int FORMAT_OnlyAddCRs=103;
	
	
	public static final int BraceStyle_Sun=4;
	public static final int BraceStyle_Adobe=5;

	public static final int MLAsteriskStyle_AsIs=0;
	public static final int MLAsteriskStyle_All=1;
	public static final int MLAsteriskStyle_None=2;
	
	
	public static final String Break_SubType_Array="array";
	public static final String Break_SubType_Parameters="parameters";
	public static final String Break_SubType_Arguments="args";
	public static final String Break_SubType_Object="object";
	public static final String Break_SubType_Other="other";
	
	public static final String Break_Commas="commas";
	public static final String Break_Arithmetic_Ops="arithmetic ops";
	public static final String Break_Logical_Ops="logical ops (&&, ||)";
	public static final String Break_Function_Calls="member access";
	public static final String Break_Other_Connectors="other connectors (., ::, etc.)";
	public static final String Break_Assignment="assignment";
	public static final String Break_Parameter_Assignment="parameter assignment";
	public static final String Break_Ternary="ternary ops";
	public static final String Break_Keyword="extends/implements";
	public static final Map<String, Integer> Break_Table_Items;
	
	public static final int Break_Commas_code=0x1;
	public static final int Break_Arithmetic_Ops_code=0x2;
	public static final int Break_Logical_Ops_code=0x4;
	public static final int Break_Function_Calls_code=0x8;
	public static final int Break_Other_Connectors_code=0x10;
	public static final int Break_Assignment_code=0x20;
	public static final int Break_Ternary_code=0x40;
	public static final int Break_Keyword_code=0x80;
	public static final int Break_XML_code=0x100;
	public static final int Break_Parameter_Assignment_code=0x200;
	
	public static final int Braces_NoModify=1;
	public static final int Braces_AddIfMissing=2; //add always
	public static final int Braces_AddRemoveSmart=4; //add smart plus remove using the same 'smart' rules
	public static final int Braces_AddSmart=8; //requires other arguments?  Don't add for 'single line' bodies
	public static final int Braces_RemoveUnnecessary=16; //mainly for 'switch' cases, where they are never necessary.

	//these are also used as items in the UI, and keys in the prefs, so don't change them
	public static final String BraceContext_package="package";
	public static final String BraceContext_Conditional="conditional compilation";
	public static final String BraceContext_interface="interface";
	public static final String BraceContext_class="class";
	public static final String BraceContext_functionDecl="function declaration";
	public static final String BraceContext_functionExpression="function expression";
	public static final String BraceContext_controlStatement="control statement";
	public static final String BraceContext_try="try";
	public static final String BraceContext_catch="catch";
	public static final String BraceContext_finally="finally";
	public static final String BraceContext_block="block";
	public static final Map<String, Integer> Brace_Context_Items;

	public static final int BraceContext_package_code=0x1;
	public static final int BraceContext_Conditional_code=0x2;
	public static final int BraceContext_interface_code=0x4;
	public static final int BraceContext_class_code=0x8;
	public static final int BraceContext_functionDecl_code=0x10;
	public static final int BraceContext_functionExpression_code=0x20;
	public static final int BraceContext_controlStatement_code=0x40;
	public static final int BraceContext_try_code=0x80;
	public static final int BraceContext_catch_code=0x100;
	public static final int BraceContext_finally_code=0x200;
	public static final int BraceContext_block_code=0x400;
	

	public static final int BindingContext_None=1;
	public static final int BindingContext_Function=2;
	public static final int BindingContext_Property=3;
	public static final int BindingContext_Class=4;
	private int mBindingContext=BindingContext_None;
	private boolean mUseAdvancedWrapping=false;
	private int mAdvancedWrappingElements=Break_Logical_Ops_code | Break_Commas_code | Break_Assignment_code;
	private boolean mAdvancedWrappingPreservePhrases=true;
	private int mAdvancedWrappingGraceColumns=5;
	private boolean mAdvancedWrappingEnforceMax=false;
	private boolean mIsKeepingExcessDeclWhitespace=false;
	private boolean mInParameterDecl=false;
//	private int mIndentStyle=INDENT_LIKE_SUBITEM;
	private int mBlockIndent;
	private int mHangingIndentTabs=1;
	private boolean mCRBeforeOpenBrace;
	private boolean mDoFormat;
	private boolean mTrimTrailingWS=true;
	private int mSpacesAfterLabel=1;
	private int mSpacesAroundAssignment=1;
	private int mSpacesBeforeFormalParameters=0;
	private int mSpacesBeforeArguments=0;
	private int mAdvancedSpacesAroundAssignmentInOptionalParameters=1;
	private int mAdvancedSpacesAroundAssignmentInMetatags=1;
	private boolean mUseAdvancedSpacesAroundAssignmentInOptionalParameters=false;
	private boolean mUseAdvancedSpacesAroundAssignmentInMetatags=false;
	private int mSpacesAroundColons=0;
	private int mAdvancedSpacesBeforeColonsInDeclarations				= 0;
	private int mAdvancedSpacesAfterColonsInDeclarations				= 0;
	private int mAdvancedSpacesBeforeColonsInFunctionTypes					= 0;
	private int mAdvancedSpacesAfterColonsInFunctionTypes					= 0;
	private boolean mUseGlobalSpacesAroundColons;
	private int mSpacesBeforeComma=0;
	private int mSpacesAfterComma=1;
	private int mSpacesInsideParensEtc=1;
	private boolean mKeepSpacesBeforeLineComments=false;
	private int mLineCommentColumn=0; //column where comments on the end of line are put.
	private boolean mAlwaysGenerateIndent=false;
	private boolean mIndentAtPackageLevel=true; //if true, normal indent; false means don't indent imports and class declarations, but leave them at column 0
	private boolean mIndentSwitchCases=true; //if true, normal indent; false means don't indent case keyword beyond level of 'switch' keyword
	private int mConditionalBraceModifyMode=Braces_NoModify;
	private int mLoopBraceModifyMode=Braces_NoModify;
	private int mSwitchBraceModifyMode=Braces_NoModify;
	
	private Set<String> mMetaTagsToKeepOnSameLineAsProperty;
	private Set<String> mMetaTagsToKeepOnSameLineAsFunction;

	private boolean mUseBraceStyleSetting=true;
	private int mBraceStyleSetting=BraceStyle_Adobe;
	private boolean mUseGlobalNewlineBeforeBraceSetting=true;
	private int mAdvancedNewlineBeforeBraceSettings;
	
	private boolean mUseLineCommentWrapping=false;
	private boolean mUseDocCommentWrapping=false;
	private boolean mUseMLCommentWrapping=false;
	private boolean mDocCommentCollapseLines=false;
	private boolean mDocCommentKeepBlankLines=true;
	private boolean mMLCommentKeepBlankLines=true;
	private boolean mMLCommentCollapseLines=false;
	private boolean mMLTextOnNewLines=false;
	private int mMLAsteriskMode=ASPrettyPrinter.MLAsteriskStyle_AsIs; //0=don't change (if I can tell), 1=add, 2=remove
	private int mDocCommentHangingIndentTabs=0;
	private boolean mKeepRelativeCommentIndent=false;
	
	private boolean mUseSpacesInsideParensEtc=true;
	private int									mAdvancedSpacesInsideParensInOtherPlaces				= 1;
	private int									mAdvancedSpacesInsideParensInParameterLists					= 1;
	private int									mAdvancedSpacesInsideParensInArgumentLists					= 1;
	private int mAdvancedSpacesInsideArrayDeclBrackets=1;
	private int mAdvancedSpacesInsideArrayReferenceBrackets=1;
	private int mAdvancedSpacesInsideObjectBraces=1;
//	private boolean mMultipleBindableItems;

	private boolean mUseGNUBraceIndent=false;
	private int mBlankLinesToKeep=0;
	private int mBlankLinesBeforeImports=0;
//	private boolean mLastBindableWasConditionalTag=false;
	private boolean mProcessingBindableTag=false;
	private boolean mCollapseSpaceForAdjacentParens=true;
	private int mTabSize=3;
	private boolean mUseTabs=false;
	private boolean mKeepBlankLines=true;
	private int mExpressionSpacesAroundSymbolicOperators=1;
	private boolean mKeepElseIfOnSameLine=true;
	private int mMaxLineLength=(-1);
	private int mBlankLinesBeforeFunction=1;
	private int mBlankLinesBeforeClass=1;
	private int mBlankLinesToStartFunctions=0;
	private int mBlankLinesToEndFunctions=0;
	private int mBlankLinesBeforeControlStatement=1;
	private int mBlankLinesBeforeProperties=0;
	private boolean mKeepSingleLineCommentsAtColumn1=true;
	private boolean mCRBeforeElse=false;
	private boolean mCRBeforeWhile=false;
	private boolean mCRBeforeCatch=false;
	private String mSourceData=null;
	private String mWorkingSource=null;
	private boolean mEmptyStatementsOnNewLine=true;
	private List<Integer> mAddedCRs;
	private CommonTokenStream mRawTokens;
//	private boolean mIsCompleteFile;
	private Token mLastToken=null;
//	private boolean mIsNewlineAfterBindable=true;
	private boolean mIsNewlineBeforeBindableFunction=true;
	private boolean mIsNewlineBeforeBindableProperty=true;
	private List<Exception> mParseErrors;
	private StringBuffer mOutputBuffer;
	private List<IndentType> mIndentStack;
	private List<Integer> mFormatTypeStack;
	private WrapOptions mArrayInitWrapOptions=null;
	private WrapOptions mMethodCallWrapOptions=null;
	private WrapOptions mMethodDeclWrapOptions=null;
	private WrapOptions mExpressionWrapOptions=null;
	private WrapOptions mXMLWrapOptions=null;
	private List<WrapInfo> mExpressionWrapData=null;
	private boolean mAllowMultiplePasses;
	
	private boolean mCanAddCRsAtBlockStart=false;
	private int mSpacesBetweenControlKeywordsAndParens=1;
	
	private boolean mNoIndentForTerminators=false;
	
	//variables for supporting lining up '=' in declarations/assignments
	private boolean mAlignDeclEquals=false;
	private boolean mAlignAssignmentEquals=false;
	public static final int Decl_Align_Consecutive=1; //align all consecutive variable declarations.  This is the default and only setting right now.
	public static final int Decl_Align_Scope=2; //align all variables in the same scope; maybe desirable, but probably not
	public static final int Decl_Align_Global=3; //align all variables in file; probably nonsense
	private int mAlignDeclMode=Decl_Align_Consecutive; //probably no need to allow other settings at this point
	private EqualContext mCurrentDeclEqualContext;
	private EqualContext mRootDeclEqualContext;
	private Map<String, Integer> mDeclEqualPosMap;
	private boolean mAlreadyHaveDeclPositions=false;
	
	
	private boolean mKeepSingleLineFunctions=false;
	
	//parser state variables
	private boolean mBindableMode;
	private int mBindablePos;
	private boolean mNextTokenInElseIfState;
	private boolean mElseIfNeedToLoseIndent;
	private int mExcludeStackCount;
	private int mLastCapturePosition;
	private Map<Integer, ReplacementRange> mReplaceMap;
	private String mAdditionalTextAdded;
	private String mAdditionalTextAddedAllPasses;
	private String mRemovedText;
	private String mRemovedTextAllPasses;
	private boolean mInSingleLineFunctionMode;
	
	
	private int mLazyIndentType;
	private int mLazyFormatType;
	
	private Point mSelectedRange=null; //x=start line, y=end line (1-based)(lines 5 and 6 would be 5,6)
	private Point mOutputRange=null; //x=start offset, y=end offset (0-based offsets into outputbuffer)
	private Point mReplaceRange=null; //same semantics as mOutputRange
	private String mLastBindableTagName;

	static {
		Break_Table_Items=new HashMap<String, Integer>();
		Break_Table_Items.put(Break_Commas, Break_Commas_code);
		Break_Table_Items.put(Break_Arithmetic_Ops, Break_Arithmetic_Ops_code); 
		Break_Table_Items.put(Break_Assignment, Break_Assignment_code); 
		Break_Table_Items.put(Break_Parameter_Assignment, Break_Parameter_Assignment_code); 
		Break_Table_Items.put(Break_Function_Calls, Break_Function_Calls_code); 
		Break_Table_Items.put(Break_Logical_Ops, Break_Logical_Ops_code);
		Break_Table_Items.put(Break_Ternary, Break_Ternary_code);
		Break_Table_Items.put(Break_Other_Connectors, Break_Other_Connectors_code);
		Break_Table_Items.put(Break_Keyword, Break_Keyword_code);
		
		Brace_Context_Items=new HashMap<String, Integer>();
		Brace_Context_Items.put(BraceContext_Conditional, BraceContext_Conditional_code);
		Brace_Context_Items.put(BraceContext_block, BraceContext_block_code);
		Brace_Context_Items.put(BraceContext_catch, BraceContext_catch_code);
		Brace_Context_Items.put(BraceContext_class, BraceContext_class_code);
		Brace_Context_Items.put(BraceContext_controlStatement, BraceContext_controlStatement_code);
		Brace_Context_Items.put(BraceContext_finally, BraceContext_finally_code);
		Brace_Context_Items.put(BraceContext_functionDecl, BraceContext_functionDecl_code);
		Brace_Context_Items.put(BraceContext_functionExpression, BraceContext_functionExpression_code);
		Brace_Context_Items.put(BraceContext_interface, BraceContext_interface_code);
		Brace_Context_Items.put(BraceContext_package, BraceContext_package_code);
		Brace_Context_Items.put(BraceContext_try, BraceContext_try_code);
	}
	
	private void initSettings()
	{
		mBlockIndent=3;
		mCRBeforeOpenBrace=true;
		mDoFormat=true;
		mArrayInitWrapOptions=new WrapOptions(WrapOptions.WRAP_NONE);
		mMethodCallWrapOptions=new WrapOptions(WrapOptions.WRAP_NONE);
		mMethodDeclWrapOptions=new WrapOptions(WrapOptions.WRAP_NONE);
		mExpressionWrapOptions=new WrapOptions(WrapOptions.WRAP_NONE);
		mXMLWrapOptions=new WrapOptions(WrapOptions.WRAP_DONT_PROCESS);
		mAdvancedNewlineBeforeBraceSettings=0;
		mMetaTagsToKeepOnSameLineAsProperty=new HashSet<String>();
		mMetaTagsToKeepOnSameLineAsFunction=new HashSet<String>();
		mAllowMultiplePasses=true;
	}
	
	public ASPrettyPrinter(File f, String charSetName) throws IOException
	{
		initSettings();
//		mIsCompleteFile=true;
		StringBuffer buffer=new StringBuffer();
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f), charSetName));
		try
		{
			while (true)
			{
				String line=br.readLine();
				if (line==null)
					break;
				buffer.append(line);
				buffer.append('\n');
			}
		}
		finally
		{
			br.close();
		}
		
		mSourceData=buffer.toString();
	}
	
	public ASPrettyPrinter(boolean isCompleteFile, String sourceData)
	{
		initSettings();
		mSourceData=sourceData;
//		mIsCompleteFile=isCompleteFile;
	}
	
	public int getBlockIndent()
	{
		return mBlockIndent;
	}
	public String print(int startIndent) throws RecognitionException
	{
		if (mSourceData.indexOf(mIgnoreFileProcessing)>=0)
		{
			mParseErrors=new ArrayList<Exception>();
			mParseErrors.add(new Exception("File ignored: Ignore tag exists in file==> "+ASPrettyPrinter.mIgnoreFileProcessing));
			return null;
		}
		
		mOutputRange=null;
		mReplaceRange=null;
		mAdditionalTextAddedAllPasses="";
		mRemovedTextAllPasses="";
		int maxPasses=2; //right now, there's only two passes if we are aligning equals signs or adding braces 
		int currentPasses=0;
		while (currentPasses<maxPasses)
		{
			currentPasses++;
			
			mAlreadyHaveDeclPositions=(mDeclEqualPosMap!=null);
			
			//initialize the parser state variables
			mBindablePos=(-1);
			mBindableMode=false;
			mInSingleLineFunctionMode=false;
			mAdditionalTextAdded="";
			mRemovedText="";
			mReplaceMap=null;
			mNextTokenInElseIfState=false;
			mElseIfNeedToLoseIndent=false;
			mLazyIndentType=(-1);
			mLazyFormatType=(-1);
			mFormatTypeStack=new ArrayList<Integer>();
			if (mDoFormat)
				pushFormatMode(FORMAT_ALL);
			else
				pushFormatMode(FORMAT_INDENT);
			mLastBindableTagName="";
			
			mExpressionWrapData=new ArrayList<WrapInfo>();
			mSourceData=Utilities.convertCarriageReturnsToLineFeeds(mSourceData);
			boolean addedEndCRs=false;
			if (getEndEOLs(mSourceData)<2)
			{
				mSourceData+="\n\n";
				addedEndCRs=true;
			}
			
			mAddBraceStack=new ArrayList<StatementBraceInfo>();
			mCompletedBraceInfos=new ArrayList<StatementBraceInfo>();
			
			mAddedCRs=new ArrayList<Integer>();
			mParseErrors=null;
			mIndentStack=new ArrayList<IndentType>();
			mIndentStack.add(new IndentType(INITIAL_INDENT, startIndent));
			mExcludeStackCount=0;
			mLastCapturePosition=0;
			mOutputBuffer=new StringBuffer();
//			mAlignDeclEquals=true;
			if (mAlignDeclEquals && !mIsKeepingExcessDeclWhitespace)// && currentPasses==0)
			{
				mCurrentDeclEqualContext=new EqualContext(null, 0);
				mRootDeclEqualContext=mCurrentDeclEqualContext;
			}
//			else
//				mCurrentDeclEqualContext=null;
			try
			{
	//			if (mIsCompleteFile)
					mWorkingSource=mSourceData;
	//			else
	//				mWorkingSource="class DummyClassWrapper {"+mSourceData+ "}";
					
				ANTLRStringStream stream=new ANTLRStringStream(mWorkingSource);
				AS3_exLexer l2=new AS3_exLexer(stream);
				mRawTokens=new CommonTokenStream(l2);
				AS3_exParser p2=new AS3_exParser(this, mRawTokens);
				
				fileContents_return retVal=p2.fileContents();
				
				//handle any remaining hidden tokens
				Token t=new CommonToken(AS3_exParser.EOF, "");
				t.setTokenIndex(mRawTokens.size());
				emit(t);
				
				//process braces that may need to be added or deleted
				{
					List<EditItem> editItems=new ArrayList<EditItem>();
					for (StatementBraceInfo info : mCompletedBraceInfos) 
					{
						if (info.isBracesCurrentlyExist())
						{
							if (isRemoveBraces(info))
							{
								int startBracePos=mOutputBuffer.indexOf("{", info.getStartBracePos());
								int endBracePos=mOutputBuffer.lastIndexOf("}", info.getEndBracePos());
								int endBracePosInSource=mSourceData.lastIndexOf('}', info.getOriginalDocEndPosition());
								editItems.add(new DeleteItem(startBracePos, 1, info.getOriginalDocStartPosition()));
								editItems.add(new DeleteItem(endBracePos, 1, endBracePosInSource));
								
								//if brace is followed by nothing but a carriage return, return the next
								//cr as well.
								int nextCR=mOutputBuffer.indexOf("\n", startBracePos+1);
								if (nextCR>=0)
								{
									String nextChars=mOutputBuffer.substring(startBracePos+1, nextCR).trim();
									if (nextChars.length()==0)
									{
										editItems.add(new DeleteItem(nextCR, 1, -1));
									}
								}
								nextCR=mOutputBuffer.indexOf("\n", endBracePos+1);
								if (nextCR>=0)
								{
									String nextChars=mOutputBuffer.substring(endBracePos+1, nextCR).trim();
									if (nextChars.length()==0)
									{
										editItems.add(new DeleteItem(nextCR, 1, -1));
									}
								}								
							}
						}
						else
						{
							if (isAddBraces(info))
							{
								editItems.add(new InsertItem(info.getStartBracePos(), "{", info.getOriginalDocStartPosition()));
								editItems.add(new InsertItem(info.getEndBracePos(), "}", info.getOriginalDocEndPosition()));
							}
						}
					}

					Collections.sort(editItems, new Comparator<EditItem>() {
						public int compare(EditItem o1, EditItem o2) {
							return o1.mLocation-o2.mLocation;
						}
					});

					if (mReplaceMap==null)
						mReplaceMap=new HashMap<Integer, ReplacementRange>();

					int addedChars=0;
					for (int j=0;j<editItems.size();j++)
					{
						EditItem item=editItems.get(j);
						if (item instanceof InsertItem)
						{
							InsertItem iItem=(InsertItem)item;
							mOutputBuffer.insert(item.getLocation()+addedChars, iItem.getData());

							ReplacementRange existingRange=mReplaceMap.get(iItem.getOriginalInsertLocation());
							if (existingRange!=null)
							{
								//this can happen if two close braces are added at the same point in the file.  As such,
								//I'm handling this special case.
								existingRange.setChangedText(existingRange.getAddedText()+iItem.getData(), existingRange.getDeletedText());
								existingRange.getRangeInFormattedDoc().y+=iItem.getData().length();
							}
							else
							{
								ReplacementRange range=new ReplacementRange(new Point(item.getLocation()+addedChars, item.getLocation()+addedChars+iItem.getData().length()), new Point(iItem.getOriginalInsertLocation(),iItem.getOriginalInsertLocation()));
								mReplaceMap.put(iItem.getOriginalInsertLocation(), range);
								range.setChangedText(iItem.getData(), "");
							}
							addedChars+=iItem.getData().length();
							mAdditionalTextAdded+=iItem.getData();
							mAdditionalTextAddedAllPasses+=iItem.getData();

						}
						else
						{
							//add replacemap item, but only if non-whitespace is being deleted
							//TODO: what if a brace is added and deleted at same location? Will that break the merging code?
							DeleteItem dItem=(DeleteItem)item;
							String removedData=mOutputBuffer.substring(dItem.getLocation()+addedChars, dItem.getLocation()+dItem.getLength()+addedChars);
							if (dItem.getOriginalDeleteLocation()>=0)
							{
								ReplacementRange range=new ReplacementRange(new Point(item.getLocation()+addedChars, item.getLocation()+addedChars), new Point(dItem.getOriginalDeleteLocation(),dItem.getOriginalDeleteLocation()+dItem.getLength()));
								mReplaceMap.put(dItem.getOriginalDeleteLocation(), range);
								range.setChangedText("", removedData);
							}
							mOutputBuffer.delete(dItem.getLocation()+addedChars, dItem.getLocation()+dItem.getLength()+addedChars);
							addedChars-=removedData.length();
							mRemovedText+=removedData;
							mRemovedTextAllPasses+=removedData;
							
						}
					}
				}
				
				String resultText=mOutputBuffer.toString();
					
				mParseErrors=p2.getParseErrors();
				if (mParseErrors!=null || resultText==null)
					return null;
	
				String oldString=mWorkingSource+mAdditionalTextAdded;
				if (mAdditionalTextAdded.length()==0 && mRemovedText.length()==0 && ActionScriptFormatter.validateNonWhitespaceIdentical(resultText, mWorkingSource) || ((mAdditionalTextAdded.length()>0 || (mRemovedText.length()>0)) && ActionScriptFormatter.validateNonWhitespaceCharCounts(resultText+mRemovedText, oldString)))
				{
					//trim extra newlines at the end of result text
					if (mDoFormat)
					{
						int oldEndLines=getEndEOLs(mWorkingSource)-(addedEndCRs ? 2 : 0); //I may have added two above
						int newEndLines=getEndEOLs(resultText);
						if (newEndLines>oldEndLines)
						{
							resultText=resultText.substring(0, resultText.length()-(newEndLines-oldEndLines));
							if (mOutputRange!=null && mOutputRange.y>resultText.length())
							{
								mOutputRange.y=resultText.length();
							}
						}
					}
					
					//if we are trying to capture the output range but haven't captured it yet
					if (mOutputRange!=null && mOutputRange.y<0)
					{
						mOutputRange.y=resultText.length();
						mReplaceRange.y=mWorkingSource.length();
						if (mDoFormat && addedEndCRs)
							mReplaceRange.y-="\n\n".length();
					}
					
					if (mRootDeclEqualContext!=null)
					{
						mDeclEqualPosMap=new HashMap<String, Integer>();
						mRootDeclEqualContext.fillMap(mDeclEqualPosMap);
					}
					
					//if multiple passes are allowed and we would benefit from one and this is not a partial format
					if (mAllowMultiplePasses && needAnotherPass() && mOutputRange==null)
					{
						mSourceData=resultText;
						continue;
					}
					return resultText;
				}
				else
				{
					mParseErrors=new ArrayList<Exception>();
					mParseErrors.add(new Exception("Internal error: Formatted text doesn't match source. "+resultText+"!="+mWorkingSource));
				}
			}
			finally
			{
			}
			return null;
		}
		
		//return what I have in case the multiple
		//passes algorithm gets confused
		return mSourceData;
	}
	
	private static int getEndEOLs(String source)
	{
		int count=0;
		for (int i=source.length()-1;i>=0;i--)
		{
			if (source.charAt(i)=='\n')
				count++;
			else if (AntlrUtilities.isASWhitespace(source.charAt(i)))
				continue;
			
			else
				break;
		}
		return count;
	}
	
	private void printNecessaryWS(StringBuffer buffer)
	{
//		if (getFormatMode()==FORMAT_INDENT)
//		{
//			mAddedCRs.clear();
//			return;
//		}
		if (buffer.length()>0) //don't add whitespace if we are at the start of the buffer
		{
			int currentBlankLines=ActionScriptFormatter.getNumberOfEmptyLinesAtEnd(buffer);
			for (Integer crCode : mAddedCRs)
			{
				if (crCode==AddCR)
				{
					if (!ActionScriptFormatter.isOnlyWhitespaceOnLastLine(buffer))
					{
						insertCRIntoBuffer(buffer);
					}
				}
				else if (crCode==AddCR_BlankLine)
				{
					if (currentBlankLines>0)
					{
						currentBlankLines--;
					}
					else
					{
						if (!ActionScriptFormatter.isOnlyWhitespaceOnLastLine(buffer))
						{
							insertCRIntoBuffer(buffer);
						}
						insertCRIntoBuffer(buffer);
					}
				}
				else if (crCode==ADD_WS)
				{
					if (!ActionScriptFormatter.isOnlyWhitespaceOnLastLine(buffer))
					{
						buffer.append(' ');
					}
				}
				else if (crCode==AddCRIfBeyondMaxCol)
				{
					if (getMaxLineLength()>0 && determineLastLineLength(buffer, getTabSize())>=getMaxLineLength())
					{
						insertCRIntoBuffer(buffer);
					}
				}
			}
		}
		
		mAddedCRs.clear();
	}
	
	private void insertCRIntoBuffer(StringBuffer buffer)
	{
		if (mTrimTrailingWS && getFormatMode()!=FORMAT_INDENT)
		{
			//This is code to trim trailing whitespace on lines.
			for (int i=buffer.length()-1;i>=0;i--)
			{
				char c=buffer.charAt(i);
				if (c==' ' || c=='\t')
					buffer.deleteCharAt(i);
				else
					break;
			}
		}
		
		if (isAlwaysGenerateIndent())
		{
			//if current line is blank, then try to generate what would be the next indent
			addIndentIfAtStartOfLine(buffer);
		}
		
		buffer.append("\n");
	}

//	private int getTokenStartOffset(Tree tree) {
//		if (!(tree instanceof CommonTree))
//			throw new IllegalStateException("Bad tree type");
//		
//		Token t=((CommonTree)tree).getToken();
//		if (t==null)
//			throw new IllegalStateException("null token");
//		
//		if ((t instanceof CommonToken))
//		{
//			return ((CommonToken)t).getStartIndex();
//		}
//		throw new IllegalStateException("Bad token type");
//	}
	
	
	static int determineLastLineLength(StringBuffer buffer, int tabSize)
	{
		int i=buffer.length()-1;
		while (i>=0)
		{
			char c=buffer.charAt(i);
			if (c=='\n' || c=='\r')
			{
				i++;
				break;
			}
			i--;
		}
		
		if (i<0 || i>=buffer.length())
			return 0;
		String lastLine=buffer.substring(i);
		int lastLineLength=0;
		for (i=0;i<lastLine.length();i++)
		{
			char c=lastLine.charAt(i);
			if (c=='\t')
			{
				int leftOver=lastLineLength%tabSize;
				if (leftOver==0)
					lastLineLength+=tabSize;
				else
					lastLineLength+=leftOver;
			}
			else
			{
				lastLineLength++;
			}
		}
		
		return lastLineLength;
	}

	void insertWS(int amt)
	{
		if (getFormatMode()==FORMAT_INDENT)
			return;
		
		for (int i=0;i<amt;i++)
		{
			mAddedCRs.add(new Integer(ADD_WS));
		}
	}
	
	void insertCRBeyondMaxCol()
	{
		if (getFormatMode()!=FORMAT_ALL && getFormatMode()!=FORMAT_OnlyAddCRs)
			return;

		if (isUseAdvancedWrapping())
			return;
		
		if (mInSingleLineFunctionMode)
			return;
		
		mAddedCRs.add(Integer.valueOf(AddCRIfBeyondMaxCol));
	}
	
	void insertCR(boolean override)
	{
		if (getFormatMode()!=FORMAT_ALL && getFormatMode()!=FORMAT_OnlyAddCRs)
			return;
		
		if (mInSingleLineFunctionMode)
		{
			insertWS(1);
			return;
		}
		
		int i=AddCR;
		if (override)
			i=AddCR_BlankLine;
		mAddedCRs.add(Integer.valueOf(i));
	}
	
	public void clearAddedWhitespace()
	{
		mAddedCRs.clear();
	}
	
	String generateIndent(int spaces)
	{
		return ActionScriptFormatter.generateIndent(spaces, mUseTabs, mTabSize);
	}
	
	public static int findIndent(String line, int tabSize)
	{
		int spaceCount=0;
		for (int k=0;k<line.length();k++)
		{
			char c=line.charAt(k);
			if (!AntlrUtilities.isASWhitespace(c))
			{
				return spaceCount;
			}
			else
			{
				if (c==' ')
					spaceCount++;
				else if (c=='\t')
				{
					int remainder=spaceCount%tabSize;
					if (remainder==0)
						spaceCount+=tabSize;
					else
					{
						spaceCount+=remainder;
					}
				}
			}
		}
		return -1;
	}
	
	public int determineLastIndent(StringBuffer buffer)
	{
		//TODO: it's pretty inefficient to split the buffer when I should only grab lines as I need them
		String[] lines=buffer.toString().split("\n");
		if (lines!=null && lines.length>0)
		{
			for (int i=lines.length-1;i>=0;i--)
			{
				String line=lines[i];
				
				//skip to next (i.e. previous) line if this line starts with a line comment
				if (AntlrUtilities.asTrim(line).startsWith("//"))
					continue;
				int spaceCount=findIndent(line, getTabSize());
				if (spaceCount>=0)
					return spaceCount;
			}
		}
		
		return getCurrentIndent();
	}

	public int getIndentForNextLine(StringBuffer buffer)
	{
		int lastIndent=determineLastIndent(buffer);
		int currentIndent=getCurrentIndent();
		int newIndent=currentIndent;
		String text=AntlrUtilities.asTrim(buffer.toString()); //TODO: something more efficient
		if (text.length()>0)
		{
			newIndent=Math.min(currentIndent, lastIndent);
			if (lastIndent<currentIndent)
			{
				newIndent=lastIndent+getBlockIndent(); //(mUnindent ? 0 : mPrinter.getBlockIndent());
			}
		}
		
		return newIndent;
	}
	
	
	public int addIndentIfAtStartOfLine(StringBuffer buffer)
	{
		if (ActionScriptFormatter.isLineEmpty(buffer))
		{
			int newIndent=getIndentForNextLine(buffer);
			buffer.append(generateIndent(newIndent));
			return newIndent;
		}
		return 0;
	}
	
	public void setDoFormat(boolean doFormat)
	{
		mDoFormat=doFormat;
	}
	
	public List<Exception> getParseErrors() {
		return mParseErrors;
	}
	public void setData(String text) {
		mSourceData=text;
	}
	public boolean isCRBeforeOpenBrace(List<Integer> contextStack)
	{
		//if we are using the simple settings for adobe/sun
		if (isUseBraceStyleSetting())
		{
			if (getBraceStyleSetting()==BraceStyle_Sun)
				return false;
			else
				return true;
		}
		
		//otherwise, if we are using the context stack to determine the proper brace location
		if (!isUseGlobalNewlineBeforeBraceSetting())
		{
			if (contextStack.size()>0)
			{
				Integer topItem=contextStack.get(contextStack.size()-1);
				if ((topItem.intValue() & mAdvancedNewlineBeforeBraceSettings)!=0)
					return true;
				else
					return false;
//				Boolean insertCR=mAdvancedNewlineBeforeBraceSettings.get(topItem);
//				if (insertCR!=null)
//					return insertCR.booleanValue();
			}
		}
		
		//otherwise we default to the explicit setting
		return mCRBeforeOpenBrace;
	}
	public void setCRBeforeOpenBrace(boolean braceOnNewLine) {
		mCRBeforeOpenBrace = braceOnNewLine;
	}
	
	public static final String mStartExcludeProcessing="{FlexFormatter:Off}";
	public static final String mStopExcludeProcessing="{FlexFormatter:On}";
	public static final String mIgnoreFileProcessing="{FlexFormatter:IgnoreFile}";
	
	void emit(Token tok)
	{
		//This (the token having no text) might occur with an empty semicolon token (semic).  I think nothing needs to be done here and
		//no state needs to be saved.
		if (tok.getText()==null)
			return;
		
		//look at hidden tokens to see if the tag to turn off formatting is seen.  If so, then just emit all the hidden tokens and
		//the main token.  Want to let operations happen that don't insert text and maintain state that we need to keep.  
		
		int currentBufferOffset=mOutputBuffer.length();
		mLastToken=tok;
	    //handle whitespace
		CommonTokenStream tokens=mRawTokens;//(CommonTokenStream)getTokenStream();
		int currentTokenIndex=((CommonToken)tok).getTokenIndex()-1;
		List<Token> hiddenTokens=new ArrayList<Token>();
		
		//collect all of the hidden tokens since the last non-whitespace token
		while (currentTokenIndex>=0)
		{
			Token t=tokens.get(currentTokenIndex);
			if (t.getChannel()==Token.DEFAULT_CHANNEL)
				break;
			hiddenTokens.add(t);
			currentTokenIndex--;
		}
		Collections.reverse(hiddenTokens);
		
		if (!mProcessingBindableTag && mBindableMode && mBindablePos>=0 && getFormatMode()==FORMAT_ALL)
		{
			//TODO: figure out how a partial range will figure into this.  I may have to shift the position if it's already
			//been set
			while (mBindablePos<mOutputBuffer.length())
			{
				char c=mOutputBuffer.charAt(mBindablePos);
				if (!AntlrUtilities.isASWhitespace(c))
					break;
				mBindablePos++;
			}
			while (mBindablePos>0)
			{
				char c=mOutputBuffer.charAt(mBindablePos-1);
				if (c=='\n')
					break;
				mBindablePos--;
			}
			String bindableText=mOutputBuffer.substring(mBindablePos);
			mOutputBuffer.delete(mBindablePos, mOutputBuffer.length());
			
			//this should spit out the blank lines before function and similar whitespace
			printNecessaryWS(mOutputBuffer);
			
			mOutputBuffer.append(bindableText);
			mBindableMode=false;
			mBindablePos=(-1);
			
			//check for one of the next hidden tokens being a comment.  If so, we
			//have to add a newline.
			boolean commentFollows=false;
			for (Token token : hiddenTokens) {
				if (AntlrUtilities.asTrim(token.getText()).length()>0)
				{
					commentFollows=true;
					break;
				}
			}

			//now, spit out either newline or space to separate bindable tag from bound item
			if (!commentFollows && ((!isNewlineBeforeBindableFunction() && mBindingContext==BindingContext_Function && getMetaTagsToKeepOnSameLineAsFunction().contains(mLastBindableTagName)) || (!isNewlineBeforeBindableProperty() && mBindingContext==BindingContext_Property && getMetaTagsToKeepOnSameLineAsProperty().contains(mLastBindableTagName))))
			{
				insertWS(1);
			}
			else
			{
				insertCR(false);
			}
//	   		if (/*isNewlineAfterBindable() || /*mMultipleBindableItems ||*/ commentFollows /*|| mLastBindableWasConditionalTag */ || (mBindingContext==BindingContext_Function && !getMetaTagsToKeepOnSameLineAsFunction().contains(mLastBindableTagName)) || (mBindingContext==BindingContext_Property && !getMetaTagsToKeepOnSameLineAsProperty().contains(mLastBindableTagName)) || mBindingContext==BindingContext_Class)
//	   		{
//	   			insertCR(false);
//	   		}
//	   		else
//	   		{
//	   			insertWS(1);
//	   		}
		}
		
		//handle user selection range
		if (mSelectedRange!=null && !mBindableMode)
		{
			if (mOutputRange==null)
			{
				if (tok.getLine()>=mSelectedRange.x)
				{
					mOutputRange=new Point(mOutputBuffer.length(), -1);
					mReplaceRange=new Point(0, -1);
					Token firstToken=tok;
					if (hiddenTokens.size()>0)
					{
						firstToken=hiddenTokens.get(0);
					}
					
					mReplaceRange.x=((CommonToken)firstToken).getStartIndex();
				}
			}
			else
			{
				if (mOutputRange.y<0 && tok.getLine()>mSelectedRange.y)
				{
					mOutputRange.y=mOutputBuffer.length();
					
					Token firstToken=tok;
					if (hiddenTokens.size()>0)
					{
						firstToken=hiddenTokens.get(0);
					}
					mReplaceRange.y=((CommonToken)firstToken).getStartIndex();
				}
			}
		}
		
		boolean onBlankLine=ActionScriptFormatter.isOnlyWhitespaceOnLastLine(mOutputBuffer); //I think I can only be on a blank line at the start of the document
		int blankLines=0;
		boolean needToEnsureNewLine=false;
		for (Integer ws : mAddedCRs) {
			if (ws.intValue()==AddCR || ws.intValue()==AddCR_BlankLine)
			{
				needToEnsureNewLine=true;
				break;
			}
		}
		
		//process hidden tokens (whitespace and comments) before this token
		boolean seenCR=(mOutputBuffer.length()==0); //if 0, then we're on a new line.  Otherwise, we're at the end of the previous code token. 
		boolean seenCommentText=false;
		for (int ti=0;ti<hiddenTokens.size();ti++)
		{
			if (seenCR)
				captureBraceStartPos();
			
			Token t=hiddenTokens.get(ti);
			switch (t.getChannel())
			{
			case AS3_exParser.CHANNEL_EOL:
				
				//if we're not formatting at all, then just add the cr.
				if (isFormatterOff())
				{
					insertCRIntoBuffer(mOutputBuffer);
					break;
				}
				
				seenCR=true;
				//we only want to preserve blank lines (or possibly not preserve blank lines) if we're in format mode where
				//we're taking control of the whitespace.   
				if (getFormatMode()==FORMAT_ALL)
				{
					if (isKeepBlankLines() || getBlankLinesToKeep()>blankLines)
					{
						if (onBlankLine)
						{
							//we got a carriage return but we were already on a new line.  Don't add the initial carriage
							//return.  This happens at the start of the file or after a line comment
							if (blankLines==0 && !ActionScriptFormatter.isOnlyWhitespaceOnLastLine(mOutputBuffer))
							{
								insertCRIntoBuffer(mOutputBuffer);
							}
							blankLines++;
							insertCRIntoBuffer(mOutputBuffer);
						}
						onBlankLine=true;
					}
					//if seen comment and !current line empty?
					else if (seenCommentText && !ActionScriptFormatter.isOnlyWhitespaceOnLastLine(getOutputBuffer()))
					{
						insertCRIntoBuffer(mOutputBuffer);
						onBlankLine=true;
						blankLines=0;
					}
				}
				else
				{
					//we're indenting (or formatting but without removing newlines), so just don't lose the CR
					insertCRIntoBuffer(mOutputBuffer);
					onBlankLine=true;
				}
				break;
			case AS3_exParser.CHANNEL_SLCOMMENT:
				//NOTE: the single line comment contains a carriage return, so we are guaranteed to
				//be on a new line afterward
				
				/////////////////////// Handling of "exclude from formatting" tags //////////////////////
				if (t.getText().indexOf(mStartExcludeProcessing)>=0)
				{
					pushFormatterOff();
				}
				else if (t.getText().indexOf(mStopExcludeProcessing)>=0)
				{
					popFormatterOff();
				}
				
				//if not in format mode at all, then just append the comment text, clear any accumulated whitespace and exit case
				if (isFormatterOff())
				{
					if (isDoFormat() && !ActionScriptFormatter.isLineEmpty(mOutputBuffer))
						insertCRIntoBuffer(mOutputBuffer);
					mOutputBuffer.append(t.getText().replace("\r\n", "\n"));
					mAddedCRs.clear();
					break;
				}
				///////////////////////////////////////////////////////////////////////////////////////
				
				//if we've seen a carriage return in the whitespace, but we haven't added one yet (because the SLComment was the next line),
				//then we need to add a CR.
				if (onBlankLine)
					insertCR(false);

				//here, we check the mDoFormat flag, NOT the format stack, because we want to know if it's legal to add spaces, not
				//whether we are currently in a "don't format" block.  We might care if we were in code, but since this
				//is for comments I think it makes sense.
				if (isDoFormat() && !seenCR) //if no carriage return before this comment, then it's on the same line with other text
				{
					addLineCommentPreSpacing(hiddenTokens, ti);
					mOutputBuffer.append(t.getText().replace("\r\n", "\n"));
				}
				else
				{
					//add a lazy space so that there will be one space before the '//' if there are preceding
					//chars on the line.  I don't know if this can ever have an effect, but it seems safest to leave it.
					insertWS(1);
					
					
					//otherwise, we add the accumulated whitespace, which may include multiple blank lines, as we push
					//the comment down to be with the following statement
					printNecessaryWS(mOutputBuffer);
					int commentIndent=0;
					if (isKeepSingleLineCommentsAtColumn1() && t.getCharPositionInLine()==0)
					{
						//do nothing; hopefully we've not added any whitespace to a blank line
					}
					else
					{
						boolean addSpecialIndent=false;
						if (tok.getType()==AS3_exParser.RCURLY)
							addSpecialIndent=true;
						if (addSpecialIndent)
							pushIndent(STATEMENT_INDENT);
						commentIndent=addIndentIfAtStartOfLine(mOutputBuffer);
						if (addSpecialIndent)
							popIndent();
					}
					
					if (isDoFormat() && isUseLineCommentWrapping())
					{
						//wrap comment based on max line length and current indent spaces
						List<CommentLineWrapData> commentLines=wrapLineComment(t.getText(), commentIndent, mMaxLineLength);
						boolean firstLine=true;
						for (CommentLineWrapData line : commentLines) 
						{
							if (!firstLine)
							{
								mOutputBuffer.append(generateIndent(commentIndent));
								int replaceOffset=((CommonToken)t).getStartIndex()+line.mOriginalStartOffset;
								ReplacementRange range=new ReplacementRange(new Point(mOutputBuffer.length(), mOutputBuffer.length()+2), new Point(replaceOffset, replaceOffset));
								if (mReplaceMap==null)
									mReplaceMap=new HashMap<Integer, ReplacementRange>();
								range.setChangedText("//", "");
								mReplaceMap.put(mOutputBuffer.length(), range);
								mAdditionalTextAdded+="//";
								mAdditionalTextAddedAllPasses+="//";
							}
							
							mOutputBuffer.append(line.mText);
							firstLine=false;
						}
					}
					else
					{
						//add the actual text
						mOutputBuffer.append(t.getText().replace("\r\n", "\n"));
					}
				}

				onBlankLine=true;
				blankLines=0; //make sure we note that we have just seen a carriage return
				seenCR=true;
				break;
			case AS3_exParser.CHANNEL_MLCOMMENT:
				
				//if not in format mode at all, then just append the comment text, clear any accumulated whitespace and exit switch
				if (isFormatterOff())
				{
					mOutputBuffer.append(t.getText().replace("\r\n", "\n"));
					mAddedCRs.clear();
					break;
				}
				
				//if we've seen a carriage return in the whitespace, but we haven't added one yet (because the MLComment was the next line),
				//then we need to add a CR.
				if (onBlankLine)
					insertCR(false);

				//here, we check the mDoFormat flag, NOT the format stack, because we want to know if it's legal to add spaces, not
				//whether we are currently in a "don't format" block.  We might care if we were in code, but since this
				//is for comments I think it makes sense.
				//if the comment is at the end of a line of text, and the comment doesn't span multiple lines (and we're in a mode that allows space insertion)
				if (isDoFormat() && !seenCR && t.getText().indexOf('\n')<0)
				{
					addLineCommentPreSpacing(hiddenTokens, ti);
					mOutputBuffer.append(t.getText());
				}
				else
				{
					seenCR=true;
					
					//add a lazy space so that there will be one space before the '/*' if there are preceding
					//chars on the line.  I don't even know if this can ever have an effect, but it seems safest to leave it. 
					insertWS(1);
					
					printNecessaryWS(mOutputBuffer);
					//TODO: something else completely, which might involve adding newlines, wrapping lines, etc.
					//    -when I wrote this for Java, I did some fancy code to determine the relative offset of
					//	  the original lines to maintain the indenting within the comment. 
					String[] commentLines=t.getText().split("\n");
					int indentAmount=getIndentForNextLine(mOutputBuffer);
					int originalIndent=0;
					boolean useReplaceRange=false;
					if (t.getText().startsWith("/**"))
					{
						if (isUseDocCommentWrapping() && !isKeepRelativeCommentIndent())
						{
							commentLines=wrapMultilineComment(indentAmount, commentLines, mDocCommentCollapseLines, isDocCommentKeepBlankLines(), true, ASPrettyPrinter.MLAsteriskStyle_All, "/**", getDocCommentHangingIndentTabs());
							useReplaceRange=true;
						}
					}
					else if (t.getText().startsWith("/*"))
					{
						if (isUseMLCommentWrapping() && !isKeepRelativeCommentIndent())
						{
							commentLines=wrapMultilineComment(indentAmount, commentLines, mMLCommentCollapseLines, isMLCommentKeepBlankLines(), mMLTextOnNewLines, mMLAsteriskMode, "/*", 0);
							useReplaceRange=true;
						}
					}
	
					if (isKeepRelativeCommentIndent())
					{
						//find original indent
						StringBuffer lineData=new StringBuffer();
						lineData.append(t.getText());
						int prevLineEnd=mSourceData.lastIndexOf('\n', ((CommonToken)t).getStartIndex());
						if (prevLineEnd<0)
							prevLineEnd=0;
						else
							prevLineEnd++; //move to start of next line
						lineData.insert(0, mSourceData.substring(prevLineEnd, ((CommonToken)t).getStartIndex()));
						originalIndent=ASPrettyPrinter.findIndent(lineData.toString(), getTabSize());
					}
					
					Point replaceArea=new Point(mOutputBuffer.length(), -1);
					for (int j=0;j<commentLines.length;j++)
					{
						boolean onLastLine=(j==commentLines.length-1);
						indentAmount=getIndentForNextLine(mOutputBuffer);
						String data=AntlrUtilities.asTrim(commentLines[j]); //this removes the \r, if it exists
						
						if (j>0)
						{
							if (isKeepRelativeCommentIndent() && originalIndent>=0)
							{
								int existingIndent=ASPrettyPrinter.findIndent(commentLines[j], getTabSize());
								indentAmount=Math.max(0, indentAmount+(existingIndent-originalIndent));
							}
							else //using rules assuming '*' lines
							{
								if (onLastLine && data.startsWith("*/")) //if we're on the last line and there's more than one line
								{
									indentAmount++;
								}
								else
								{
									//on a middle line, we have 2 cases.  If the line starts with an asterisk, then attempt to line the
									//asterisk up with the asterisk on the first line.  Otherwise, indent the text to the right of the open asterisk.
									String nextLine=data;
									if (nextLine.length()>0 && nextLine.charAt(0)=='*')
										indentAmount+=1;
									else
										indentAmount+=3;
								}
							}
						}
						
						//only add indent if not on an empty line
						if (data.length()>0)
						{
							if (ActionScriptFormatter.isLineEmpty(mOutputBuffer))
							{
								mOutputBuffer.append(generateIndent(indentAmount));
							}
							mOutputBuffer.append(data);
						}
						
						if (!onLastLine)
						{
							insertCRIntoBuffer(mOutputBuffer);
						}
					}
					
					if (useReplaceRange)
					{
						replaceArea.y=mOutputBuffer.length();
						CommonToken commonTok=(CommonToken)t;
						for (;replaceArea.x<mOutputBuffer.length();replaceArea.x++)
						{
							if (!Character.isWhitespace(mOutputBuffer.charAt(replaceArea.x)))
								break;
						}
						ReplacementRange replaceRange=new ReplacementRange(replaceArea, new Point(commonTok.getStartIndex(), commonTok.getStartIndex()+t.getText().length()));
						replaceRange.setChangedText(mOutputBuffer.substring(replaceArea.x), t.getText());
						if (!ActionScriptFormatter.validateNonWhitespaceIdentical(replaceRange.getAddedText(), replaceRange.getDeletedText()))
						{
							if (mReplaceMap==null)
								mReplaceMap=new HashMap<Integer, ReplacementRange>();
							mReplaceMap.put(replaceArea.x, replaceRange);
						}
					}
				}
				
				onBlankLine=false;
				blankLines=0;
				seenCommentText=true;
				break;
			case AS3_exParser.CHANNEL_WHITESPACE:
				if (isFormatterOff())
				{
					//just spit the text out
					mOutputBuffer.append(t.getText());
				}
				else if (getFormatMode()==FORMAT_INDENT) // || isFirstTokenBeforeChangeFromFormatToIndent() || isFirstTokenBeforeChangeFromIndentToFormat())
				{
					//we don't care about whitespace if we are on the first token in an format-indent element; we only
					//care about whitespace within the element
					if (!ActionScriptFormatter.isOnlyWhitespaceOnLastLine(mOutputBuffer))// && (getFormatMode()==FORMAT_INDENT || !AntlrUtilities.isASWhitespace(mOutputBuffer.charAt(mOutputBuffer.length()-1))))
						mOutputBuffer.append(t.getText());
				}
				else
				{
					//do nothing; we'll add our own whitespace
				}
				break;
			default:
			}
		}

//		mFirstTokenBeforeChangeFromFormatToIndent=false;
//		mFirstTokenBeforeChangeFromIndentToFormat=false;
//		mFirstTokenBeforeIndentBoundary=false;
		
		//if still in non-formatting mode, then clear any whitespace that has been added to our list
		if (isFormatterOff())
			mAddedCRs.clear();
		
		printNecessaryWS(mOutputBuffer);
		
		if (!isFormatterOff())
		{
			if ((getFormatMode()==FORMAT_ALL || getFormatMode()==FORMAT_OnlyAddCRs /*|| (getFormatMode()==FORMAT_INDENT && isFirstTokenBeforeChangeFromIndentToFormat())*/) && needToEnsureNewLine)
			{
				if (!ActionScriptFormatter.isOnlyWhitespaceOnLastLine(mOutputBuffer))
				{
					insertCRIntoBuffer(mOutputBuffer);
				}
			}

			{
				int indentAmount=getCurrentIndent();
				if (getIndentType(0)!=EXPRESSION_INDENT && (tok.getType()==AS3_exParser.LCURLY || tok.getType()==AS3_exParser.RCURLY))
				{
				    if (!isHardIndent())
				    {
				    	if (!mUseGNUBraceIndent)
				    		indentAmount-=getBlockIndent();
				    	else
				    	{
				    		IndentType indentInfo=mIndentStack.get(mIndentStack.size()-1);
				    		if (tok.getType()==AS3_exParser.LCURLY)
				    		{
					    		indentInfo.mOriginalIndent=indentInfo.mAmount;
					    		indentInfo.mAmount+=getTabSize();
				    		}
				    		else //rcurly
				    		{
				    			if (indentInfo.mOriginalIndent>=0)
				    				indentInfo.mAmount=indentInfo.mOriginalIndent;
				    			indentAmount=indentInfo.mAmount;
				    		}
				    	}
				    }
				}
				if (ActionScriptFormatter.isLineEmpty(mOutputBuffer))
				{
				    String indentString=generateIndent(indentAmount);
				    mOutputBuffer.append(indentString);
				}
			}
		
			if( getFormatMode() != FORMAT_INDENT )
			{
				if( mOutputBuffer.length() > 0 && Character.isJavaIdentifierPart( mOutputBuffer.charAt( mOutputBuffer.length() - 1 ) ) )
			{
				//if the start of the token is a java identifier char (any letter or number or '_')  
				//or if the token doesn't start with whitespace, but isn't an operator either ('+=' is an operator, so we wouldn't add space, but we would add space for a string literal) 
				if ((tok.getText().length()>0 && Character.isJavaIdentifierPart(tok.getText().charAt(0))) || isSymbolTokenThatShouldHaveSpaceBeforeIt(tok.getType()))
				{
					mOutputBuffer.append(' ');
				}
			}
		
			//remove extra spaces before token for paren/brace/bracket case (if we're in a format mode)
				if (tok.getText().length()==1)
				{
					char c=tok.getText().charAt(0);
					if (isGroupingChar(c))
					{
						int i=mOutputBuffer.length()-1;
						int spaceCount=0;
						while (i>=0)
						{
							if (mOutputBuffer.charAt(i)==' ')
							{
								spaceCount++;
								i--;
							}
							else
								break;
						}
						
						if (i>=0 && spaceCount>0) //only care if there are some spaces
						{
							char prevChar=mOutputBuffer.charAt(i);
							int nestingType=determineMatchingGroupingCharType(c, prevChar);
							if (nestingType!=Nesting_Unrelated)
							{
								int amtToKeep=0;
								//TODO: not sure how this will work with advanced spacing settings
								if (!isCollapseSpaceForAdjacentParens() && nestingType!=Nesting_Opposite)
								{
									//TODO: Determine if the current context is a function call or a general
									//expression so that the proper 'getAdvancedSpaces' call can be made
									if (prevChar=='(' || c==')')
										amtToKeep = getAdvancedSpacesInsideParensInOtherPlaces();
									else if (prevChar=='{' || c=='}')
										amtToKeep=getAdvancedSpacesInsideObjectBraces();
									else if (prevChar=='[' || c==']')
									{
										amtToKeep=getAdvancedSpacesInsideArrayDeclBrackets();
									}
								}
								if (amtToKeep<spaceCount)
								{
									mOutputBuffer.delete(mOutputBuffer.length()-(spaceCount-amtToKeep), mOutputBuffer.length());
								}
							}
						}
					}
				}
			}
		}		
		
		//if we have a lazy indent, push it after we've processed the token
		if (mLazyIndentType>0)
		{
			if (mLazyIndentType==EXPRESSION_INDENT_NEXTITEM)
			{
				pushExpressionIndent(determineLastLineLength(mOutputBuffer, getTabSize()));
			}
			else
			{
				//we know it's an expression indent because that's the other possibility.  We take the current indent and add
				//the number of tab stops based on the hanging indent setting.
				pushIndent(mLazyIndentType);
			}
			mLazyIndentType=0;
		}
		
		if (mLazyFormatType>0)
		{
			pushFormatMode(mLazyFormatType);
			mLazyFormatType=0;
		}
		
		//special case for handling the need to lose 1 indent to support 'else-if' on the same line.  The grammar has code to 
		//pop an indent and re-add later, but it can't know whether it needs to until we get to this point and determine whether
		//the 'if' is actually on the same line with the else or not (based on else-if setting, blank lines settings, indent vs. format, etc.)
		if (mNextTokenInElseIfState)
		{
			mNextTokenInElseIfState=false;
			String newText=mOutputBuffer.substring(currentBufferOffset);
			if (!newText.contains("\n"))
			{
				mElseIfNeedToLoseIndent=true;
			}
		}
		
		if (mLastCapturePosition<0)
			mLastCapturePosition=mOutputBuffer.length();
		
		captureBraceStartPos();
		
		mOutputBuffer.append(tok.getText());
	}
	
	private String[] wrapMultilineComment(int indentAmount, String[] commentLines, boolean reflowLines, boolean keepBlankLines, boolean headerTextOnNewLines, int asteriskMode, String commentStart, int hangingIndentTabs) 
	{
		//do I need an option for whether the begin/end are on separate lines from the text, and
		//maybe it doesn't matter if it all fits on one line?
		if (commentLines.length==1 && indentAmount+commentLines[0].length()<getMaxLineLength())
		{
			//nothing to do here; no wrapping required
			return commentLines;
		}
		else
		{
			//this will ignore relative comment indent; I don't think that setting is compatible with this
			List<String> lines=new ArrayList<String>();
			for (String line : commentLines) {
				lines.add(AntlrUtilities.asTrim(line));
			}
			String asteriskPrefix="* ";
			
			boolean hasAsterisks=false;
			for (int i=0;i<lines.size();i++)
			{
				String line=lines.get(i);
				if (line.startsWith("*") && !line.startsWith("*/"))
				{
					hasAsterisks=true;
					line=line.substring(1);
					line=AntlrUtilities.asTrim(line);
					lines.set(i, line);
					mRemovedText+="*";
					mRemovedTextAllPasses+="*";
				}
			}
			
			/////////////////////////////////////////////////////////////////////////////
			//add space after open tag
			String startLine=lines.get(0);
			startLine=AntlrUtilities.asTrim(startLine.substring(commentStart.length()));
			startLine=commentStart+(startLine.length()>0 ? " "+startLine : "");
			lines.set(0, startLine);
			
			//and before close tag
			String endLine=lines.get(lines.size()-1);
			endLine=AntlrUtilities.asTrim(endLine.substring(0, endLine.length()-2));
			endLine=(endLine.length()>0 ? endLine+" " : "")+"*/";
			lines.set(lines.size()-1, endLine);
			/////////////////////////////////////////////////////////////////////////////
			
			if (reflowLines)
			{
				StringBuffer buffer=new StringBuffer();
				List<String> newLines=new ArrayList<String>();
				for (String line : lines) {
					if (line.length()==0 && keepBlankLines)
					{
						if (buffer.length()>0)
							newLines.add(buffer.toString());
						buffer=new StringBuffer();
						newLines.add(""); //for the additional blank line
						continue;
					}
					else if (line.startsWith("@")) //don't join lines that start with '@', because those are asdoc attributes
					{
						if (buffer.length()>0)
							newLines.add(buffer.toString());
						buffer=new StringBuffer(line);
						continue;
					}
					if (buffer.length()>0 && line.length()>0)
						buffer.append(' ');
					buffer.append(line);
				}
				if (buffer.length()>0)
					newLines.add(buffer.toString());
				lines=newLines;
			}
			
			//if header text on new line option set and it's a doc comment OR we have multiple lines OR the one line needs to be broken up
			if (headerTextOnNewLines && (commentStart.equals("/**") || lines.size()>1 || indentAmount+lines.get(0).length()>mMaxLineLength))
			{
				if (lines.get(0).length()>commentStart.length()) //if more than /* or /**
				{
					lines.add(0, commentStart);
					lines.set(1, AntlrUtilities.asTrim(lines.get(1).substring(commentStart.length())));
				}
				if (lines.get(lines.size()-1).length()>2)
				{
					String lineData=lines.get(lines.size()-1);
					lines.set(lines.size()-1, AntlrUtilities.asTrim(lineData.substring(0, lineData.length()-2)));
					lines.add("*/");
				}
			}
			
			//now, wrap the lines I have left
			List<String> wrappedLines=new ArrayList<String>();
			
			boolean workingOnAttribute=false;
			for (int i=0;i<lines.size();i++)
			{
				String line=lines.get(i);
				if (line.length()==0)
					workingOnAttribute=false;
				int extraSpaces=0; //accounts for aligning text to right of "/* " or "*"
				//					boolean lineHasAsterisk=line.startsWith("*") && !line.startsWith("*/");
				if (line.startsWith("@"))
					workingOnAttribute=true; //once we see an attribute, we assume that subsequent lines are as well, until we see a blank line
				while (true)
				{
					String currentLine;
					int hangingSpaces=0;
					if (workingOnAttribute && !line.startsWith("@"))
						hangingSpaces=mTabSize*hangingIndentTabs;
					int nextBreakPoint=findCommentBreakpoint(line, indentAmount+extraSpaces+hangingSpaces, mMaxLineLength);
					extraSpaces=3; //accounts for aligning text to right of "/* " or "*" on lines 2..n-1
					if (nextBreakPoint<0 || nextBreakPoint>=line.length())
					{
						currentLine=line;
						line="";
					}
					else
					{
						currentLine=line.substring(0, nextBreakPoint);
						line=AntlrUtilities.asTrim(line.substring(nextBreakPoint));
					}

					if (!currentLine.startsWith("/*") && !currentLine.startsWith("*/") && (asteriskMode==ASPrettyPrinter.MLAsteriskStyle_All || (asteriskMode==ASPrettyPrinter.MLAsteriskStyle_AsIs && hasAsterisks)))
					{
						//if we are on an attribute line, but not on the first loop iteration (i.e. we have already wrapped at least once)
						if (workingOnAttribute && hangingIndentTabs>0 && !currentLine.startsWith("@"))
						{
							//I think I should always use spaces here
							currentLine=ActionScriptFormatter.generateIndent(mTabSize*hangingIndentTabs, false, mTabSize)+currentLine;
						}
						currentLine=asteriskPrefix+currentLine;
						mAdditionalTextAdded+=asteriskPrefix;
						mAdditionalTextAddedAllPasses+=asteriskPrefix;
					}
					wrappedLines.add(currentLine);
					if (line.length()==0)
						break;
				}
			}
			
			commentLines=wrappedLines.toArray(new String[]{});
			return commentLines;
		}
	}

	private static class CommentLineWrapData
	{
		public String mText;
		public int mOriginalStartOffset;
	}
	
	//wrap the text of the line comment and return an array of line comments to replace the original.  
	//The original should be returned if it doesn't violate the max length
	private List<CommentLineWrapData> wrapLineComment(String text, int indent, int maxLength) {
//		int spacesBeforeText=0;
		List<CommentLineWrapData> lines=new ArrayList<CommentLineWrapData>();
		int originalOffset=0;
		String remainingText=text;
		if (remainingText.startsWith("//"))
		{
			remainingText=remainingText.substring(2);
			originalOffset+=2;
		}
		String preWhitespace=AntlrUtilities.findPreWhitespace(remainingText);
		originalOffset+=preWhitespace.length();
		remainingText=remainingText.trim();
		
		while (true)
		{
			int breakPoint=findCommentBreakpoint(remainingText, indent+preWhitespace.length()+2, maxLength);
			String lineText="";
			if (breakPoint<0 || breakPoint>=remainingText.length())
			{
				lineText=remainingText;
				remainingText="";
			}
			else
			{
				lineText=remainingText.substring(0, breakPoint);
				remainingText=remainingText.substring(breakPoint);
			}
			lineText=lineText.trim();
			CommentLineWrapData wrapData=new CommentLineWrapData();
			wrapData.mText="//"+preWhitespace+lineText+"\n";
			wrapData.mOriginalStartOffset=originalOffset;
			lines.add(wrapData);
			originalOffset+=breakPoint;
			String newPreWS=AntlrUtilities.findPreWhitespace(remainingText);
			originalOffset+=newPreWS.length();
			remainingText=remainingText.trim();
			if (remainingText.length()==0)
				break;
		}
		
		return lines;
	}
	
	private int findCommentBreakpoint(String text, int extraChars, int maxLength)
	{
		int testPos=Math.min(text.length(), maxLength-extraChars);
		if (testPos<=0 || testPos==text.length())
			return testPos;
		
		while (true)
		{
			if (testPos<=1)
				return maxLength-extraChars;
			char ch=text.charAt(testPos-1);
			
			if (AntlrUtilities.isASWhitespace(ch)) //possible break point
			{
				return testPos;
			}
			testPos--; //make progress searching backward
		}
	}

	private void addLineCommentPreSpacing(List<Token> hiddenTokens, int ti)
	{
		if (lastCharIsNonWhitespace(mOutputBuffer))
		{
			if (mKeepSpacesBeforeLineComments)
			{
				for (int j=ti-1;j>=0;j--)
				{
					Token ws=hiddenTokens.get(j);
					if (ws.getChannel()==AS3_exParser.CHANNEL_WHITESPACE)
					{
						mOutputBuffer.append(ws.getText());
					}
					else
						break;
				}
				return;
			}
			else if (mLineCommentColumn>0)
			{
				//check to see if there is a carriage return following the comment token.
				//The idea is that if the token text is on the same line, then this isn't really
				//an 'end of line' comment.
				boolean foundCR=false;
				for (int j=ti;j<hiddenTokens.size();j++)
				{
					Token ws=hiddenTokens.get(j);
					if (ws.getChannel()==AS3_exParser.CHANNEL_EOL || ws.getChannel()==AS3_exParser.CHANNEL_SLCOMMENT)
					{
						foundCR=true;
						break;
					}
				}
				
				if (foundCR)
				{
					boolean addedAtLeastOneSpace=false;
					int lastLineLength=determineLastLineLength(getOutputBuffer(), getTabSize());
					for (int i=lastLineLength+1;i<mLineCommentColumn;i++)
					{
						mOutputBuffer.append(' ');
						addedAtLeastOneSpace=true;
					}
					if (addedAtLeastOneSpace)
						return;
				}
			}

			//fall through to here if don't meet other conditions or no spaces added
			mOutputBuffer.append(' ');
		}
	}

	private static final int Nesting_Opposite=1; 
	private static final int Nesting_Unrelated=2; 
	private static final int Nesting_Deeper=3; 
	/**
	 * 
	 * @param currentChar
	 * @param prevChar
	 * @return 
	 */
	private int determineMatchingGroupingCharType(char currentChar, char prevChar)
	{
		switch (currentChar)
		{
			case '(':
			case '{':
			case '[':
				if (prevChar=='(' || prevChar=='{' || prevChar=='[')
					return Nesting_Deeper;
				break;
			case ')':
				if (prevChar=='(')
					return Nesting_Opposite;
			case '}':
				if (prevChar=='{')
					return Nesting_Opposite;
			case ']':
				if (prevChar=='[')
					return Nesting_Opposite;
				
				if (prevChar==')' || prevChar=='}' || prevChar==']')
					return Nesting_Deeper;
				break;
		}
		
		return Nesting_Unrelated;
	}

	private boolean isGroupingChar(char c)
	{
		if (c==')' || c=='(' || c=='['  || c==']' || c=='{' || c=='}')
			return true;
		return false;
	}

	private boolean lastCharIsNonWhitespace(StringBuffer buffer)
	{
		if (buffer.length()>0 && !AntlrUtilities.isASWhitespace(buffer.charAt(buffer.length()-1)))
		{
			return true;
		}
		return false;
	}
	

	public boolean isSymbolTokenThatShouldHaveSpaceBeforeIt(int tokenType)
	{
		switch (tokenType)
		{
		case AS3_exParser.COMMENT_MULTILINE:
		case AS3_exParser.COMMENT_SINGLELINE:
		case AS3_exParser.DOLLAR:
		case AS3_exParser.DOUBLE_QUOTE_LITERAL:
		case AS3_exParser.REGULAR_EXPR_LITERAL:
		case AS3_exParser.SINGLE_QUOTE_LITERAL:
		case AS3_exParser.UNICODE_ESCAPE:
			//xml elements?
			return true;
		}
		
		return false;
	}

	public static final int INITIAL_INDENT=0;
	public static final int BRACE_INDENT=34;
	public static final int STATEMENT_INDENT=35;
	public static final int EXPRESSION_INDENT=36;
	public static final int EXPRESSION_INDENT_NEXTITEM=37;
	public static class IndentType
	{
		private boolean mLabeledIndent;
		private int mType;
		private int mAmount;
		private int mOriginalIndent=(-1);
		public IndentType(int type, int amount)
		{
			mType=type;
			mAmount=amount;
			mLabeledIndent=false;
		}
		public boolean isLabeledIndent() {
			return mLabeledIndent;
		}
		public void setLabeledIndent(boolean labeledIndent) {
			mLabeledIndent = labeledIndent;
		}
		
		public String toString()
		{
			StringBuffer buffer=new StringBuffer();
			buffer.append("Size="+mAmount+"->");
			switch (mType)
			{
			case INITIAL_INDENT: buffer.append("Initial indent");
			break;
			case BRACE_INDENT: buffer.append("Brace indent");
			break;
			case STATEMENT_INDENT: buffer.append("statement indent");
			break;
			case EXPRESSION_INDENT: buffer.append("expression indent");
			break;
			default:
				buffer.append("Unknown indent type");
			}
			return buffer.toString();
		}
		
	}
	
	void pushExpressionIndent(int amount)
	{
		mIndentStack.add(new IndentType(EXPRESSION_INDENT, amount));
	}
	
	void pushRelativeIndent(int type, int relativeTabs)
	{
		mIndentStack.add(new IndentType(type, getCurrentIndent()+relativeTabs*getBlockIndent()));
	}
	
	void pushFunctionExpressionStatementIndent()
	{
		//find the indent for the previous line to use as the new indent.  This may be smaller than the
		//current indent level captured in the indent stack.  This is kind of weird, but the idea is that
		//function expressions are the one exception to the expression indenting rules because they generate
		//new brace indents.  The behavior seems better if we indent the braces to the indent of the previous
		//line.  This requires that the expression indent we put on the stack (and previous expression indents)
		//get that same indent so that expressions after the function expression don't indent funny themselves.
		//This may be revisited if changes to the wrapping algorithm need to cause further indent adjustments.
		int lastIndent=determineLastIndent(mOutputBuffer);
		
		//change previous expression indents to match the one we're going to add because we've effectively changed
		//the base indent
		for (int i=mIndentStack.size()-1;i>=0;i--)
		{
			IndentType indentType=mIndentStack.get(i);
			if (indentType.mType==EXPRESSION_INDENT)
			{
				indentType.mAmount=lastIndent;
			}
			else
				break;
		}		
		pushExpressionIndent(lastIndent);
	}
	
	void pushIndent(int type)
	{
		int currentIndent=getCurrentIndent();
		
		//if this is a brace indent and the current is a statement indent, then we don't need to increase the indent.
		if (type == BRACE_INDENT)
		{
			if (mIndentStack.size() > 0)
			{
				int indentType = getIndentType(0);
				if (indentType == STATEMENT_INDENT)
				{
					mIndentStack.add(new IndentType(type, currentIndent));
					return;
				}
			}
		}
		else if (type==EXPRESSION_INDENT)
		{
			if (mIndentStack.size() > 0)
			{
				int indentType = getIndentType(0);
				
				//if previous type was expression indent, don't change the indent
				if (indentType == EXPRESSION_INDENT)
				{
					mIndentStack.add(new IndentType(type, currentIndent));
					return;
				}
			}
		}
		else if (type==STATEMENT_INDENT)
		{
			//walk backwards to find previous brace or statement indent and use that as my base indent
			for (int i=mIndentStack.size()-1;i>=0;i--)
			{
				IndentType indentType=mIndentStack.get(i);
				if (indentType.mType==BRACE_INDENT || indentType.mType==STATEMENT_INDENT || indentType.mType==INITIAL_INDENT)
				{
					currentIndent=indentType.mAmount;
					break;
				}
			}
		}
//		else if (type==STATEMENT_INDENT)
//		{
//			//walk backward until I find last brace indent and then make that my 'current' indent
//			for (int i=mIndentStack.size()-1;i>=0;i--)
//			{
//				IndentType indentType=mIndentStack.get(i);
//				if (indentType.mType==BRACE_INDENT)
//				{
//					currentIndent=indentType.mAmount;
//					break;
//				}
//				else if (indentType.mType==INITIAL_INDENT)
//				{
//					currentIndent=indentType.mAmount;
//					break;
//				}
//			}
//			
//		}
		
		int extraIndentAmount=getBlockIndent();
		if (type==EXPRESSION_INDENT)
			extraIndentAmount*=getHangingIndentTabs();
	    mIndentStack.add(new IndentType(type, currentIndent+extraIndentAmount));
	}
	
	int getIndentType(int back)
	{
		if (mIndentStack.size()>back)
		{
			return mIndentStack.get(mIndentStack.size()-(back+1)).mType;
		}
		return INITIAL_INDENT; //have to return something
	}
	
	private int getCurrentIndent()
	{
	    if (mIndentStack.size()>0)
	    {
	        return mIndentStack.get(mIndentStack.size()-1).mAmount;
	    }
	    return 0;
	}
	
	public void makeLabeledIndent()
	{
	    if (mIndentStack.size()>0)
	    {
	    	IndentType type=mIndentStack.get(mIndentStack.size()-1);
	    	type.setLabeledIndent(true);
	    	if (mIndentStack.size()>1)
	    	{
	    		type.mAmount=mIndentStack.get(mIndentStack.size()-2).mAmount;
	    	}
	    }
	}
	
	public boolean isLabeledIndent()
	{
	    if (mIndentStack.size()>0)
	    {
	    	IndentType type=mIndentStack.get(mIndentStack.size()-1);
	    	return type.isLabeledIndent();
	    }
	    return false;
	}
	
	void popIndent()
	{
	    if (mIndentStack.size()>0)
	    {
	        mIndentStack.remove(mIndentStack.size()-1);
	        if (getCurrentIndent()==-1)
	        {
	            popIndent();
	        }
	    }
	}
	
	boolean isHardIndent()
	{
	    if (mIndentStack.size()>=1)
	    {
	    	IndentType indent=mIndentStack.get(mIndentStack.size()-1);
	    	if (indent.mType==BRACE_INDENT || indent.mType==INITIAL_INDENT)
	    		return true;
//	       Integer value=mIndentStack.get(mIndentStack.size()-2);
//	       if (value==(-1))
//	          return true;
	    }
	    
	    return false;
	}
	
	public int getBlankLinesBeforeClass() {
		return mBlankLinesBeforeClass;
	}
	public void setBlankLinesBeforeClass(int blankLinesBeforeClass) {
		mBlankLinesBeforeClass = blankLinesBeforeClass;
	}
	public int getBlankLinesBeforeControlStatement() {
		return mBlankLinesBeforeControlStatement;
	}
	public void setBlankLinesBeforeControlStatement(
			int blankLinesBeforeControlStatement) {
		mBlankLinesBeforeControlStatement = blankLinesBeforeControlStatement;
	}
	public int getBlankLinesToStartFunctions() {
		return mBlankLinesToStartFunctions;
	}
	public void setBlankLinesToStartFunctions(
			int lines) {
		mBlankLinesToStartFunctions = lines;
	}
	public int getBlankLinesToEndFunctions() {
		return mBlankLinesToEndFunctions;
	}
	public void setBlankLinesToEndFunctions(
			int lines) {
		mBlankLinesToEndFunctions = lines;
	}
	public boolean isCRBeforeElse() {
		if (isUseBraceStyleSetting())
		{
			if (getBraceStyleSetting()==BraceStyle_Sun)
				return false;
			else
				return true;
		} 
		return mCRBeforeElse;
	}
	public void setCRBeforeElse(boolean beforeElse) {
		mCRBeforeElse = beforeElse;
	}
	public boolean isCRBeforeWhile() {
		if (isUseBraceStyleSetting())
		{
			return false; //always false as one of the default styles
		} 
		return mCRBeforeWhile;
	}
	public void setCRBeforeWhile(boolean beforeWhile) {
		mCRBeforeWhile= beforeWhile;
	}
	public boolean isCRBeforeCatch() {
		if (isUseBraceStyleSetting())
		{
			if (getBraceStyleSetting()==BraceStyle_Sun)
				return false;
			else
				return true;
		} 
		return mCRBeforeCatch;
	}
	public void setCRBeforeCatch(boolean beforeCatch) {
		mCRBeforeCatch = beforeCatch;
	}
	public boolean isKeepElseIfOnSameLine() {
		if (isUseBraceStyleSetting())
		{
			return true;
		} 
		return mKeepElseIfOnSameLine;
	}
	public void setKeepElseIfOnSameLine(boolean keepElseIfOnSameLine) {
		mKeepElseIfOnSameLine = keepElseIfOnSameLine;
	}
	public int getMaxLineLength() {
		return mMaxLineLength;
	}
	public void setMaxLineLength(int maxLineLength) {
		mMaxLineLength = maxLineLength;
	}
	public Token getLastToken() {
		return mLastToken;
	}
	public int getSpacesAroundAssignment() {
		return mSpacesAroundAssignment;
	}
	public void setSpacesAroundAssignment(int spacesAroundAssignment) {
		mSpacesAroundAssignment = spacesAroundAssignment;
	}
	public int getSpacesBeforeComma() {
		return mSpacesBeforeComma;
	}
	public void setSpacesBeforeComma(int spacesBeforeComma) {
		mSpacesBeforeComma = spacesBeforeComma;
	}
	public int getSpacesAfterComma() {
		return mSpacesAfterComma;
	}
	public void setSpacesAfterComma(int spacesAfterComma) {
		mSpacesAfterComma = spacesAfterComma;
	}
	public int getTabSize() {
		return mTabSize;
	}
	public void setTabSize(int tabSize) {
		mTabSize = tabSize;
	}
	public boolean isUseTabs() {
		return mUseTabs;
	}
	public void setUseTabs(boolean useTabs) {
		mUseTabs = useTabs;
	}
	public boolean isKeepBlankLines() {
		return mKeepBlankLines;
	}
	public void setKeepBlankLines(boolean keepBlankLines) {
		mKeepBlankLines = keepBlankLines;
	}
	public int getBlankLinesBeforeFunction() {
		return mBlankLinesBeforeFunction;
	}
	public void setBlankLinesBeforeFunction(int blankLinesBeforeFunction) {
		mBlankLinesBeforeFunction = blankLinesBeforeFunction;
	}
	public boolean isDoFormat() {
		return mDoFormat;
	}
	public void setBlockIndent(int blockIndent) {
		mBlockIndent = blockIndent;
	}

	public boolean isKeepSingleLineCommentsAtColumn1() {
		return mKeepSingleLineCommentsAtColumn1;
	}

	public void setKeepSingleLineCommentsAtColumn1(
			boolean keepSingleLineCommentsAtColumn1) {
		mKeepSingleLineCommentsAtColumn1 = keepSingleLineCommentsAtColumn1;
	}

	public Point getSelectedRange() {
		return mSelectedRange;
	}

	public void setSelectedRange(Point selectedRange) {
		mSelectedRange = selectedRange;
	}

	public Point getOutputRange() {
		return mOutputRange;
	}

	public Point getReplaceRange() {
		return mReplaceRange;
	}

	public WrapOptions getArrayInitWrapOptions() {
		return mArrayInitWrapOptions;
	}

	public void setArrayInitWrapOptions(WrapOptions arrayInitWrapOptions) {
		mArrayInitWrapOptions = arrayInitWrapOptions;
	}

	public WrapOptions getMethodCallWrapOptions() {
		return mMethodCallWrapOptions;
	}

	public void setMethodCallWrapOptions(WrapOptions methodCallWrapOptions) {
		mMethodCallWrapOptions = methodCallWrapOptions;
	}

	public WrapOptions getMethodDeclWrapOptions() {
		return mMethodDeclWrapOptions;
	}

	public void setMethodDeclWrapOptions(WrapOptions methodDeclWrapOptions) {
		mMethodDeclWrapOptions = methodDeclWrapOptions;
	}

	public WrapOptions getExpressionWrapOptions() {
		return mExpressionWrapOptions;
	}

	public void setExpressionWrapOptions(WrapOptions expressionWrapOptions) {
		mExpressionWrapOptions = expressionWrapOptions;
	}

//	public int getIndentStyle(){
//		return mIndentStyle;
//	}
//	
//	public void setIndentStyle(int indentStyle) {
//		mIndentStyle = indentStyle;
//	}
//
	public int getExpressionSpacesAroundSymbolicOperators() {
		return mExpressionSpacesAroundSymbolicOperators;
	}

	public void setExpressionSpacesAroundSymbolicOperators(
			int expressionSpacesAroundSymbolicOperators) {
		mExpressionSpacesAroundSymbolicOperators = expressionSpacesAroundSymbolicOperators;
	}
	
	public void pushFormatMode(int formatMode)
	{
		mFormatTypeStack.add(formatMode);
	}
	
	public void popFormatMode()
	{
		if (mFormatTypeStack.size()>1)
		{
			mFormatTypeStack.remove(mFormatTypeStack.size()-1);
		}
	}
	
	public int getFormatMode()
	{
		return mFormatTypeStack.get(mFormatTypeStack.size()-1);
	}

	public WrapOptions getXMLWrapOptions() {
		return mXMLWrapOptions;
	}

	public void setXMLWrapOptions(WrapOptions wrapOptions) {
		mXMLWrapOptions = wrapOptions;
	}
	
	public void pushLazyIndent(int indentType)
	{
		mLazyIndentType=indentType;
	}
	
	public void pushLazyFormat(int formatType)
	{
		mLazyFormatType=formatType;
	}

	public int getSpacesAroundColons() {
		return mSpacesAroundColons;
	}

	public void setSpacesAroundColons(int spacesAroundColons) {
		mSpacesAroundColons = spacesAroundColons;
	}
	
	public boolean isAtBlockStart()
	{
		if (mLastToken!=null && mLastToken.getText().equals("{"))
			return true;
		return false;
	}
	
	public boolean isCanAddCRsAtBlockStart()
	{
		return mCanAddCRsAtBlockStart;
	}
	
	public void setCanAddCRsAtBlockStart(boolean mode)
	{
		mCanAddCRsAtBlockStart=mode;
	}

	public int getSpacesBetweenControlKeywordsAndParens() {
		return mSpacesBetweenControlKeywordsAndParens;
	}

	public void setSpacesBetweenControlKeywordsAndParens(
			int spacesBetweenControlKeywordsAndParens) {
		mSpacesBetweenControlKeywordsAndParens = spacesBetweenControlKeywordsAndParens;
	}

//	public int getAdvancedSpacesInsideParens() {
//		return mAdvancedSpacesInsideParens;
//	}
//
//	public void setAdvancedSpacesInsideParens(int spacesInsideParens) {
//		mAdvancedSpacesInsideParens = spacesInsideParens;
//	}

	public boolean isCollapseSpaceForAdjacentParens() {
		return mCollapseSpaceForAdjacentParens;
	}

	public void setCollapseSpaceForAdjacentParens(
			boolean collapseSpaceForAdjacentParens) {
		mCollapseSpaceForAdjacentParens = collapseSpaceForAdjacentParens;
	}

	boolean isDirectiveForNextElement(String directiveName)
	{
		return true; //looks like they should always be associated with the next code item
//		//TODO: maybe change these to equals exact because they seem to be case sensitive
//		if (directiveName.equalsIgnoreCase("Bindable") || directiveName.equalsIgnoreCase("Inspectable") || directiveName.equalsIgnoreCase("ArrayElementType"))
//			return true;
//		return false;
				
	}

//	public boolean isNewlineAfterBindable() {
//		return mIsNewlineAfterBindable;
//	}
//
//	public void setNewlineAfterBindable(boolean newlineAfterBindable) {
//		this.mIsNewlineAfterBindable = newlineAfterBindable;
//	}
	
	void markBindablePos(boolean conditionalCompilerTag)
	{
		if (mBindablePos<0)
		{
//			mMultipleBindableItems=false;
			mBindablePos=mOutputBuffer.length();
		}
		else 
		{
			//we've still got a bindable pos set, so we have more than one item
//			mMultipleBindableItems=true;
		}
//		mLastBindableWasConditionalTag=conditionalCompilerTag;
		mProcessingBindableTag=true;
	}
	
	void setBindableMode()
	{
		mBindableMode=true;
		mProcessingBindableTag=false;
	}

	public int getSpacesAfterLabel() {
		return mSpacesAfterLabel;
	}

	public void setSpacesAfterLabel(int spacesAfterLabel) {
		mSpacesAfterLabel = spacesAfterLabel;
	}

	public boolean isTrimTrailingWS() {
		return mTrimTrailingWS;
	}

	public void setTrimTrailingWS(boolean trimTrailingWS) {
		mTrimTrailingWS = trimTrailingWS;
	}

	public boolean isEmptyStatementsOnNewLine() {
		return mEmptyStatementsOnNewLine;
	}

	public void setEmptyStatementsOnNewLine(boolean emptyStatementsOnNewLine) {
		mEmptyStatementsOnNewLine = emptyStatementsOnNewLine;
	}

//	public boolean isProcessingBindableTag() {
//		return mProcessingBindableTag;
//	}
//
//	public void setProcessingBindableTag(boolean processingBindableTag) {
//		mProcessingBindableTag = processingBindableTag;
//	}
	public int getSpacesInsideParensEtc() {
		return mSpacesInsideParensEtc;
	}

	public void setSpacesInsideParensEtc(int spacesInsideParensEtc) {
		mSpacesInsideParensEtc = spacesInsideParensEtc;
	}

	public int getAdvancedSpacesInsideParensInOtherPlaces() {
		if( mUseSpacesInsideParensEtc )
			return getSpacesInsideParensEtc();
		return mAdvancedSpacesInsideParensInOtherPlaces;
	}

	public void setAdvancedSpacesInsideParensInOtherPlaces(
			int spaces ) {
		mAdvancedSpacesInsideParensInOtherPlaces = spaces;
	}

	public int getAdvancedSpacesInsideParensInParameterLists() {
		if (mUseSpacesInsideParensEtc)
			return getSpacesInsideParensEtc();
		return mAdvancedSpacesInsideParensInParameterLists;
	}

	public void setAdvancedSpacesInsideParensInParameterLists(
			int spaces ) {
		mAdvancedSpacesInsideParensInParameterLists = spaces;
	}

	public int getAdvancedSpacesInsideParensInArgumentLists() {
		if (mUseSpacesInsideParensEtc)
			return getSpacesInsideParensEtc();
		return mAdvancedSpacesInsideParensInArgumentLists;
	}

	public void setAdvancedSpacesInsideParensInArgumentLists(
			int spaces ) {
		mAdvancedSpacesInsideParensInArgumentLists = spaces;
	}

	public int getAdvancedSpacesInsideArrayDeclBrackets() {
		if (mUseSpacesInsideParensEtc)
			return getSpacesInsideParensEtc();
		return mAdvancedSpacesInsideArrayDeclBrackets;
	}

	public void setAdvancedSpacesInsideArrayDeclBrackets(
			int advancedSpacesInsideArrayDeclBrackets) {
		mAdvancedSpacesInsideArrayDeclBrackets = advancedSpacesInsideArrayDeclBrackets;
	}

	public int getAdvancedSpacesInsideArrayReferenceBrackets() {
		if (mUseSpacesInsideParensEtc)
			return getSpacesInsideParensEtc();
		return mAdvancedSpacesInsideArrayReferenceBrackets;
	}

	public void setAdvancedSpacesInsideArrayReferenceBrackets(
			int advancedSpacesInsideArrayReferenceBrackets) {
		mAdvancedSpacesInsideArrayReferenceBrackets = advancedSpacesInsideArrayReferenceBrackets;
	}

	public int getAdvancedSpacesInsideObjectBraces() {
		if (mUseSpacesInsideParensEtc)
			return getSpacesInsideParensEtc();
		return mAdvancedSpacesInsideObjectBraces;
	}

	public void setAdvancedSpacesInsideObjectBraces(
			int advancedSpacesInsideObjectBraces) {
		mAdvancedSpacesInsideObjectBraces = advancedSpacesInsideObjectBraces;
	}

	public boolean isUseSpacesInsideParensEtc() {
		return mUseSpacesInsideParensEtc;
	}

	public void setUseSpacesInsideParensEtc(boolean useSpacesInsideParensEtc) {
		mUseSpacesInsideParensEtc = useSpacesInsideParensEtc;
	}

	public int getAdvancedSpacesAroundAssignmentInOptionalParameters() {
		if (!isUseAdvancedSpacesAroundAssignmentInOptionalParameters())
			return getSpacesAroundAssignment();
		return mAdvancedSpacesAroundAssignmentInOptionalParameters;
	}

	public void setAdvancedSpacesAroundAssignmentInOptionalParameters(
			int advancedSpacesAroundAssignmentInOptionalParameters) {
		mAdvancedSpacesAroundAssignmentInOptionalParameters = advancedSpacesAroundAssignmentInOptionalParameters;
	}

	public boolean isUseAdvancedSpacesAroundAssignmentInOptionalParameters() {
		return mUseAdvancedSpacesAroundAssignmentInOptionalParameters;
	}

	public void setUseAdvancedSpacesAroundAssignmentInOptionalParameters(
			boolean useAdvancedSpacesAroundAssignmentInOptionalParameters) {
		mUseAdvancedSpacesAroundAssignmentInOptionalParameters = useAdvancedSpacesAroundAssignmentInOptionalParameters;
	}

	public boolean isInParameterDecl() {
		return mInParameterDecl;
	}

	public void setInParameterDecl(boolean inParameterDecl) {
		mInParameterDecl = inParameterDecl;
	}

	public boolean isUseBraceStyleSetting() {
		return mUseBraceStyleSetting;
	}

	public void setUseBraceStyleSetting(boolean useBraceStyleSetting) {
		mUseBraceStyleSetting = useBraceStyleSetting;
	}

	public int getBraceStyleSetting() {
		return mBraceStyleSetting;
	}

	public void setBraceStyleSetting(int braceStyleSetting) {
		mBraceStyleSetting = braceStyleSetting;
	}

	public void setAlwaysGenerateIndent(boolean selection) {
		mAlwaysGenerateIndent=selection;
	}

	public boolean isAlwaysGenerateIndent() {
		return mAlwaysGenerateIndent;
	}

	public int getBlankLinesToKeep() {
		return mBlankLinesToKeep;
	}

	public void setBlankLinesToKeep(int blankLinesToKeep) {
		mBlankLinesToKeep = blankLinesToKeep;
	}

	public int getAdvancedSpacesBeforeColonsInFunctionTypes() {
		if( isUseGlobalSpacesAroundColons() )
			return getSpacesAroundColons();
		return mAdvancedSpacesBeforeColonsInFunctionTypes;
	}

	public void setAdvancedSpacesBeforeColonsInFunctionTypes(
			int advancedSpacesBeforeColonsInFunctions ) {
		mAdvancedSpacesBeforeColonsInFunctionTypes = advancedSpacesBeforeColonsInFunctions;
	}

	public int getAdvancedSpacesBeforeColonsInDeclarations() {
		if( isUseGlobalSpacesAroundColons() )
			return getSpacesAroundColons();
		return mAdvancedSpacesBeforeColonsInDeclarations;
	}

	public void setAdvancedSpacesBeforeColonsInDeclarations( int advancedSpacesBeforeColonsInDeclarations ) {
		mAdvancedSpacesBeforeColonsInDeclarations = advancedSpacesBeforeColonsInDeclarations;
	}

	public int getAdvancedSpacesAfterColonsInFunctionTypes() {
		if (isUseGlobalSpacesAroundColons())
			return getSpacesAroundColons();
		return mAdvancedSpacesAfterColonsInFunctionTypes;
	}

	public void setAdvancedSpacesAfterColonsInFunctionTypes(
			int advancedSpacesAfterColonsInFunctions ) {
		mAdvancedSpacesAfterColonsInFunctionTypes = advancedSpacesAfterColonsInFunctions;
	}

	public int getAdvancedSpacesAfterColonsInDeclarations() {
		if (isUseGlobalSpacesAroundColons())
			return getSpacesAroundColons();
		return mAdvancedSpacesAfterColonsInDeclarations;
	}

	public void setAdvancedSpacesAfterColonsInDeclarations( int advancedSpacesAfterColonsInDeclarations ) {
		mAdvancedSpacesAfterColonsInDeclarations = advancedSpacesAfterColonsInDeclarations;
	}

	public boolean isUseGlobalSpacesAroundColons() {
		return mUseGlobalSpacesAroundColons;
	}

	public void setUseGlobalSpacesAroundColons(boolean useGlobalSpacesAroundColons) {
		mUseGlobalSpacesAroundColons = useGlobalSpacesAroundColons;
	}

	public void setBlankLinesBeforeProperties(int lines)
	{
		mBlankLinesBeforeProperties=lines;
	}

	public int getBlankLinesBeforeProperties() {
		return mBlankLinesBeforeProperties;
	}

	public boolean isIndentAtPackageLevel() {
		return mIndentAtPackageLevel;
	}

	public void setIndentAtPackageLevel(boolean indentAtPackageLevel) {
		mIndentAtPackageLevel = indentAtPackageLevel;
	}

	public boolean isIndentSwitchCases() {
		return mIndentSwitchCases;
	}

	public void setIndentSwitchCases(boolean indentSwitchCases) {
		mIndentSwitchCases = indentSwitchCases;
	}

	public boolean isKeepingExcessDeclWhitespace() {
		return mIsKeepingExcessDeclWhitespace;
	}

	public void setKeepingExcessDeclWhitespace(boolean isKeepingExcessDeclWhitespace) {
		mIsKeepingExcessDeclWhitespace = isKeepingExcessDeclWhitespace;
	}

	public int getColumnForIndex(Token token, int indexWithinToken)
	{
		//walk tokens back to the start of the line so that I can find other tab characters that would modify the index
		int col=0;
		int startTokenIndex=token.getTokenIndex()-1;
		while (startTokenIndex>0)
		{
			Token t=mRawTokens.get(startTokenIndex);
			if (t.getChannel()==AS3_exParser.CHANNEL_EOL || t.getChannel()==AS3_exParser.CHANNEL_SLCOMMENT)
			{
				startTokenIndex++;
				break;
			}
			else if (t.getChannel()==AS3_exParser.CHANNEL_MLCOMMENT)
			{
				//handle line break in the middle of comment
				int lastCR=t.getText().lastIndexOf('\n');
				if (lastCR>=0)
				{
					String text=t.getText().substring(lastCR+1);
					col=getColumnLength(col, text, 0, text.length());
					startTokenIndex++;
					break;
				}
			}
			
			startTokenIndex--;
		}

		//now, walk forward counting columns
		for (int i=startTokenIndex;i<token.getTokenIndex();i++)
		{
			Token t=mRawTokens.get(i);
			if (t.getChannel()==AS3_exParser.CHANNEL_WHITESPACE)
			{
				col=getColumnLength(col, t.getText(), 0, t.getText().length());
			}
			else
				col+=t.getText().length();
		}
		
		//handle the partial token we're currently on
		Token t=mRawTokens.get(token.getTokenIndex());
		String text=t.getText().substring(0, indexWithinToken);
		col=getColumnLength(col, text, 0, indexWithinToken);
		
		return col;
	}
	
	private int getColumnLength(int col, String text, int start, int end)
	{
		for (int k=start;k<end && k<text.length();k++)
		{
			if (text.charAt(k)=='\t')
			{
				if (col%getTabSize()==0)
					col+=getTabSize();
				else
					col+=getTabSize()-col%getTabSize();
			}
			else
				col++;
		}
		
		return col;
	}
	
	public void setElseIfState()
	{
		mNextTokenInElseIfState=true;
	}
	
	public boolean getElseIfNeedToLoseIndent()
	{
		boolean result=mElseIfNeedToLoseIndent;
		mElseIfNeedToLoseIndent=false;
		return result;
	}
	
	public void pushFormatterOff()
	{
		mExcludeStackCount++;
	}
	
	public void popFormatterOff()
	{
		mExcludeStackCount--;
		if (mExcludeStackCount<0)
			mExcludeStackCount=0;
	}
	
	public boolean isFormatterOff()
	{
		return (mExcludeStackCount>0);
	}
	
	private static class WrapInfo
	{
		private List<WrapItem> mWrapItems=new ArrayList<WrapItem>();
		private int mStartPos;
		private boolean mNewLevel; //if true, this is an important grouping level
		private int mBaseLevel;
		public WrapInfo(boolean newLevel, int start, int baseLevel)
		{
			mStartPos=start;
			mNewLevel=newLevel;
			mBaseLevel=baseLevel;
		}
		public void incorporateData(WrapInfo wi)
		{
			for (WrapItem item : wi.mWrapItems)
			{
				if (!wi.isNewLevel())
					item.setDepth(mBaseLevel);
				mWrapItems.add(item);
			}
//			mWrapItems.addAll(wi.mWrapItems);
		}
		
		public void addWrapChar(Token op, int depth, int position, int breakType, boolean breakBefore, boolean indentToFirstParm, int firstParmLocation, int currentIndent, String commaContextType)
		{
			WrapItem item=new WrapItem(op, depth, position, breakType, breakBefore, currentIndent);
			item.setIndentToFirstParm(indentToFirstParm);
			item.setFirstParmPos(firstParmLocation);
			item.setCommaContextType(commaContextType);
			mWrapItems.add(item);
		}
		public void incrementPositions(int position, int amount)
		{
			for (WrapItem item : mWrapItems) {
				item.incrementPositions(position, amount);
			}
		}
		public int getStartPos() {
			return mStartPos;
		}
		public boolean isNewLevel() {
			return mNewLevel;
		}
	}
	
	private static class WrapItem
	{
		private int mDepth;
		private String mText;
		private int mStartPos;
		private int mBreakType;
		private boolean mBreakBefore;
		private int mFirstParmPos;
		private int mIndent;
		private boolean mBreakUsed;
		private boolean mIndentToFirstParm;
		private String mCommaContextType;
		private int mNextItemPos; //used for aligning object/array items
		private int mAlternateFirstItemWrapPoint;
		public WrapItem(Token t, int depth, int position, int breakType, boolean breakBefore, int currentIndent)
		{
			mStartPos=position;
			mDepth=depth;
			mText="";
			if (t!=null)
				mText=t.getText();
			mBreakType=breakType;
			mBreakBefore=breakBefore;
			mFirstParmPos=(-1);
			mIndent=currentIndent;
			mBreakUsed=false;
			mIndentToFirstParm=false;
			mNextItemPos=(-1);
			mAlternateFirstItemWrapPoint=(-1);
		}
		public void setCommaContextType(String commaContextType) {
			mCommaContextType=commaContextType;
		}
		public String getCommaContextType()
		{
			return mCommaContextType;
		}
		public void incrementPositions(int position, int amount)
		{
			if (getStartPos()>=position)
				setStartPos(getStartPos()+amount);
			if (mFirstParmPos>=0 && mFirstParmPos>=position)
				mFirstParmPos+=amount;
		}
		public void setDepth(int level)
		{
			mDepth=level;
		}
		public int getStartPos() {
			return mStartPos;
		}
		public void setStartPos(int startPos) {
			mStartPos = startPos;
		}
		public int getDepth() {
			return mDepth;
		}
		public String getText() {
			return mText;
		}
		public int getBreakType() {
			return mBreakType;
		}
		public boolean isBreakBefore() {
			return mBreakBefore;
		}
		public int getFirstParmPos() {
			return mFirstParmPos;
		}
		public void setFirstParmPos(int firstParmPos) {
			mFirstParmPos = firstParmPos;
		}
		public int getIndent() {
			return mIndent;
		}
		public boolean isBreakUsed() {
			return mBreakUsed;
		}
		public void setBreakUsed(boolean breakUsed) {
			mBreakUsed = breakUsed;
		}
		public boolean isIndentToFirstParm() {
			return mIndentToFirstParm;
		}
		public void setIndentToFirstParm(boolean indentToFirstParm) {
			mIndentToFirstParm = indentToFirstParm;
		}
		public void setIndent(int spaces) 
		{
			mIndent=spaces;
		}
		public int getNextItemPos() {
			return mNextItemPos;
		}
		public void setNextItemPos(int nextItemPos) {
			mNextItemPos = nextItemPos;
		}
		public int getAlternateFirstItemWrapPoint() {
			return mAlternateFirstItemWrapPoint;
		}
		public void setAlternateFirstItemWrapPoint(int alternateFirstItemWrapPoint) {
			mAlternateFirstItemWrapPoint = alternateFirstItemWrapPoint;
		}
	}
	
	public void createWrapContext(boolean newLevel)
	{
		mExpressionWrapData.add(new WrapInfo(newLevel, mOutputBuffer.length(), mExpressionWrapData.size()));
	}
	
	public void adjustLastLineIndent()
	{
		//this method is to unindent the last line of an expression statement if it is still indented when it
		//shouldn't be.  This is mainly the case if the last line is just the end brace of a function expression
		//or the end bracket of an array decl, etc.  However, in the normal wrapping case, we want to leave the
		//regular hanging indent if there is other content on the line.
		int lastCR=mOutputBuffer.lastIndexOf("\n");
		if (lastCR>=0)
		{
			int lastIndent=determineLastIndent(mOutputBuffer);
			int currentIndent=getCurrentIndent();
			if (lastIndent==currentIndent)
				return;
			String data=mOutputBuffer.substring(lastCR+1).trim();
			if (data.length()==0)
				return; //don't think it should ever be empty, but doesn't hurt to check
			for (int i=0;i<data.length();i++)
			{
				char ch=data.charAt(i);
				if (Character.isJavaIdentifierPart(ch))
					return; //if any chars/numbers, then definitely keep indent
				if (ch!=')' && ch!='}' && ch!=';' && ch!=']')
					return; //only certain characters legal on last line as part of a 'terminator' sequence
			}
			
			if (data.charAt(0)!='}' && data.charAt(0)!=')' && data.charAt(0)!=']')
				return; //only certain chars are allowed as 'terminators'
			
			boolean unindent=data.equals("}");
			if (!unindent)
			{
				if (isNoIndentForTerminators())
					unindent=true;
			}
			
			if (unindent)
			{
				String newIndentString=generateIndent(currentIndent);
				int firstNonWS=0;
				String originalString=mOutputBuffer.substring(lastCR+1);
				for (int i=0;i<originalString.length();i++)
				{
					if (!Character.isWhitespace(originalString.charAt(i)))
					{
						firstNonWS=i;
						break;
					}
				}
				if (firstNonWS>=0)
				{
					mOutputBuffer.delete(lastCR+1, lastCR+1+firstNonWS);
				}
				mOutputBuffer.insert(lastCR+1, newIndentString);
			}
		}
	}
	
	public boolean isNoIndentForTerminators()
	{
		return mNoIndentForTerminators;
	}
	
	public void setNoIndentForTerminators(boolean value)
	{
		mNoIndentForTerminators=value;
	}
	
	public void popWrapContext()
	{
		if (!isUseAdvancedWrapping())
			return;
		
		if (mExpressionWrapData.size()>0)
		{
			WrapInfo wi=mExpressionWrapData.get(mExpressionWrapData.size()-1);
			mExpressionWrapData.remove(mExpressionWrapData.size()-1);
			if (mExpressionWrapData.size()==0)
			{
				//Perform wrapping; go back to first line and start breaking where necessary
				//Algorithm (for preserving phrases):
				//   1. find previous line end and save that position
				//   2. find next line end and save that position
				//   3. if length is < max, iterate; otherwise continue
				//   4. walk through each level in the data
				//   5. find break points at the lowest levels and work my way up
				//   6. stop if I find a break point that is allowed by the current settings and < max (maybe add grace columns in here too)
				//   7. insert break and move positions
				//   8. continue to next line
				
				int maxLevel=0;
				for (WrapItem item : wi.mWrapItems) {
					maxLevel=Math.max(maxLevel, item.mDepth);
				}
				
				int lineStart=wi.getStartPos();
//				//1 find previous line end
//				for (int i=wi.getStartPos();i>=0;i--)
//				{
//					if (mOutputBuffer.charAt(i)=='\n')
//					{
//						lineStart=i+1;
//						break;
//					}
//				}
				
				int loopSafety=0;
				while (lineStart<mOutputBuffer.length())
				{
					if (loopSafety++ > 1000) //just a check to make sure we don't lock up if the algorithm gets off
						break;
				
					//1 find previous line end
					for (int i=lineStart;i>=0;i--)
					{
						if (mOutputBuffer.charAt(i)=='\n')
						{
							lineStart=i+1;
							break;
						}
						if (i==0) //handle case of being on the first line
							lineStart=0;
					}

					//2. find next line end
					int lineEnd=mOutputBuffer.length();
					for (int i=lineStart;i<mOutputBuffer.length();i++)
					{
						if (mOutputBuffer.charAt(i)=='\n')
						{
							lineEnd=i;
							break;
						}
					}
					
					//3 if already within max length, then go to next line
					if (getColumnLength(0, mOutputBuffer.substring(lineStart, lineEnd), 0, lineEnd-lineStart)<=getMaxLineLength())
					{
						lineStart=lineEnd+1; //move to next line
						continue;
					}
					
					//4
					if (isAdvancedWrappingPreservePhrases())
					{
						int nextLineStart=(-1);
						for (int i=0;i<=maxLevel;i++)
						{
							nextLineStart=findWrapPoint(wi, lineStart, lineEnd, i, false, false);
							if (nextLineStart>=0)
							{
								break;
							}
						}
						
						if (nextLineStart<0)
						{
							//search forward to find a user-preferred wrap at any level
							if (!isAdvancedWrappingEnforceMax())
								nextLineStart=findWrapPoint(wi, lineStart, lineEnd, -1, false, true);
							if (isAdvancedWrappingEnforceMax())
								nextLineStart=findWrapPoint(wi, lineStart, lineEnd, -1, true, true);
							if (nextLineStart<0)
								nextLineStart=lineEnd+1;
						}
						lineStart=nextLineStart;
					}
					else
					{
						int nextLineStart=findWrapPoint(wi, lineStart, lineEnd, -1, false, false);
						if (nextLineStart<0)
						{
							if (isAdvancedWrappingEnforceMax())
								nextLineStart=findWrapPoint(wi, lineStart, lineEnd, -1, true, true);
							if (nextLineStart<0)
								nextLineStart=lineEnd+1;
						}
						lineStart=nextLineStart;
					}
				}
			}
			else
			{
				mExpressionWrapData.get(mExpressionWrapData.size()-1).incorporateData(wi);
			}
		}
	}
	
	private int findWrapPoint(WrapInfo wi, int lineStart, int lineEnd, int depth, boolean forceWrap, boolean ignoreMax)
	{
		String restOfText=mOutputBuffer.substring(lineStart);
		for (int i=wi.mWrapItems.size()-1;i>=0;i--)
		{
			WrapItem info=wi.mWrapItems.get(i);
			if (info.mStartPos<lineEnd && info.mStartPos>lineStart)
			{
				int nextLineIndex=possiblyWrap(wi, info, i, lineStart, restOfText, depth, forceWrap, getMaxLineLength());
				if (nextLineIndex>=0)
					return nextLineIndex;
			}
		}
		
		{
			for (int i=0;i<wi.mWrapItems.size();i++)
			{
				WrapItem info=wi.mWrapItems.get(i);
				if (info.mStartPos<lineEnd && info.mStartPos>lineStart)
				{
					int nextLineIndex=possiblyWrap(wi, info, i, lineStart, restOfText, depth, forceWrap, ignoreMax ? Integer.MAX_VALUE : getMaxLineLength()+getAdvancedWrappingGraceColumns());
					if (nextLineIndex>=0)
						return nextLineIndex;
				}				
			}
		}
		
		return -1;
	}
	
	private boolean mWrapAllArgumentsIfAny=false; //break on every comma if any need to be broken
	private boolean mWrapAllParametersIfAny=false; //break on every comma if any need to be broken
	private boolean mWrapFirstArgument=false; //when wrapping all args, put first arg on a newline as well
	private boolean mWrapFirstParameter=false; //when wrapping all parameters, put first parameter on a newline as well
	
	//Needs spaces stored somewhere to allow aligning by colon or aligning item starts across lines.
	//Choice between wrap as necessary and take over wrapping, starting on a new line.
	private boolean mWrapAllObjectItemsIfAny=false; //take over wrapping if any wrapping needs to be done
	private boolean mWrapFirstObjectItem=false; //wrap starting with the first item
	private boolean mWrapObjectItemsAlignStart=false; //align items on key start column.
//	private int mWrapObjectItemsPerLine=0; //0->wrap to max length, otherwise, items per line
	
	private boolean mWrapAllArrayItemsIfAny=false;
	private boolean mWrapFirstArrayItem=false; //wrap starting with the first item
	private boolean mWrapArrayItemsAlignStart=false; //align items on key start column.
	
	private boolean isWrapAllObject(WrapItem info)
	{
		return (info.getBreakType()==Break_Commas_code && 
				mWrapAllObjectItemsIfAny && info.getCommaContextType().equals(Break_SubType_Object));
	}
	
	private boolean isWrapAllArray(WrapItem info)
	{
		return (info.getBreakType()==Break_Commas_code && 
				mWrapAllArrayItemsIfAny && info.getCommaContextType().equals(Break_SubType_Array));
	}
	
	private boolean isWrapAllArgsAndParms(WrapItem info)
	{
		return (info.getBreakType()==Break_Commas_code && 
		((mWrapAllArgumentsIfAny && info.getCommaContextType().equals(Break_SubType_Arguments)) || 
		(mWrapAllParametersIfAny && info.getCommaContextType().equals(Break_SubType_Parameters))));		
	}
	
	private int possiblyWrap(WrapInfo wi, WrapItem info, int infoIndex, int lineStart, String restOfText, int depth, boolean forceWrap, int maxLineLength)
	{
		if (info.isBreakUsed())
			return -1;
		if ((depth<0 || (depth==info.mDepth)) && (forceWrap || (info.getBreakType() & getAdvancedWrappingElements())!=0) || info.getBreakType()==Break_XML_code)
		{
			//TODO: handle before/after calculation and processing
			int[] splitPointInfo=new int[2];
			findSplitPoint(info, restOfText, lineStart, splitPointInfo);
			int splitPoint=splitPointInfo[0];
			int lengthAtSplit=splitPointInfo[1];

			//TODO: need to split lines even if no split would satisfy the line length.  It appears as well that
			//my current algorithm should force a split if the alternative is not splitting at all.
			if (lengthAtSplit<=maxLineLength) // || wrapAllArgsOrParms || wrapAllObjectDef || wrapAllArrayDef)
			{
				//at this point, we need to check to see if the current wrap point is for a comma in one of the special
				//wrapping modes.  If so, we want to walk up to lower depths to find the highest level item of one of 
				//these special wrap types.
				
				boolean wrapAllObjectDef=isWrapAllObject(info);
				boolean wrapAllArgsOrParms=isWrapAllArgsAndParms(info);
				boolean wrapAllArrayDef=isWrapAllArray(info);
//				if (wrapAllArgsOrParms || wrapAllObjectDef || wrapAllArrayDef)
				{
					int workingDepth=info.getDepth();
					WrapItem higherItem=info;
					//search for higher level items further down the list
					for (int i=infoIndex+1;i<wi.mWrapItems.size();i++)
					{
						WrapItem testItem=wi.mWrapItems.get(i);
						if (testItem.getDepth()<workingDepth)
						{
							workingDepth=testItem.getDepth();
							if (!testItem.isBreakUsed())
							{
								if (isWrapAllArgsAndParms(testItem) || isWrapAllObject(testItem) || isWrapAllArray(testItem))
								{
									higherItem=testItem;
								}
							}
						}
					}
					
					if (higherItem!=info)
					{
						info=higherItem;
						wrapAllObjectDef=isWrapAllObject(info);
						wrapAllArgsOrParms=isWrapAllArgsAndParms(info);
						wrapAllArrayDef=isWrapAllArray(info);
						
					}
				}
				
				List<WrapItem> wrapItems=new ArrayList<ASPrettyPrinter.WrapItem>();
				wrapItems.add(info);
				
				int returnedInsertPos=(-1); //want to return the first insert pos if we split multiple args in this one pass, because within an arg, the line could violate the length.
				
				//arg/parm wrapping (if all items are to be wrapped)
				boolean wrappingFirstParm=wrapAllArgsOrParms && ((mWrapFirstArgument && info.getCommaContextType().equals(Break_SubType_Arguments)) ||
						(mWrapFirstParameter && info.getCommaContextType().equals(Break_SubType_Parameters)));
				if (wrapAllArgsOrParms)
				{
					wrapItems.clear();
					if (wrappingFirstParm && info.getFirstParmPos()>=0)
					{
						WrapItem firstItem=new WrapItem(null, info.getDepth(), info.getFirstParmPos(), info.getBreakType(), true, info.getIndent());
						wrapItems.add(firstItem);
					}
					findWrapItemsAndAdjustChildIndent(wi, info, wrapItems);
					returnedInsertPos=info.getFirstParmPos();
				}
				
				//////////////////////////////////////////////////////////////////////////////
				//object item wrapping
				//////////////////////////////////////////////////////////////////////////////
				if (wrapAllObjectDef)
				{
					wrapArrayOrObjectItems(wi, wrapItems, info, mWrapFirstObjectItem, mWrapObjectItemsAlignStart, false);
					returnedInsertPos=info.getFirstParmPos();
				}

				///////////////////////////////////////////////////////////////////////////////
				//array wrapping
				///////////////////////////////////////////////////////////////////////////////
				if (wrapAllArrayDef)
				{
					wrapArrayOrObjectItems(wi, wrapItems, info, mWrapFirstArrayItem, mWrapArrayItemsAlignStart, true);
					returnedInsertPos=info.getFirstParmPos();
				}
				

				for (WrapItem wrapItem : wrapItems) 
				{
					restOfText=mOutputBuffer.substring(lineStart);
					findSplitPoint(wrapItem, restOfText, lineStart, splitPointInfo);
					splitPoint=splitPointInfo[0];
					lengthAtSplit=splitPointInfo[1];
					
					int insertPos=splitPoint;
					if (wrapItem.isBreakBefore())
					{
						//nothing to do.
					}
					else
					{
						//move the split point past the operator/split token
						insertPos+=wrapItem.getText().length();
					}
					
					//remove whitespace at insert point, because we will be starting a new line
					int loopSafety=0;
					while (insertPos<mOutputBuffer.length())
					{
						char testChar=mOutputBuffer.charAt(insertPos);
						if (testChar==' ' || testChar=='\t')
						{
							mOutputBuffer.delete(insertPos, insertPos+1);
							wi.incrementPositions(insertPos, -1);
						}
						else
							break;
						if (loopSafety++ > 1000) //kick out if I seem to be stuck
							break;
					}
					
					//handle hanging indent
					int indentAmount=wrapItem.getIndent();
					if (wrapItem.getFirstParmPos()>=0 && wrapItem.isIndentToFirstParm() && !wrappingFirstParm)
					{
						indentAmount=getColumnForPosition(wrapItem.getFirstParmPos());
					}
					String indentWS=generateIndent(indentAmount);
					
					mOutputBuffer.insert(insertPos, "\n"+indentWS);
					wrapItem.setBreakUsed(true);
					
					//adjust other positions based on the characters we've inserted
					wi.incrementPositions(insertPos+1, 1+indentWS.length());
					
					if (returnedInsertPos<0)
					{
						//return the position just after the \n
						returnedInsertPos=(insertPos+1);
					}
				}
				
				return returnedInsertPos;
//				return insertPos+1;
			}
		}
		return -1;
	}
	
	private void wrapArrayOrObjectItems(WrapInfo wi, List<WrapItem> wrapItems, WrapItem info, boolean wrapFirstItem, boolean wrapItemsAlignStart, boolean isArray) 
	{
		wrapItems.clear();
		
		//we're taking over wrapping, so now we need to figure out where all the items are going to
		//go
//		wrappingFirstParm=mWrapFirstObjectItem;
		//put in entry for first item to make things easier for alignment code and if the
		//first item needs to be wrapped.
		WrapItem firstItem=new WrapItem(null, info.getDepth(), info.getFirstParmPos(), info.getBreakType(), true, info.getIndent());
		firstItem.setNextItemPos(info.getFirstParmPos());
		wrapItems.add(firstItem);
		int alignmentOffset=updateEarlyWrapPoint(firstItem, isArray);
		
		int itemIndent=info.getIndent();
		if ((!wrapFirstItem || isFirstItemOnLine(firstItem.getStartPos())) && info.getFirstParmPos()>0)
		{
			itemIndent=getColumnForPosition(firstItem.getStartPos());
			wrapFirstItem=false;
		}

//		itemIndent-=alignmentOffset; //to adjust for the open brace/bracket and spaces
//		if (!wrapFirstItem)
//			itemIndent=getColumnForPosition(firstItem.getStartPos());
		
		findWrapItemsAndAdjustChildIndent(wi, info, wrapItems);
		
		//find the first text pos for each item
		for (WrapItem item : wrapItems) 
		{
			if (item.getNextItemPos()<0)
			{
				//update item pos by finding the next non-whitespace char after the comma pos
				int location=item.getStartPos()+1;
				for (;location<mOutputBuffer.length();location++)
				{
					if (!AntlrUtilities.isASWhitespace(mOutputBuffer.charAt(location)))
						break;
				}
				item.setNextItemPos(location);
			}
		}						
		
//		if (mWrapObjectItemsPerLine>0) //predetermined how many to do per line (usually one)
//		{
//			//remove items that aren't used, and mark them 'used' so that they won't break again
//			
//			int[] maxItemWidths=new int[mWrapObjectItemsPerLine];
//			findMaxItemWidths(wrapItems, maxItemWidths);
//			setUsedItems(wrapItems, itemIndent, itemsPerLine);
//			
//			//determine (and add) extra spaces before removing items from list
//			if (mWrapObjectItemsAlignStart)
//			{
//				addInternalSpaces(wi, wrapItems, maxItemWidths);					
//			}
//			
//			//remove items that won't receive breaks
//			removeUsedItems(wrapItems);
//		}
//		else
		{
			if (wrapItemsAlignStart)
			{
				if (wrapFirstItem && !isFirstItemOnLine(firstItem.getStartPos()))
				{
				}
				else
				{
					firstItem.setBreakUsed(true);
				}
				

				//start with max items, then work down as I find a line that's tool long 
				//because of the aligned columns.
				int itemsPerLine=wrapItems.size();
				
				//decrement itemsPerLine as we try to find a fit
				int[] maxItemWidths=new int[itemsPerLine];
				while (itemsPerLine>1)
				{
					//find the max widths of items based on the given items per line
					maxItemWidths=new int[itemsPerLine];
					findMaxItemWidths(wrapItems, maxItemWidths);
					
					//now, check the max widths and see if this number of items will work for all lines
					int column=itemIndent;
					for (int i=0;i<itemsPerLine;i++)
					{
						column+=maxItemWidths[i];
					}
					if (column<=mMaxLineLength)
						break;
					
					itemsPerLine--;
				}
				
				//mark the items as 'used' if we're not going to have to break them further
				setUsedItems(wrapItems, itemIndent, itemsPerLine, wrapFirstItem, wrapFirstItem ? alignmentOffset : 0);
				
				//I've picked a correct number of items per line--now, increment the positions where necessary.
				addInternalSpaces(wi, wrapItems, maxItemWidths);
				
				//remove items that won't receive breaks
				removeUsedItems(wrapItems);
			}
			else //just wrapping items to line length, no alignment
			{
				//determine each wrap item and remove the others
				int originalIndent=info.getIndent();
				int currentIndent=itemIndent;
				int currentItemStartPos=firstItem.getStartPos();
				if (wrapFirstItem && firstItem.getAlternateFirstItemWrapPoint()>=0)
					currentItemStartPos=firstItem.getAlternateFirstItemWrapPoint();
				int itemsSinceLastWrap=0;
				for (int i=0;i<wrapItems.size();i++)
				{
					WrapItem item=wrapItems.get(i);
					int nextStartPos=mOutputBuffer.indexOf("\n", item.getStartPos());
					if (nextStartPos<0)
						nextStartPos=mOutputBuffer.length();
					if (i+1<wrapItems.size())
					{
						WrapItem nextItem=wrapItems.get(i+1);
						nextStartPos=nextItem.getStartPos();
					}
					
					int testLength=nextStartPos-currentItemStartPos+currentIndent+1;
//					String testString=mOutputBuffer.substring(currentItemStartPos, nextStartPos);
//					int testLength=currentIndent+getColumnLength(0, testString, 0, testString.length());	
					if (testLength>mMaxLineLength && itemsSinceLastWrap>0 && !isFirstItemOnLine(item.getStartPos()))
					{
						currentItemStartPos=item.getNextItemPos();
						if (!info.isIndentToFirstParm()) //switch indent if we are not indenting to first item
						{
							currentIndent=originalIndent+alignmentOffset;
						}
						itemsSinceLastWrap=1;
					}
					else
					{
						if (i>0 || !wrapFirstItem || isFirstItemOnLine(item.getStartPos()))
							item.setBreakUsed(true); //don't need to break here
						
						if (item.isBreakUsed() || i==0) //1st item is special because it doesn't matter if it's wrapped or not, it's still an item on the line
							itemsSinceLastWrap++;
						else
							itemsSinceLastWrap=1; //to account for case where already on a line by itself
					}
				}
				
				for (int i=1;i<wrapItems.size();i++) //start at 1 to skip the first item
				{
					WrapItem item=wrapItems.get(i);
					item.setIndent(originalIndent+alignmentOffset);
				}
				
				//remove items that won't receive breaks
				removeUsedItems(wrapItems);
			}
		}
	}

	private int updateEarlyWrapPoint(WrapItem firstItem, boolean isArray) 
	{
		for (int i=firstItem.getStartPos()-1;i>=0;i--)
		{
			char ch=mOutputBuffer.charAt(i);
			if (!AntlrUtilities.isASWhitespace(ch) && ((isArray && ch!='{') || (!isArray && ch!='[')))
			{
				firstItem.setAlternateFirstItemWrapPoint(i);
//				firstItem.setStartPos(i);
//				firstItem.setNextItemPos(i);
				break;
			}
		}
		
		int alignmentOffset=1; //always 1 for the [ or {
		for (int i=firstItem.getStartPos()-1;i>=0;i--)
		{
			char ch=mOutputBuffer.charAt(i);
			if (!AntlrUtilities.isASWhitespace(ch))
			{
				int adjustedStartPos=i;
				if ((isArray && ch=='[') || (!isArray && ch=='{'))
					adjustedStartPos++;
				firstItem.setStartPos(adjustedStartPos);
				firstItem.setNextItemPos(adjustedStartPos);
				return alignmentOffset;
			}
			alignmentOffset++; //for additional spaces
		}
		return alignmentOffset;
	}

	private boolean isFirstItemOnLine(int startPos) 
	{
		int lastLineStart=mOutputBuffer.lastIndexOf("\n", startPos);
		if (lastLineStart<0)
			lastLineStart=0;
		else
			lastLineStart++;
		
		for (int i=lastLineStart;i<startPos;i++)
		{
			char ch=mOutputBuffer.charAt(i);
			if (ch=='[' || ch=='{')
				continue;
			if (AntlrUtilities.isASWhitespace(ch))
				continue;
			
			return false;
		}
		
		return true;
	}

	private void addInternalSpaces(WrapInfo wi, List<WrapItem> wrapItems, int[] maxItemWidths) 
	{
		int itemsPerLine=maxItemWidths.length;
		for (int i=0;i<wrapItems.size();i++)
		{
			WrapItem item=wrapItems.get(i);
			if (item.isBreakUsed() && i>0)
			{
				int columnIndex=(i%itemsPerLine);
				if (columnIndex>0) //we don't want to add spaces after last column of items
				{
					WrapItem previousItem=wrapItems.get(i-1);
					int newSpaces=maxItemWidths[columnIndex-1]-(item.getNextItemPos()-previousItem.getNextItemPos());
					if (newSpaces>0)
					{
						mOutputBuffer.insert(item.getStartPos()+1, ActionScriptFormatter.generateIndent(newSpaces, false, 0));
						wi.incrementPositions(item.getStartPos()+1, newSpaces);
					}
				}
			}
		}
	}

	private void setUsedItems(List<WrapItem> wrapItems, int itemIndent, int itemsPerLine, boolean wrapFirstItem, int alignmentOffset) 
	{
		for (int i=0;i<wrapItems.size();i++)
		{
			WrapItem item=wrapItems.get(i);
			
			//if we are on an item to be wrapped (1st item may or may not be wrapped based on options)
			if (i%itemsPerLine==0 || i==0)
			{
				if (i>0 || wrapFirstItem)
				{
					if (!isFirstItemOnLine(item.getStartPos()))
					{
						int mungedIndent=itemIndent;
						if (i>0)
							mungedIndent+=alignmentOffset;
						item.setIndent(mungedIndent);
						continue;
					}
				}
			}
			
			item.setBreakUsed(true);
		}
	}

	private void findMaxItemWidths(List<WrapItem> wrapItems, int[] maxItemWidths) 
	{
		int itemsPerLine=maxItemWidths.length;
		for (int i=0;i<wrapItems.size();i++)
		{
			WrapItem item=wrapItems.get(i);
			int nextItemPos=mOutputBuffer.length();
			if (i+1<wrapItems.size())
			{
				WrapItem nextItem=wrapItems.get(i+1);
				nextItemPos=nextItem.getNextItemPos();
			}
			int columnIndex=(i%itemsPerLine);
			maxItemWidths[columnIndex]=Math.max(maxItemWidths[columnIndex], nextItemPos-item.getNextItemPos());
		}
	}

	private void removeUsedItems(List<WrapItem> wrapItems) 
	{
		for (int i=wrapItems.size()-1;i>=0;i--)
		{
			WrapItem item=wrapItems.get(i);
			if (item.isBreakUsed())
				wrapItems.remove(i);
		}
	}

	private int getColumnForPosition(int filePosition)
	{
		//TODO: use the first parm pos to determine the number of columns to indent
		//search backward for line start from the first parm pos.
		int previousNewline=mOutputBuffer.lastIndexOf("\n", filePosition);
		if (previousNewline<0)
			previousNewline=0;
		else
			previousNewline++;
		return getColumnLength(0, mOutputBuffer.toString(), previousNewline, filePosition);
		
	}
	
	private void findWrapItemsAndAdjustChildIndent(WrapInfo wi, WrapItem info, List<WrapItem> wrapItems) 
	{
		boolean seenFirstItemAtDepth=false;
		for (int i=0;i<wi.mWrapItems.size();i++)
		{
			WrapItem testItem=wi.mWrapItems.get(i);
			//only the same if the same type, depth, AND if the first parm pos is the same.  Otherwise, two arg lists at the same level would appear the same
			if (info.getBreakType()==testItem.getBreakType() && info.getDepth()==testItem.getDepth() && info.getFirstParmPos()==testItem.getFirstParmPos())
			{
				seenFirstItemAtDepth=true;
				wrapItems.add(testItem);
			}
			else if (!testItem.isBreakUsed() && testItem.getDepth()>info.getDepth())
			{
				testItem.setIndent(info.getIndent()+getTabSize());
			}
			//quit if we have seen the first item at the proper scope and now we have 
			//unwound and seen the end of that item's scope
			if (seenFirstItemAtDepth && testItem.getDepth()<info.getDepth())
				break;
		}
	}

	public void findSplitPoint(WrapItem info, String restOfText, int lineStart, int[] results)
	{
		int splitPoint=info.getAlternateFirstItemWrapPoint();
		if (splitPoint<0)
			splitPoint=info.mStartPos;
		int lengthAtSplit=getColumnLength(0, restOfText, 0, splitPoint-lineStart);
		if (info.isBreakBefore())
		{
			//look for whitespace before split point, that shouldn't be counted
			int removedWSCount=0;
			for (int i=splitPoint-lineStart-1;i>=0;i--)
			{
				char c=restOfText.charAt(i);
				if (c==' ' || c=='\t')
					removedWSCount++;
				else
					break;
			}
			
			//if we found some whitespace to trim, then recalculate the split position
			if (removedWSCount>0)
			{
				lengthAtSplit=getColumnLength(0, restOfText, 0, (splitPoint-lineStart)-removedWSCount);
				splitPoint=splitPoint-removedWSCount;
			}
		}
		else
		{
			lengthAtSplit+=info.getText().length();
		}		
		
		results[0]=splitPoint;
		results[1]=lengthAtSplit;
	}
	
	public void captureNextTextPosition()
	{
		mLastCapturePosition=(-1);
	}
	
	public int getLastCapturePosition()
	{
		return mLastCapturePosition;
	}

	public void saveWrapChar(Token op, int breakType, boolean breakBefore, boolean indentToFirstParm, int firstParmLocation, String commaContextType)
	{
		if (!isUseAdvancedWrapping())
			return;
		
		if (mExpressionWrapData.size()==0)
			return;
		
		WrapInfo info=mExpressionWrapData.get(mExpressionWrapData.size()-1);
		info.addWrapChar(op, mExpressionWrapData.size(), mOutputBuffer.length()-op.getText().length(), breakType, breakBefore, indentToFirstParm, firstParmLocation, getCurrentIndent(), commaContextType);
	}

	public int getHangingIndentTabs() {
		return mHangingIndentTabs;
	}

	public void setHangingIndentTabs(int hangingIndentTabs) {
		mHangingIndentTabs = hangingIndentTabs;
	}

	public boolean isUseGlobalNewlineBeforeBraceSetting() {
		return mUseGlobalNewlineBeforeBraceSetting;
	}

	public void setUseGlobalNewlineBeforeBraceSetting(
			boolean useGlobalNewlineBeforeBraceSetting) {
		mUseGlobalNewlineBeforeBraceSetting = useGlobalNewlineBeforeBraceSetting;
	}
	
	public void setAdvancedNewlineBeforeBraceSettings(int mask)
	{
		mAdvancedNewlineBeforeBraceSettings=mask;
//		mAdvancedNewlineBeforeBraceSettings.clear();
//		mAdvancedNewlineBeforeBraceSettings.putAll(settings);
	}

	public int getAdvancedWrappingElements() {
		return mAdvancedWrappingElements;
	}

	public void setAdvancedWrappingElements(int advancedWrappingElements) {
		mAdvancedWrappingElements = advancedWrappingElements;
	}

	public boolean isAdvancedWrappingPreservePhrases() {
		return mAdvancedWrappingPreservePhrases;
	}

	public void setAdvancedWrappingPreservePhrases(
			boolean advancedWrappingPreservePhrases) {
		mAdvancedWrappingPreservePhrases = advancedWrappingPreservePhrases;
	}

	public int getAdvancedWrappingGraceColumns() {
		return mAdvancedWrappingGraceColumns;
	}

	public void setAdvancedWrappingGraceColumns(int advancedWrappingGraceColumns) {
		mAdvancedWrappingGraceColumns = advancedWrappingGraceColumns;
	}

	public boolean isAdvancedWrappingEnforceMax() {
		return mAdvancedWrappingEnforceMax;
	}

	public void setAdvancedWrappingEnforceMax(boolean advancedWrappingEnforceMax) {
		mAdvancedWrappingEnforceMax = advancedWrappingEnforceMax;
	}

	public boolean isUseAdvancedWrapping() {
		return mUseAdvancedWrapping;
	}

	public void setUseAdvancedWrapping(boolean useAdvancedWrapping) {
		mUseAdvancedWrapping = useAdvancedWrapping;
	}

	public int getSpacesBeforeFormalParameters() {
		return mSpacesBeforeFormalParameters;
	}

	public void setSpacesBeforeFormalParameters(int spacesBeforeFormalParameters) {
		mSpacesBeforeFormalParameters = spacesBeforeFormalParameters;
	}
	
	public int getSpacesBeforeArguments() {
		return mSpacesBeforeArguments;
	}

	public void setSpacesBeforeArguments(int spacesBeforeArguments) {
		mSpacesBeforeArguments = spacesBeforeArguments;
	}

	public Set<String> getMetaTagsToKeepOnSameLineAsProperty() {
		return mMetaTagsToKeepOnSameLineAsProperty;
	}

	public void setMetaTagsToKeepOnSameLineAsProperty(
			Set<String> metaTagsToKeepOnSameLineAsProperty) {
		mMetaTagsToKeepOnSameLineAsProperty = metaTagsToKeepOnSameLineAsProperty;
	}

	public Set<String> getMetaTagsToKeepOnSameLineAsFunction() {
		return mMetaTagsToKeepOnSameLineAsFunction;
	}

	public void setMetaTagsToKeepOnSameLineAsFunction(
			Set<String> metaTagsToKeepOnSameLineAsFunction) {
		mMetaTagsToKeepOnSameLineAsFunction = metaTagsToKeepOnSameLineAsFunction;
	}

	public boolean isNewlineBeforeBindableFunction() {
		return mIsNewlineBeforeBindableFunction;
	}

	public void setNewlineBeforeBindableFunction(
			boolean isNewlineBeforeBindableFunction) {
		mIsNewlineBeforeBindableFunction = isNewlineBeforeBindableFunction;
	}

	public boolean isNewlineBeforeBindableProperty() {
		return mIsNewlineBeforeBindableProperty;
	}

	public void setNewlineBeforeBindableProperty(
			boolean isNewlineBeforeBindableProperty) {
		mIsNewlineBeforeBindableProperty = isNewlineBeforeBindableProperty;
	}

	public void setLastBindableTagName(String lastBindableTagName) {
		mLastBindableTagName = lastBindableTagName;
	}

	public void setBindingContext(int bindingContext) {
		mBindingContext = bindingContext;
	}

	public boolean isRemoveConditionalBraces(StatementBraceInfo info)
	{
		if (getConditionalBraceModifyMode()==Braces_AddRemoveSmart)
		{
			return !isNeedConditionalBraces(info);
		}
			
		return false;
	}

	public boolean isNeedConditionalBraces(StatementBraceInfo info)
	{
		//if always adding, return true
		if (getConditionalBraceModifyMode()==Braces_AddIfMissing)
			return true;
		
		//if smart mode, then do some more tests
		if (getConditionalBraceModifyMode()==Braces_AddSmart || getConditionalBraceModifyMode()==Braces_AddRemoveSmart)
		{
			return isSmartNeedBraces(info);
		}
		
		return false;
	}
	
	public boolean isRemoveLoopBraces(StatementBraceInfo info)
	{
		if (getLoopBraceModifyMode()==Braces_AddRemoveSmart)
		{
			return !isNeedLoopBraces(info);
		}
			
		return false;
	}

	public boolean isNeedLoopBraces(StatementBraceInfo info)
	{
		//if always adding, return true
		if (getLoopBraceModifyMode()==Braces_AddIfMissing)
			return true;
		
		//if smart mode, then do some more tests
		if (getLoopBraceModifyMode()==Braces_AddSmart || getLoopBraceModifyMode()==Braces_AddRemoveSmart)
		{
			return isSmartNeedBraces(info);
		}
		
		return false;
	}
	
	private boolean isSmartNeedBraces(StatementBraceInfo info)
	{
		//if there's a comment before the statement, definitely add braces
		if (info.isHasComment())
			return true;
		
		//if the loop control statement itself crosses lines, then we want to add braces
		if (info.getOutputStatementStartPos()>0 && info.getOutputStatementEndPos()>0)
		{
			String statementText=mOutputBuffer.substring(info.getOutputStatementStartPos(), info.getOutputStatementEndPos());
			if (statementText.trim().indexOf('\n')>=0)
				return true;
		}
		
		//if the statement is on more than one line (that is, if there is wrapping involved), return true
		String statementData=mOutputBuffer.substring(info.getStartBracePos(), info.getEndBracePos()).trim();
		if (info.isBracesCurrentlyExist())
		{
			//remove braces from statementData, if at ends
			statementData=statementData.trim();
			if (statementData.startsWith("{"))
				statementData=statementData.substring(1);
			if (statementData.endsWith("}"))
				statementData=statementData.substring(0, statementData.length()-1);
		}
		if (statementData.trim().indexOf('\n')>=0)
			return true;
		
		//more than 2 statements means block plus more than one statement.  If there is only one statement (and braces exist),
		//that means an empty block, which we don't want to touch either.
		if (info.getStatementCount()>2 || (info.isBracesCurrentlyExist() && info.getStatementCount()<=1))
			return true;

		return false;
	}
	
	public int getCurrentOutputLength()
	{
		return mOutputBuffer.length();
	}
	
	public void captureStatementStart(Token t, int bufferPos)
	{
		if (mAddBraceStack.size()>0)
		{
			StatementBraceInfo braceInfo=mAddBraceStack.get(mAddBraceStack.size()-1);
			braceInfo.setOriginalStatementStartPos(((CommonToken)t).getStartIndex());
			braceInfo.setOutputStatementStartPos(bufferPos);
		}
	}
	
	public void captureStatementEnd(Token t, int bufferPos)
	{
		if (mAddBraceStack.size()>0)
		{
			StatementBraceInfo braceInfo=mAddBraceStack.get(mAddBraceStack.size()-1);
			braceInfo.setOriginalStatementEndPos(((CommonToken)t).getStartIndex());
			braceInfo.setOutputStatementEndPos(bufferPos);
		}
	}
	
	public boolean isRemoveSwitchBraces(StatementBraceInfo info)
	{
		if (getSwitchBraceModifyMode()==Braces_RemoveUnnecessary)
		{
			return true;
		}
			
		return false;
	}
	
	public boolean isNeedSwitchBraces(StatementBraceInfo info)
	{
		if (getSwitchBraceModifyMode()==Braces_AddSmart)
		{
			//if smart adding, return true unless the next token is the end 'break' (empty case statement),
			//the next case/default statement, or the end brace of the switch 
			if (info.getNextTokenType()!=AS3_exLexer.BREAK && info.getNextTokenType()!=AS3_exLexer.CASE && info.getNextTokenType()!=AS3_exLexer.DEFAULT && info.getNextTokenType()!=AS3_exLexer.RCURLY)
				return true;
		}
//		//if smart mode, then do some more tests
//		if (getLoopBraceModifyMode()==Braces_AddSmart || getLoopBraceModifyMode()==Braces_AddRemoveSmart)
//		{
//			//if there's a comment before the statement, definitely add braces
//			if (info.isHasComment())
//				return true;
//			
//			//if the loop control statement itself crosses lines, then we want to add braces
//			String statementText=mOutputBuffer.substring(info.getOutputStatementStartPos(), info.getOutputStatementEndPos());
//			if (statementText.indexOf('\n')>=0)
//				return true;
//			
//			//if the statement is on more than one line (that is, if there is wrapping involved), return true
//			String statementData=mOutputBuffer.substring(info.getStartBracePos(), info.getEndBracePos());
//			if (statementData.indexOf('\n')>=0)
//				return true;
//		}
		
		return false;
	}
	
	public int getConditionalBraceModifyMode()
	{
		return mConditionalBraceModifyMode;
	}
	
	public int getLoopBraceModifyMode()
	{
		return mLoopBraceModifyMode;
	}
	
	public int getSwitchBraceModifyMode()
	{
		return mSwitchBraceModifyMode;
	}
	
	public static final int BraceAdd_Loop=1;
	public static final int BraceAdd_Conditional=2;
	public static final int BraceAdd_Switch=3;
	private boolean isAddBraces(StatementBraceInfo info)
	{
		switch (info.getStatementType())
		{
		case BraceAdd_Loop:
			return isNeedLoopBraces(info);
		case BraceAdd_Conditional:
			return isNeedConditionalBraces(info);
		case BraceAdd_Switch:
			return isNeedSwitchBraces(info);
		default:
			return false;
		}
	}
	
	private boolean isRemoveBraces(StatementBraceInfo info)
	{
		switch (info.getStatementType())
		{
		case BraceAdd_Loop:
			return isRemoveLoopBraces(info);
		case BraceAdd_Conditional:
			return isRemoveConditionalBraces(info);
		case BraceAdd_Switch:
			return isRemoveSwitchBraces(info);
		default:
			return false;
		}
	}
	
	public void setLoopBraceMode(int braceModifyMode)
	{
		mLoopBraceModifyMode=braceModifyMode;
	}
	
	public void setSwitchBraceMode(int braceModifyMode)
	{
		mSwitchBraceModifyMode=braceModifyMode;
	}
	
	public void setConditionalBraceMode(int braceModifyMode)
	{
		mConditionalBraceModifyMode=braceModifyMode;
	}
	
	public boolean addOpenBraceForElse(TokenStream input)
	{
		//if we are putting 'if' on a separate line after 'else' or the next token is not 'if', then we 
		//continue with the normal checks
		if (input.LA(1)!=AS3_exLexer.IF || !isKeepElseIfOnSameLine())
		{
			return addOpenBrace(input, BraceContext_Conditional_code);
		}
		
		//otherwise, 
		return false;
	}
	
	private boolean mNoCRBeforeReturn=false;
	private boolean mNoCRBeforeContinue=false;
	private boolean mNoCRBeforeBreak=false;
	private boolean mNoCRBeforeThrow=false;
	private boolean mNoCRBeforeExpressions=false;
	public boolean statementNeedsCR(Token t)
	{
		//we only apply this special processing if we're inside an if/else statement
		if (!mInCondition && !mInLoop)
			return true;
		
		//first, detect if there's already a CR there.
		List<Token> preTokens=AntlrUtilities.getHiddenTokens(t, mRawTokens, true, true);
		for (Token token : preTokens) {
			if (token.getChannel()==AS3_exParser.CHANNEL_EOL)
			{
				return true;
			}
		}
		
		//otherwise, check based on the statement type.
		if (t.getType()==AS3_exLexer.RETURN)
		{ 
			if (mNoCRBeforeReturn)
				return false;
		}
		else if (t.getType()==AS3_exLexer.CONTINUE)
		{
			if (mNoCRBeforeContinue)
				return false;
		}
		else if (t.getType()==AS3_exLexer.BREAK)
		{
			if (mNoCRBeforeBreak)
				return false;
		}
		else if (t.getType()==AS3_exLexer.THROW)
		{
			if (mNoCRBeforeThrow)
				return false;
		}
		else if (mNoCRBeforeExpressions)
		{
			return false;
		}
		
		//by default, we will add a carriage return
		return true;
	}
	
	private boolean mInCondition;
	private boolean mInLoop;
	public void inCondition()
	{
		inOther();
		mInCondition=true;
	}
	
	public void inLoop()
	{
		inOther();
		mInLoop=true;
	}
	
	public void inOther()
	{
		mInCondition=false;
		mInLoop=false;
	}
	
	public boolean addOpenBrace(TokenStream input, int braceCode)
	{
//		if (input.LA(1)!=AS3_exLexer.LCURLY && input.LA(1)!=AS3_exLexer.RCURLY && (braceCode!=BraceAdd_Switch || (input.LA(1)!=AS3_exLexer.BREAK && input.LA(1)!=AS3_exLexer.CASE && input.LA(1)!=AS3_exLexer.DEFAULT)))
		{
			//if adding braces and the next statement is not a block statement
			addOpenBrace(input.LT(1), braceCode);
//			return true;
		}		
		return true;
	}
	
	private List<StatementBraceInfo> mAddBraceStack;
	private List<StatementBraceInfo> mCompletedBraceInfos;
	public void addOpenBrace(Token nextToken, int braceCode)
	{
//		addBracePart(nextToken, braceCode, "{");
		if (getFormatMode()!=FORMAT_INDENT)
		{
			CommonToken token=(CommonToken)nextToken;
			boolean hasBraces=(token.getType()==AS3_exParser.LCURLY);
			
			StatementBraceInfo braceInfo=new StatementBraceInfo(braceCode, hasBraces);
//			braceInfo.setStartBracePos(mOutputBuffer.length()); //add the current pos; will be cleaned up later if there is a brace
			
			CommonToken startToken=findNextNonWhitespaceTokenAfterCurrentLine(token);
			if (startToken.getChannel()==AS3_exParser.CHANNEL_MLCOMMENT || startToken.getChannel()==AS3_exParser.CHANNEL_SLCOMMENT)
				braceInfo.setHasComment(true);
			else
				braceInfo.setHasComment(false);
			braceInfo.setNextTokenType(nextToken.getType());
			braceInfo.setOriginalDocStartPosition(startToken.getStartIndex());
			mAddBraceStack.add(braceInfo);
		}
		
	}
	
	private void captureBraceStartPos()
	{
		//the idea here is to capture the position of the next token, 
		if (getFormatMode()!=FORMAT_INDENT)
		{
			if (mAddBraceStack.size()>0)
			{
				StatementBraceInfo braceInfo=mAddBraceStack.get(mAddBraceStack.size()-1);
				if (braceInfo.getStartBracePos()<0)
					braceInfo.setStartBracePos(mOutputBuffer.length());
			}
		}
	}
	
	
	public Map<Integer, ReplacementRange> getReplaceMap()
	{
		return mReplaceMap;
	}
	
//	private void addBracePart(Token nextToken, int braceCode, String braceChar)
//	{
//		if (getFormatMode()!=FORMAT_INDENT && isAddBraces(braceCode) && (nextToken instanceof CommonToken))
//		{
//			if (mReplaceMap==null)
//				mReplaceMap=new HashMap<Integer, ReplacementRange>();
//			CommonToken token=(CommonToken)nextToken;
//			//for the replacement point, I want the next non-whitespace location in the original document.  This might
//			//occur prior to nextToken if there is a comment there.
//			List<Token> preTokens=AntlrUtilities.getHiddenTokens(nextToken, mRawTokens, true, true);
//			for (Token t : preTokens) {
//				if (t.getText()!=null && AntlrUtilities.asTrim(t.getText()).length()>0 && (t instanceof CommonToken))
//				{
//					token=(CommonToken)t;
//					break;
//				}
//			}
//			ReplacementRange range=new ReplacementRange(new Point(mOutputBuffer.length(), mOutputBuffer.length()+braceChar.length()), new Point(token.getStartIndex(),token.getStartIndex()));
//			mReplaceMap.put(mOutputBuffer.length(), range);
//			range.setChangedText(braceChar, "");
//			mOutputBuffer.append(braceChar);
//			mAdditionalTextAdded+=braceChar;
//			mAdditionalTextAddedAllPasses+=braceChar;
//		}
//	}
	
	/**
	 * Is there a comment that goes with the statement?  Don't count a single-line comment (of either type) that
	 * occurs on the first line, because I treat that as going with the control statement.
	 */
	private CommonToken findNextNonWhitespaceTokenAfterCurrentLine(CommonToken startToken)
	{
		//look in the hidden tokens for the first carriage return, then see if there is a comment after it.  
		//Handle a comment containing a carriage return as well (return true).
		List<Token> preTokens=AntlrUtilities.getHiddenTokens(startToken, mRawTokens, true, true);
		boolean seenFirstCR=false;
		for (Token t : preTokens) {
			if (t.getType()==AS3_exParser.COMMENT_SINGLELINE)
			{
				if (seenFirstCR)
					return (CommonToken)t;
				seenFirstCR=true;
			}
			else if (t.getType()==AS3_exParser.COMMENT_MULTILINE)
			{
				if (seenFirstCR)
					return (CommonToken)t;
				if (t.getText()!=null && t.getText().indexOf('\n')>=0)
					return (CommonToken)t;
			}
			else
			{
				if (t.getText()!=null && t.getText().indexOf('\n')>=0)
					seenFirstCR=true;
			}
		}
		return startToken;
	}

	public void checkForSingleLineFunctionMode(Token functionStart)
	{
		if (!mKeepSingleLineFunctions)
			return;
		
		if (mInSingleLineFunctionMode)
		{
			mInSingleLineFunctionMode=false;
			return;
		}
		
		//search forward looking for next matching { and } and keep in single line more (no CRs) if
		//the following conditions hold.
		//1. no CRs
		//2. no other braces
		//3. NO? length of characters (including whitespace?) less than max line length (if enforcing) 
		boolean seenFirstBrace=false;
		int currentToken=functionStart.getTokenIndex();
		for (int i=currentToken; i<mRawTokens.size(); i++)
		{
			Token t=mRawTokens.get(i);
			String tokenText=t.getText();
			if (tokenText.indexOf('\n')>=0)
			{
				return;
			}
			if (t.getType()==AS3_exLexer.LCURLY)
			{
				if (seenFirstBrace)
					return; //if previously seen first brace
				seenFirstBrace=true;
			}
			if (t.getType()==AS3_exLexer.RCURLY)
			{
				if (!seenFirstBrace)
					return;
				break;
			}
		}
		
		//indicate that we are in single line function mode if no disqualifying circumstances discovered.
		mInSingleLineFunctionMode=true; 
	}
	
	public void endSingleLineFunctionMode()
	{
		mInSingleLineFunctionMode=false;
	}
	
	private CommonToken findNextNonWhitespaceToken(CommonToken startToken)
	{
		//for the replacement point, I want the next non-whitespace location in the original document.  This might
		//occur prior to nextToken if there is a comment there.
		List<Token> preTokens=AntlrUtilities.getHiddenTokens(startToken, mRawTokens, true, true);
		for (Token t : preTokens) {
			if (t.getText()!=null && AntlrUtilities.asTrim(t.getText()).length()>0 && (t instanceof CommonToken))
			{
				return (CommonToken)t;
			}
		}
		return startToken;
	}
	
	
	public void addCloseBrace(Token nextToken, int braceCode, int statementsIncluded)
	{
//		addBracePart(nextToken, braceCode, "}");
		if (getFormatMode()!=FORMAT_INDENT)
		{
			if (mAddBraceStack.size()>0)
			{
				StatementBraceInfo braceInfo=mAddBraceStack.remove(mAddBraceStack.size()-1);
				//TODO: confirm close brace here (for switch); otherwise, we don't really have 'surrounding' braces
				if (braceCode==BraceAdd_Switch && braceInfo.isBracesCurrentlyExist())
				{
					Token prevToken=mRawTokens.get(nextToken.getTokenIndex()-1);
					while (prevToken.getChannel()!=AS3_exParser.DEFAULT_TOKEN_CHANNEL)
					{
						if (prevToken.getTokenIndex()==0)
							break;
						prevToken=mRawTokens.get(prevToken.getTokenIndex()-1);
					}
					if (prevToken.getType()==AS3_exParser.SEMI)
						prevToken=mRawTokens.get(prevToken.getTokenIndex()-1);
					
					if (prevToken.getType()!=AS3_exParser.RCURLY && prevToken.getType()!=AS3_exParser.BREAK)
					{
						braceInfo.setBracesCurrentlyExist(false);
					}
				}
				CommonToken token=(CommonToken)nextToken;
				braceInfo.setEndBracePos(mOutputBuffer.length());
				braceInfo.setStatementCount(statementsIncluded);
				CommonToken startToken=findNextNonWhitespaceToken(token);
				braceInfo.setOriginalDocEndPosition(startToken.getStartIndex());
				mCompletedBraceInfos.add(braceInfo);
			}
		}
	}
	
	public boolean didLastStatementHaveBraces()
	{
		//NOTE: this method only makes sense in format mode, because brace locations are only captured there.
		if (mCompletedBraceInfos.size()>0)
		{
			StatementBraceInfo braceInfo=mCompletedBraceInfos.get(mCompletedBraceInfos.size()-1);
			return braceInfo.isBracesCurrentlyExist();
		}
		return false;
	}
	
	public boolean needAnotherPass()
	{
		return ((mReplaceMap!=null && mReplaceMap.size()>0) || (!mAlreadyHaveDeclPositions && mRootDeclEqualContext!=null && mRootDeclEqualContext.doesNeedChanges()));
	}

	public void disableMultiPassMode()
	{
		mAllowMultiplePasses=false;
	}
	
	public String getAddedText()
	{
		return mAdditionalTextAddedAllPasses;
	}

	public StringBuffer getOutputBuffer() {
		return mOutputBuffer;
	}

	public int getBlankLinesBeforeImports() {
		return mBlankLinesBeforeImports;
	}

	public void setBlankLinesBeforeImports(int blankLinesBeforeImports) {
		mBlankLinesBeforeImports = blankLinesBeforeImports;
	}

	public boolean isAlignDeclEquals() {
		return mAlignDeclEquals;
	}

	public void setAlignDeclEquals(boolean alignDeclEquals) {
		mAlignDeclEquals = alignDeclEquals;
	}
	
	public boolean isKeepSpacesBeforeLineComments() {
		return mKeepSpacesBeforeLineComments;
	}

	public void setKeepSpacesBeforeLineComments(boolean keepSpaces) {
		this.mKeepSpacesBeforeLineComments = keepSpaces;
	}

	public int getAlignDeclMode() {
		return mAlignDeclMode;
	}

	public void setAlignDeclMode(int mode) {
		mAlignDeclMode = mode;
	}

	public void addDeclEqualsBlock()
	{
		if (mCurrentDeclEqualContext!=null)
		{
			if (mAlignDeclMode!=Decl_Align_Global)
				mCurrentDeclEqualContext=mCurrentDeclEqualContext.addChildContext();
		}
	}
	
	/**
	 * call to indicate that a non-declaration was seen.  If there are declarations in the current context, then create
	 * another peer context and hook it into the structure.
	 */
	public void markDeclEqualsContextChange()
	{
		if (mCurrentDeclEqualContext!=null)
		{
			if (mAlignDeclMode==Decl_Align_Consecutive)
			{
				if (mCurrentDeclEqualContext.getItemsSeen()>0)
				{
					//create a peer
					if (mCurrentDeclEqualContext.getParent()!=null)
						mCurrentDeclEqualContext=mCurrentDeclEqualContext.getParent().addChildContext();
				}
			}
		}
	}
	
	public void popDeclEqualsBlock()
	{
		if (mCurrentDeclEqualContext!=null)
		{
			mCurrentDeclEqualContext=mCurrentDeclEqualContext.getParent();
		}
	}

	public void captureDeclEqualPosition()
	{
		if (mCurrentDeclEqualContext!=null)
		{
			int equalPos=determineLastLineLength(mOutputBuffer, getTabSize())-1;
			mCurrentDeclEqualContext.capturePos(equalPos);
		}
	}
	
	public void augmentDeclEqualPosition()
	{
		if (mCurrentDeclEqualContext!=null && mDeclEqualPosMap!=null)
		{
			int currentLength=determineLastLineLength(mOutputBuffer, getTabSize());
			Integer amt=mDeclEqualPosMap.get(mCurrentDeclEqualContext.getPathCode());
			if (amt!=null)
			{
				//now, increase the current length based on the number of extra whitespaces that are currently in the pipeline
				for (int i=mAddedCRs.size()-1;i>=0;i--)
				{
					if (mAddedCRs.get(i)==ADD_WS)
						currentLength++;
					else
						break;
				}
				insertWS(amt-currentLength);
			}
		}
	}
	
	static class EqualContext
	{
		List<EqualContext> mChildren;
		int mMaxPosition;
		EqualContext mParent;
		String mPathCode;
		boolean mNeedChanges;
		int mItemsSeen=0;
		public EqualContext(EqualContext parent, int index)
		{
			mMaxPosition=0;
			mChildren=new ArrayList<EqualContext>();
			mParent=parent;
			if (parent!=null)
			{
				mPathCode=parent.mPathCode+Integer.toString(index);
			}
			else
			{
				mPathCode="0";
			}
			mNeedChanges=false;
		}
		
		public void fillMap(Map<String, Integer> declEqualPosMap)
		{
			declEqualPosMap.put(getPathCode(), getMaxPosition());
			for (EqualContext context : mChildren) {
				context.fillMap(declEqualPosMap);
			}
		}

		public EqualContext addChildContext()
		{
			EqualContext newContext=new EqualContext(this, mChildren.size());
			mChildren.add(newContext);
			return newContext;
		}
		
		public EqualContext getParent()
		{
			return mParent;
		}
		
		public int getItemsSeen()
		{
			return mItemsSeen;
		}
		
		public void capturePos(int newEqualColumn)
		{
			mItemsSeen++;
			if (mMaxPosition!=newEqualColumn)
				mNeedChanges=true;
			mMaxPosition=Math.max(mMaxPosition, newEqualColumn);
		}
		
		public boolean doesNeedChanges()
		{
			boolean needChanges=mNeedChanges;
			for (EqualContext context : mChildren) {
				needChanges|=context.doesNeedChanges();
			}
			return needChanges;
		}

		public int getMaxPosition() {
			return mMaxPosition;
		}
		
		public String getPathCode()
		{
			return mPathCode;
		}
		
	}
	
	public boolean isKeepRelativeCommentIndent()
	{
		return mKeepRelativeCommentIndent;
	}

	public void setKeepRelativeCommentIndent(boolean value)
	{
		mKeepRelativeCommentIndent=value;
	}

	public int getLineCommentColumn() {
		return mLineCommentColumn;
	}

	public void setLineCommentColumn(int column) {
		mLineCommentColumn = column;
	}

	private static class StatementBraceInfo
	{
		//TODO: hold info on whether there were carriage returns in the statement already, or whether it was
		//all on one line.  I would need to prevent carriage return from being added.  Not sure how this can work yet.
		//Store token start pos and end pos if no brace so that 
	
		private int mNextTokenType;
		private int mStartBracePos;
		private int mEndBracePos;
		private boolean mBracesCurrentlyExist;
		private boolean mHasComment;
		private int mStatementType;
		private int mOriginalDocStartPosition;
		private int mOriginalDocEndPosition;
		private int mOriginalDocStatementStartPos;
		private int mOriginalDocStatementEndPos;
		private int mStartStatementPos;
		private int mEndStatementPos;
		private int mStatementsIncluded;
		public StatementBraceInfo(int statementType, boolean bracesCurrentlyExist)
		{
			mStartBracePos=(-1);
			mEndBracePos=(-1);
			mBracesCurrentlyExist=bracesCurrentlyExist;
			mHasComment=true;
			mStatementType=statementType;
			mOriginalDocStartPosition=(-1);
			mOriginalDocEndPosition=(-1);
			mOriginalDocStatementEndPos=(-1);
			mOriginalDocStatementStartPos=(-1);
			mStartStatementPos=(-1);
			mEndStatementPos=(-1);
			mNextTokenType=(-1);
			mStatementsIncluded=(-1);
		}
		
		public void setStatementCount(int statementsIncluded) {
			mStatementsIncluded=statementsIncluded;
		}
		public int getStatementCount()
		{
			return mStatementsIncluded;
		}
		public void setOutputStatementEndPos(int index) {
			mEndStatementPos=index;
		}
		public void setOriginalStatementEndPos(int index) {
			mOriginalDocStatementEndPos=index;
		}
		public void setOutputStatementStartPos(int index) {
			mStartStatementPos=index;
		}
		public void setOriginalStatementStartPos(int index) {
			mOriginalDocStatementStartPos=index;
		}
		public int getOutputStatementEndPos() {
			return mEndStatementPos;
		}
		public int getOriginalStatementEndPos() {
			return mOriginalDocStatementEndPos;
		}
		public int getOutputStatementStartPos() {
			return mStartStatementPos;
		}
		public int getOriginalStatementStartPos() {
			return mOriginalDocStatementStartPos;
		}
		public void setOriginalDocEndPosition(int start) {
			mOriginalDocEndPosition=start;
		}
		public void setOriginalDocStartPosition(int start) {
			mOriginalDocStartPosition=start;
		}
		public int getOriginalDocStartPosition() {
			return mOriginalDocStartPosition;
		}
		public int getOriginalDocEndPosition() {
			return mOriginalDocEndPosition;
		}
		public int getStartBracePos() {
			return mStartBracePos;
		}
		public void setStartBracePos(int startBracePos) {
			mStartBracePos = startBracePos;
		}
		public int getEndBracePos() {
			return mEndBracePos;
		}
		public void setEndBracePos(int endBracePos) {
			mEndBracePos = endBracePos;
		}
		public boolean isBracesCurrentlyExist() {
			return mBracesCurrentlyExist;
		}
		public void setBracesCurrentlyExist(boolean bracesCurrentlyExist) {
			mBracesCurrentlyExist = bracesCurrentlyExist;
		}
		public boolean isHasComment() {
			return mHasComment;
		}
		public void setHasComment(boolean hasComment) {
			mHasComment = hasComment;
		}
		public int getStatementType() {
			return mStatementType;
		}
//		public void shiftPositions(int increment)
//		{
//			mEndBracePos+=increment;
//			mStartBracePos+=increment;
//			mEndStatementPos+=increment;
//			mStartStatementPos+=increment;
//		}

		public int getNextTokenType() {
			return mNextTokenType;
		}

		public void setNextTokenType(int tokenType) {
			mNextTokenType = tokenType;
		}
		
	}
	
	private static class EditItem
	{
		private int mLocation;
		public EditItem(int location)
		{
			mLocation=location;
		}
		public int getLocation() {
			return mLocation;
		}
	}
	
	private static class InsertItem extends EditItem
	{
		private String mData;
		private int mOriginalInsertLocation;
		public InsertItem(int location, String insertString, int originalPos)
		{
			super(location);
			mData=insertString;
			mOriginalInsertLocation=originalPos;
		}
		public String getData() {
			return mData;
		}
		public int getOriginalInsertLocation() {
			return mOriginalInsertLocation;
		}
//		public void setOriginalInsertLocation(int mOriginalInsertLocation) {
//			this.mOriginalInsertLocation = mOriginalInsertLocation;
//		}
		
	}

	private static class DeleteItem extends EditItem
	{
		private int mLength;
		private int mOriginalDeleteLocation;
		public DeleteItem(int location, int length, int originalLocation)
		{
			super(location);
			mLength=length;
			mOriginalDeleteLocation=originalLocation;
		}
		public int getLength() {
			return mLength;
		}
		public int getOriginalDeleteLocation() {
			return mOriginalDeleteLocation;
		}
	}

	public String getRemovedText() {
		return mRemovedTextAllPasses;
	}

	public void setNoCRBeforeReturn(boolean value) {
		mNoCRBeforeReturn = value;
	}

	public void setNoCRBeforeContinue(boolean value) {
		mNoCRBeforeContinue = value;
	}

	public void setNoCRBeforeThrow(boolean value) {
		mNoCRBeforeThrow = value;
	}

	public void setNoCRBeforeExpressions(boolean value) {
		mNoCRBeforeExpressions = value;
	}

	public void setNoCRBeforeBreak(boolean value) {
		mNoCRBeforeBreak = value;
	}

	public boolean isWrapAllArgumentsIfAny() {
		return mWrapAllArgumentsIfAny;
	}

	public void setWrapAllArgumentsIfAny(boolean wrapAllArgumentsIfAny) {
		mWrapAllArgumentsIfAny = wrapAllArgumentsIfAny;
	}

	public boolean isWrapAllParametersIfAny() {
		return mWrapAllParametersIfAny;
	}

	public void setWrapAllParametersIfAny(boolean wrapAllParametersIfAny) {
		mWrapAllParametersIfAny = wrapAllParametersIfAny;
	}

	public boolean isWrapFirstArgument() {
		return mWrapFirstArgument;
	}

	public void setWrapFirstArgument(boolean wrapFirstArgument) {
		mWrapFirstArgument = wrapFirstArgument;
	}

	public boolean isWrapFirstParameter() {
		return mWrapFirstParameter;
	}

	public void setWrapFirstParameter(boolean wrapFirstParameter) {
		mWrapFirstParameter = wrapFirstParameter;
	}

	public boolean isWrapAllObjectItemsIfAny() {
		return mWrapAllObjectItemsIfAny;
	}

	public void setWrapAllObjectItemsIfAny(boolean wrapAllObjectItemsIfAny) {
		mWrapAllObjectItemsIfAny = wrapAllObjectItemsIfAny;
	}

	public boolean isWrapFirstObjectItem() {
		return mWrapFirstObjectItem;
	}

	public void setWrapFirstObjectItem(boolean wrapFirstObjectItem) {
		mWrapFirstObjectItem = wrapFirstObjectItem;
	}

	public boolean isWrapObjectItemsAlignStart() {
		return mWrapObjectItemsAlignStart;
	}

	public void setWrapObjectItemsAlignStart(boolean wrapObjectItemsAlignStart) {
		mWrapObjectItemsAlignStart = wrapObjectItemsAlignStart;
	}

	public boolean isWrapAllArrayItemsIfAny() {
		return mWrapAllArrayItemsIfAny;
	}

	public void setWrapAllArrayItemsIfAny(boolean wrapAllArrayItemsIfAny) {
		mWrapAllArrayItemsIfAny = wrapAllArrayItemsIfAny;
	}

	public boolean isWrapFirstArrayItem() {
		return mWrapFirstArrayItem;
	}

	public void setWrapFirstArrayItem(boolean wrapFirstArrayItem) {
		mWrapFirstArrayItem = wrapFirstArrayItem;
	}

	public boolean isWrapArrayItemsAlignStart() {
		return mWrapArrayItemsAlignStart;
	}

	public void setWrapArrayItemsAlignStart(boolean wrapArrayItemsAlignStart) {
		mWrapArrayItemsAlignStart = wrapArrayItemsAlignStart;
	}

	public boolean isUseLineCommentWrapping() {
		return mUseLineCommentWrapping;
	}

	public void setUseLineCommentWrapping(boolean useLineCommentWrapping) {
		mUseLineCommentWrapping = useLineCommentWrapping;
	}

	public boolean isUseDocCommentWrapping() {
		return mUseDocCommentWrapping;
	}

	public void setUseDocCommentWrapping(boolean useDocCommentWrapping) {
		mUseDocCommentWrapping = useDocCommentWrapping;
	}

	public boolean isUseMLCommentWrapping() {
		return mUseMLCommentWrapping;
	}

	public void setUseMLCommentWrapping(boolean useMLCommentWrapping) {
		mUseMLCommentWrapping = useMLCommentWrapping;
	}

	public int getDocCommentHangingIndentTabs() {
		return mDocCommentHangingIndentTabs;
	}

	public void setDocCommentHangingIndentTabs(int docCommentHangingIndentTabs) {
		mDocCommentHangingIndentTabs = docCommentHangingIndentTabs;
	}

	public boolean isDocCommentCollapseLines() {
		return mDocCommentCollapseLines;
	}

	public void setDocCommentCollapseLines(boolean docCommentCollapseLines) {
		mDocCommentCollapseLines = docCommentCollapseLines;
	}

	public boolean isMLCommentCollapseLines() {
		return mMLCommentCollapseLines;
	}

	public void setMLCommentCollapseLines(boolean mLCommentCollapseLines) {
		mMLCommentCollapseLines = mLCommentCollapseLines;
	}

	public boolean isMLTextOnNewLines() {
		return mMLTextOnNewLines;
	}

	public void setMLTextOnNewLines(boolean mLTextOnNewLines) {
		mMLTextOnNewLines = mLTextOnNewLines;
	}

	public int getMLAsteriskMode() {
		return mMLAsteriskMode;
	}

	public void setMLAsteriskMode(int mLAsteriskMode) {
		mMLAsteriskMode = mLAsteriskMode;
	}

	public boolean isDocCommentKeepBlankLines() {
		return mDocCommentKeepBlankLines;
	}

	public void setDocCommentKeepBlankLines(boolean docCommentKeepBlankLines) {
		mDocCommentKeepBlankLines = docCommentKeepBlankLines;
	}

	public boolean isMLCommentKeepBlankLines() {
		return mMLCommentKeepBlankLines;
	}

	public void setMLCommentKeepBlankLines(boolean mLCommentKeepBlankLines) {
		mMLCommentKeepBlankLines = mLCommentKeepBlankLines;
	}

	public boolean isUseGNUBraceIndent() {
		return mUseGNUBraceIndent;
	}

	public void setUseGNUBraceIndent(boolean useGNUBraceIndent) {
		mUseGNUBraceIndent = useGNUBraceIndent;
	}

	public void setAdvancedSpacesAroundAssignmentInMetatags(int value) {
		mAdvancedSpacesAroundAssignmentInMetatags=value;
	}

	public void setUseAdvancedSpacesAroundAssignmentInMetatags(boolean value) {
		mUseAdvancedSpacesAroundAssignmentInMetatags=value;
	}
	
	public int getAdvancedSpacesAroundAssignmentInMetatags() {
		if (!mUseAdvancedSpacesAroundAssignmentInMetatags)
			return getSpacesAroundAssignment();
		return mAdvancedSpacesAroundAssignmentInMetatags;
	}

	public boolean isKeepSingleLineFunctions() {
		return mKeepSingleLineFunctions;
	}

	public void setKeepSingleLineFunctions(boolean keepSingleLineFunctions) {
		mKeepSingleLineFunctions = keepSingleLineFunctions;
	}
	
}
