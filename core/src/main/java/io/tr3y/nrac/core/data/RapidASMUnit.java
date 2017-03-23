package io.tr3y.nrac.core.data;

import java.util.Collections;
import java.util.List;

public final class RapidASMUnit {

	private final List<BuildObject> objects;

	public RapidASMUnit(List<BuildObject> objs) {
		this.objects = objs;
	}

	public List<BuildObject> getObjects() {
		return Collections.unmodifiableList(this.objects);
	}

}
