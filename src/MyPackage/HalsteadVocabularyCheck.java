package MyPackage;

import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//Halstead Vocabulary is the sum of the number of unique operators and unique
//operands

public class HalsteadVocabularyCheck extends AbstractCheck {

	private Set<Integer> uniqueOperators = new HashSet<>();
	private Set<Integer> uniqueOperands = new HashSet<>();
	public int halsteadVocabulary = 0;

	@Override
	public void beginTree(DetailAST rootAST) {
		uniqueOperators.clear();
		uniqueOperands.clear();
		halsteadVocabulary = 0;
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

	// for testing
	public Set<Integer> getUniqueOperators() {
		return uniqueOperators;
	}

	public Set<Integer> getUniqueOperands() {
		return uniqueOperands;
	}

	public int getHalsteadVocabulary() {
		return halsteadVocabulary;
	}
	// for testing

	@Override
	public void finishTree(DetailAST rootAST) {
		halsteadVocabulary = uniqueOperators.size() + uniqueOperands.size();
		// log(rootAST.getLineNo(), "Halstead Vocabulary: " + halsteadVocabulary);
	}

}
