package io.tr3y.nrac.core.data.func;

public interface Anonymizable {

	/**
	 * Returns if this object does not have a primary label and, as such is
	 * considered an anonymous object. This is usually limited to subroutines.
	 * 
	 * @return if this object is anonymous
	 */
	boolean isAnonymous();

}
