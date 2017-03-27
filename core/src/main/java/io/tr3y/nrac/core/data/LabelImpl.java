package io.tr3y.nrac.core.data;

public class LabelImpl implements Label {

	private final String name;
	private Labelable owner;

	public LabelImpl(String name) {
		this.name = name;
	}

	@Override
	public String getSymbolName() {
		return this.name;
	}

	@Override
	public void bind(Labelable owner) throws AlreadyBoundException {
		
		if (this.owner != null)
			throw new AlreadyBoundException(this.owner);

		this.owner = owner;

	}

	@Override
	public boolean isBound() {
		return this.owner != null;
	}

}
