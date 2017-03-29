package io.tr3y.nrac.parser;

import io.tr3y.nrac.core.data.BuildObject;
import io.tr3y.nrac.parser.ast.RapidASMBaseListener;

public class ObjectASTWalkListener extends RapidASMBaseListener {

	private final UnitConfig config;

	public ObjectASTWalkListener(UnitConfig conf) {
		this.config = conf;
	}

	public BuildObject getPreparedObject() {
		return null;
	}

	// TODO Implement the object parser.

}
