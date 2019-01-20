// $ANTLR 3.1.1 AS3_ex.g3 2011-08-05 17:41:16

package flexprettyprint.handlers;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

import heyesh.app.language.as3.parser.UnicodeUtil;
public class AS3_exLexer extends Lexer {
    public static final int LOR=91;
    public static final int SHR=83;
    public static final int FUNCTION=19;
    public static final int PACKAGE=31;
    public static final int INTERNAL=26;
    public static final int EXPONENT=139;
    public static final int LT=67;
    public static final int STAR=77;
    public static final int BACKSLASH_SEQUENCE=135;
    public static final int WHILE=47;
    public static final int MOD=79;
    public static final int SHL=82;
    public static final int CONST=9;
    public static final int MOD_ASSIGN=96;
    public static final int CASE=6;
    public static final int NEW=29;
    public static final int DO=13;
    public static final int NOT=88;
    public static final int IDENT_ASCII_START=143;
    public static final int HEX_NUMBER_LITERAL=137;
    public static final int EOF=-1;
    public static final int DIV_ASSIGN=95;
    public static final int BREAK=5;
    public static final int INC=80;
    public static final int RPAREN=62;
    public static final int FINAL=55;
    public static final int IMPORT=22;
    public static final int EOL=124;
    public static final int INCLUDE=53;
    public static final int XML_AT=112;
    public static final int RETURN=35;
    public static final int THIS=38;
    public static final int REGULAR_EXPR_FIRST_CHAR=133;
    public static final int XML_CDATA=145;
    public static final int XML_PI=146;
    public static final int IDENT_NAME_ASCII_START=141;
    public static final int GET=50;
    public static final int VAR=45;
    public static final int VOID=46;
    public static final int EACH=49;
    public static final int SUPER=36;
    public static final int EQ=71;
    public static final int SHU=84;
    public static final int RBRACK=64;
    public static final int ADD_ASSIGN=97;
    public static final int PRIVATE=32;
    public static final int STATIC=57;
    public static final int INV=89;
    public static final int SWITCH=37;
    public static final int NULL=30;
    public static final int LAND_ASSIGN=102;
    public static final int ELSE=14;
    public static final int NUMBER=118;
    public static final int DOUBLE_QUOTE_LITERAL=129;
    public static final int ELLIPSIS=107;
    public static final int NATIVE=28;
    public static final int WHITESPACE=125;
    public static final int UNDERSCORE=115;
    public static final int LCURLY=59;
    public static final int DELETE=12;
    public static final int TRY=42;
    public static final int NAMESPACE=52;
    public static final int REGULAR_EXPR_CHAR=134;
    public static final int TYPEOF=43;
    public static final int XML_LS_END=114;
    public static final int QUE=92;
    public static final int OR=86;
    public static final int IDENT_PART=136;
    public static final int GT=68;
    public static final int USE=44;
    public static final int DEC_NUMBER=138;
    public static final int CATCH=7;
    public static final int FALSE=16;
    public static final int LAND=90;
    public static final int XML_E_TEND=110;
    public static final int THROW=39;
    public static final int DYNAMIC=54;
    public static final int COMMENT_SINGLELINE=127;
    public static final int DOLLAR=116;
    public static final int PROTECTED=33;
    public static final int DEC=81;
    public static final int CLASS=8;
    public static final int LBRACK=63;
    public static final int REGULAR_EXPR_BODY=130;
    public static final int GTE=70;
    public static final int FOR=18;
    public static final int SHU_ASSIGN=101;
    public static final int SUB=76;
    public static final int AND=85;
    public static final int LTE=69;
    public static final int AND_ASSIGN=104;
    public static final int XML_LS_STD=113;
    public static final int LPAREN=61;
    public static final int IF=20;
    public static final int SHR_ASSIGN=100;
    public static final int AS=4;
    public static final int ESCAPE_SEQUENCE=123;
    public static final int UNICODE_ESCAPE=122;
    public static final int XML_COMMENT=144;
    public static final int SHL_ASSIGN=99;
    public static final int IN=23;
    public static final int DEC_NUMBER_LITERAL=140;
    public static final int IMPLEMENTS=21;
    public static final int CONTINUE=10;
    public static final int COMMA=66;
    public static final int IS=27;
    public static final int IDENTIFIER=142;
    public static final int XML_ELLIPSIS=108;
    public static final int XOR_ASSIGN=105;
    public static final int PLUS=75;
    public static final int DOT=65;
    public static final int WITH=48;
    public static final int XOR=87;
    public static final int TO=40;
    public static final int ALPHABET=117;
    public static final int NSAME=74;
    public static final int DEFAULT=11;
    public static final int REGULAR_EXPR_FLAG=131;
    public static final int HEX_DIGIT=119;
    public static final int SET=51;
    public static final int INSTANCEOF=24;
    public static final int XML_TEXT=147;
    public static final int SEMI=58;
    public static final int TRUE=41;
    public static final int SAME=73;
    public static final int COLON=93;
    public static final int OR_ASSIGN=106;
    public static final int NEQ=72;
    public static final int SINGLE_QUOTE_LITERAL=128;
    public static final int FINALLY=17;
    public static final int OVERRIDE=56;
    public static final int XML_NS_OP=111;
    public static final int RCURLY=60;
    public static final int ASSIGN=94;
    public static final int REGULAR_EXPR_LITERAL=132;
    public static final int INTERFACE=25;
    public static final int DIV=78;
    public static final int XML_TEND=109;
    public static final int CR=120;
    public static final int EXTENDS=15;
    public static final int PUBLIC=34;
    public static final int SUB_ASSIGN=98;
    public static final int COMMENT_MULTILINE=126;
    public static final int LOR_ASSIGN=103;
    public static final int LF=121;

        /**  */
        private Token lastDefaultCnlToken = null;
        
        // override
        public Token nextToken()
        {
            Token result = super.nextToken();
            if (result!=null && result.getChannel() != AS3_exParser.CHANNEL_WHITESPACE )
            {
                lastDefaultCnlToken = result;
            }
            return result;      
        }
        
    public void reset()
    {
    	super.reset(); // reset all recognizer state variables
    	if (input instanceof ANTLRStringStream)
    	{
    		((ANTLRStringStream)input).reset();
    	}
    }
        

    	//TODO: fix this so that regular expression embedded within xml text will work
        private final boolean isRegularExpression(){
            if(lastDefaultCnlToken!=null){
                switch(lastDefaultCnlToken.getType()){
                    case NULL :
                    case TRUE :
                    case FALSE:
                    case THIS :
                    case SUPER:
                    case IDENTIFIER:
                    case HEX_NUMBER_LITERAL:
                    case DEC_NUMBER_LITERAL:
                    case SINGLE_QUOTE_LITERAL:
                    case DOUBLE_QUOTE_LITERAL:
                    case RCURLY:
                    case RBRACK:
                    case RPAREN:
                    	//this is an attempt to not think something is a regular expression if it happens
                    	//to be part of a mathematical expression.
                        return false;
                    default:
                        break;
                }
            }

            System.out.println("start to predict if is a RegularExpression");
            // start to predict if the next is a regular expression
            int next = -1;
            int index=1;
            boolean success = false;
            if((next=input.LA(index)) != '/'){
                success = false;
                return success;
            }
            index++;
            // check the first regular character
            next=input.LA(index);
            if(next == '\r' || next == '\n' || 
            	next == '*' || //starts a comment
            	next == '/'  //if no regex content?
            	//|| next == '>' //I think the idea of failing on /> is to prevent conflicts with other tokens, but I think that is irrelevant since I've made this context sensitive.
             	){
                success = false;
                return success;
            }else if(next == '\\'){
                next=input.LA(index+1);
                if(next == '\r' || next == '\n'){
                    success=false;
                    return success;
                }
                // we omit the escape sequence \ u XXXX or \ x XX
                index++;
            }
            index++;
            // check the body of regular character
            while((next=input.LA(index))!=-1){
                //System.out.println("char["+index+"] = ("+(char)next+")");
                switch(next){
                    case '\r':
                    case '\n':
                        success = false;
                        return success;
                    case '\\':
                        next=input.LA(index+1);
                        if(next == '\r' || next == '\n'){
                            success=false;
                            return success;
                        }
                        // we omit the escape sequence \ u XXXX or \ x XX
                        index++;
                        break;
                    case '/':
                        success = true;
                        return success;
                }            
                index++;
            }
            return success;
        }
            
       /**
        * <pre> judge if is a XMLName </pre>
        * @param ch character
        * @return if is a XMLName return true
        */
        static final boolean isXMLText(int ch){
            System.out.println("isXMLText start");
            return (ch!='{'&&ch!='<'&&!(isUnicodeIdentifierPart(ch)));
        }
            
        /*---------------------------UNICODE_INDENTIFER START------------------------------------------*/    
        private static final boolean isUnicodeIdentifierPart(int ch){
            return ch=='$'||ch=='_'||UnicodeUtil.isUnicodeLetter(ch)||UnicodeUtil.isUnicodeDigit(ch)||UnicodeUtil.isUnicodeCombiningMark(ch)||UnicodeUtil.isUnicodeConnectorPunctuation(ch);
        }
        
        private final void consumeIdentifierUnicodeStart() throws RecognitionException, NoViableAltException{
            int ch = input.LA(1);
            if (UnicodeUtil.isUnicodeLetter(ch) || ch=='$' || ch=='_')
            {
                matchAny();
                do
                {
                    ch = input.LA(1);
                    if (isUnicodeIdentifierPart(ch))
                    {
                        mIDENT_PART();
                    }
                    else
                    {
                        return;
                    }
                }
                while (true);
            }
            else
            {
                throw new NoViableAltException();
            }
        }

        /*---------------------------UNICODE_INDENTIFER END------------------------------------------*/
        private final void debugMethod(String methodName,String text){
            System.out.println("recognized as <<"+methodName+">> text=("+text+")");
        }    


    // delegates
    // delegators

    public AS3_exLexer() {;} 
    public AS3_exLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public AS3_exLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "AS3_ex.g3"; }

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:158:4: ( 'as' )
            // AS3_ex.g3:158:6: 'as'
            {
            match("as"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AS"

    // $ANTLR start "BREAK"
    public final void mBREAK() throws RecognitionException {
        try {
            int _type = BREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:159:7: ( 'break' )
            // AS3_ex.g3:159:9: 'break'
            {
            match("break"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BREAK"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:160:6: ( 'case' )
            // AS3_ex.g3:160:8: 'case'
            {
            match("case"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "CATCH"
    public final void mCATCH() throws RecognitionException {
        try {
            int _type = CATCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:161:7: ( 'catch' )
            // AS3_ex.g3:161:9: 'catch'
            {
            match("catch"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CATCH"

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:162:7: ( 'class' )
            // AS3_ex.g3:162:9: 'class'
            {
            match("class"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "CONST"
    public final void mCONST() throws RecognitionException {
        try {
            int _type = CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:163:7: ( 'const' )
            // AS3_ex.g3:163:9: 'const'
            {
            match("const"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONST"

    // $ANTLR start "CONTINUE"
    public final void mCONTINUE() throws RecognitionException {
        try {
            int _type = CONTINUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:164:10: ( 'continue' )
            // AS3_ex.g3:164:12: 'continue'
            {
            match("continue"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONTINUE"

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:165:9: ( 'default' )
            // AS3_ex.g3:165:11: 'default'
            {
            match("default"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "DELETE"
    public final void mDELETE() throws RecognitionException {
        try {
            int _type = DELETE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:166:8: ( 'delete' )
            // AS3_ex.g3:166:10: 'delete'
            {
            match("delete"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DELETE"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:167:4: ( 'do' )
            // AS3_ex.g3:167:6: 'do'
            {
            match("do"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DO"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:168:6: ( 'else' )
            // AS3_ex.g3:168:8: 'else'
            {
            match("else"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "EXTENDS"
    public final void mEXTENDS() throws RecognitionException {
        try {
            int _type = EXTENDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:169:9: ( 'extends' )
            // AS3_ex.g3:169:11: 'extends'
            {
            match("extends"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXTENDS"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:170:7: ( 'false' )
            // AS3_ex.g3:170:9: 'false'
            {
            match("false"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "FINALLY"
    public final void mFINALLY() throws RecognitionException {
        try {
            int _type = FINALLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:171:9: ( 'finally' )
            // AS3_ex.g3:171:11: 'finally'
            {
            match("finally"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINALLY"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:172:5: ( 'for' )
            // AS3_ex.g3:172:7: 'for'
            {
            match("for"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "FUNCTION"
    public final void mFUNCTION() throws RecognitionException {
        try {
            int _type = FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:173:10: ( 'function' )
            // AS3_ex.g3:173:12: 'function'
            {
            match("function"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FUNCTION"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:174:4: ( 'if' )
            // AS3_ex.g3:174:6: 'if'
            {
            match("if"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "IMPLEMENTS"
    public final void mIMPLEMENTS() throws RecognitionException {
        try {
            int _type = IMPLEMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:175:12: ( 'implements' )
            // AS3_ex.g3:175:14: 'implements'
            {
            match("implements"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPLEMENTS"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:176:8: ( 'import' )
            // AS3_ex.g3:176:10: 'import'
            {
            match("import"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:177:4: ( 'in' )
            // AS3_ex.g3:177:6: 'in'
            {
            match("in"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "INSTANCEOF"
    public final void mINSTANCEOF() throws RecognitionException {
        try {
            int _type = INSTANCEOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:178:12: ( 'instanceof' )
            // AS3_ex.g3:178:14: 'instanceof'
            {
            match("instanceof"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INSTANCEOF"

    // $ANTLR start "INTERFACE"
    public final void mINTERFACE() throws RecognitionException {
        try {
            int _type = INTERFACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:179:11: ( 'interface' )
            // AS3_ex.g3:179:13: 'interface'
            {
            match("interface"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTERFACE"

    // $ANTLR start "INTERNAL"
    public final void mINTERNAL() throws RecognitionException {
        try {
            int _type = INTERNAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:180:10: ( 'internal' )
            // AS3_ex.g3:180:12: 'internal'
            {
            match("internal"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTERNAL"

    // $ANTLR start "IS"
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:181:4: ( 'is' )
            // AS3_ex.g3:181:6: 'is'
            {
            match("is"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "NATIVE"
    public final void mNATIVE() throws RecognitionException {
        try {
            int _type = NATIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:182:8: ( 'native' )
            // AS3_ex.g3:182:10: 'native'
            {
            match("native"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NATIVE"

    // $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:183:5: ( 'new' )
            // AS3_ex.g3:183:7: 'new'
            {
            match("new"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEW"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:184:6: ( 'null' )
            // AS3_ex.g3:184:8: 'null'
            {
            match("null"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "PACKAGE"
    public final void mPACKAGE() throws RecognitionException {
        try {
            int _type = PACKAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:185:9: ( 'package' )
            // AS3_ex.g3:185:11: 'package'
            {
            match("package"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PACKAGE"

    // $ANTLR start "PRIVATE"
    public final void mPRIVATE() throws RecognitionException {
        try {
            int _type = PRIVATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:186:9: ( 'private' )
            // AS3_ex.g3:186:11: 'private'
            {
            match("private"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRIVATE"

    // $ANTLR start "PROTECTED"
    public final void mPROTECTED() throws RecognitionException {
        try {
            int _type = PROTECTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:187:11: ( 'protected' )
            // AS3_ex.g3:187:13: 'protected'
            {
            match("protected"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PROTECTED"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:188:8: ( 'public' )
            // AS3_ex.g3:188:10: 'public'
            {
            match("public"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PUBLIC"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:189:8: ( 'return' )
            // AS3_ex.g3:189:10: 'return'
            {
            match("return"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "SUPER"
    public final void mSUPER() throws RecognitionException {
        try {
            int _type = SUPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:190:7: ( 'super' )
            // AS3_ex.g3:190:9: 'super'
            {
            match("super"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUPER"

    // $ANTLR start "SWITCH"
    public final void mSWITCH() throws RecognitionException {
        try {
            int _type = SWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:191:8: ( 'switch' )
            // AS3_ex.g3:191:10: 'switch'
            {
            match("switch"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SWITCH"

    // $ANTLR start "THIS"
    public final void mTHIS() throws RecognitionException {
        try {
            int _type = THIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:192:6: ( 'this' )
            // AS3_ex.g3:192:8: 'this'
            {
            match("this"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THIS"

    // $ANTLR start "THROW"
    public final void mTHROW() throws RecognitionException {
        try {
            int _type = THROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:193:7: ( 'throw' )
            // AS3_ex.g3:193:9: 'throw'
            {
            match("throw"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THROW"

    // $ANTLR start "TO"
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:194:4: ( 'to' )
            // AS3_ex.g3:194:6: 'to'
            {
            match("to"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TO"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:195:6: ( 'true' )
            // AS3_ex.g3:195:8: 'true'
            {
            match("true"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "TRY"
    public final void mTRY() throws RecognitionException {
        try {
            int _type = TRY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:196:5: ( 'try' )
            // AS3_ex.g3:196:7: 'try'
            {
            match("try"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRY"

    // $ANTLR start "TYPEOF"
    public final void mTYPEOF() throws RecognitionException {
        try {
            int _type = TYPEOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:197:8: ( 'typeof' )
            // AS3_ex.g3:197:10: 'typeof'
            {
            match("typeof"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TYPEOF"

    // $ANTLR start "USE"
    public final void mUSE() throws RecognitionException {
        try {
            int _type = USE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:198:5: ( 'use' )
            // AS3_ex.g3:198:7: 'use'
            {
            match("use"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "USE"

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:199:5: ( 'var' )
            // AS3_ex.g3:199:7: 'var'
            {
            match("var"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:200:6: ( 'void' )
            // AS3_ex.g3:200:8: 'void'
            {
            match("void"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:201:7: ( 'while' )
            // AS3_ex.g3:201:9: 'while'
            {
            match("while"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "WITH"
    public final void mWITH() throws RecognitionException {
        try {
            int _type = WITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:202:6: ( 'with' )
            // AS3_ex.g3:202:8: 'with'
            {
            match("with"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WITH"

    // $ANTLR start "EACH"
    public final void mEACH() throws RecognitionException {
        try {
            int _type = EACH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:203:6: ( 'each' )
            // AS3_ex.g3:203:8: 'each'
            {
            match("each"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EACH"

    // $ANTLR start "GET"
    public final void mGET() throws RecognitionException {
        try {
            int _type = GET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:204:5: ( 'get' )
            // AS3_ex.g3:204:7: 'get'
            {
            match("get"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GET"

    // $ANTLR start "SET"
    public final void mSET() throws RecognitionException {
        try {
            int _type = SET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:205:5: ( 'set' )
            // AS3_ex.g3:205:7: 'set'
            {
            match("set"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SET"

    // $ANTLR start "NAMESPACE"
    public final void mNAMESPACE() throws RecognitionException {
        try {
            int _type = NAMESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:206:11: ( 'namespace' )
            // AS3_ex.g3:206:13: 'namespace'
            {
            match("namespace"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NAMESPACE"

    // $ANTLR start "INCLUDE"
    public final void mINCLUDE() throws RecognitionException {
        try {
            int _type = INCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:207:9: ( 'include' )
            // AS3_ex.g3:207:11: 'include'
            {
            match("include"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INCLUDE"

    // $ANTLR start "DYNAMIC"
    public final void mDYNAMIC() throws RecognitionException {
        try {
            int _type = DYNAMIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:208:9: ( 'dynamic' )
            // AS3_ex.g3:208:11: 'dynamic'
            {
            match("dynamic"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DYNAMIC"

    // $ANTLR start "FINAL"
    public final void mFINAL() throws RecognitionException {
        try {
            int _type = FINAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:209:7: ( 'final' )
            // AS3_ex.g3:209:9: 'final'
            {
            match("final"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINAL"

    // $ANTLR start "OVERRIDE"
    public final void mOVERRIDE() throws RecognitionException {
        try {
            int _type = OVERRIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:210:10: ( 'override' )
            // AS3_ex.g3:210:12: 'override'
            {
            match("override"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OVERRIDE"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:211:8: ( 'static' )
            // AS3_ex.g3:211:10: 'static'
            {
            match("static"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATIC"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:212:6: ( ';' )
            // AS3_ex.g3:212:8: ';'
            {
            match(';'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:213:8: ( '{' )
            // AS3_ex.g3:213:10: '{'
            {
            match('{'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:214:8: ( '}' )
            // AS3_ex.g3:214:10: '}'
            {
            match('}'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:215:8: ( '(' )
            // AS3_ex.g3:215:10: '('
            {
            match('('); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:216:8: ( ')' )
            // AS3_ex.g3:216:10: ')'
            {
            match(')'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:217:8: ( '[' )
            // AS3_ex.g3:217:10: '['
            {
            match('['); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:218:8: ( ']' )
            // AS3_ex.g3:218:10: ']'
            {
            match(']'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:219:5: ( '.' )
            // AS3_ex.g3:219:7: '.'
            {
            match('.'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:220:7: ( ',' )
            // AS3_ex.g3:220:9: ','
            {
            match(','); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:221:4: ( '<' )
            // AS3_ex.g3:221:6: '<'
            {
            match('<'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:222:4: ( '>' )
            // AS3_ex.g3:222:6: '>'
            {
            match('>'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LTE"
    public final void mLTE() throws RecognitionException {
        try {
            int _type = LTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:223:5: ( '<=' )
            // AS3_ex.g3:223:7: '<='
            {
            match("<="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LTE"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:224:4: ( '==' )
            // AS3_ex.g3:224:6: '=='
            {
            match("=="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NEQ"
    public final void mNEQ() throws RecognitionException {
        try {
            int _type = NEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:225:5: ( '!=' )
            // AS3_ex.g3:225:7: '!='
            {
            match("!="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEQ"

    // $ANTLR start "SAME"
    public final void mSAME() throws RecognitionException {
        try {
            int _type = SAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:226:6: ( '===' )
            // AS3_ex.g3:226:8: '==='
            {
            match("==="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SAME"

    // $ANTLR start "NSAME"
    public final void mNSAME() throws RecognitionException {
        try {
            int _type = NSAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:227:7: ( '!==' )
            // AS3_ex.g3:227:9: '!=='
            {
            match("!=="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NSAME"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:228:6: ( '+' )
            // AS3_ex.g3:228:8: '+'
            {
            match('+'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "SUB"
    public final void mSUB() throws RecognitionException {
        try {
            int _type = SUB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:229:5: ( '-' )
            // AS3_ex.g3:229:7: '-'
            {
            match('-'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUB"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:230:6: ( '*' )
            // AS3_ex.g3:230:8: '*'
            {
            match('*'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:231:5: ( '/' )
            // AS3_ex.g3:231:7: '/'
            {
            match('/'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:232:5: ( '%' )
            // AS3_ex.g3:232:7: '%'
            {
            match('%'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "INC"
    public final void mINC() throws RecognitionException {
        try {
            int _type = INC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:233:5: ( '++' )
            // AS3_ex.g3:233:7: '++'
            {
            match("++"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INC"

    // $ANTLR start "DEC"
    public final void mDEC() throws RecognitionException {
        try {
            int _type = DEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:234:5: ( '--' )
            // AS3_ex.g3:234:7: '--'
            {
            match("--"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEC"

    // $ANTLR start "SHL"
    public final void mSHL() throws RecognitionException {
        try {
            int _type = SHL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:235:5: ( '<<' )
            // AS3_ex.g3:235:7: '<<'
            {
            match("<<"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHL"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:236:5: ( '&' )
            // AS3_ex.g3:236:7: '&'
            {
            match('&'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:237:4: ( '|' )
            // AS3_ex.g3:237:6: '|'
            {
            match('|'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
        try {
            int _type = XOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:238:5: ( '^' )
            // AS3_ex.g3:238:7: '^'
            {
            match('^'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XOR"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:239:5: ( '!' )
            // AS3_ex.g3:239:7: '!'
            {
            match('!'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "INV"
    public final void mINV() throws RecognitionException {
        try {
            int _type = INV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:240:5: ( '~' )
            // AS3_ex.g3:240:7: '~'
            {
            match('~'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INV"

    // $ANTLR start "LAND"
    public final void mLAND() throws RecognitionException {
        try {
            int _type = LAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:241:6: ( '&&' )
            // AS3_ex.g3:241:8: '&&'
            {
            match("&&"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LAND"

    // $ANTLR start "LOR"
    public final void mLOR() throws RecognitionException {
        try {
            int _type = LOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:242:5: ( '||' )
            // AS3_ex.g3:242:7: '||'
            {
            match("||"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOR"

    // $ANTLR start "QUE"
    public final void mQUE() throws RecognitionException {
        try {
            int _type = QUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:243:5: ( '?' )
            // AS3_ex.g3:243:7: '?'
            {
            match('?'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUE"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:244:7: ( ':' )
            // AS3_ex.g3:244:9: ':'
            {
            match(':'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:245:8: ( '=' )
            // AS3_ex.g3:245:10: '='
            {
            match('='); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "DIV_ASSIGN"
    public final void mDIV_ASSIGN() throws RecognitionException {
        try {
            int _type = DIV_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:246:12: ( '/=' )
            // AS3_ex.g3:246:14: '/='
            {
            match("/="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV_ASSIGN"

    // $ANTLR start "MOD_ASSIGN"
    public final void mMOD_ASSIGN() throws RecognitionException {
        try {
            int _type = MOD_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:247:12: ( '%=' )
            // AS3_ex.g3:247:14: '%='
            {
            match("%="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MOD_ASSIGN"

    // $ANTLR start "ADD_ASSIGN"
    public final void mADD_ASSIGN() throws RecognitionException {
        try {
            int _type = ADD_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:248:12: ( '+=' )
            // AS3_ex.g3:248:14: '+='
            {
            match("+="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ADD_ASSIGN"

    // $ANTLR start "SUB_ASSIGN"
    public final void mSUB_ASSIGN() throws RecognitionException {
        try {
            int _type = SUB_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:249:12: ( '-=' )
            // AS3_ex.g3:249:14: '-='
            {
            match("-="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUB_ASSIGN"

    // $ANTLR start "SHL_ASSIGN"
    public final void mSHL_ASSIGN() throws RecognitionException {
        try {
            int _type = SHL_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:250:12: ( '<<=' )
            // AS3_ex.g3:250:14: '<<='
            {
            match("<<="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHL_ASSIGN"

    // $ANTLR start "LAND_ASSIGN"
    public final void mLAND_ASSIGN() throws RecognitionException {
        try {
            int _type = LAND_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:251:13: ( '&&=' )
            // AS3_ex.g3:251:15: '&&='
            {
            match("&&="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LAND_ASSIGN"

    // $ANTLR start "LOR_ASSIGN"
    public final void mLOR_ASSIGN() throws RecognitionException {
        try {
            int _type = LOR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:252:12: ( '||=' )
            // AS3_ex.g3:252:14: '||='
            {
            match("||="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOR_ASSIGN"

    // $ANTLR start "AND_ASSIGN"
    public final void mAND_ASSIGN() throws RecognitionException {
        try {
            int _type = AND_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:253:12: ( '&=' )
            // AS3_ex.g3:253:14: '&='
            {
            match("&="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND_ASSIGN"

    // $ANTLR start "XOR_ASSIGN"
    public final void mXOR_ASSIGN() throws RecognitionException {
        try {
            int _type = XOR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:254:12: ( '^=' )
            // AS3_ex.g3:254:14: '^='
            {
            match("^="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XOR_ASSIGN"

    // $ANTLR start "OR_ASSIGN"
    public final void mOR_ASSIGN() throws RecognitionException {
        try {
            int _type = OR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:255:11: ( '|=' )
            // AS3_ex.g3:255:13: '|='
            {
            match("|="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR_ASSIGN"

    // $ANTLR start "ELLIPSIS"
    public final void mELLIPSIS() throws RecognitionException {
        try {
            int _type = ELLIPSIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:256:10: ( '...' )
            // AS3_ex.g3:256:12: '...'
            {
            match("..."); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELLIPSIS"

    // $ANTLR start "XML_ELLIPSIS"
    public final void mXML_ELLIPSIS() throws RecognitionException {
        try {
            int _type = XML_ELLIPSIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:257:14: ( '..' )
            // AS3_ex.g3:257:16: '..'
            {
            match(".."); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_ELLIPSIS"

    // $ANTLR start "XML_TEND"
    public final void mXML_TEND() throws RecognitionException {
        try {
            int _type = XML_TEND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:258:10: ( '/>' )
            // AS3_ex.g3:258:12: '/>'
            {
            match("/>"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_TEND"

    // $ANTLR start "XML_E_TEND"
    public final void mXML_E_TEND() throws RecognitionException {
        try {
            int _type = XML_E_TEND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:259:12: ( '</' )
            // AS3_ex.g3:259:14: '</'
            {
            match("</"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_E_TEND"

    // $ANTLR start "XML_NS_OP"
    public final void mXML_NS_OP() throws RecognitionException {
        try {
            int _type = XML_NS_OP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:260:11: ( '::' )
            // AS3_ex.g3:260:13: '::'
            {
            match("::"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_NS_OP"

    // $ANTLR start "XML_AT"
    public final void mXML_AT() throws RecognitionException {
        try {
            int _type = XML_AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:261:8: ( '@' )
            // AS3_ex.g3:261:10: '@'
            {
            match('@'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_AT"

    // $ANTLR start "XML_LS_STD"
    public final void mXML_LS_STD() throws RecognitionException {
        try {
            int _type = XML_LS_STD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:262:12: ( '<>' )
            // AS3_ex.g3:262:14: '<>'
            {
            match("<>"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_LS_STD"

    // $ANTLR start "XML_LS_END"
    public final void mXML_LS_END() throws RecognitionException {
        try {
            int _type = XML_LS_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:263:12: ( '</>' )
            // AS3_ex.g3:263:14: '</>'
            {
            match("</>"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_LS_END"

    // $ANTLR start "UNDERSCORE"
    public final void mUNDERSCORE() throws RecognitionException {
        try {
            // AS3_ex.g3:1074:22: ( '_' )
            // AS3_ex.g3:1074:24: '_'
            {
            match('_'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "UNDERSCORE"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            // AS3_ex.g3:1075:22: ( '$' )
            // AS3_ex.g3:1075:24: '$'
            {
            match('$'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "ALPHABET"
    public final void mALPHABET() throws RecognitionException {
        try {
            // AS3_ex.g3:1077:30: ( 'a' .. 'z' | 'A' .. 'Z' )
            // AS3_ex.g3:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "ALPHABET"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            // AS3_ex.g3:1079:30: ( '0' .. '9' )
            // AS3_ex.g3:1079:35: '0' .. '9'
            {
            matchRange('0','9'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // AS3_ex.g3:1081:30: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // AS3_ex.g3:1081:35: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "CR"
    public final void mCR() throws RecognitionException {
        try {
            // AS3_ex.g3:1083:30: ( '\\r' )
            // AS3_ex.g3:1083:35: '\\r'
            {
            match('\r'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "CR"

    // $ANTLR start "LF"
    public final void mLF() throws RecognitionException {
        try {
            // AS3_ex.g3:1085:30: ( '\\n' )
            // AS3_ex.g3:1085:35: '\\n'
            {
            match('\n'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "LF"

    // $ANTLR start "UNICODE_ESCAPE"
    public final void mUNICODE_ESCAPE() throws RecognitionException {
        try {
            // AS3_ex.g3:1087:30: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // AS3_ex.g3:1087:35: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); if (state.failed) return ;
            match('u'); if (state.failed) return ;
            mHEX_DIGIT(); if (state.failed) return ;
            mHEX_DIGIT(); if (state.failed) return ;
            mHEX_DIGIT(); if (state.failed) return ;
            mHEX_DIGIT(); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "UNICODE_ESCAPE"

    // $ANTLR start "ESCAPE_SEQUENCE"
    public final void mESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // AS3_ex.g3:1090:30: ( '\\\\' '\\\\' | '\\\\' ~ ( '\\\\' ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='\\') ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1=='\\') ) {
                    alt1=1;
                }
                else if ( ((LA1_1>='\u0000' && LA1_1<='[')||(LA1_1>=']' && LA1_1<='\uFFFF')) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // AS3_ex.g3:1093:31: '\\\\' '\\\\'
                    {
                    match('\\'); if (state.failed) return ;
                    match('\\'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1094:32: '\\\\' ~ ( '\\\\' )
                    {
                    match('\\'); if (state.failed) return ;
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();
                    state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_SEQUENCE"

    // $ANTLR start "EOL"
    public final void mEOL() throws RecognitionException {
        try {
            int _type = EOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1101:5: ( ( CR LF | CR | LF ) )
            // AS3_ex.g3:1101:10: ( CR LF | CR | LF )
            {
            // AS3_ex.g3:1101:10: ( CR LF | CR | LF )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\r') ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='\n') ) {
                    alt2=1;
                }
                else {
                    alt2=2;}
            }
            else if ( (LA2_0=='\n') ) {
                alt2=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // AS3_ex.g3:1101:11: CR LF
                    {
                    mCR(); if (state.failed) return ;
                    mLF(); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1101:19: CR
                    {
                    mCR(); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // AS3_ex.g3:1101:24: LF
                    {
                    mLF(); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               _channel = AS3_exParser.CHANNEL_EOL; 
            }

            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("EOL",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "EOL"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1108:5: ( ( ( '\\u0020' | '\\u0009' | '\\u000B' | '\\u00A0' | '\\u000C' ) | ( '\\u001C' .. '\\u001F' ) )+ )
            // AS3_ex.g3:1108:9: ( ( '\\u0020' | '\\u0009' | '\\u000B' | '\\u00A0' | '\\u000C' ) | ( '\\u001C' .. '\\u001F' ) )+
            {
            // AS3_ex.g3:1108:9: ( ( '\\u0020' | '\\u0009' | '\\u000B' | '\\u00A0' | '\\u000C' ) | ( '\\u001C' .. '\\u001F' ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\t'||(LA3_0>='\u000B' && LA3_0<='\f')||LA3_0==' '||LA3_0=='\u00A0') ) {
                    alt3=1;
                }
                else if ( ((LA3_0>='\u001C' && LA3_0<='\u001F')) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // AS3_ex.g3:1108:10: ( '\\u0020' | '\\u0009' | '\\u000B' | '\\u00A0' | '\\u000C' )
            	    {
            	    if ( input.LA(1)=='\t'||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||input.LA(1)==' '||input.LA(1)=='\u00A0' ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;
            	case 2 :
            	    // AS3_ex.g3:1108:57: ( '\\u001C' .. '\\u001F' )
            	    {
            	    // AS3_ex.g3:1108:57: ( '\\u001C' .. '\\u001F' )
            	    // AS3_ex.g3:1108:58: '\\u001C' .. '\\u001F'
            	    {
            	    matchRange('\u001C','\u001F'); if (state.failed) return ;

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            if ( state.backtracking==0 ) {
               _channel = AS3_exParser.CHANNEL_WHITESPACE; 
            }

            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("WHITESPACE",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "COMMENT_MULTILINE"
    public final void mCOMMENT_MULTILINE() throws RecognitionException {
        try {
            int _type = COMMENT_MULTILINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1115:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // AS3_ex.g3:1115:9: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); if (state.failed) return ;

            // AS3_ex.g3:1115:14: ( options {greedy=false; } : . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='*') ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1=='/') ) {
                        alt4=2;
                    }
                    else if ( ((LA4_1>='\u0000' && LA4_1<='.')||(LA4_1>='0' && LA4_1<='\uFFFF')) ) {
                        alt4=1;
                    }


                }
                else if ( ((LA4_0>='\u0000' && LA4_0<=')')||(LA4_0>='+' && LA4_0<='\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // AS3_ex.g3:1115:42: .
            	    {
            	    matchAny(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match("*/"); if (state.failed) return ;

            if ( state.backtracking==0 ) {
               _channel = AS3_exParser.CHANNEL_MLCOMMENT; 
            }

            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("COMMENT_MULTILINE",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "COMMENT_MULTILINE"

    // $ANTLR start "COMMENT_SINGLELINE"
    public final void mCOMMENT_SINGLELINE() throws RecognitionException {
        try {
            int _type = COMMENT_SINGLELINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1121:5: ( '//' (~ ( CR | LF ) )* ( CR LF | CR | LF ) )
            // AS3_ex.g3:1121:9: '//' (~ ( CR | LF ) )* ( CR LF | CR | LF )
            {
            match("//"); if (state.failed) return ;

            // AS3_ex.g3:1121:14: (~ ( CR | LF ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='\t')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // AS3_ex.g3:1121:14: ~ ( CR | LF )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // AS3_ex.g3:1121:28: ( CR LF | CR | LF )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1=='\n') ) {
                    alt6=1;
                }
                else {
                    alt6=2;}
            }
            else if ( (LA6_0=='\n') ) {
                alt6=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // AS3_ex.g3:1121:29: CR LF
                    {
                    mCR(); if (state.failed) return ;
                    mLF(); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1121:37: CR
                    {
                    mCR(); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // AS3_ex.g3:1121:42: LF
                    {
                    mLF(); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               _channel = AS3_exParser.CHANNEL_SLCOMMENT; 
            }

            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("COMMENT_SINGLELINE",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "COMMENT_SINGLELINE"

    // $ANTLR start "SINGLE_QUOTE_LITERAL"
    public final void mSINGLE_QUOTE_LITERAL() throws RecognitionException {
        try {
            int _type = SINGLE_QUOTE_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1129:5: ( '\\'' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\\'' ) )* '\\'' )
            // AS3_ex.g3:1129:9: '\\'' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\\'' ) )* '\\''
            {
            match('\''); if (state.failed) return ;
            // AS3_ex.g3:1129:14: ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\\'' ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\\') ) {
                    alt7=1;
                }
                else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // AS3_ex.g3:1129:16: ESCAPE_SEQUENCE
            	    {
            	    mESCAPE_SEQUENCE(); if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // AS3_ex.g3:1129:34: ~ ( '\\\\' | '\\'' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match('\''); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("SINGLE_QUOTE_LITERAL",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "SINGLE_QUOTE_LITERAL"

    // $ANTLR start "DOUBLE_QUOTE_LITERAL"
    public final void mDOUBLE_QUOTE_LITERAL() throws RecognitionException {
        try {
            int _type = DOUBLE_QUOTE_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1134:5: ( '\"' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )* '\"' )
            // AS3_ex.g3:1134:9: '\"' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); if (state.failed) return ;
            // AS3_ex.g3:1134:14: ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )*
            loop8:
            do {
                int alt8=3;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\\') ) {
                    alt8=1;
                }
                else if ( ((LA8_0>='\u0000' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='[')||(LA8_0>=']' && LA8_0<='\uFFFF')) ) {
                    alt8=2;
                }


                switch (alt8) {
            	case 1 :
            	    // AS3_ex.g3:1134:16: ESCAPE_SEQUENCE
            	    {
            	    mESCAPE_SEQUENCE(); if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // AS3_ex.g3:1134:34: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match('\"'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("DOUBLE_QUOTE_LITERAL",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_QUOTE_LITERAL"

    // $ANTLR start "REGULAR_EXPR_LITERAL"
    public final void mREGULAR_EXPR_LITERAL() throws RecognitionException {
        try {
            int _type = REGULAR_EXPR_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1144:5: ({...}? => DIV REGULAR_EXPR_BODY DIV ( REGULAR_EXPR_FLAG )* )
            // AS3_ex.g3:1144:9: {...}? => DIV REGULAR_EXPR_BODY DIV ( REGULAR_EXPR_FLAG )*
            {
            if ( !((isRegularExpression())) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "REGULAR_EXPR_LITERAL", "isRegularExpression()");
            }
            mDIV(); if (state.failed) return ;
            mREGULAR_EXPR_BODY(); if (state.failed) return ;
            mDIV(); if (state.failed) return ;
            // AS3_ex.g3:1144:63: ( REGULAR_EXPR_FLAG )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='$'||(LA9_0>='0' && LA9_0<='9')||(LA9_0>='A' && LA9_0<='Z')||LA9_0=='_'||(LA9_0>='a' && LA9_0<='z')) ) {
                    alt9=1;
                }
                else if ( ((isUnicodeIdentifierPart(input.LA(1)))) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // AS3_ex.g3:1144:63: REGULAR_EXPR_FLAG
            	    {
            	    mREGULAR_EXPR_FLAG(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("REGULAR_EXPR_LITERAL",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "REGULAR_EXPR_LITERAL"

    // $ANTLR start "REGULAR_EXPR_BODY"
    public final void mREGULAR_EXPR_BODY() throws RecognitionException {
        try {
            // AS3_ex.g3:1148:5: ( REGULAR_EXPR_FIRST_CHAR ( REGULAR_EXPR_CHAR )* )
            // AS3_ex.g3:1148:9: REGULAR_EXPR_FIRST_CHAR ( REGULAR_EXPR_CHAR )*
            {
            mREGULAR_EXPR_FIRST_CHAR(); if (state.failed) return ;
            // AS3_ex.g3:1148:33: ( REGULAR_EXPR_CHAR )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='.')||(LA10_0>='0' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // AS3_ex.g3:1148:33: REGULAR_EXPR_CHAR
            	    {
            	    mREGULAR_EXPR_CHAR(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "REGULAR_EXPR_BODY"

    // $ANTLR start "REGULAR_EXPR_FIRST_CHAR"
    public final void mREGULAR_EXPR_FIRST_CHAR() throws RecognitionException {
        try {
            // AS3_ex.g3:1153:5: (~ ( CR | LF | '*' | '\\\\' | '/' ) | BACKSLASH_SEQUENCE )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<=')')||(LA11_0>='+' && LA11_0<='.')||(LA11_0>='0' && LA11_0<='[')||(LA11_0>=']' && LA11_0<='\uFFFF')) ) {
                alt11=1;
            }
            else if ( (LA11_0=='\\') ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // AS3_ex.g3:1153:9: ~ ( CR | LF | '*' | '\\\\' | '/' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<=')')||(input.LA(1)>='+' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();
                    state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1154:9: BACKSLASH_SEQUENCE
                    {
                    mBACKSLASH_SEQUENCE(); if (state.failed) return ;

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "REGULAR_EXPR_FIRST_CHAR"

    // $ANTLR start "REGULAR_EXPR_CHAR"
    public final void mREGULAR_EXPR_CHAR() throws RecognitionException {
        try {
            // AS3_ex.g3:1158:5: (~ ( CR | LF | '\\\\' | '/' ) | BACKSLASH_SEQUENCE )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='.')||(LA12_0>='0' && LA12_0<='[')||(LA12_0>=']' && LA12_0<='\uFFFF')) ) {
                alt12=1;
            }
            else if ( (LA12_0=='\\') ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // AS3_ex.g3:1158:9: ~ ( CR | LF | '\\\\' | '/' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();
                    state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1159:9: BACKSLASH_SEQUENCE
                    {
                    mBACKSLASH_SEQUENCE(); if (state.failed) return ;

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "REGULAR_EXPR_CHAR"

    // $ANTLR start "BACKSLASH_SEQUENCE"
    public final void mBACKSLASH_SEQUENCE() throws RecognitionException {
        try {
            // AS3_ex.g3:1162:28: ( '\\\\' ~ ( CR | LF ) )
            // AS3_ex.g3:1162:33: '\\\\' ~ ( CR | LF )
            {
            match('\\'); if (state.failed) return ;
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "BACKSLASH_SEQUENCE"

    // $ANTLR start "REGULAR_EXPR_FLAG"
    public final void mREGULAR_EXPR_FLAG() throws RecognitionException {
        try {
            // AS3_ex.g3:1164:28: ( IDENT_PART )
            // AS3_ex.g3:1164:33: IDENT_PART
            {
            mIDENT_PART(); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "REGULAR_EXPR_FLAG"

    // $ANTLR start "HEX_NUMBER_LITERAL"
    public final void mHEX_NUMBER_LITERAL() throws RecognitionException {
        try {
            int _type = HEX_NUMBER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1174:5: ( '0' ( 'X' | 'x' ) ( HEX_DIGIT )+ )
            // AS3_ex.g3:1174:7: '0' ( 'X' | 'x' ) ( HEX_DIGIT )+
            {
            match('0'); if (state.failed) return ;
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // AS3_ex.g3:1174:21: ( HEX_DIGIT )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='0' && LA13_0<='9')||(LA13_0>='A' && LA13_0<='F')||(LA13_0>='a' && LA13_0<='f')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // AS3_ex.g3:1174:21: HEX_DIGIT
            	    {
            	    mHEX_DIGIT(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("HEX_NUMBER_LITERAL",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "HEX_NUMBER_LITERAL"

    // $ANTLR start "DEC_NUMBER"
    public final void mDEC_NUMBER() throws RecognitionException {
        try {
            // AS3_ex.g3:1176:30: ( ( NUMBER )+ '.' ( NUMBER )* | '.' ( NUMBER )+ | ( NUMBER )+ )
            int alt18=3;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // AS3_ex.g3:1176:33: ( NUMBER )+ '.' ( NUMBER )*
                    {
                    // AS3_ex.g3:1176:33: ( NUMBER )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // AS3_ex.g3:1176:33: NUMBER
                    	    {
                    	    mNUMBER(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);

                    match('.'); if (state.failed) return ;
                    // AS3_ex.g3:1176:45: ( NUMBER )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // AS3_ex.g3:1176:45: NUMBER
                    	    {
                    	    mNUMBER(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1176:55: '.' ( NUMBER )+
                    {
                    match('.'); if (state.failed) return ;
                    // AS3_ex.g3:1176:59: ( NUMBER )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // AS3_ex.g3:1176:59: NUMBER
                    	    {
                    	    mNUMBER(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt16 >= 1 ) break loop16;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(16, input);
                                throw eee;
                        }
                        cnt16++;
                    } while (true);


                    }
                    break;
                case 3 :
                    // AS3_ex.g3:1176:69: ( NUMBER )+
                    {
                    // AS3_ex.g3:1176:69: ( NUMBER )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // AS3_ex.g3:1176:69: NUMBER
                    	    {
                    	    mNUMBER(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "DEC_NUMBER"

    // $ANTLR start "DEC_NUMBER_LITERAL"
    public final void mDEC_NUMBER_LITERAL() throws RecognitionException {
        try {
            int _type = DEC_NUMBER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1182:5: ( DEC_NUMBER ( EXPONENT )? )
            // AS3_ex.g3:1182:8: DEC_NUMBER ( EXPONENT )?
            {
            mDEC_NUMBER(); if (state.failed) return ;
            // AS3_ex.g3:1182:19: ( EXPONENT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='E'||LA19_0=='e') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // AS3_ex.g3:1182:19: EXPONENT
                    {
                    mEXPONENT(); if (state.failed) return ;

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("DEC_NUMBER_LITERAL",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "DEC_NUMBER_LITERAL"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // AS3_ex.g3:1184:30: ( ( 'e' | 'E' ) ( '+' | '-' )? ( NUMBER )+ )
            // AS3_ex.g3:1184:32: ( 'e' | 'E' ) ( '+' | '-' )? ( NUMBER )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // AS3_ex.g3:1184:42: ( '+' | '-' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='+'||LA20_0=='-') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // AS3_ex.g3:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // AS3_ex.g3:1184:53: ( NUMBER )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='0' && LA21_0<='9')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // AS3_ex.g3:1184:53: NUMBER
            	    {
            	    mNUMBER(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1192:5: ( IDENT_NAME_ASCII_START | ( UNICODE_ESCAPE )+ | )
            int alt23=3;
            switch ( input.LA(1) ) {
            case '$':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '_':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                {
                alt23=1;
                }
                break;
            case '\\':
                {
                alt23=2;
                }
                break;
            default:
                alt23=3;}

            switch (alt23) {
                case 1 :
                    // AS3_ex.g3:1192:9: IDENT_NAME_ASCII_START
                    {
                    mIDENT_NAME_ASCII_START(); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1193:9: ( UNICODE_ESCAPE )+
                    {
                    // AS3_ex.g3:1193:9: ( UNICODE_ESCAPE )+
                    int cnt22=0;
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0=='\\') ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // AS3_ex.g3:1193:9: UNICODE_ESCAPE
                    	    {
                    	    mUNICODE_ESCAPE(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt22 >= 1 ) break loop22;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(22, input);
                                throw eee;
                        }
                        cnt22++;
                    } while (true);


                    }
                    break;
                case 3 :
                    // AS3_ex.g3:1194:9: 
                    {
                    if ( state.backtracking==0 ) {
                      consumeIdentifierUnicodeStart();
                    }

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("Identifier",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "IDENT_NAME_ASCII_START"
    public final void mIDENT_NAME_ASCII_START() throws RecognitionException {
        try {
            // AS3_ex.g3:1197:35: ( IDENT_ASCII_START ( IDENT_PART )* )
            // AS3_ex.g3:1197:37: IDENT_ASCII_START ( IDENT_PART )*
            {
            mIDENT_ASCII_START(); if (state.failed) return ;
            // AS3_ex.g3:1197:55: ( IDENT_PART )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='$'||(LA24_0>='0' && LA24_0<='9')||(LA24_0>='A' && LA24_0<='Z')||LA24_0=='_'||(LA24_0>='a' && LA24_0<='z')) ) {
                    alt24=1;
                }
                else if ( ((isUnicodeIdentifierPart(input.LA(1)))) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // AS3_ex.g3:1197:55: IDENT_PART
            	    {
            	    mIDENT_PART(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "IDENT_NAME_ASCII_START"

    // $ANTLR start "IDENT_ASCII_START"
    public final void mIDENT_ASCII_START() throws RecognitionException {
        try {
            // AS3_ex.g3:1199:35: ( ALPHABET | DOLLAR | UNDERSCORE )
            // AS3_ex.g3:
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "IDENT_ASCII_START"

    // $ANTLR start "IDENT_PART"
    public final void mIDENT_PART() throws RecognitionException {
        try {
            // AS3_ex.g3:1205:5: ( ( IDENT_ASCII_START )=> IDENT_ASCII_START | NUMBER | {...}?)
            int alt25=3;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='$'||(LA25_0>='A' && LA25_0<='Z')||LA25_0=='_'||(LA25_0>='a' && LA25_0<='z')) && (synpred1_AS3_ex())) {
                alt25=1;
            }
            else if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                alt25=2;
            }
            else {
                alt25=3;}
            switch (alt25) {
                case 1 :
                    // AS3_ex.g3:1205:9: ( IDENT_ASCII_START )=> IDENT_ASCII_START
                    {
                    mIDENT_ASCII_START(); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1206:9: NUMBER
                    {
                    mNUMBER(); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // AS3_ex.g3:1207:9: {...}?
                    {
                    if ( !((isUnicodeIdentifierPart(input.LA(1)))) ) {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        throw new FailedPredicateException(input, "IDENT_PART", "isUnicodeIdentifierPart(input.LA(1))");
                    }
                    if ( state.backtracking==0 ) {
                      matchAny();
                    }

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "IDENT_PART"

    // $ANTLR start "XML_COMMENT"
    public final void mXML_COMMENT() throws RecognitionException {
        try {
            int _type = XML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1214:5: ( '<!--' ( options {greedy=false; } : . )* '-->' )
            // AS3_ex.g3:1214:9: '<!--' ( options {greedy=false; } : . )* '-->'
            {
            match("<!--"); if (state.failed) return ;

            // AS3_ex.g3:1214:16: ( options {greedy=false; } : . )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0=='-') ) {
                    int LA26_1 = input.LA(2);

                    if ( (LA26_1=='-') ) {
                        int LA26_3 = input.LA(3);

                        if ( (LA26_3=='>') ) {
                            alt26=2;
                        }
                        else if ( ((LA26_3>='\u0000' && LA26_3<='=')||(LA26_3>='?' && LA26_3<='\uFFFF')) ) {
                            alt26=1;
                        }


                    }
                    else if ( ((LA26_1>='\u0000' && LA26_1<=',')||(LA26_1>='.' && LA26_1<='\uFFFF')) ) {
                        alt26=1;
                    }


                }
                else if ( ((LA26_0>='\u0000' && LA26_0<=',')||(LA26_0>='.' && LA26_0<='\uFFFF')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // AS3_ex.g3:1214:44: .
            	    {
            	    matchAny(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            match("-->"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("XML_COMMENT",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "XML_COMMENT"

    // $ANTLR start "XML_CDATA"
    public final void mXML_CDATA() throws RecognitionException {
        try {
            int _type = XML_CDATA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1220:5: ( '<![CDATA' ( options {greedy=false; } : . )* ']]>' )
            // AS3_ex.g3:1220:9: '<![CDATA' ( options {greedy=false; } : . )* ']]>'
            {
            match("<![CDATA"); if (state.failed) return ;

            // AS3_ex.g3:1220:20: ( options {greedy=false; } : . )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==']') ) {
                    int LA27_1 = input.LA(2);

                    if ( (LA27_1==']') ) {
                        int LA27_3 = input.LA(3);

                        if ( (LA27_3=='>') ) {
                            alt27=2;
                        }
                        else if ( ((LA27_3>='\u0000' && LA27_3<='=')||(LA27_3>='?' && LA27_3<='\uFFFF')) ) {
                            alt27=1;
                        }


                    }
                    else if ( ((LA27_1>='\u0000' && LA27_1<='\\')||(LA27_1>='^' && LA27_1<='\uFFFF')) ) {
                        alt27=1;
                    }


                }
                else if ( ((LA27_0>='\u0000' && LA27_0<='\\')||(LA27_0>='^' && LA27_0<='\uFFFF')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // AS3_ex.g3:1220:48: .
            	    {
            	    matchAny(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            match("]]>"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("XML_CDATA",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "XML_CDATA"

    // $ANTLR start "XML_PI"
    public final void mXML_PI() throws RecognitionException {
        try {
            int _type = XML_PI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1226:5: ( '<?' ( options {greedy=false; } : . )* '?>' )
            // AS3_ex.g3:1226:9: '<?' ( options {greedy=false; } : . )* '?>'
            {
            match("<?"); if (state.failed) return ;

            // AS3_ex.g3:1226:14: ( options {greedy=false; } : . )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0=='?') ) {
                    int LA28_1 = input.LA(2);

                    if ( (LA28_1=='>') ) {
                        alt28=2;
                    }
                    else if ( ((LA28_1>='\u0000' && LA28_1<='=')||(LA28_1>='?' && LA28_1<='\uFFFF')) ) {
                        alt28=1;
                    }


                }
                else if ( ((LA28_0>='\u0000' && LA28_0<='>')||(LA28_0>='@' && LA28_0<='\uFFFF')) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // AS3_ex.g3:1226:42: .
            	    {
            	    matchAny(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            match("?>"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("XML_PI",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "XML_PI"

    // $ANTLR start "XML_TEXT"
    public final void mXML_TEXT() throws RecognitionException {
        try {
            int _type = XML_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // AS3_ex.g3:1233:5: ( '\\u0020' .. '\\u003b' | '\\u003d' .. '\\u007a' | '\\u007c' .. '\\u007e' | {...}?)
            int alt29=4;
            switch ( input.LA(1) ) {
            case ' ':
            case '!':
            case '\"':
            case '#':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ':':
            case ';':
                {
                alt29=1;
                }
                break;
            case '=':
            case '>':
            case '?':
            case '@':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                {
                alt29=2;
                }
                break;
            case '|':
            case '}':
            case '~':
                {
                alt29=3;
                }
                break;
            default:
                alt29=4;}

            switch (alt29) {
                case 1 :
                    // AS3_ex.g3:1233:7: '\\u0020' .. '\\u003b'
                    {
                    matchRange(' ',';'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // AS3_ex.g3:1234:7: '\\u003d' .. '\\u007a'
                    {
                    matchRange('=','z'); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // AS3_ex.g3:1235:7: '\\u007c' .. '\\u007e'
                    {
                    matchRange('|','~'); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // AS3_ex.g3:1236:7: {...}?
                    {
                    if ( !((isXMLText(input.LA(1)))) ) {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        throw new FailedPredicateException(input, "XML_TEXT", "isXMLText(input.LA(1))");
                    }
                    if ( state.backtracking==0 ) {
                      matchAny();
                    }

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                  debugMethod("XMLText",getText());

            }    }
        finally {
        }
    }
    // $ANTLR end "XML_TEXT"

    public void mTokens() throws RecognitionException {
        // AS3_ex.g3:1:8: ( AS | BREAK | CASE | CATCH | CLASS | CONST | CONTINUE | DEFAULT | DELETE | DO | ELSE | EXTENDS | FALSE | FINALLY | FOR | FUNCTION | IF | IMPLEMENTS | IMPORT | IN | INSTANCEOF | INTERFACE | INTERNAL | IS | NATIVE | NEW | NULL | PACKAGE | PRIVATE | PROTECTED | PUBLIC | RETURN | SUPER | SWITCH | THIS | THROW | TO | TRUE | TRY | TYPEOF | USE | VAR | VOID | WHILE | WITH | EACH | GET | SET | NAMESPACE | INCLUDE | DYNAMIC | FINAL | OVERRIDE | STATIC | SEMI | LCURLY | RCURLY | LPAREN | RPAREN | LBRACK | RBRACK | DOT | COMMA | LT | GT | LTE | EQ | NEQ | SAME | NSAME | PLUS | SUB | STAR | DIV | MOD | INC | DEC | SHL | AND | OR | XOR | NOT | INV | LAND | LOR | QUE | COLON | ASSIGN | DIV_ASSIGN | MOD_ASSIGN | ADD_ASSIGN | SUB_ASSIGN | SHL_ASSIGN | LAND_ASSIGN | LOR_ASSIGN | AND_ASSIGN | XOR_ASSIGN | OR_ASSIGN | ELLIPSIS | XML_ELLIPSIS | XML_TEND | XML_E_TEND | XML_NS_OP | XML_AT | XML_LS_STD | XML_LS_END | EOL | WHITESPACE | COMMENT_MULTILINE | COMMENT_SINGLELINE | SINGLE_QUOTE_LITERAL | DOUBLE_QUOTE_LITERAL | REGULAR_EXPR_LITERAL | HEX_NUMBER_LITERAL | DEC_NUMBER_LITERAL | IDENTIFIER | XML_COMMENT | XML_CDATA | XML_PI | XML_TEXT )
        int alt30=120;
        alt30 = dfa30.predict(input);
        switch (alt30) {
            case 1 :
                // AS3_ex.g3:1:10: AS
                {
                mAS(); if (state.failed) return ;

                }
                break;
            case 2 :
                // AS3_ex.g3:1:13: BREAK
                {
                mBREAK(); if (state.failed) return ;

                }
                break;
            case 3 :
                // AS3_ex.g3:1:19: CASE
                {
                mCASE(); if (state.failed) return ;

                }
                break;
            case 4 :
                // AS3_ex.g3:1:24: CATCH
                {
                mCATCH(); if (state.failed) return ;

                }
                break;
            case 5 :
                // AS3_ex.g3:1:30: CLASS
                {
                mCLASS(); if (state.failed) return ;

                }
                break;
            case 6 :
                // AS3_ex.g3:1:36: CONST
                {
                mCONST(); if (state.failed) return ;

                }
                break;
            case 7 :
                // AS3_ex.g3:1:42: CONTINUE
                {
                mCONTINUE(); if (state.failed) return ;

                }
                break;
            case 8 :
                // AS3_ex.g3:1:51: DEFAULT
                {
                mDEFAULT(); if (state.failed) return ;

                }
                break;
            case 9 :
                // AS3_ex.g3:1:59: DELETE
                {
                mDELETE(); if (state.failed) return ;

                }
                break;
            case 10 :
                // AS3_ex.g3:1:66: DO
                {
                mDO(); if (state.failed) return ;

                }
                break;
            case 11 :
                // AS3_ex.g3:1:69: ELSE
                {
                mELSE(); if (state.failed) return ;

                }
                break;
            case 12 :
                // AS3_ex.g3:1:74: EXTENDS
                {
                mEXTENDS(); if (state.failed) return ;

                }
                break;
            case 13 :
                // AS3_ex.g3:1:82: FALSE
                {
                mFALSE(); if (state.failed) return ;

                }
                break;
            case 14 :
                // AS3_ex.g3:1:88: FINALLY
                {
                mFINALLY(); if (state.failed) return ;

                }
                break;
            case 15 :
                // AS3_ex.g3:1:96: FOR
                {
                mFOR(); if (state.failed) return ;

                }
                break;
            case 16 :
                // AS3_ex.g3:1:100: FUNCTION
                {
                mFUNCTION(); if (state.failed) return ;

                }
                break;
            case 17 :
                // AS3_ex.g3:1:109: IF
                {
                mIF(); if (state.failed) return ;

                }
                break;
            case 18 :
                // AS3_ex.g3:1:112: IMPLEMENTS
                {
                mIMPLEMENTS(); if (state.failed) return ;

                }
                break;
            case 19 :
                // AS3_ex.g3:1:123: IMPORT
                {
                mIMPORT(); if (state.failed) return ;

                }
                break;
            case 20 :
                // AS3_ex.g3:1:130: IN
                {
                mIN(); if (state.failed) return ;

                }
                break;
            case 21 :
                // AS3_ex.g3:1:133: INSTANCEOF
                {
                mINSTANCEOF(); if (state.failed) return ;

                }
                break;
            case 22 :
                // AS3_ex.g3:1:144: INTERFACE
                {
                mINTERFACE(); if (state.failed) return ;

                }
                break;
            case 23 :
                // AS3_ex.g3:1:154: INTERNAL
                {
                mINTERNAL(); if (state.failed) return ;

                }
                break;
            case 24 :
                // AS3_ex.g3:1:163: IS
                {
                mIS(); if (state.failed) return ;

                }
                break;
            case 25 :
                // AS3_ex.g3:1:166: NATIVE
                {
                mNATIVE(); if (state.failed) return ;

                }
                break;
            case 26 :
                // AS3_ex.g3:1:173: NEW
                {
                mNEW(); if (state.failed) return ;

                }
                break;
            case 27 :
                // AS3_ex.g3:1:177: NULL
                {
                mNULL(); if (state.failed) return ;

                }
                break;
            case 28 :
                // AS3_ex.g3:1:182: PACKAGE
                {
                mPACKAGE(); if (state.failed) return ;

                }
                break;
            case 29 :
                // AS3_ex.g3:1:190: PRIVATE
                {
                mPRIVATE(); if (state.failed) return ;

                }
                break;
            case 30 :
                // AS3_ex.g3:1:198: PROTECTED
                {
                mPROTECTED(); if (state.failed) return ;

                }
                break;
            case 31 :
                // AS3_ex.g3:1:208: PUBLIC
                {
                mPUBLIC(); if (state.failed) return ;

                }
                break;
            case 32 :
                // AS3_ex.g3:1:215: RETURN
                {
                mRETURN(); if (state.failed) return ;

                }
                break;
            case 33 :
                // AS3_ex.g3:1:222: SUPER
                {
                mSUPER(); if (state.failed) return ;

                }
                break;
            case 34 :
                // AS3_ex.g3:1:228: SWITCH
                {
                mSWITCH(); if (state.failed) return ;

                }
                break;
            case 35 :
                // AS3_ex.g3:1:235: THIS
                {
                mTHIS(); if (state.failed) return ;

                }
                break;
            case 36 :
                // AS3_ex.g3:1:240: THROW
                {
                mTHROW(); if (state.failed) return ;

                }
                break;
            case 37 :
                // AS3_ex.g3:1:246: TO
                {
                mTO(); if (state.failed) return ;

                }
                break;
            case 38 :
                // AS3_ex.g3:1:249: TRUE
                {
                mTRUE(); if (state.failed) return ;

                }
                break;
            case 39 :
                // AS3_ex.g3:1:254: TRY
                {
                mTRY(); if (state.failed) return ;

                }
                break;
            case 40 :
                // AS3_ex.g3:1:258: TYPEOF
                {
                mTYPEOF(); if (state.failed) return ;

                }
                break;
            case 41 :
                // AS3_ex.g3:1:265: USE
                {
                mUSE(); if (state.failed) return ;

                }
                break;
            case 42 :
                // AS3_ex.g3:1:269: VAR
                {
                mVAR(); if (state.failed) return ;

                }
                break;
            case 43 :
                // AS3_ex.g3:1:273: VOID
                {
                mVOID(); if (state.failed) return ;

                }
                break;
            case 44 :
                // AS3_ex.g3:1:278: WHILE
                {
                mWHILE(); if (state.failed) return ;

                }
                break;
            case 45 :
                // AS3_ex.g3:1:284: WITH
                {
                mWITH(); if (state.failed) return ;

                }
                break;
            case 46 :
                // AS3_ex.g3:1:289: EACH
                {
                mEACH(); if (state.failed) return ;

                }
                break;
            case 47 :
                // AS3_ex.g3:1:294: GET
                {
                mGET(); if (state.failed) return ;

                }
                break;
            case 48 :
                // AS3_ex.g3:1:298: SET
                {
                mSET(); if (state.failed) return ;

                }
                break;
            case 49 :
                // AS3_ex.g3:1:302: NAMESPACE
                {
                mNAMESPACE(); if (state.failed) return ;

                }
                break;
            case 50 :
                // AS3_ex.g3:1:312: INCLUDE
                {
                mINCLUDE(); if (state.failed) return ;

                }
                break;
            case 51 :
                // AS3_ex.g3:1:320: DYNAMIC
                {
                mDYNAMIC(); if (state.failed) return ;

                }
                break;
            case 52 :
                // AS3_ex.g3:1:328: FINAL
                {
                mFINAL(); if (state.failed) return ;

                }
                break;
            case 53 :
                // AS3_ex.g3:1:334: OVERRIDE
                {
                mOVERRIDE(); if (state.failed) return ;

                }
                break;
            case 54 :
                // AS3_ex.g3:1:343: STATIC
                {
                mSTATIC(); if (state.failed) return ;

                }
                break;
            case 55 :
                // AS3_ex.g3:1:350: SEMI
                {
                mSEMI(); if (state.failed) return ;

                }
                break;
            case 56 :
                // AS3_ex.g3:1:355: LCURLY
                {
                mLCURLY(); if (state.failed) return ;

                }
                break;
            case 57 :
                // AS3_ex.g3:1:362: RCURLY
                {
                mRCURLY(); if (state.failed) return ;

                }
                break;
            case 58 :
                // AS3_ex.g3:1:369: LPAREN
                {
                mLPAREN(); if (state.failed) return ;

                }
                break;
            case 59 :
                // AS3_ex.g3:1:376: RPAREN
                {
                mRPAREN(); if (state.failed) return ;

                }
                break;
            case 60 :
                // AS3_ex.g3:1:383: LBRACK
                {
                mLBRACK(); if (state.failed) return ;

                }
                break;
            case 61 :
                // AS3_ex.g3:1:390: RBRACK
                {
                mRBRACK(); if (state.failed) return ;

                }
                break;
            case 62 :
                // AS3_ex.g3:1:397: DOT
                {
                mDOT(); if (state.failed) return ;

                }
                break;
            case 63 :
                // AS3_ex.g3:1:401: COMMA
                {
                mCOMMA(); if (state.failed) return ;

                }
                break;
            case 64 :
                // AS3_ex.g3:1:407: LT
                {
                mLT(); if (state.failed) return ;

                }
                break;
            case 65 :
                // AS3_ex.g3:1:410: GT
                {
                mGT(); if (state.failed) return ;

                }
                break;
            case 66 :
                // AS3_ex.g3:1:413: LTE
                {
                mLTE(); if (state.failed) return ;

                }
                break;
            case 67 :
                // AS3_ex.g3:1:417: EQ
                {
                mEQ(); if (state.failed) return ;

                }
                break;
            case 68 :
                // AS3_ex.g3:1:420: NEQ
                {
                mNEQ(); if (state.failed) return ;

                }
                break;
            case 69 :
                // AS3_ex.g3:1:424: SAME
                {
                mSAME(); if (state.failed) return ;

                }
                break;
            case 70 :
                // AS3_ex.g3:1:429: NSAME
                {
                mNSAME(); if (state.failed) return ;

                }
                break;
            case 71 :
                // AS3_ex.g3:1:435: PLUS
                {
                mPLUS(); if (state.failed) return ;

                }
                break;
            case 72 :
                // AS3_ex.g3:1:440: SUB
                {
                mSUB(); if (state.failed) return ;

                }
                break;
            case 73 :
                // AS3_ex.g3:1:444: STAR
                {
                mSTAR(); if (state.failed) return ;

                }
                break;
            case 74 :
                // AS3_ex.g3:1:449: DIV
                {
                mDIV(); if (state.failed) return ;

                }
                break;
            case 75 :
                // AS3_ex.g3:1:453: MOD
                {
                mMOD(); if (state.failed) return ;

                }
                break;
            case 76 :
                // AS3_ex.g3:1:457: INC
                {
                mINC(); if (state.failed) return ;

                }
                break;
            case 77 :
                // AS3_ex.g3:1:461: DEC
                {
                mDEC(); if (state.failed) return ;

                }
                break;
            case 78 :
                // AS3_ex.g3:1:465: SHL
                {
                mSHL(); if (state.failed) return ;

                }
                break;
            case 79 :
                // AS3_ex.g3:1:469: AND
                {
                mAND(); if (state.failed) return ;

                }
                break;
            case 80 :
                // AS3_ex.g3:1:473: OR
                {
                mOR(); if (state.failed) return ;

                }
                break;
            case 81 :
                // AS3_ex.g3:1:476: XOR
                {
                mXOR(); if (state.failed) return ;

                }
                break;
            case 82 :
                // AS3_ex.g3:1:480: NOT
                {
                mNOT(); if (state.failed) return ;

                }
                break;
            case 83 :
                // AS3_ex.g3:1:484: INV
                {
                mINV(); if (state.failed) return ;

                }
                break;
            case 84 :
                // AS3_ex.g3:1:488: LAND
                {
                mLAND(); if (state.failed) return ;

                }
                break;
            case 85 :
                // AS3_ex.g3:1:493: LOR
                {
                mLOR(); if (state.failed) return ;

                }
                break;
            case 86 :
                // AS3_ex.g3:1:497: QUE
                {
                mQUE(); if (state.failed) return ;

                }
                break;
            case 87 :
                // AS3_ex.g3:1:501: COLON
                {
                mCOLON(); if (state.failed) return ;

                }
                break;
            case 88 :
                // AS3_ex.g3:1:507: ASSIGN
                {
                mASSIGN(); if (state.failed) return ;

                }
                break;
            case 89 :
                // AS3_ex.g3:1:514: DIV_ASSIGN
                {
                mDIV_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 90 :
                // AS3_ex.g3:1:525: MOD_ASSIGN
                {
                mMOD_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 91 :
                // AS3_ex.g3:1:536: ADD_ASSIGN
                {
                mADD_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 92 :
                // AS3_ex.g3:1:547: SUB_ASSIGN
                {
                mSUB_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 93 :
                // AS3_ex.g3:1:558: SHL_ASSIGN
                {
                mSHL_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 94 :
                // AS3_ex.g3:1:569: LAND_ASSIGN
                {
                mLAND_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 95 :
                // AS3_ex.g3:1:581: LOR_ASSIGN
                {
                mLOR_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 96 :
                // AS3_ex.g3:1:592: AND_ASSIGN
                {
                mAND_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 97 :
                // AS3_ex.g3:1:603: XOR_ASSIGN
                {
                mXOR_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 98 :
                // AS3_ex.g3:1:614: OR_ASSIGN
                {
                mOR_ASSIGN(); if (state.failed) return ;

                }
                break;
            case 99 :
                // AS3_ex.g3:1:624: ELLIPSIS
                {
                mELLIPSIS(); if (state.failed) return ;

                }
                break;
            case 100 :
                // AS3_ex.g3:1:633: XML_ELLIPSIS
                {
                mXML_ELLIPSIS(); if (state.failed) return ;

                }
                break;
            case 101 :
                // AS3_ex.g3:1:646: XML_TEND
                {
                mXML_TEND(); if (state.failed) return ;

                }
                break;
            case 102 :
                // AS3_ex.g3:1:655: XML_E_TEND
                {
                mXML_E_TEND(); if (state.failed) return ;

                }
                break;
            case 103 :
                // AS3_ex.g3:1:666: XML_NS_OP
                {
                mXML_NS_OP(); if (state.failed) return ;

                }
                break;
            case 104 :
                // AS3_ex.g3:1:676: XML_AT
                {
                mXML_AT(); if (state.failed) return ;

                }
                break;
            case 105 :
                // AS3_ex.g3:1:683: XML_LS_STD
                {
                mXML_LS_STD(); if (state.failed) return ;

                }
                break;
            case 106 :
                // AS3_ex.g3:1:694: XML_LS_END
                {
                mXML_LS_END(); if (state.failed) return ;

                }
                break;
            case 107 :
                // AS3_ex.g3:1:705: EOL
                {
                mEOL(); if (state.failed) return ;

                }
                break;
            case 108 :
                // AS3_ex.g3:1:709: WHITESPACE
                {
                mWHITESPACE(); if (state.failed) return ;

                }
                break;
            case 109 :
                // AS3_ex.g3:1:720: COMMENT_MULTILINE
                {
                mCOMMENT_MULTILINE(); if (state.failed) return ;

                }
                break;
            case 110 :
                // AS3_ex.g3:1:738: COMMENT_SINGLELINE
                {
                mCOMMENT_SINGLELINE(); if (state.failed) return ;

                }
                break;
            case 111 :
                // AS3_ex.g3:1:757: SINGLE_QUOTE_LITERAL
                {
                mSINGLE_QUOTE_LITERAL(); if (state.failed) return ;

                }
                break;
            case 112 :
                // AS3_ex.g3:1:778: DOUBLE_QUOTE_LITERAL
                {
                mDOUBLE_QUOTE_LITERAL(); if (state.failed) return ;

                }
                break;
            case 113 :
                // AS3_ex.g3:1:799: REGULAR_EXPR_LITERAL
                {
                mREGULAR_EXPR_LITERAL(); if (state.failed) return ;

                }
                break;
            case 114 :
                // AS3_ex.g3:1:820: HEX_NUMBER_LITERAL
                {
                mHEX_NUMBER_LITERAL(); if (state.failed) return ;

                }
                break;
            case 115 :
                // AS3_ex.g3:1:839: DEC_NUMBER_LITERAL
                {
                mDEC_NUMBER_LITERAL(); if (state.failed) return ;

                }
                break;
            case 116 :
                // AS3_ex.g3:1:858: IDENTIFIER
                {
                mIDENTIFIER(); if (state.failed) return ;

                }
                break;
            case 117 :
                // AS3_ex.g3:1:869: XML_COMMENT
                {
                mXML_COMMENT(); if (state.failed) return ;

                }
                break;
            case 118 :
                // AS3_ex.g3:1:881: XML_CDATA
                {
                mXML_CDATA(); if (state.failed) return ;

                }
                break;
            case 119 :
                // AS3_ex.g3:1:891: XML_PI
                {
                mXML_PI(); if (state.failed) return ;

                }
                break;
            case 120 :
                // AS3_ex.g3:1:898: XML_TEXT
                {
                mXML_TEXT(); if (state.failed) return ;

                }
                break;

        }

    }

    // $ANTLR start synpred1_AS3_ex
    public final void synpred1_AS3_ex_fragment() throws RecognitionException {   
        // AS3_ex.g3:1205:9: ( IDENT_ASCII_START )
        // AS3_ex.g3:1205:10: IDENT_ASCII_START
        {
        mIDENT_ASCII_START(); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_AS3_ex

    public final boolean synpred1_AS3_ex() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_AS3_ex_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA18 dfa18 = new DFA18(this);
    protected DFA30 dfa30 = new DFA30(this);
    static final String DFA18_eotS =
        "\1\uffff\1\3\3\uffff";
    static final String DFA18_eofS =
        "\5\uffff";
    static final String DFA18_minS =
        "\2\56\3\uffff";
    static final String DFA18_maxS =
        "\2\71\3\uffff";
    static final String DFA18_acceptS =
        "\2\uffff\1\2\1\3\1\1";
    static final String DFA18_specialS =
        "\5\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1",
            "",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "1176:10: fragment DEC_NUMBER : ( ( NUMBER )+ '.' ( NUMBER )* | '.' ( NUMBER )+ | ( NUMBER )+ );";
        }
    }
    static final String DFA30_eotS =
        "\1\64\21\70\7\uffff\1\150\1\uffff\1\161\1\uffff\1\164\1\166\1\171"+
        "\1\174\1\uffff\1\u0082\1\u0085\1\u0088\1\u008b\1\u008d\2\uffff\1"+
        "\u0091\4\uffff\2\66\1\151\2\uffff\1\66\3\uffff\1\u0096\1\uffff\5"+
        "\70\1\u009e\10\70\1\u00a7\1\70\1\u00ac\1\u00ad\14\70\1\u00bd\11"+
        "\70\6\uffff\1\u00c9\4\uffff\1\u00cb\1\u00cd\5\uffff\1\u00d1\1\uffff"+
        "\1\u00d3\10\uffff\1\u00d4\1\u00d5\6\uffff\1\u00d7\2\uffff\1\u00d9"+
        "\15\uffff\7\70\1\uffff\6\70\1\u00e8\1\70\1\uffff\4\70\2\uffff\2"+
        "\70\1\u00f1\10\70\1\u00fa\3\70\1\uffff\1\70\1\u00ff\1\70\1\u0101"+
        "\1\u0102\3\70\1\u0106\1\70\22\uffff\1\70\1\u0109\7\70\1\u0111\1"+
        "\70\1\u0113\2\70\1\uffff\10\70\1\uffff\1\u011e\7\70\1\uffff\1\70"+
        "\1\u0127\1\70\1\u0129\1\uffff\1\70\2\uffff\1\u012b\1\70\1\u012d"+
        "\1\uffff\1\70\1\u012f\1\uffff\1\u0130\1\u0131\1\u0132\4\70\1\uffff"+
        "\1\70\1\uffff\1\u0138\1\u013a\10\70\1\uffff\5\70\1\u0149\2\70\1"+
        "\uffff\1\u014c\1\uffff\1\70\1\uffff\1\u014e\1\uffff\1\70\4\uffff"+
        "\2\70\1\u0152\2\70\1\uffff\1\70\1\uffff\2\70\1\u0158\4\70\1\u015d"+
        "\4\70\1\u0162\1\u0163\1\uffff\1\u0164\1\u0165\1\uffff\1\u0166\1"+
        "\uffff\2\70\1\u0169\1\uffff\1\u016a\1\u016b\1\u016c\2\70\1\uffff"+
        "\3\70\1\u0172\1\uffff\1\70\1\u0174\1\u0175\1\70\5\uffff\1\70\1\u0178"+
        "\4\uffff\1\u0179\3\70\1\u017d\1\uffff\1\70\2\uffff\1\70\1\u0180"+
        "\2\uffff\2\70\1\u0183\1\uffff\1\u0184\1\u0185\1\uffff\1\u0186\1"+
        "\u0187\5\uffff";
    static final String DFA30_eofS =
        "\u0188\uffff";
    static final String DFA30_minS =
        "\1\11\1\163\1\162\1\141\1\145\2\141\1\146\2\141\2\145\1\150\1\163"+
        "\1\141\1\150\1\145\1\166\7\uffff\1\56\1\uffff\1\41\1\uffff\2\75"+
        "\1\53\1\55\1\uffff\1\0\1\75\1\46\2\75\2\uffff\1\72\4\uffff\2\0\1"+
        "\130\2\uffff\1\165\1\0\2\uffff\1\44\1\uffff\1\145\1\163\1\141\1"+
        "\156\1\146\1\44\1\156\1\163\1\164\1\143\1\154\1\156\1\162\1\156"+
        "\1\44\1\160\2\44\1\155\1\167\1\154\1\143\1\151\1\142\1\164\1\160"+
        "\1\151\1\164\1\141\1\151\1\44\1\165\1\160\1\145\1\162\2\151\2\164"+
        "\1\145\6\uffff\1\56\4\uffff\1\75\1\76\1\uffff\1\55\3\uffff\1\75"+
        "\1\uffff\1\75\10\uffff\2\0\6\uffff\1\75\2\uffff\1\75\15\uffff\1"+
        "\141\1\145\1\143\2\163\1\141\1\145\1\uffff\1\141\2\145\1\150\1\163"+
        "\1\141\1\44\1\143\1\uffff\1\154\1\164\1\145\1\154\2\uffff\1\151"+
        "\1\145\1\44\1\154\1\153\1\166\1\164\1\154\1\165\1\145\1\164\1\44"+
        "\1\164\1\163\1\157\1\uffff\1\145\1\44\1\145\2\44\1\144\1\154\1\150"+
        "\1\44\1\162\22\uffff\1\153\1\44\1\150\1\163\1\164\1\151\1\165\1"+
        "\164\1\155\1\44\1\156\1\44\1\145\1\154\1\uffff\1\164\1\145\1\162"+
        "\1\141\1\162\1\165\1\166\1\163\1\uffff\1\44\2\141\1\145\1\151\2"+
        "\162\1\143\1\uffff\1\151\1\44\1\167\1\44\1\uffff\1\157\2\uffff\1"+
        "\44\1\145\1\44\1\uffff\1\162\1\44\1\uffff\3\44\1\156\1\154\1\145"+
        "\1\151\1\uffff\1\144\1\uffff\2\44\1\151\1\155\1\164\1\156\1\146"+
        "\1\144\1\145\1\160\1\uffff\1\147\1\164\2\143\1\156\1\44\1\150\1"+
        "\143\1\uffff\1\44\1\uffff\1\146\1\uffff\1\44\1\uffff\1\151\4\uffff"+
        "\1\165\1\164\1\44\1\143\1\163\1\uffff\1\171\1\uffff\1\157\1\145"+
        "\1\44\1\143\2\141\1\145\1\44\1\141\2\145\1\164\2\44\1\uffff\2\44"+
        "\1\uffff\1\44\1\uffff\1\144\1\145\1\44\1\uffff\3\44\2\156\1\uffff"+
        "\1\145\1\143\1\154\1\44\1\uffff\1\143\2\44\1\145\5\uffff\1\145\1"+
        "\44\4\uffff\1\44\1\164\1\157\1\145\1\44\1\uffff\1\145\2\uffff\1"+
        "\144\1\44\2\uffff\1\163\1\146\1\44\1\uffff\2\44\1\uffff\2\44\5\uffff";
    static final String DFA30_maxS =
        "\1\u00a0\1\163\1\162\1\157\1\171\1\170\1\165\1\163\2\165\1\145"+
        "\1\167\1\171\1\163\1\157\1\151\1\145\1\166\7\uffff\1\71\1\uffff"+
        "\1\77\1\uffff\4\75\1\uffff\1\uffff\2\75\1\174\1\75\2\uffff\1\72"+
        "\4\uffff\2\uffff\1\170\2\uffff\1\165\1\0\2\uffff\1\172\1\uffff\1"+
        "\145\1\164\1\141\1\156\1\154\1\172\1\156\1\163\1\164\1\143\1\154"+
        "\1\156\1\162\1\156\1\172\1\160\2\172\1\164\1\167\1\154\1\143\1\157"+
        "\1\142\1\164\1\160\1\151\1\164\1\141\1\162\1\172\1\171\1\160\1\145"+
        "\1\162\2\151\2\164\1\145\6\uffff\1\56\4\uffff\1\75\1\76\1\uffff"+
        "\1\133\3\uffff\1\75\1\uffff\1\75\10\uffff\2\uffff\6\uffff\1\75\2"+
        "\uffff\1\75\15\uffff\1\141\1\145\1\143\1\163\1\164\1\141\1\145\1"+
        "\uffff\1\141\2\145\1\150\1\163\1\141\1\172\1\143\1\uffff\1\157\1"+
        "\164\1\145\1\154\2\uffff\1\151\1\145\1\172\1\154\1\153\1\166\1\164"+
        "\1\154\1\165\1\145\1\164\1\172\1\164\1\163\1\157\1\uffff\1\145\1"+
        "\172\1\145\2\172\1\144\1\154\1\150\1\172\1\162\22\uffff\1\153\1"+
        "\172\1\150\1\163\1\164\1\151\1\165\1\164\1\155\1\172\1\156\1\172"+
        "\1\145\1\154\1\uffff\1\164\1\145\1\162\1\141\1\162\1\165\1\166\1"+
        "\163\1\uffff\1\172\2\141\1\145\1\151\2\162\1\143\1\uffff\1\151\1"+
        "\172\1\167\1\172\1\uffff\1\157\2\uffff\1\172\1\145\1\172\1\uffff"+
        "\1\162\1\172\1\uffff\3\172\1\156\1\154\1\145\1\151\1\uffff\1\144"+
        "\1\uffff\2\172\1\151\1\155\1\164\2\156\1\144\1\145\1\160\1\uffff"+
        "\1\147\1\164\2\143\1\156\1\172\1\150\1\143\1\uffff\1\172\1\uffff"+
        "\1\146\1\uffff\1\172\1\uffff\1\151\4\uffff\1\165\1\164\1\172\1\143"+
        "\1\163\1\uffff\1\171\1\uffff\1\157\1\145\1\172\1\143\2\141\1\145"+
        "\1\172\1\141\2\145\1\164\2\172\1\uffff\2\172\1\uffff\1\172\1\uffff"+
        "\1\144\1\145\1\172\1\uffff\3\172\2\156\1\uffff\1\145\1\143\1\154"+
        "\1\172\1\uffff\1\143\2\172\1\145\5\uffff\1\145\1\172\4\uffff\1\172"+
        "\1\164\1\157\1\145\1\172\1\uffff\1\145\2\uffff\1\144\1\172\2\uffff"+
        "\1\163\1\146\1\172\1\uffff\2\172\1\uffff\2\172\5\uffff";
    static final String DFA30_acceptS =
        "\22\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\uffff\1\77\1\uffff"+
        "\1\101\4\uffff\1\111\5\uffff\1\123\1\126\1\uffff\1\150\1\153\2\154"+
        "\3\uffff\1\163\1\164\2\uffff\1\164\1\170\1\uffff\1\164\50\uffff"+
        "\1\67\1\71\1\72\1\73\1\74\1\75\1\uffff\1\76\1\163\1\77\1\102\2\uffff"+
        "\1\151\1\uffff\1\167\1\100\1\101\1\uffff\1\130\1\uffff\1\122\1\114"+
        "\1\133\1\107\1\115\1\134\1\110\1\111\2\uffff\1\155\1\156\1\112\1"+
        "\161\1\132\1\113\1\uffff\1\140\1\117\1\uffff\1\142\1\120\1\141\1"+
        "\121\1\123\1\126\1\147\1\127\1\150\1\157\1\160\1\162\1\1\7\uffff"+
        "\1\12\10\uffff\1\21\4\uffff\1\24\1\30\17\uffff\1\45\12\uffff\1\143"+
        "\1\144\1\135\1\116\1\152\1\146\1\165\1\166\1\105\1\103\1\106\1\104"+
        "\1\131\1\145\1\136\1\124\1\137\1\125\16\uffff\1\17\10\uffff\1\32"+
        "\10\uffff\1\60\4\uffff\1\47\1\uffff\1\51\1\52\3\uffff\1\57\2\uffff"+
        "\1\3\7\uffff\1\13\1\uffff\1\56\12\uffff\1\33\10\uffff\1\43\1\uffff"+
        "\1\46\1\uffff\1\53\1\uffff\1\55\1\uffff\1\2\1\4\1\5\1\6\5\uffff"+
        "\1\15\1\uffff\1\64\16\uffff\1\41\2\uffff\1\44\1\uffff\1\54\3\uffff"+
        "\1\11\5\uffff\1\23\4\uffff\1\31\4\uffff\1\37\1\40\1\42\1\66\1\50"+
        "\2\uffff\1\10\1\63\1\14\1\16\5\uffff\1\62\1\uffff\1\34\1\35\2\uffff"+
        "\1\7\1\20\3\uffff\1\27\2\uffff\1\65\2\uffff\1\26\1\61\1\36\1\22"+
        "\1\25";
    static final String DFA30_specialS =
        "\42\uffff\1\2\13\uffff\1\3\1\4\4\uffff\1\5\111\uffff\1\0\1\1\u0108"+
        "\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\55\1\53\2\55\1\53\16\uffff\4\55\1\54\1\36\1\57\1\66\1\62"+
            "\1\43\1\44\1\56\1\25\1\26\1\41\1\37\1\32\1\40\1\31\1\42\1\60"+
            "\11\61\1\51\1\22\1\33\1\35\1\34\1\50\1\52\32\65\1\27\1\63\1"+
            "\30\1\46\1\65\1\66\1\1\1\2\1\3\1\4\1\5\1\6\1\20\1\65\1\7\4\65"+
            "\1\10\1\21\1\11\1\65\1\12\1\13\1\14\1\15\1\16\1\17\3\65\1\23"+
            "\1\45\1\24\1\47\41\uffff\1\55",
            "\1\67",
            "\1\71",
            "\1\72\12\uffff\1\73\2\uffff\1\74",
            "\1\75\11\uffff\1\76\11\uffff\1\77",
            "\1\102\12\uffff\1\100\13\uffff\1\101",
            "\1\103\7\uffff\1\104\5\uffff\1\105\5\uffff\1\106",
            "\1\107\6\uffff\1\110\1\111\4\uffff\1\112",
            "\1\113\3\uffff\1\114\17\uffff\1\115",
            "\1\116\20\uffff\1\117\2\uffff\1\120",
            "\1\121",
            "\1\124\16\uffff\1\125\1\122\1\uffff\1\123",
            "\1\126\6\uffff\1\127\2\uffff\1\130\6\uffff\1\131",
            "\1\132",
            "\1\133\15\uffff\1\134",
            "\1\135\1\136",
            "\1\137",
            "\1\140",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\147\1\uffff\12\151",
            "",
            "\1\157\15\uffff\1\155\14\uffff\1\154\1\153\1\156\1\160",
            "",
            "\1\163",
            "\1\165",
            "\1\167\21\uffff\1\170",
            "\1\172\17\uffff\1\173",
            "",
            "\12\u0083\1\uffff\2\u0083\1\uffff\34\u0083\1\u0080\4\u0083"+
            "\1\u0081\15\u0083\1\176\1\177\uffc1\u0083",
            "\1\u0084",
            "\1\u0086\26\uffff\1\u0087",
            "\1\u008a\76\uffff\1\u0089",
            "\1\u008c",
            "",
            "",
            "\1\u0090",
            "",
            "",
            "",
            "",
            "\0\u0093",
            "\0\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "",
            "",
            "\1\70",
            "\1\uffff",
            "",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u0097",
            "\1\u0098\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c\5\uffff\1\u009d",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u00a8",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\2"+
            "\70\1\u00ab\17\70\1\u00a9\1\u00aa\6\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u00af\6\uffff\1\u00ae",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3\5\uffff\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb\10\uffff\1\u00bc",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u00be\3\uffff\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00c8",
            "",
            "",
            "",
            "",
            "\1\u00ca",
            "\1\u00cc",
            "",
            "\1\u00ce\55\uffff\1\u00cf",
            "",
            "",
            "",
            "\1\u00d0",
            "",
            "\1\u00d2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\u0083\1\uffff\2\u0083\1\uffff\ufff2\u0083",
            "\12\u0083\1\uffff\2\u0083\1\uffff\ufff2\u0083",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00d6",
            "",
            "",
            "\1\u00d8",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u00e9",
            "",
            "\1\u00ea\2\uffff\1\u00eb",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "",
            "",
            "\1\u00ef",
            "\1\u00f0",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "",
            "\1\u00fe",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0100",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0107",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0108",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0112",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0114",
            "\1\u0115",
            "",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\1\u011c",
            "\1\u011d",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "",
            "\1\u0126",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0128",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u012a",
            "",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u012c",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u012e",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0133",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "",
            "\1\u0137",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\13"+
            "\70\1\u0139\16\70",
            "\1\u013b",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f\7\uffff\1\u0140",
            "\1\u0141",
            "\1\u0142",
            "\1\u0143",
            "",
            "\1\u0144",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u014a",
            "\1\u014b",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u014d",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u014f",
            "",
            "",
            "",
            "",
            "\1\u0150",
            "\1\u0151",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0153",
            "\1\u0154",
            "",
            "\1\u0155",
            "",
            "\1\u0156",
            "\1\u0157",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0159",
            "\1\u015a",
            "\1\u015b",
            "\1\u015c",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u015e",
            "\1\u015f",
            "\1\u0160",
            "\1\u0161",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u0167",
            "\1\u0168",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u016d",
            "\1\u016e",
            "",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u0173",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u0176",
            "",
            "",
            "",
            "",
            "",
            "\1\u0177",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "",
            "",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\u017a",
            "\1\u017b",
            "\1\u017c",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\u017e",
            "",
            "",
            "\1\u017f",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "",
            "\1\u0181",
            "\1\u0182",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "\1\70\13\uffff\12\70\7\uffff\32\70\4\uffff\1\70\1\uffff\32"+
            "\70",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AS | BREAK | CASE | CATCH | CLASS | CONST | CONTINUE | DEFAULT | DELETE | DO | ELSE | EXTENDS | FALSE | FINALLY | FOR | FUNCTION | IF | IMPLEMENTS | IMPORT | IN | INSTANCEOF | INTERFACE | INTERNAL | IS | NATIVE | NEW | NULL | PACKAGE | PRIVATE | PROTECTED | PUBLIC | RETURN | SUPER | SWITCH | THIS | THROW | TO | TRUE | TRY | TYPEOF | USE | VAR | VOID | WHILE | WITH | EACH | GET | SET | NAMESPACE | INCLUDE | DYNAMIC | FINAL | OVERRIDE | STATIC | SEMI | LCURLY | RCURLY | LPAREN | RPAREN | LBRACK | RBRACK | DOT | COMMA | LT | GT | LTE | EQ | NEQ | SAME | NSAME | PLUS | SUB | STAR | DIV | MOD | INC | DEC | SHL | AND | OR | XOR | NOT | INV | LAND | LOR | QUE | COLON | ASSIGN | DIV_ASSIGN | MOD_ASSIGN | ADD_ASSIGN | SUB_ASSIGN | SHL_ASSIGN | LAND_ASSIGN | LOR_ASSIGN | AND_ASSIGN | XOR_ASSIGN | OR_ASSIGN | ELLIPSIS | XML_ELLIPSIS | XML_TEND | XML_E_TEND | XML_NS_OP | XML_AT | XML_LS_STD | XML_LS_END | EOL | WHITESPACE | COMMENT_MULTILINE | COMMENT_SINGLELINE | SINGLE_QUOTE_LITERAL | DOUBLE_QUOTE_LITERAL | REGULAR_EXPR_LITERAL | HEX_NUMBER_LITERAL | DEC_NUMBER_LITERAL | IDENTIFIER | XML_COMMENT | XML_CDATA | XML_PI | XML_TEXT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_126 = input.LA(1);

                         
                        int index30_126 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA30_126>='\u0000' && LA30_126<='\t')||(LA30_126>='\u000B' && LA30_126<='\f')||(LA30_126>='\u000E' && LA30_126<='\uFFFF')) && ((isRegularExpression()))) {s = 131;}

                        else s = 212;

                         
                        input.seek(index30_126);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA30_127 = input.LA(1);

                         
                        int index30_127 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA30_127>='\u0000' && LA30_127<='\t')||(LA30_127>='\u000B' && LA30_127<='\f')||(LA30_127>='\u000E' && LA30_127<='\uFFFF')) && ((isRegularExpression()))) {s = 131;}

                        else s = 213;

                         
                        input.seek(index30_127);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA30_34 = input.LA(1);

                         
                        int index30_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA30_34=='=') ) {s = 126;}

                        else if ( (LA30_34=='>') ) {s = 127;}

                        else if ( (LA30_34=='*') ) {s = 128;}

                        else if ( (LA30_34=='/') ) {s = 129;}

                        else if ( ((LA30_34>='\u0000' && LA30_34<='\t')||(LA30_34>='\u000B' && LA30_34<='\f')||(LA30_34>='\u000E' && LA30_34<=')')||(LA30_34>='+' && LA30_34<='.')||(LA30_34>='0' && LA30_34<='<')||(LA30_34>='?' && LA30_34<='\uFFFF')) && ((isRegularExpression()))) {s = 131;}

                        else s = 130;

                         
                        input.seek(index30_34);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA30_46 = input.LA(1);

                        s = -1;
                        if ( ((LA30_46>='\u0000' && LA30_46<='\uFFFF')) ) {s = 147;}

                        else s = 54;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA30_47 = input.LA(1);

                        s = -1;
                        if ( ((LA30_47>='\u0000' && LA30_47<='\uFFFF')) ) {s = 148;}

                        else s = 54;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA30_52 = input.LA(1);

                         
                        int index30_52 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (!(((isXMLText(input.LA(1)))))) ) {s = 56;}

                        else if ( ((isXMLText(input.LA(1)))) ) {s = 54;}

                         
                        input.seek(index30_52);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 30, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}