package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LinesOfComment extends AbstractCheck {

	protected int loc = 0;

	@Override
	public void beginTree(DetailAST rootAST) {
		loc = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
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
		switch (ast.getType()) {
		case TokenTypes.SINGLE_LINE_COMMENT:
			loc++;
			break;
		case TokenTypes.BLOCK_COMMENT_BEGIN:
			loc += countLinesInBlockComment(ast);
			break;
		default:
			// Ignore other token types
			break;
		}
	}

	private int countLinesInBlockComment(DetailAST blockCommentAst) {
		DetailAST endAst = blockCommentAst.findFirstToken(TokenTypes.BLOCK_COMMENT_END);
		if (endAst != null) {
			return endAst.getLineNo() - blockCommentAst.getLineNo() + 1;
		}
		return 0;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// log(rootAST.getLineNo(), "Total lines of comments: " + loc);
	}

	public int getLinesOfComment() {
		// TODO Auto-generated method stub
		return loc;
	}
}
