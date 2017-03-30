package io.tr3y.nrac.core.arch;

import io.tr3y.nrac.core.data.DataWidth;

public class SimpleRegisterImpl implements Register {

	private final String name;
	private final DataWidth width;

	public SimpleRegisterImpl(String name, DataWidth width) {
		this.name = name;
		this.width = width;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public DataWidth getWidth() {
		return this.width;
	}

}
