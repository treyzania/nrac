package io.tr3y.nrac.core.data;

public class SymbolVariable implements SectionMember, TypedValue {

	private final String sectionName;
	private final String varName;
	private final RapidType dataType;

	public SymbolVariable(String section, String name, RapidType type) {

		this.sectionName = section;
		this.varName = name;
		this.dataType = type;

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
