grammar RapidASM;

unit : unitMember* EOF ;

unitMember : objectDef
           | optionDef
           ;

optionDef : '@' Identifier optionArg* ;
optionArg : Identifier
          | number
          ;

objectDef : ObjectToken Identifier '{' objectMember* '}' ;
objectMember : valDef
             | varDef
             | subDef
             | funcDef
             ;

// Vals get stored in the `.data` section unless otherwise specified.
valDef : label* ValToken sectionSpec? litTypename Identifier '=' literal ';' ;

// Vars get stored in the `.bss` section unless otherwise specified.
varDef : label* VarToken sectionSpec? litTypename Identifier ';' ;

/*
 * Subroutines and funtions are both in the `.text` section unless otherwise
 * specified.  Functions have headers generated for them, and can take a number
 * of arguments.  Subroutines cannot take arguments or return anything, but
 * still can do anything a func can.  You also can have up to one anonymous
 * subroutine that gets all of its code places before everything else in the
 * section, so you can use this like an entry point of sorts.
 */
subDef : label* SubToken sectionSpec? Identifier? statementBlock ;
funcDef : label* FuncToken sectionSpec? Identifier '(' funcArgsDef? ')' statementBlock ;
funcArgsDef : funcArgDef (',' funcArgDef)* ;
funcArgDef : SizeTypename ValIdentifier
           | PointerTypename ValIdentifier
           ;

// Statements
statementBlock : '{' (label* statement ';')* '}' ;
statement : statementBlock
          | instructionStatement
          | assignmentStatement
          | ifStatement
          | whileStatement
          | doWhileStatement
          | returnStatement
          ;

// Actual instructions.
instructionStatement : Identifier instructionArg (',' instructionArg)* ;
instructionArg : number
               | ValIdentifier
               | RegIdentifier
               ;

// Assignment
assignmentStatement : ValIdentifier '=' numericValue
                    | RegIdentifier '=' numericValue
                    ;

// Things involving branching.
ifStatement : Likelihood? IfToken '(' booleanValue ')' statementBlock ;
whileStatement : Likelihood? WhileToken '(' booleanValue ')' statementBlock ;
doWhileStatement : statementBlock Likelihood? WhileToken '(' booleanValue ')' ;
booleanValue : numericValue cmpOp numericValue
             | Boolean
             ;

cmpOp : '==' | '!=' | '<' | '>' | '<=' | '>=' ;
numericValue : ValIdentifier
             | RegIdentifier
             | number
             ;

// Return
returnStatement : ReturnToken numericValue ;

// Misc
sectionSpec : SectionToken '<' Identifier '>' ;
literal : String | number ;
litTypename : StringTypename | SizeTypename ;
label : '!label' String '\n';

// Components
ObjectToken : 'object' ;
SectionToken : 'section' ;
ValToken : 'val' ;
VarToken : 'var' ;
SubToken : 'sub' ;
FuncToken : 'func' ;

// Control flow
IfToken : 'if' ;
Likelihood : 'likely' | 'unlikely' ;
DoToken : 'do' ;
WhileToken : 'while' ;
ReturnToken : 'return' ;

// Numbers
number : DecNumber | HexNumber | BinNumber ;
DecNumber : '-'? [0-9]+ ;
HexNumber : '0x' ([0-9a-fA-F][0-9a-fA-F])+ ;
BinNumber : ([01][01][01][01][01][01][01][01])+ 'b' ;
Boolean : 'true' | 'false' ;

// Typenames
PointerTypename : 'ptr' ;
StringTypename : 'str' ;
SizeTypename : 'i' [0-9]+
             | 'u' [0-9]+
             ;

// Identifiers
ValIdentifier : '$' Identifier ;
RegIdentifier : '%' Identifier ;
Identifier : [a-zA-Z][a-zA-Z0-9]* ;
String : '\'' StringChar* '\'' ;
fragment StringChar : ~["\\\n] | '\\' ;
fragment StrEscapeSeq : '\\' [btnfr"'\\] ;

// Other misc pseudotokens
Whitespace : [ \t\r\n\u000C]+ -> skip ;
MultiCommment : '/*' .*? '*/' -> skip ;
LineComment : '//' ~[\r\n]* '\n' -> skip ;
