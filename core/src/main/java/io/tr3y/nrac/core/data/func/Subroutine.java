package io.tr3y.nrac.core.data.func;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.tr3y.nrac.core.data.Label;
import io.tr3y.nrac.core.data.SectionMember;
import io.tr3y.nrac.core.data.StatementBlock;

public class Subroutine implements SectionMember, Anonymizable, AsmCallable {

	private final String name;
	private final String sectionName;

	private final StatementBlock rootBlock;

	private List<Label> labels = new ArrayList<>();

	public Subroutine(String name, String section, StatementBlock block) {

		this.name = name;
		this.sectionName = section;

		this.rootBlock = block;

	}

	public Subroutine(String section, StatementBlock block) {
		this(null, section, block);
	}

	@Override
	public String getSectionName() {
		return this.sectionName;
	}

	@Override
	public boolean isAnonymous() {
		return this.name == null;
	}

	@Override
	public StatementBlock getRootExecutionBlock() {
		return this.rootBlock;
	}

	@Override
	public Label getEntryPoint() {
		// TODO Figure this out.
		return null;
	}

	@Override
	public void addLabel(Label l) {
		this.labels.add(l);
	}

	@Override
	public List<Label> getLabels() {
		return Collections.unmodifiableList(this.labels);
	}

}
