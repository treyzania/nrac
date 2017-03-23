package io.tr3y.nrac.core.data;

import java.util.List;

/**
 * Something that can contain other statements.
 * 
 * @author treyzania
 *
 */
public interface StatementBlock {

	/**
	 * Returns a list of statements contained in this block in the order they
	 * are apparently to be executed.
	 * 
	 * @return the list of statements
	 */
	List<Statement> getStatements();

}
