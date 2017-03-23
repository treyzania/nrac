package io.tr3y.nrac.core;

import io.tr3y.nrac.core.data.RapidASMUnit;

public interface UnitParser<T> {

	RapidASMUnit parse(T src) throws ParseException;

}
