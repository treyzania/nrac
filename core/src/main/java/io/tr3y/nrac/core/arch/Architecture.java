package io.tr3y.nrac.core.arch;

import java.util.List;

import io.tr3y.nrac.core.data.DataWidth;

/**
 * Container class for translating everything in RSM source code to different
 * platforms.
 * 
 * @author treyzania
 *
 */
public interface Architecture {
	
	/**
	 * A list of registers currently supported by the system.
	 * 
	 * @return the registers
	 */
	List<Register> getRegisters();
	
	/**
	 * Returns the width of "words", which is what the CPU likes dealing with
	 * the most.
	 * 
	 * @return the word width
	 */
	DataWidth getWordWidth();

	/**
	 * Returns the width of memory pointers, which is usually the same as the
	 * word size, but can often be different.
	 * 
	 * @return the pointer width
	 */
	DataWidth getPointerWidth();
	
	/**
	 * "Naturalizes" a type into something that a C compiler will definitely use
	 * of the specified size and signedness.
	 * 
	 * @param type
	 *            the width
	 * @param signed
	 *            if the type should be signed
	 * @return a string for the type used in C
	 */
	String getNaturalizedType(DataWidth type, boolean signed);

}
