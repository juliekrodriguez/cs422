package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadLengthCheck extends AbstractCheck {

	HalsteadTokens halsteadTokens = new HalsteadTokens();

	protected int totalOperators = 0;
	protected int totalOperands = 0;
	public int hl = 0;

	@Override
	public void beginTree(DetailAST rootAST) {
		totalOperators = 0;
		totalOperands = 0;
		hl = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.EXPR };

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
	public void visitToken(DetailAST ast) {
		countOperatorsAndOperands(ast);
	}

	void countOperatorsAndOperands(DetailAST ast) {
		int type = ast.getType();

		if (isOperator(type, halsteadTokens.getOperators())) {
			totalOperators++;
		} else if (isOperand(type, halsteadTokens.getOperands())) {
			totalOperands++;
		}

		// Recursively visit child nodes
		DetailAST child = ast.getFirstChild();
		while (child != null) {
			countOperatorsAndOperands(child);
			child = child.getNextSibling();
		}
	}

	boolean isOperator(int type, int[] operatorTypes) {
		for (int operatorType : operatorTypes) {
			if (type == operatorType) {
				return true;
			}
		}
		return false;
	}

	boolean isOperand(int type, int[] operandTypes) {
		for (int operandType : operandTypes) {
			if (type == operandType) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		hl = totalOperators + totalOperands;
		// log(rootAST.getLineNo(), "Halstead Length: " + hl);

	}

	public int getlen() {
		return hl;
	}

}
