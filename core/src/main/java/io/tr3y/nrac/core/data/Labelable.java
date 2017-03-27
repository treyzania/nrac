package io.tr3y.nrac.core.data;

import java.util.List;

public interface Labelable {

	void addLabel(Label l);

	List<Label> getLabels();

}
