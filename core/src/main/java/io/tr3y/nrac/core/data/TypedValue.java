package io.tr3y.nrac.core.data;

public interface TypedValue {

	/**
	 * Returns cosntant the RapidASM type of the data represented by the object
	 * this stores.
	 * 
	 * @return the data type of this object
	 */
	RapidType getDataType();

}
