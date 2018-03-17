%{
#include <stdio.h>
FILE *Line;
int yyline=1;
%}

DIGIT [0-9]

NUMBER {DIGIT}+

REAL {DIGIT}*"."{DIGIT}+

TEXT [a-zA-Z ]+

TEXT_NUMBERS [a-zA-Z0-9]

IF "if"

ELSE "else"

ELSE_IF "else if"

SWITCH "switch"

CASE "case"

KEYWORD "unsigned"|"signed"|"short"|"long"|"static"

IO "printf"|"scanf"

FILE_IO "fopen"|"fwrite"|"fread"|"fclose"|"write"|"read"|"open"|"close"

DATATYPE "int"|"float"|"char"|"double"|"void"|"boolean"|"bool"

FOR "for"

WHILE "while"

DO "do"

BREAK "break"

CONTINUE "continue"

RETURN "return"

DEFAULT "default"

PREPROCESSOR "#"|"#line"|"#undef"|"#error"|"#elif"|"#else"|"#endif"|"#if"|"#define"|"#include"|"#pragma"|"#ifndef"|"#ifdef"

FILE "<"{IDENTIFIER}.h">"

HEADER {PREPROCESSOR}{DELIMITER}*{FILE}

DELIMITER [%;, :\t\n()"]

IDENTIFIER [a-zA-Z]{TEXT_NUMBERS}*|[a-zA-Z]{TEXT_NUMBERS}*[[{NUMBER}+]]

NON_IDENTIFIER {NUMBER}+[A-Za-z]+

FORMAT_SPECIFIER "%"{IDENTIFIER}+

BLOCK_BEGINS "{"

BLOCK_ENDS "}"

UNARY "++"|"--"

LOPERATOR "&"|"|"|"&&"|"~"|"||"|">"|"<"|">="|"<="|"=="

AOPERATOR "+"|"-"|"*"|"/"|"="

MAIN "main"{DELIMITER}*"("({DELIMITER}*{DATATYPE}*{DELIMITER}*{TEXT}*{AOPERATOR}*{DELIMITER}*)*")"

FUNCTION_CALL {IDENTIFIER}+"("{DELIMITER}*{TEXT}{TEXT_NUMBERS}*{DELIMITER}*")"

FUNCTION {DATATYPE}{DELIMITER}{IDENTIFIER}{DELIMITER}*"("({DELIMITER}*{TEXT}*{DELIMITER}*)*")"

ARRAY {IDENTIFIER}{DELIMITER}*"["{DELIMITER}*{IDENTIFIER}{DELIMITER}*"]"

%%

\/\*(.|\n)*\*\/
\/\/.*

\n {yyline++;}

{DIGIT} { fprintf(yyout,"a"); fprintf(Line,"%d ",yyline); }

{NUMBER} { fprintf(yyout,"b"); fprintf(Line,"%d ",yyline); }

{REAL} { fprintf(yyout,"c"); fprintf(Line,"%d ",yyline); }

{IF} { fprintf(yyout,"d"); fprintf(Line,"%d ",yyline); }

{ELSE} { fprintf(yyout,"e"); fprintf(Line,"%d ",yyline); }

{ELSE_IF} { fprintf(yyout,"f"); fprintf(Line,"%d ",yyline); }

{SWITCH} { fprintf(yyout,"g"); fprintf(Line,"%d ",yyline); }

{CASE} { fprintf(yyout,"h"); fprintf(Line,"%d ",yyline); }

{KEYWORD} { fprintf(yyout,"i"); fprintf(Line,"%d ",yyline); }

{DATATYPE} { fprintf(yyout,"j"); fprintf(Line,"%d ",yyline); }

{FOR} { fprintf(yyout,"k"); fprintf(Line,"%d ",yyline); }

{WHILE} { fprintf(yyout,"l"); fprintf(Line,"%d ",yyline); }

{DO} { fprintf(yyout,"m"); fprintf(Line,"%d ",yyline); }

{BREAK} { fprintf(yyout,"n"); fprintf(Line,"%d ",yyline); }

{CONTINUE} { fprintf(yyout,"o"); fprintf(Line,"%d ",yyline); }

{RETURN} { fprintf(yyout,"p"); fprintf(Line,"%d ",yyline); }

{PREPROCESSOR} { fprintf(yyout,"q"); fprintf(Line,"%d ",yyline); }

{FILE} { fprintf(yyout,"r"); fprintf(Line,"%d ",yyline); }

{MAIN} { fprintf(yyout,"s"); fprintf(Line,"%d ",yyline); }

{HEADER} { }

{DELIMITER} { }

{IDENTIFIER} { fprintf(yyout,"t"); fprintf(Line,"%d ",yyline); }

{NON_IDENTIFIER} { }

{BLOCK_BEGINS} { fprintf(yyout,"u"); fprintf(Line,"%d ",yyline); }

{BLOCK_ENDS} { fprintf(yyout,"v"); fprintf(Line,"%d ",yyline); }

{UNARY} { fprintf(yyout,"w"); fprintf(Line,"%d ",yyline); }

{AOPERATOR} { fprintf(yyout,"x"); fprintf(Line,"%d ",yyline); }

{LOPERATOR} { fprintf(yyout,"y"); fprintf(Line,"%d ",yyline); }

{FUNCTION_CALL} { fprintf(yyout,"z"); fprintf(Line,"%d ",yyline); }

{FUNCTION} { fprintf(yyout,"A"); fprintf(Line,"%d ",yyline); }

{ARRAY} { fprintf(yyout,"B"); fprintf(Line,"%d ",yyline); }

%%

int main(int argc, char *argv[]) 
{
	yyin = fopen(argv[1],"r");
	yyout = fopen(argv[2],"a");
	Line = fopen(argv[3], "a");
	yylex();
	fclose(Line);
	return 0;
}
