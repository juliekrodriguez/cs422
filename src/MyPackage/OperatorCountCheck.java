package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperatorCountCheck extends AbstractCheck {
	public int operatorCount;

	@Override
	public int[] getDefaultTokens() {
		// call token types in ht class here
		return new int[] { TokenTypes.LCURLY, TokenTypes.LPAREN, TokenTypes.ARRAY_DECLARATOR, TokenTypes.LITERAL_TRY,
				TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_FINALLY, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN,
				TokenTypes.STAR_ASSIGN, TokenTypes.DIV_ASSIGN, TokenTypes.MOD_ASSIGN, TokenTypes.SR_ASSIGN,
				TokenTypes.BSR_ASSIGN, TokenTypes.SL_ASSIGN, TokenTypes.BAND_ASSIGN, TokenTypes.BXOR_ASSIGN,
				TokenTypes.BOR_ASSIGN, TokenTypes.QUESTION, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.BOR,
				TokenTypes.BXOR, TokenTypes.BAND, TokenTypes.NOT_EQUAL, TokenTypes.EQUAL, TokenTypes.LT, TokenTypes.GT,
				TokenTypes.LE, TokenTypes.GE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.SL, TokenTypes.SR,
				TokenTypes.BSR, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.MOD, TokenTypes.INC,
				TokenTypes.DEC, TokenTypes.BNOT, TokenTypes.LNOT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_FALSE,
				TokenTypes.LITERAL_NULL, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_ASSERT, TokenTypes.STATIC_IMPORT,
				TokenTypes.ENUM, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_BREAK, TokenTypes.ELLIPSIS,
				TokenTypes.LAMBDA, TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.DOUBLE_COLON, TokenTypes.COMMA,
				TokenTypes.STAR, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_IF,
				TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
				TokenTypes.LITERAL_INTERFACE, TokenTypes.UNARY_PLUS, TokenTypes.UNARY_MINUS, TokenTypes.METHOD_CALL,
				TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_SYNCHRONIZED,
				TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_TRANSIENT, TokenTypes.SEMI,
				TokenTypes.STRICTFP, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.LITERAL_CLASS,
				TokenTypes.PACKAGE_DEF, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_PRIVATE,
				TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_PROTECTED, TokenTypes.ABSTRACT, TokenTypes.FINAL,
				TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_SYNCHRONIZED,
				TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_DEFAULT, TokenTypes.DOT, TokenTypes.DO_WHILE,
				TokenTypes.SLIST, TokenTypes.IMPORT
				// Add more token types as needed for other operators
		};
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

	@Override
	public void visitToken(DetailAST ast) {
		operatorCount++;
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		operatorCount = 0;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// log(rootAST.getLineNo(), "Number of operators: " + operatorCount);
	}

	public int getOperatorCount() {
		return operatorCount;
	}
}
