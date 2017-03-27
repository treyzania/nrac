package io.tr3y.nrac.core.data.func;

import io.tr3y.nrac.core.data.RapidType;
import io.tr3y.nrac.core.data.StatementBlock;
import io.tr3y.nrac.core.data.TypedValue;

public class Function extends Subroutine implements TypedValue {

	private FunctionSignature signature;

	public Function(String name, String section, FunctionSignature signature, StatementBlock block) {

		super(name, section, block);

		this.signature = signature;

	}

	public RapidType getReturnType() {
		return this.signature.getReturnType();
	}

	@Override
	public RapidType getDataType() {
		return this.getReturnType();
	}

}
