# nrac : The *New* RapidASM Compiler

[![Build Status](https://travis-ci.org/treyzania/nrac.svg?branch=master)](https://travis-ci.org/treyzania/nrac)

After making a lot of incorrect decisions in the implementation of my original
compiler for RapidASM, and after finding a few problems with the design of the
language as a whole, I have elected to rewrite it from scratch, but still in
Java as there's nothing wrong with using it for a compiler and ANTLR has really
nice Maven tooling for Java anyways.

You can find the original [here](https://github.com/treyzania/RapidASM-compiler).

## Targets

I hope to have some sort of support for the following platforms:

* x86

* amd64

* [bollocks](https://github.com/treyzania/bollocksvm)

* copper

But I will focus on x86 for now.
