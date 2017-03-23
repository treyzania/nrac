package io.tr3y.nrac.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import io.tr3y.nrac.core.ParseException;
import io.tr3y.nrac.core.UnitParser;
import io.tr3y.nrac.core.data.RapidASMUnit;
import io.tr3y.nrac.parser.ast.RapidASMLexer;
import io.tr3y.nrac.parser.ast.RapidASMParser;

public class ReaderUnitParser implements UnitParser<Reader> {

	public RapidASMUnit parse(Reader src) throws ParseException {

		// Read and clean input.
		Scanner in = new Scanner(src);
		in.useDelimiter("\\Z");
		String raw = in.next();
		String cleaned = raw.replaceAll("\r", "");
		in.close();

		// Prepare input stream.
		ANTLRInputStream ais = null;
		try {
			ais = new ANTLRInputStream(new StringReader(cleaned));
		} catch (IOException e) {
			throw new ParseException("Problem reading cleaned data.", e);
		}

		// Actual pipeline setup.
		RapidASMLexer lexer = new RapidASMLexer(ais);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		RapidASMParser parser = new RapidASMParser(tokenStream);

		// Walk through the tree.
		RapidASMASTWalkListener listener = new RapidASMASTWalkListener();
		try {
			ParseTreeWalker.DEFAULT.walk(listener, parser.unit());
		} catch (Throwable t) {
			throw new ParseException("Problem processing AST.", t);
		}

		return listener.getLastUnit();

	}

}
