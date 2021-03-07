package com.huyong.study.grammar;

import com.huyong.study.grammer.CalculatorLexer;
import com.huyong.study.grammer.CalculatorParser;
import com.huyong.study.grammer.CalculatorVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;

import java.util.List;

/**
 * 描述:基于antlr的语法解析
 *
 * @author huyong
 * @date 2020-10-13 5:23 下午
 */
public class Test {
    public static void main(String[] args) {
        String query = "3.1 * (6.3 - 4.51) + 1 + 5 * 4";
        String query2 = "4^4";
        System.out.println(getResultByQuery(query));
        System.out.println(getResultByQuery(query2));

    }

    public static Float getResultByQuery(String query) {
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(query));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        List<Token> tokens = commonTokenStream.getTokens();
        for (Token token : tokens) {
            System.out.println(token.getText());
        }
        CalculatorParser parser = new CalculatorParser(commonTokenStream);
        CalculatorVisitor<Float> visitor = new MyCalculatorVisitor();
        return visitor.visit(parser.expr());
    }
}
