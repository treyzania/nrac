# nrac : The *New* RapidASM Compiler

After making a lot of incorrect decisions in the implementation of my original
compiler for RapidASM, and after finding a few problems with the design of the
language as a whole, I have elected to rewrite it from scratch, but this time in
OCaml since it's a great language.

You can find the original [here](https://github.com/treyzania/RapidASM-compiler).

## Targets

I hope to have some sort of support for the following platforms:

* x86/amd64

* riscv

But I will focus on x86/amd64 for now.
