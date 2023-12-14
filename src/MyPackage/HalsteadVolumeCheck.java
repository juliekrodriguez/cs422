package MyPackage;

import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//Halstead Volume is the program length (N) times the log2 of the program vocabulary (n)
//[1,2] : Volume = N log2 n

public class HalsteadVolumeCheck extends AbstractCheck {
	private Set<Integer> uniqueOperators = new HashSet<>();
	private Set<Integer> uniqueOperands = new HashSet<>();
	private int programLength = 0;
	public double halsteadVolume = 0.0;
	private int vocabulary = 0;

	@Override
	public void beginTree(DetailAST rootAST) {
		uniqueOperators.clear();
		uniqueOperands.clear();
		programLength = 0;
		halsteadVolume = 0.0;
		vocabulary = 0;
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
	public void visitToken(DetailAST aAST) {
		countOperatorsAndOperands(aAST);
	}

	protected void countOperatorsAndOperands(DetailAST ast) {
		int type = ast.getType();
		HalsteadTokens halsteadTokens = new HalsteadTokens();

		if (isOperator(type, halsteadTokens.getOperators())) {
			uniqueOperators.add(type);
			programLength++;
		} else if (isOperand(type, halsteadTokens.getOperands())) {
			uniqueOperands.add(type);
			programLength++;
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
		vocabulary = uniqueOperators.size() + uniqueOperands.size();
		if (vocabulary > 0) {
			halsteadVolume = programLength * Math.log(vocabulary) / Math.log(2);
		} else {
			halsteadVolume = 0.0;
		}
	}

	public double getHalsteadVolume() {
		return halsteadVolume;
	}

	// for testing
	public Set<Integer> getUniqueOperators() {
		return uniqueOperators;
	}

	public Set<Integer> getUniqueOperands() {
		return uniqueOperands;
	}

	public int getProgramLength() {
		return programLength;
	}

	public int getVocabulary() {
		return vocabulary;
	}
	// for testing

}
