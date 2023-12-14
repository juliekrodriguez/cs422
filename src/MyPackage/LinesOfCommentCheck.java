package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LinesOfCommentCheck extends AbstractCheck {
	private static final String CATCH_MSG = "Total comment lines found: ";
	public int count = 0;

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT, TokenTypes.BLOCK_COMMENT_BEGIN };
	}

	@Override
	public void visitToken(DetailAST aAST) {
		if (aAST.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
			DetailAST end = aAST.findFirstToken(TokenTypes.BLOCK_COMMENT_END);
			count += (end.getLineNo() - aAST.getLineNo()) - 1;
		}
		count++;
	}

	@Override
	public void beginTree(DetailAST aAST) {
		count = 0;
	}

	@Override
	public void finishTree(DetailAST aAST) {
		log(aAST.getLineNo(), CATCH_MSG + count);
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
	public boolean isCommentNodesRequired() {
		return true;
	}
}
