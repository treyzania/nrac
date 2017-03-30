package io.tr3y.nrac.core;

import java.util.List;

import io.tr3y.nrac.core.arch.Architecture;

public interface CompilerSettings {

	/**
	 * Creates an {@code Architecture} based on the name given and the specified
	 * parameters.
	 * 
	 * @param name
	 *            the name of the architecture
	 * @param params
	 *            any parameters supplied
	 * @return the created architecture
	 */
	Architecture configureArchiecture(String name, List<String> params);

	/**
	 * Returns a list of object names we should be compiling. If
	 * <code>null</code> then compile all of them.
	 * 
	 * @return the modules to compile
	 */
	List<String> getCompiledObjectNames();

	/**
	 * Returns if this object should be compiled based on its name.
	 * 
	 * @param name
	 *            the name of the object
	 * @return if it should be compiled
	 */
	default boolean isObjectCompilationRequested(String name) {

		List<String> todo = this.getCompiledObjectNames();
		return todo == null || todo.contains(name);

	}

}
