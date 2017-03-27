package io.tr3y.nrac.core.data;

import java.nio.channels.AlreadyBoundException;

public interface Label {

	/**
	 * Gets the name of this symbol.
	 * 
	 * @return
	 */
	String getSymbolName();

	/**
	 * Binds the label to the labelable.
	 * 
	 * @param owner
	 *            the owner to bind to
	 * @throws AlreadyBoundException
	 *             if this label is already bound
	 */
	void bind(Labelable owner) throws AlreadyBoundException;

	/**
	 * Returns if this label is already bound to a labelable.
	 * 
	 * @return the binding state
	 */
	boolean isBound();

}
