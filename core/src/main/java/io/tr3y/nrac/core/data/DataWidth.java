package io.tr3y.nrac.core.data;

public enum DataWidth {

	BYTE(8), SHORT(16), INT(32), LONG(64);

	public final int bits;

	private DataWidth(int bits) {
		this.bits = bits;
	}

}
