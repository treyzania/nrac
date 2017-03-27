package io.tr3y.nrac.core.data;

public class AlreadyBoundException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309449726243182355L;

	private Labelable boundTo;

	public AlreadyBoundException(Labelable to) {

		super("Already bound to " + to.toString());

		this.boundTo = to;

	}

	public Labelable getPreviousBinding() {
		return this.boundTo;
	}

}
