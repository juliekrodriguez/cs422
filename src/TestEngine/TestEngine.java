package TestEngine;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.JavaParser.Options;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

public class TestEngine {
	private final AbstractCheck codeCheck;
	private final String sourcePath;
	private DetailAST astRoot;

	public TestEngine(String sourceFilePath, AbstractCheck codeChecker) {
		this.sourcePath = sourceFilePath;
		this.codeCheck = codeChecker;
	}

	public void analyze() throws IOException, CheckstyleException {
		File sourceFile = new File(sourcePath);
		FileContents fileContents = new FileContents(new FileText(sourceFile, "UTF-8"));

		// Parse the source file
		astRoot = codeCheck.isCommentNodesRequired() ? JavaParser.parseFile(sourceFile, Options.WITH_COMMENTS)
				: JavaParser.parse(fileContents);

		// Set up and configure the check
		codeCheck.configure(new DefaultConfiguration("CustomCheck"));
		codeCheck.contextualize(new DefaultContext());

		// Traverse the AST
		codeCheck.beginTree(astRoot);
		traverseAst(codeCheck, astRoot);

		// Complete the check process
		codeCheck.finishTree(astRoot);
	}

	private void traverseAst(AbstractCheck checker, DetailAST currentNode) {
		int[] tokenTypes = checker.getAcceptableTokens();
		while (currentNode != null) {
			if (isTokenTypePresent(tokenTypes, currentNode.getType())) {
				checker.visitToken(currentNode);
			}

			DetailAST childNode = currentNode.getFirstChild();
			while (currentNode != null && childNode == null) {
				checker.leaveToken(currentNode);
				childNode = currentNode.getNextSibling();
				currentNode = currentNode.getParent();
			}
			currentNode = childNode;
		}
	}

	private boolean isTokenTypePresent(int[] tokens, int type) {
		return IntStream.of(tokens).anyMatch(token -> token == type);
	}
}