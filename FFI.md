# RapidASM FFI Documentation

RapidASM's FFI is rather similar to the cdecl calling convention.  What is
a little strange is that the RSM calling convention effectively *is* the FFI
because *every function* in RSM behaves like a foreign function call.  This
document currently refers only to the x86 implementation right now, but it
would be relatively similar on other architectures.

## Calling

Let's take an object like this:

```
object foo {
	func u32 bar(u16 a, ptr b) {
		// ...
	}
}
```

Push the arguments onto the stack from left to right.  So if we assume the
following mappings:

* `a`: `ax`
* `b`: `edx`

...then this would translate into:

```asm
pushw %ax
pushl %edx
```

The name mangling is usually pretty consistent, but for our function above
would be `rsm_foo_bar`.  So the full sequence for calling our function `bar`
would be this:

```asm
pushw %ax
pushl %edx
call rsm_foo_bar
```

## Returning

Returns are a bit of the reverse.  You push the return value onto the stack and
execute the `ret` instruction.  So if we assume that for some reason our return
value is in our `ebx` register, then our whole call procedure would be the
following, including cleaning up the stack (assuming the stack grows downward):

```asm
pushl %ebx
ret
```

But this happens automatically, so we don't have to actually write it, the RSM
code for this would be:

```
return %ebx;
```

And you could get that value back and into `ebx` again (after cleaning up the
stack):

```asm
popl %ebx
add %esp, $6
```

## Conclusion

If we wanted to write a wrapper function for this `bar` function in C, then it
would probably look something like the following.  Remember that `nrac` does
this all for you, so you generally don't have to unless you're doing something
special.

```c
inline uint32_t barwrapper(uint16_t a, void* b) {	
	uint32_t ret = 0;
	size_t diff = sizeof(uint16_t) + sizeof(void*);
	asm (
		"push %1\n\t"
		"push %2\n\t"
		"call rsm_bar_foo\n\t"
		"pop %0\n\t"
		"add %%esp, %3"
		: "=g" (ret)
		: "g" (a), "g" (b), "g" (diff)
		: "cc" // If the function does any branching, etc.
		);
	return ret;
}
```

