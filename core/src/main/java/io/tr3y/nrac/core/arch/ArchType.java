package io.tr3y.nrac.core.arch;

public @interface ArchType {

	/**
	 * The name of this architecture specified by `@arch` flags in RSM units.
	 * 
	 * @return the name of the architecture to use
	 */
	String value();

}
