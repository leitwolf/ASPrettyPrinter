package flexprettyprint.handlers;

public class WrapOptions {
	public static final int WRAP_NONE=1;
	public static final int WRAP_DONT_PROCESS=2;
//	public static final int WRAP_ITEMS_PER_LINE=4;
	public static final int WRAP_FORMAT_NO_CRs=4;
	public static final int WRAP_BY_COLUMN=8;
	public static final int WRAP_BY_COLUMN_ONLY_ADD_CRS=16;
	public static final int WRAP_BY_TAG=128;

	public static final int WRAP_STYLE_INDENT_NORMAL=1000;
	public static final int WRAP_STYLE_INDENT_TO_WRAP_ELEMENT=1001;
	
	private int mWrapType;
	private boolean mBeforeSeparator; //usually, separator is 'comma'
	private boolean mBeforeLogicalOperator; //And/Or
	private boolean mBeforeArithmeticOperator; 
	private boolean mBeforeAssignmentOperator; 
	private int mIndentStyle;
	
	public WrapOptions(int wrapType)
	{
		mWrapType=wrapType;
		mBeforeSeparator=false;
		mBeforeArithmeticOperator=false;
		mBeforeAssignmentOperator=false;
		mBeforeLogicalOperator=false;
		mIndentStyle=WRAP_STYLE_INDENT_NORMAL;
	}
	public int getWrapType() {
		return mWrapType;
	}
	public void setWrapType(int wrapType) {
		mWrapType = wrapType;
	}
//	public int getItemsPerLine() {
//		return mItemsPerLine;
//	}
//	public void setItemsPerLine(int itemsPerLine) {
//		mItemsPerLine = itemsPerLine;
//	}
	public int getIndentStyle() {
		return mIndentStyle;
	}
	public void setIndentStyle(int indentStyle) {
		mIndentStyle = indentStyle;
	}
	public boolean isBeforeSeparator() {
		return mBeforeSeparator;
	}
	public void setBeforeSeparator(boolean beforeSeparator) {
		mBeforeSeparator = beforeSeparator;
	}
	public boolean isBeforeLogicalOperator() {
		return mBeforeLogicalOperator;
	}
	public void setBeforeLogicalOperator(boolean beforeLogicalOperator) {
		mBeforeLogicalOperator = beforeLogicalOperator;
	}
	public boolean isBeforeArithmeticOperator() {
		return mBeforeArithmeticOperator;
	}
	public void setBeforeArithmeticOperator(boolean beforeArithmeticOperator) {
		mBeforeArithmeticOperator = beforeArithmeticOperator;
	}
	public boolean isBeforeAssignmentOperator() {
		return mBeforeAssignmentOperator;
	}
	public void setBeforeAssignmentOperator(boolean beforeAssignmentOperator) {
		mBeforeAssignmentOperator = beforeAssignmentOperator;
	}
}
