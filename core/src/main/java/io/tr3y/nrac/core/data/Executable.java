package io.tr3y.nrac.core.data;

public interface Executable {

	/**
	 * Gets the root block that we can jump to and start execution, ignoring
	 * calling conventions and other effects that might be beyond this.
	 * 
	 * @return the execution block.
	 */
	StatementBlock getRootExecutionBlock();

}
