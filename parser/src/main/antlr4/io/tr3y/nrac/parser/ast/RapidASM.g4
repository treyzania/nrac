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
objectMember : subDef
             | funcDef
             ;

subDef : SubToken sectionSpec? Identifier? statementBlock ;
funcDef : FuncToken sectionSpec? Identifier '(' funcArgsDef? ')' statementBlock ;
funcArgsDef : funcArgDef (',' funcArgDef)* ;
funcArgDef : Size ValIdentifier ;

// Statements
statementBlock : '{' (statement ';')* '}' ;
statement : ';' ; // TODO Add more statements.

sectionSpec : SectionToken '<' Identifier '>' ;

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

Size : 'i' [0-9]+
     | 'u' [0-9]+
     ;

// Identifiers
ValIdentifier : '$' Identifier ;
RegIdentifier : '%' Identifier ;
Identifier : [a-zA-Z][a-zA-Z0-9]* ;

Whitespace : [ \t\r\n\u000C]+ -> skip ;

// Comments
MultiCommment : '/*' .*? '*/' -> skip ;
LineComment : '//' ~[\r\n]* '\n' -> skip ;
