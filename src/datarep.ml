
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
	| Argument of string (* Function arguments, when we support that. *)

(* Statements that are just literal instructions. *)
type instruction_stmt = {
	isn_name : string ;
	prefixes : string list ;
	arguments : isn_arg list
}

(* Some imperative statement in a block. *)
type statement =
	| Nop
	| Instruction of instruction_stmt
	| Block of statement list

(* Executable subroutines that emit assembly code. *)
type subroutine = {
	name : string ;
	body : statement list
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
