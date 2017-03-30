package io.tr3y.nrac.arch.x86;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.tr3y.nrac.core.arch.ArchType;
import io.tr3y.nrac.core.arch.Architecture;
import io.tr3y.nrac.core.arch.Register;
import io.tr3y.nrac.core.arch.SimpleRegisterImpl;
import io.tr3y.nrac.core.data.DataWidth;

@ArchType("x86")
public class X86Arch implements Architecture {
	
	private final List<Register> regs = Arrays.asList(new Register[] {
		new SimpleRegisterImpl("al", DataWidth.BYTE),
		new SimpleRegisterImpl("ah", DataWidth.BYTE),
		new SimpleRegisterImpl("ax", DataWidth.SHORT),
		new SimpleRegisterImpl("eax", DataWidth.INT),
		new SimpleRegisterImpl("bl", DataWidth.BYTE),
		new SimpleRegisterImpl("bh", DataWidth.BYTE),
		new SimpleRegisterImpl("bx", DataWidth.SHORT),
		new SimpleRegisterImpl("ebx", DataWidth.INT),
		new SimpleRegisterImpl("cl", DataWidth.BYTE),
		new SimpleRegisterImpl("ch", DataWidth.BYTE),
		new SimpleRegisterImpl("cx", DataWidth.SHORT),
		new SimpleRegisterImpl("ecx", DataWidth.INT),
		new SimpleRegisterImpl("dl", DataWidth.BYTE),
		new SimpleRegisterImpl("dh", DataWidth.BYTE),
		new SimpleRegisterImpl("dx", DataWidth.SHORT),
		new SimpleRegisterImpl("edx", DataWidth.INT),
		new SimpleRegisterImpl("sp", DataWidth.SHORT),
		new SimpleRegisterImpl("esp", DataWidth.INT),
		new SimpleRegisterImpl("bp", DataWidth.SHORT),
		new SimpleRegisterImpl("ebp", DataWidth.INT),
	});
	
	public X86Arch(List<String> params) {
		// TODO
	}

	@Override
	public List<Register> getRegisters() {
		return Collections.unmodifiableList(this.regs);
	}

	@Override
	public DataWidth getWordWidth() {
		return DataWidth.INT;
	}

	@Override
	public DataWidth getPointerWidth() {
		return DataWidth.INT; // Not always.
	}

	@Override
	public String getNaturalizedType(DataWidth width, boolean signed) {
		
		String s = signed ? "s" : "u";
		
		switch (width) {

		case BYTE:
			return s + "int8_t";
		case SHORT:
			return s + "int16_t";
		case INT:
			return s + "int32_t";
		case LONG:
			return s + "int64_t";
		default:
			return "size_t";

		}

	}

}
