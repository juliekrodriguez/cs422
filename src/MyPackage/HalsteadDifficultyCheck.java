package MyPackage;

import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/*
 * Halstead Difficulty is half of the unique operators multiplied by the total number of
 * operands, divided by the number of unique operands
 */
public class HalsteadDifficultyCheck extends AbstractCheck {

	private HashSet<Integer> uniqueOperators = new HashSet<>();
	private HashSet<Integer> uniqueOperands = new HashSet<>();
	private int totalNumberOfOperands = 0;
	public double halsteadDifficulty = 0.0;

	@Override
	public void beginTree(DetailAST rootAST) {
		uniqueOperators.clear();
		uniqueOperands.clear();
		halsteadDifficulty = 0;
		totalNumberOfOperands = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.EXPR };
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
		countOperatorsAndOperands(ast);
	}

	protected void countOperatorsAndOperands(DetailAST ast) {
		int type = ast.getType();
		HalsteadTokens halsteadTokens = new HalsteadTokens();

		if (isOperator(type, halsteadTokens.getOperators())) {
			uniqueOperators.add(type);
		} else if (isOperand(type, halsteadTokens.getOperands())) {
			uniqueOperands.add(type);
			totalNumberOfOperands++;
		}

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
		double numberOfUniqueOperators = uniqueOperators.size();
		double numberOfUniqueOperands = uniqueOperands.size();

		if (numberOfUniqueOperands > 0) {
			halsteadDifficulty = (numberOfUniqueOperators / 2.0) * (totalNumberOfOperands / numberOfUniqueOperands);

		}

		// log(rootAST.getLineNo(), "Halstead Difficulty: " + halsteadDifficulty);
	}

	public double getHalsteadDifficulty() {
		return halsteadDifficulty;
	}

	// for testing
	public Set<Integer> getUniqueOperators() {
		return uniqueOperators;
	}

	public Set<Integer> getUniqueOperands() {
		return uniqueOperands;
	}

	public int getTotalNumberOfOperands() {
		return totalNumberOfOperands;
	}
}
