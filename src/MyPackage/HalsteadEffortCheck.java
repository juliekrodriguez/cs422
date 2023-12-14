package MyPackage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//Halstead Effort is the difficulty multiplied by the volume. Effort = DV. 

public class HalsteadEffortCheck extends AbstractCheck {

	public Set<String> uniques = new HashSet<String>();
	public Set<String> uniqueORS = new HashSet<String>();
	public Set<String> uniqueV = new HashSet<String>();
	int len;
	int halsVolume;
	int t;
	int halsDiff;
	public int count;

	// Just creatiing new token here cuz for some reason when i call get tokens it
	// break
	int[] Operators = new int[] { TokenTypes.LCURLY, TokenTypes.LPAREN, TokenTypes.ARRAY_DECLARATOR,
			TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_FINALLY, TokenTypes.PLUS_ASSIGN,
			TokenTypes.MINUS_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.DIV_ASSIGN, TokenTypes.MOD_ASSIGN,
			TokenTypes.SR_ASSIGN, TokenTypes.BSR_ASSIGN, TokenTypes.SL_ASSIGN, TokenTypes.BAND_ASSIGN,
			TokenTypes.BXOR_ASSIGN, TokenTypes.BOR_ASSIGN, TokenTypes.QUESTION, TokenTypes.LOR, TokenTypes.LAND,
			TokenTypes.BOR, TokenTypes.BXOR, TokenTypes.BAND, TokenTypes.NOT_EQUAL, TokenTypes.EQUAL, TokenTypes.LT,
			TokenTypes.GT, TokenTypes.LE, TokenTypes.GE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.SL, TokenTypes.SR,
			TokenTypes.BSR, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.MOD, TokenTypes.INC,
			TokenTypes.DEC, TokenTypes.BNOT, TokenTypes.LNOT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_FALSE,
			TokenTypes.LITERAL_NULL, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_ASSERT, TokenTypes.STATIC_IMPORT,
			TokenTypes.ENUM, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_BREAK, TokenTypes.ELLIPSIS, TokenTypes.LAMBDA,
			TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.DOUBLE_COLON, TokenTypes.COMMA, TokenTypes.STAR,
			TokenTypes.LITERAL_DO, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE,
			TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_INTERFACE, TokenTypes.UNARY_PLUS,
			TokenTypes.UNARY_MINUS, TokenTypes.METHOD_CALL, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_VOLATILE,
			TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER,
			TokenTypes.LITERAL_TRANSIENT, TokenTypes.SEMI, TokenTypes.STRICTFP, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.LITERAL_CLASS, TokenTypes.PACKAGE_DEF, TokenTypes.LITERAL_RETURN,
			TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_PROTECTED, TokenTypes.ABSTRACT,
			TokenTypes.FINAL, TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_VOLATILE,
			TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_DEFAULT, TokenTypes.DOT,
			TokenTypes.DO_WHILE, TokenTypes.SLIST, TokenTypes.IMPORT };

	int[] Operands = new int[] { TokenTypes.LITERAL_VOID, TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BYTE,
			TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT,
			TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_DOUBLE, TokenTypes.IDENT, TokenTypes.NUM_DOUBLE,
			TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, TokenTypes.CHAR_LITERAL,
			TokenTypes.STRING_LITERAL };

	int[] Tokens = new int[] { TokenTypes.LCURLY, TokenTypes.LPAREN, TokenTypes.ARRAY_DECLARATOR,
			TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_FINALLY, TokenTypes.PLUS_ASSIGN,
			TokenTypes.MINUS_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.DIV_ASSIGN, TokenTypes.MOD_ASSIGN,
			TokenTypes.SR_ASSIGN, TokenTypes.BSR_ASSIGN, TokenTypes.SL_ASSIGN, TokenTypes.BAND_ASSIGN,
			TokenTypes.BXOR_ASSIGN, TokenTypes.BOR_ASSIGN, TokenTypes.QUESTION, TokenTypes.LOR, TokenTypes.LAND,
			TokenTypes.BOR, TokenTypes.BXOR, TokenTypes.BAND, TokenTypes.NOT_EQUAL, TokenTypes.EQUAL, TokenTypes.LT,
			TokenTypes.GT, TokenTypes.LE, TokenTypes.GE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.SL, TokenTypes.SR,
			TokenTypes.BSR, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.MOD, TokenTypes.INC,
			TokenTypes.DEC, TokenTypes.BNOT, TokenTypes.LNOT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_FALSE,
			TokenTypes.LITERAL_NULL, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_ASSERT, TokenTypes.STATIC_IMPORT,
			TokenTypes.ENUM, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_BREAK, TokenTypes.ELLIPSIS, TokenTypes.LAMBDA,
			TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.DOUBLE_COLON, TokenTypes.COMMA, TokenTypes.STAR,
			TokenTypes.LITERAL_DO, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE,
			TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_INTERFACE, TokenTypes.UNARY_PLUS,
			TokenTypes.UNARY_MINUS, TokenTypes.METHOD_CALL, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_VOLATILE,
			TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER,
			TokenTypes.LITERAL_TRANSIENT, TokenTypes.SEMI, TokenTypes.STRICTFP, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.LITERAL_CLASS, TokenTypes.PACKAGE_DEF, TokenTypes.LITERAL_RETURN,
			TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_PROTECTED, TokenTypes.ABSTRACT,
			TokenTypes.FINAL, TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_VOLATILE,
			TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_DEFAULT, TokenTypes.DOT,
			TokenTypes.DO_WHILE, TokenTypes.SLIST, TokenTypes.IMPORT, TokenTypes.LITERAL_VOID,
			TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_SHORT,
			TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_DOUBLE,
			TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
			TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL };

	@Override
	public void beginTree(DetailAST rootAST) {
		len = 0;
		halsVolume = 0;
		t = 0;
		halsDiff = 0;
		count = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		return Tokens;
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}

	@Override
	public void visitToken(DetailAST aAST) {
		len++;
		uniques.add(aAST.getText());
		// now see if we gonna add or not
		if (Arrays.stream(Operators).anyMatch(Integer.valueOf(aAST.getType())::equals)) {
			uniqueORS.add(aAST.getText());
		}
		if (Arrays.stream(Operands).anyMatch(Integer.valueOf(aAST.getType())::equals)) {
			uniqueV.add(aAST.getText());
			t++;
		}
	}

	@Override
	public void finishTree(DetailAST aAST) {
		halsVolume = (int) ((int) len * (Math.log(uniques.size()) / Math.log(2)));
		if (uniqueV.size() > 0) {
			halsDiff = (uniqueORS.size() / 2) * (t) / (uniqueV.size());
		}
		count = halsVolume * halsDiff;
		log(aAST.getLineNo(), "hals effort: " + count);
	}

}
