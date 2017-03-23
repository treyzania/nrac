package io.tr3y.nrac.core.data;

/**
 * A bundle of compilation artifact data.
 * 
 * @author treyzania
 *
 */
public class BuildObject {

	private String name;

	public BuildObject(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the object. If this were to return "foo", then the
	 * files generated would be "foo.S" and "foo.gen.h".
	 * 
	 * @return the name of the object
	 */
	public String getName() {
		return this.name;
	}

}
