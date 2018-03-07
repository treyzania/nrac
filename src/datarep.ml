
(* Literal value, used for some initialization. *)
type value =
	| Str of string
	| Byte of int
	| Word of int64

(* Simple value definitions, possibly with some initial value. *)
type property = {
	name : string ;
	initial_value : value option
}

(* Arguments to an instruction. *)
type isn_arg =
	| Blob of string (* This is for when we don't know what is going on, like
						in x86 most of the time. *)
	| Literal of value
	| Register of string

(* Loading a value from an argument. *)
type argload_stmt = {
	arg_name : string ;
	destination : string
}

(* Storing a value back in as an argument, probably not used a lot. *)
type argstore_stmt = {
	arg_name : string ;
	source : string
}

(* Returning values, via the stack. *)
type return_stmt = {
	source : isn_arg option
}

(* Statements that are just literal instructions. *)
type instruction_stmt = {
	isn_name : string ;
	prefixes : string list ; (* x86 has a "lock" prefix, etc. *)
	arguments : isn_arg list
}

(* A block of multiple statements. *)
type stmt_block = statement list

(* Some imperative statement in a block. *)
and statement =
	| Nop
	| Block of stmt_block
	| Instruction of instruction_stmt
	| ArgLoad of argload_stmt
	| ArgStore of argstore_stmt
	| Return of return_stmt

(* Executable subroutines that emit assembly code. *)
type subroutine = {
	name : string ;
	body : stmt_block
}

(* Calling conventions. *)
type cconv = string

(* Argument types, only supporting integers for now. *)
type argtype =
	| Pointer
	| U8
	| U16
	| U32
	| U64
	| Buffer of int (* Arbitrary sized stuff, passed as pointers. *)

(* Arguments to functions. *)
type func_arg = {
	name : string ;
	argtype : argtype
}

(* Functions are like subroutines, but actually have a calling conventions and take arguments. *)
type func = {
	name : string ;
	calling_convention : cconv ;
	arguments : func_arg list ;
	body : stmt_block
}

(* Definitions inside of a section. *)
type sec_def =
	| Property of property
	| Subroutine of subroutine

(* Seperate section in a single object file. *)
type section = {
	name : string ;
	defs : sec_def list
}

(* Object file definition. *)
type rsm_object = {
	name : string ;
	sections : section list
}
