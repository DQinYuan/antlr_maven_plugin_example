package org.du.antlrtest.script;

import java.util.HashMap;
import java.util.Map;


public class ASTEvaluator extends PlayScriptBaseVisitor<Integer> {
    Map<String, Integer> memory = new HashMap<String, Integer>();

    @Override
    public Integer visitAdditiveExpression(PlayScriptParser.AdditiveExpressionContext ctx) {
        if (ctx.getToken(PlayScriptParser.ADD, 0) != null || ctx.getToken(PlayScriptParser.SUB, 0) != null) {
            Integer left = visitAdditiveExpression(ctx.additiveExpression());
            Integer right = visitMultiplicativeExpression(ctx.multiplicativeExpression());
            if (ctx.getToken(PlayScriptParser.ADD, 0) != null) {
                return left + right;
            } else {
                return left - right;
            }
        } else {
            return visitMultiplicativeExpression(ctx.multiplicativeExpression());
        }
    }

    @Override
    public Integer visitArgumentExpressionList(PlayScriptParser.ArgumentExpressionListContext ctx) {
        return super.visitArgumentExpressionList(ctx);
    }

    @Override
    public Integer visitAssignmentExpression(PlayScriptParser.AssignmentExpressionContext ctx) {
        String id = ctx.Identifier().getText();
        System.out.println(id);
        return super.visitAssignmentExpression(ctx);
    }

    @Override
    public Integer visitBlockItem(PlayScriptParser.BlockItemContext ctx) {
        return super.visitBlockItem(ctx);
    }

    @Override
    public Integer visitBlockItemList(PlayScriptParser.BlockItemListContext ctx) {
        return super.visitBlockItemList(ctx);
    }

    @Override
    public Integer visitCompoundStatement(PlayScriptParser.CompoundStatementContext ctx) {
        return super.visitCompoundStatement(ctx);
    }

    @Override
    public Integer visitDeclaration(PlayScriptParser.DeclarationContext ctx) {
        return super.visitDeclaration(ctx);
    }

    @Override
    public Integer visitExpression(PlayScriptParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public Integer visitExpressionStatement(PlayScriptParser.ExpressionStatementContext ctx) {
        return super.visitExpressionStatement(ctx);
    }

    @Override
    public Integer visitInitializer(PlayScriptParser.InitializerContext ctx) {
        return super.visitInitializer(ctx);
    }

    @Override
    public Integer visitMultiplicativeExpression(PlayScriptParser.MultiplicativeExpressionContext ctx) {
        if (ctx.getToken(PlayScriptParser.MUL, 0) != null || ctx.getToken(PlayScriptParser.DIV, 0) != null
                || ctx.getToken(PlayScriptParser.MOD, 0) != null) {
            Integer left = visitMultiplicativeExpression(ctx.multiplicativeExpression());
            Integer right = visitPrimaryExpression(ctx.primaryExpression());
            if (ctx.getToken(PlayScriptParser.MUL, 0) != null) {
                return left * right;
            } else if (ctx.getToken(PlayScriptParser.DIV, 0) != null) {
                return left / right;
            } else {
                return left % right;
            }
        } else {
            return visitPrimaryExpression(ctx.primaryExpression());
        }
    }

    @Override
    public Integer visitPrimaryExpression(PlayScriptParser.PrimaryExpressionContext ctx) {
        if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        }
        return 0;
    }

    @Override
    public Integer visitStatement(PlayScriptParser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    @Override
    public Integer visitAssignmentOperator(PlayScriptParser.AssignmentOperatorContext ctx) {
        return super.visitAssignmentOperator(ctx);
    }

    @Override
    public Integer visitLiteral(PlayScriptParser.LiteralContext ctx) {
        if (ctx.IntegerLiteral() !=null){
            return Integer.valueOf(ctx.IntegerLiteral().getText());
        }
        return 0;
    }

    @Override
    public Integer visitPrimitiveType(PlayScriptParser.PrimitiveTypeContext ctx) {
        return super.visitPrimitiveType(ctx);
    }

}
