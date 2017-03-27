package io.tr3y.nrac.core.data;

public class SymbolValue implements SectionMember, TypedValue {

	private final String sectionName;
	private final String valName;
	private final RapidType dataType;

	// FIXME Figure out how to represent this stuff.
	private final Object storedValue;

	public SymbolValue(String section, String name, RapidType type, Object value) {

		this.sectionName = section;
		this.valName = name;
		this.dataType = type;
		this.storedValue = value;

	}

	@Override
	public String getSectionName() {
		return this.sectionName;
	}

	@Override
	public RapidType getDataType() {
		return this.dataType;
	}

}
