package io.tr3y.nrac.parser;

import io.tr3y.nrac.core.data.RapidASMUnit;
import io.tr3y.nrac.parser.ast.RapidASMBaseListener;

public class RapidASMASTWalkListener extends RapidASMBaseListener {

	/**
	 * Returns the result of the last RapidASM unit that was generated using
	 * this walk listener. This can be called as many times in succession and
	 * will return the same object, but if the listener is walked another tree
	 * it will change. Walking over the same tree multiple times should produce
	 * equivalent results.
	 * 
	 * @return the unit
	 */
	public RapidASMUnit getLastUnit() {
		return null;
	}

	// TODO Implement the parser.

}
