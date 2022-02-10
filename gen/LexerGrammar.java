// Generated from /Users/huyong/Documents/workhome/daily-study/src/main/antlr4/com/huyong/study/grammer/LexerGrammar.g4 by ANTLR 4.9
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LexerGrammar extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SPACE=1, SPEC_ESSQL_COMMENT=2, COMMENT_INPUT=3, WS=4, LINE_COMMENT=5, 
		MINUS=6, STAR=7, COLON=8, EQ=9, NE=10, BOOLOR=11, BOOLAND=12, DOT=13, 
		LBRACKET=14, RBRACKET=15, LPAREN=16, RPAREN=17, COMMA=18, SEMI=19, GT=20, 
		AFTER=21, SINGLE_QUOTE=22, DOUBLE_QUOTE=23, REVERSE_QUOTE=24, UNDERLINE=25, 
		CHINESE=26, ID=27, INT=28, FLOAT=29, DOTINTEGER=30, DOTID=31;
	public static final int
		ESQLCOMMENT=2, ERRORCHANNEL=3;
	public static final int
		AFTER_DOT=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN", "ESQLCOMMENT", "ERRORCHANNEL"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "AFTER_DOT"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SPACE", "SPEC_ESSQL_COMMENT", "COMMENT_INPUT", "WS", "LINE_COMMENT", 
			"MINUS", "STAR", "COLON", "EQ", "NE", "BOOLOR", "BOOLAND", "DOT", "LBRACKET", 
			"RBRACKET", "LPAREN", "RPAREN", "COMMA", "SEMI", "GT", "AFTER", "SINGLE_QUOTE", 
			"DOUBLE_QUOTE", "REVERSE_QUOTE", "UNDERLINE", "CHINESE", "ID", "INT", 
			"FLOAT", "DEC_DIGIT", "ID_LETTER", "A", "B", "C", "D", "E", "F", "G", 
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", 
			"V", "W", "X", "Y", "Z", "DOTINTEGER", "DOTID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'-'", "'*'", null, "'='", "'!='", 
			null, null, "'.'", "'['", "']'", "'('", "')'", null, "';'", "'>'", null, 
			"'''", "'\"'", "'`'", "'_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SPACE", "SPEC_ESSQL_COMMENT", "COMMENT_INPUT", "WS", "LINE_COMMENT", 
			"MINUS", "STAR", "COLON", "EQ", "NE", "BOOLOR", "BOOLAND", "DOT", "LBRACKET", 
			"RBRACKET", "LPAREN", "RPAREN", "COMMA", "SEMI", "GT", "AFTER", "SINGLE_QUOTE", 
			"DOUBLE_QUOTE", "REVERSE_QUOTE", "UNDERLINE", "CHINESE", "ID", "INT", 
			"FLOAT", "DOTINTEGER", "DOTID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LexerGrammar(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LexerGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u017b\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2"+
		"\6\2|\n\2\r\2\16\2}\3\2\3\2\3\3\3\3\3\3\3\3\3\3\6\3\u0087\n\3\r\3\16\3"+
		"\u0088\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u0094\n\4\f\4\16\4\u0097"+
		"\13\4\3\4\3\4\3\4\3\4\3\4\3\5\6\5\u009f\n\5\r\5\16\5\u00a0\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\5\6\u00a9\n\6\3\6\7\6\u00ac\n\6\f\6\16\6\u00af\13\6\3\6\5"+
		"\6\u00b2\n\6\3\6\3\6\5\6\u00b6\n\6\3\6\3\6\3\6\3\6\5\6\u00bc\n\6\3\6\3"+
		"\6\5\6\u00c0\n\6\5\6\u00c2\n\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\5\f\u00d4\n\f\3\r\3\r\3\r\3\r\5\r\u00da\n"+
		"\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\6\34\u0108\n\34\r\34\16\34\u0109\3\35\5\35\u010d\n\35\3"+
		"\35\6\35\u0110\n\35\r\35\16\35\u0111\3\36\5\36\u0115\n\36\3\36\6\36\u0118"+
		"\n\36\r\36\16\36\u0119\3\36\3\36\6\36\u011e\n\36\r\36\16\36\u011f\3\36"+
		"\5\36\u0123\n\36\3\36\3\36\6\36\u0127\n\36\r\36\16\36\u0128\5\36\u012b"+
		"\n\36\3\37\3\37\3 \3 \5 \u0131\n \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3"+
		"&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3"+
		"\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\3"+
		"8\38\39\39\3:\3:\3;\3;\3;\7;\u016a\n;\f;\16;\u016d\13;\5;\u016f\n;\3;"+
		"\3;\3<\3<\7<\u0175\n<\f<\16<\u0178\13<\3<\3<\4\u0088\u0095\2=\4\3\6\4"+
		"\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20 \21\"\22"+
		"$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\66\348\35:\36<\37>\2@\2B\2"+
		"D\2F\2H\2J\2L\2N\2P\2R\2T\2V\2X\2Z\2\\\2^\2`\2b\2d\2f\2h\2j\2l\2n\2p\2"+
		"r\2t\2v x!\4\2\3%\5\2\13\f\17\17\"\"\4\2\f\f\17\17\4\2<<\uff1c\uff1c\4"+
		"\2..\uff0e\uff0e\3\2\62;\4\2C\\c|\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2"+
		"GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4"+
		"\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXx"+
		"x\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\3\2\63;\5\2C\\aac|\6\2\62;C\\aac|"+
		"\2\u0181\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2"+
		"\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3"+
		"\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2"+
		"$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60"+
		"\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2"+
		"\2<\3\2\2\2\3v\3\2\2\2\3x\3\2\2\2\4{\3\2\2\2\6\u0081\3\2\2\2\b\u008f\3"+
		"\2\2\2\n\u009e\3\2\2\2\f\u00c1\3\2\2\2\16\u00c5\3\2\2\2\20\u00c7\3\2\2"+
		"\2\22\u00c9\3\2\2\2\24\u00cb\3\2\2\2\26\u00cd\3\2\2\2\30\u00d3\3\2\2\2"+
		"\32\u00d9\3\2\2\2\34\u00db\3\2\2\2\36\u00df\3\2\2\2 \u00e1\3\2\2\2\"\u00e3"+
		"\3\2\2\2$\u00e5\3\2\2\2&\u00e7\3\2\2\2(\u00e9\3\2\2\2*\u00eb\3\2\2\2,"+
		"\u00ed\3\2\2\2.\u00f3\3\2\2\2\60\u00f5\3\2\2\2\62\u00f7\3\2\2\2\64\u00f9"+
		"\3\2\2\2\66\u00fb\3\2\2\28\u0107\3\2\2\2:\u010c\3\2\2\2<\u012a\3\2\2\2"+
		">\u012c\3\2\2\2@\u0130\3\2\2\2B\u0132\3\2\2\2D\u0134\3\2\2\2F\u0136\3"+
		"\2\2\2H\u0138\3\2\2\2J\u013a\3\2\2\2L\u013c\3\2\2\2N\u013e\3\2\2\2P\u0140"+
		"\3\2\2\2R\u0142\3\2\2\2T\u0144\3\2\2\2V\u0146\3\2\2\2X\u0148\3\2\2\2Z"+
		"\u014a\3\2\2\2\\\u014c\3\2\2\2^\u014e\3\2\2\2`\u0150\3\2\2\2b\u0152\3"+
		"\2\2\2d\u0154\3\2\2\2f\u0156\3\2\2\2h\u0158\3\2\2\2j\u015a\3\2\2\2l\u015c"+
		"\3\2\2\2n\u015e\3\2\2\2p\u0160\3\2\2\2r\u0162\3\2\2\2t\u0164\3\2\2\2v"+
		"\u016e\3\2\2\2x\u0172\3\2\2\2z|\t\2\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2"+
		"}~\3\2\2\2~\177\3\2\2\2\177\u0080\b\2\2\2\u0080\5\3\2\2\2\u0081\u0082"+
		"\7\61\2\2\u0082\u0083\7,\2\2\u0083\u0084\7#\2\2\u0084\u0086\3\2\2\2\u0085"+
		"\u0087\13\2\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3"+
		"\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\7,\2\2\u008b"+
		"\u008c\7\61\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\3\3\2\u008e\7\3\2\2"+
		"\2\u008f\u0090\7\61\2\2\u0090\u0091\7,\2\2\u0091\u0095\3\2\2\2\u0092\u0094"+
		"\13\2\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0096\3\2\2\2"+
		"\u0095\u0093\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099"+
		"\7,\2\2\u0099\u009a\7\61\2\2\u009a\u009b\3\2\2\2\u009b\u009c\b\4\2\2\u009c"+
		"\t\3\2\2\2\u009d\u009f\t\2\2\2\u009e\u009d\3\2\2\2\u009f\u00a0\3\2\2\2"+
		"\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3"+
		"\b\5\4\2\u00a3\13\3\2\2\2\u00a4\u00a5\7/\2\2\u00a5\u00a6\7/\2\2\u00a6"+
		"\u00a9\7\"\2\2\u00a7\u00a9\7%\2\2\u00a8\u00a4\3\2\2\2\u00a8\u00a7\3\2"+
		"\2\2\u00a9\u00ad\3\2\2\2\u00aa\u00ac\n\3\2\2\u00ab\u00aa\3\2\2\2\u00ac"+
		"\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b5\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b2\7\17\2\2\u00b1\u00b0\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b6\7\f\2\2\u00b4\u00b6\7\2"+
		"\2\3\u00b5\u00b1\3\2\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00c2\3\2\2\2\u00b7"+
		"\u00b8\7/\2\2\u00b8\u00b9\7/\2\2\u00b9\u00bf\3\2\2\2\u00ba\u00bc\7\17"+
		"\2\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00c0\7\f\2\2\u00be\u00c0\7\2\2\3\u00bf\u00bb\3\2\2\2\u00bf\u00be\3\2"+
		"\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00a8\3\2\2\2\u00c1\u00b7\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c4\b\6\2\2\u00c4\r\3\2\2\2\u00c5\u00c6\7/\2\2"+
		"\u00c6\17\3\2\2\2\u00c7\u00c8\7,\2\2\u00c8\21\3\2\2\2\u00c9\u00ca\t\4"+
		"\2\2\u00ca\23\3\2\2\2\u00cb\u00cc\7?\2\2\u00cc\25\3\2\2\2\u00cd\u00ce"+
		"\7#\2\2\u00ce\u00cf\7?\2\2\u00cf\27\3\2\2\2\u00d0\u00d1\7~\2\2\u00d1\u00d4"+
		"\7~\2\2\u00d2\u00d4\7~\2\2\u00d3\u00d0\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4"+
		"\31\3\2\2\2\u00d5\u00d6\7(\2\2\u00d6\u00da\7(\2\2\u00d7\u00da\5&\23\2"+
		"\u00d8\u00da\7(\2\2\u00d9\u00d5\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8"+
		"\3\2\2\2\u00da\33\3\2\2\2\u00db\u00dc\7\60\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00de\b\16\5\2\u00de\35\3\2\2\2\u00df\u00e0\7]\2\2\u00e0\37\3\2\2\2\u00e1"+
		"\u00e2\7_\2\2\u00e2!\3\2\2\2\u00e3\u00e4\7*\2\2\u00e4#\3\2\2\2\u00e5\u00e6"+
		"\7+\2\2\u00e6%\3\2\2\2\u00e7\u00e8\t\5\2\2\u00e8\'\3\2\2\2\u00e9\u00ea"+
		"\7=\2\2\u00ea)\3\2\2\2\u00eb\u00ec\7@\2\2\u00ec+\3\2\2\2\u00ed\u00ee\5"+
		"B!\2\u00ee\u00ef\5L&\2\u00ef\u00f0\5h\64\2\u00f0\u00f1\5J%\2\u00f1\u00f2"+
		"\5d\62\2\u00f2-\3\2\2\2\u00f3\u00f4\7)\2\2\u00f4/\3\2\2\2\u00f5\u00f6"+
		"\7$\2\2\u00f6\61\3\2\2\2\u00f7\u00f8\7b\2\2\u00f8\63\3\2\2\2\u00f9\u00fa"+
		"\7a\2\2\u00fa\65\3\2\2\2\u00fb\u00fc\4\u4e02\u9fa7\2\u00fc\67\3\2\2\2"+
		"\u00fd\u0108\5\66\33\2\u00fe\u0108\5@ \2\u00ff\u0108\5\34\16\2\u0100\u0108"+
		"\5\16\7\2\u0101\u0108\5\64\32\2\u0102\u0108\5:\35\2\u0103\u0108\5<\36"+
		"\2\u0104\u0108\5\62\31\2\u0105\u0108\5\60\30\2\u0106\u0108\5.\27\2\u0107"+
		"\u00fd\3\2\2\2\u0107\u00fe\3\2\2\2\u0107\u00ff\3\2\2\2\u0107\u0100\3\2"+
		"\2\2\u0107\u0101\3\2\2\2\u0107\u0102\3\2\2\2\u0107\u0103\3\2\2\2\u0107"+
		"\u0104\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0106\3\2\2\2\u0108\u0109\3\2"+
		"\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a9\3\2\2\2\u010b\u010d"+
		"\5\16\7\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f\3\2\2\2"+
		"\u010e\u0110\5>\37\2\u010f\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f"+
		"\3\2\2\2\u0111\u0112\3\2\2\2\u0112;\3\2\2\2\u0113\u0115\5\16\7\2\u0114"+
		"\u0113\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0117\3\2\2\2\u0116\u0118\5>"+
		"\37\2\u0117\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119"+
		"\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011d\5\34\16\2\u011c\u011e\5"+
		">\37\2\u011d\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u011d\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120\u012b\3\2\2\2\u0121\u0123\5\16\7\2\u0122\u0121\3"+
		"\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\5\34\16\2\u0125"+
		"\u0127\5>\37\2\u0126\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0126\3\2"+
		"\2\2\u0128\u0129\3\2\2\2\u0129\u012b\3\2\2\2\u012a\u0114\3\2\2\2\u012a"+
		"\u0122\3\2\2\2\u012b=\3\2\2\2\u012c\u012d\t\6\2\2\u012d?\3\2\2\2\u012e"+
		"\u0131\t\7\2\2\u012f\u0131\5\64\32\2\u0130\u012e\3\2\2\2\u0130\u012f\3"+
		"\2\2\2\u0131A\3\2\2\2\u0132\u0133\t\b\2\2\u0133C\3\2\2\2\u0134\u0135\t"+
		"\t\2\2\u0135E\3\2\2\2\u0136\u0137\t\n\2\2\u0137G\3\2\2\2\u0138\u0139\t"+
		"\13\2\2\u0139I\3\2\2\2\u013a\u013b\t\f\2\2\u013bK\3\2\2\2\u013c\u013d"+
		"\t\r\2\2\u013dM\3\2\2\2\u013e\u013f\t\16\2\2\u013fO\3\2\2\2\u0140\u0141"+
		"\t\17\2\2\u0141Q\3\2\2\2\u0142\u0143\t\20\2\2\u0143S\3\2\2\2\u0144\u0145"+
		"\t\21\2\2\u0145U\3\2\2\2\u0146\u0147\t\22\2\2\u0147W\3\2\2\2\u0148\u0149"+
		"\t\23\2\2\u0149Y\3\2\2\2\u014a\u014b\t\24\2\2\u014b[\3\2\2\2\u014c\u014d"+
		"\t\25\2\2\u014d]\3\2\2\2\u014e\u014f\t\26\2\2\u014f_\3\2\2\2\u0150\u0151"+
		"\t\27\2\2\u0151a\3\2\2\2\u0152\u0153\t\30\2\2\u0153c\3\2\2\2\u0154\u0155"+
		"\t\31\2\2\u0155e\3\2\2\2\u0156\u0157\t\32\2\2\u0157g\3\2\2\2\u0158\u0159"+
		"\t\33\2\2\u0159i\3\2\2\2\u015a\u015b\t\34\2\2\u015bk\3\2\2\2\u015c\u015d"+
		"\t\35\2\2\u015dm\3\2\2\2\u015e\u015f\t\36\2\2\u015fo\3\2\2\2\u0160\u0161"+
		"\t\37\2\2\u0161q\3\2\2\2\u0162\u0163\t \2\2\u0163s\3\2\2\2\u0164\u0165"+
		"\t!\2\2\u0165u\3\2\2\2\u0166\u016f\7\62\2\2\u0167\u016b\t\"\2\2\u0168"+
		"\u016a\t\6\2\2\u0169\u0168\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2"+
		"\2\2\u016b\u016c\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016e"+
		"\u0166\3\2\2\2\u016e\u0167\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\b;"+
		"\6\2\u0171w\3\2\2\2\u0172\u0176\t#\2\2\u0173\u0175\t$\2\2\u0174\u0173"+
		"\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0179\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u017a\b<\6\2\u017ay\3\2\2\2\37"+
		"\2\3}\u0088\u0095\u00a0\u00a8\u00ad\u00b1\u00b5\u00bb\u00bf\u00c1\u00d3"+
		"\u00d9\u0107\u0109\u010c\u0111\u0114\u0119\u011f\u0122\u0128\u012a\u0130"+
		"\u016b\u016e\u0176\7\2\3\2\2\4\2\b\2\2\4\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}