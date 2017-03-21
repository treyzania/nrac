package io.tr3y.nrac.cli;

public class Main {

	public static void main(String[] args) {

		if (args.length != 1 || args[0].equals("seejvmargs")) {

			System.out.println("error: use the provided wrapper script to use nrac");
			return;

		}

		String src = System.getProperty("nrac.rt.src");
		String destAsm = System.getProperty("nrac.rt.dest.asm");
		String destHeader = System.getProperty("nrac.rt.dest.h");
		String arch = System.getProperty("nrac.rt.arch");

		// TODO Connect the parameters to actually executes the compiler.
		
	}

}
