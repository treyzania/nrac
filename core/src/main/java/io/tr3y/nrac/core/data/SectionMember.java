package io.tr3y.nrac.core.data;

public interface SectionMember {

	/**
	 * Returns the name of the section that this "thing" should be in in the
	 * final emitted assembly code. Usually always the same for a given type,
	 * but can be overriden by the user for one reason or another.
	 * 
	 * @return the final section's name
	 */
	String getSectionName();

}
