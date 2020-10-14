package com.huyong.study.grammar;

import com.huyong.study.grammer.CalculatorLexer;
import com.huyong.study.grammer.CalculatorParser;
import com.huyong.study.grammer.CalculatorVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * 描述:基于antlr的语法解析
 *
 * @author huyong
 * @date 2020-10-13 5:23 下午
 */
public class Test {
    public static void main(String[] args) {
        String query = "3.1 * (6.3 - 4.51) + 1 + 5 * 4";
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(query));
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));
        CalculatorVisitor<Float> visitor = new MyCalculatorVisitor();
        System.out.println(visitor.visit(parser.expr()));
    }
}
