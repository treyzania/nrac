package io.tr3y.nrac.core.data.func;

import io.tr3y.nrac.core.data.Executable;
import io.tr3y.nrac.core.data.Label;
import io.tr3y.nrac.core.data.Labelable;

public interface AsmCallable extends Executable, Labelable {

	Label getEntryPoint();

}
