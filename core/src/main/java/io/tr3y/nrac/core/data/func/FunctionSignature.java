package io.tr3y.nrac.core.data.func;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import io.tr3y.nrac.core.data.RapidType;

public class FunctionSignature {

	private final RapidType retType;
	private final List<Argument> arguments;

	public FunctionSignature(RapidType type, List<Argument> args) {

		// Validate
		Stream<String> names = args.stream().map(a -> a.name);
		if (names.count() != names.distinct().count())
			throw new IllegalArgumentException("Non-unique argument names.");

		this.retType = type;
		this.arguments = new ArrayList<>(args);

	}

	public RapidType getReturnType() {
		return this.retType;
	}

	public List<Argument> getArguments() {
		return Collections.unmodifiableList(this.arguments);
	}

	public static FunctionSignature create(RapidType type, Object... args) {

		if (args.length % 2 != 0)
			throw new IllegalArgumentException("Improperly formatted function signature macro.");

		List<Argument> sigArgs = new ArrayList<>();
		for (int i = 0; i < args.length; i += 2) {

			Object t = args[i];
			Object n = args[i + 1];

			if (t == null || t.getClass() != RapidType.class)
				throw new IllegalArgumentException("Improperly formatted function signature macro.");

			if (n == null || n.getClass() != String.class)
				throw new IllegalArgumentException("Improperly formatted function signature macro.");

			sigArgs.add(new Argument((String) n, (RapidType) t));

		}

		return new FunctionSignature(type, sigArgs);

	}

	public static class Argument {

		public final String name;
		public final RapidType type;

		public Argument(String name, RapidType type) {

			this.name = name;
			this.type = type;

		}

	}

}
