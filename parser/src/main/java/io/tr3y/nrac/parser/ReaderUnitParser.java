package io.tr3y.nrac.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import io.tr3y.nrac.core.ParseException;
import io.tr3y.nrac.core.UnitParser;
import io.tr3y.nrac.core.data.BuildObject;
import io.tr3y.nrac.core.data.RapidASMUnit;
import io.tr3y.nrac.parser.ast.RapidASMLexer;
import io.tr3y.nrac.parser.ast.RapidASMParser;
import io.tr3y.nrac.parser.ast.RapidASMParser.ObjectDefContext;
import io.tr3y.nrac.parser.ast.RapidASMParser.OptionDefContext;
import io.tr3y.nrac.parser.ast.RapidASMParser.UnitContext;
import io.tr3y.nrac.parser.ast.RapidASMParser.UnitMemberContext;

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

		// Pull out stuff like `@arch x86` before processing the objects.
		UnitContext root = parser.unit();
		List<ObjectDefContext> objectDefs = new ArrayList<>();
		UnitConfig conf = new UnitConfig();
		for (UnitMemberContext um : root.unitMember()) {

			// If it's an object definition then we want to deal with it now.
			OptionDefContext opt = um.optionDef();
			if (opt == null) {

				objectDefs.add(um.objectDef());
				continue;

			}
			
			String optName = opt.Identifier().getText();
			if ("arch".equals(optName)) {
				
				/*
				 * FIXME This can index out of bounds if we just do an @arch
				 * alone, so we need better validation. This could require a
				 * change in the grammar. But it works for now.
				 */
				conf.setArch(opt.optionArg().get(0).getText());
				
			}
			
		}

		List<BuildObject> objects = new ArrayList<>();
		for (ObjectDefContext def : objectDefs) {

			// Create the processor.
			ObjectASTWalkListener listener = new ObjectASTWalkListener(conf);

			// Actually traverse the object definition to create it.
			ParseTreeWalker.DEFAULT.walk(listener, def);
			objects.add(listener.getPreparedObject());

		}

		return null; // TODO Implement the actual unit assembly.

	}

}
