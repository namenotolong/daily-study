grammar Calculator;
@after {System.out.println("after matching rule; before finally");}

line : expr EOF ;
expr : '(' expr ')'             # parenExpr
     | expr ('*'|'/') expr      # multOrDiv
     | expr ('+'|'-') expr      # addOrSubstract
     | expr ('^') expr      # power
     | FLOAT                    # float
     ;


WS : [ \t\n\r]+ -> skip;
FLOAT : DIGIT+ '.' DIGIT* EXPONET?
      | '.' DIGIT+ EXPONET?
      | DIGIT+ EXPONET?
      ;

SPACE: [ \t\r\n]+ -> channel(HIDDEN);
//这些碎片规则不会暴露给parser
fragment DIGIT : '0'..'9' ;
fragment EXPONET : ('e'|'E') ('+'|'-')? DIGIT+ ;
