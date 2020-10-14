package com.huyong.study.grammar;

import com.huyong.study.grammer.CalculatorBaseVisitor;
import com.huyong.study.grammer.CalculatorParser;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-14 9:54 上午
 */
public class MyCalculatorVisitor extends CalculatorBaseVisitor<Float> {
    @Override
    public Float visitParenExpr(CalculatorParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Float visitMultOrDiv(CalculatorParser.MultOrDivContext ctx) {
        Float obj0 = ctx.expr(0).accept(this);
        Float obj1 = ctx.expr(1).accept(this);

        if ("*".equals(ctx.getChild(1).getText())) {
            return obj0 * obj1;
        } else if ("/".equals(ctx.getChild(1).getText())) {
            return obj0 / obj1;
        }
        return 0f;
    }
    @Override
    public Float visitAddOrSubstract(CalculatorParser.AddOrSubstractContext ctx) {
        Float obj0 = ctx.expr(0).accept(this);
        Float obj1 = ctx.expr(1).accept(this);

        if ("+".equals(ctx.getChild(1).getText())) {
            return obj0 + obj1;
        } else if ("-".equals(ctx.getChild(1).getText())) {
            return obj0 - obj1;
        }
        return 0f;
    }

    @Override
    public Float visitFloat(CalculatorParser.FloatContext ctx) {
        return Float.parseFloat(ctx.getText());
    }
}
