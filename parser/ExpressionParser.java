package expression.parser;

import expression.expression.*;

import java.util.Map;
import java.util.Stack;

public class ExpressionParser implements TripleParser {

    private final Map<String, Integer> priority = Map.of(
            "- ", 9,
            "*", 8,
            "/", 8,
            "+", 7,
            "-", 7,
            "&", 6,
            "^", 5,
            "|", 4
    );

    private final Stack<String> operations = new Stack<>();
    private final Stack<AbstractExpression> expressions = new Stack<>();

    private String previousToken;
    private boolean isLastOp = false;

    @Override
    public TripleExpression parse(String expression) {
        operations.clear();
        expressions.clear();
        int i = 0;
        int indexStart = 0;

        String token = "";
        previousToken = null;
        char symbol;

        while (i < expression.length()) {
            symbol = expression.charAt(i);

            if (isOperation(symbol) || isBracket(symbol)) {
                if (symbol == '-' && i < expression.length() - 1) {
                    if (!isUnaryMinus()) {
                        isLastOp = true;
                        pushToOpStack("-");
                    } else {
                        if (!Character.isDigit(expression.charAt(i + 1))) {
                            previousToken = "- ";
                            operations.push("- ");
                            isLastOp = true;
                        } else {
                            i++;

                            indexStart = i;
                            while (i <= expression.length() - 1 && Character.isDigit(expression.charAt(i))) {
                                i++;
                            }
                            i--;

                            token = expression.substring(indexStart, i > expression.length() - 1 ? i : i + 1);
                            previousToken = token;
                            isLastOp = false;
                            expressions.push(new Const(Integer.parseInt("-" + token)));
                        }
                    }
                } else {
                    pushToOpStack(String.valueOf(expression.charAt(i)));
                    isLastOp = isOperation(expression.charAt(i));
                }
            } else if (!Character.isWhitespace(symbol)) {
                indexStart = i;

                while (i <= expression.length() - 1 && Character.isLetterOrDigit(expression.charAt(i))) {
                    i++;
                }

                i--;
                token = expression.substring(indexStart, i > expression.length() - 1 ? i : i + 1);
                isLastOp = false;
                previousToken = token;

                if (!token.isEmpty()) {
                    expressions.add(
                            Character.isDigit(token.charAt(token.length() - 1)) ?
                                    new Const(Integer.parseInt(token)) :
                                    new Variable(token));
                }
                token = "";
            }
            i++;
        }
        while (!operations.isEmpty()) {
            pushToExpressionStack(operations.pop());
        }
        return expressions.pop();
    }

    private void pushToOpStack(String token) {

        if (isBracket(token.charAt(0))) {
            if (token.equals(")")) {
                while (!operations.peek().equals("(")) {
                    pushToExpressionStack(operations.pop());
                }
                operations.pop();
            } else {
                previousToken = token;
                operations.push(token);
            }
        } else {
            if (!operations.isEmpty() && !operations.peek().equals("(")) {
                while (!(operations.isEmpty() || operations.peek().equals("("))
                        && priority.get(token) <= priority.get(operations.peek())) {
                    pushToExpressionStack(operations.pop());
                }
            }
            operations.push(token);
            previousToken = token;
        }
    }

    private void pushToExpressionStack(String token) {
        AbstractExpression temp;
        AbstractExpression anTemp = expressions.pop();
        switch (token) {
            case ("- ") -> temp = new UnaryMinus(anTemp);
            case ("+") -> temp = new Add(expressions.pop(), anTemp);
            case ("-") -> temp = new Subtract(expressions.pop(), anTemp);
            case ("*") -> temp = new Multiply(expressions.pop(), anTemp);
            case ("/") -> temp = new Divide(expressions.pop(), anTemp);
            case ("&") -> temp = new BitwiseAnd(expressions.pop(), anTemp);
            case ("^") -> temp = new BitwiseXor(expressions.pop(), anTemp);
            case ("|") -> temp = new BitwiseOr(expressions.pop(), anTemp);
            default -> throw new RuntimeException();
        }
        expressions.push(temp);
    }

    private boolean isOperation(char symbol) {
        return switch (symbol) {
            case ('+'), ('-'), ('*'), ('/'), ('~'), ('&'), ('|'), ('^') -> true;
            default -> false;
        };
    }

    private boolean isBracket(char symbol) {
        return switch (symbol) {
            case ('('), (')') -> true;
            default -> false;
        };
    }

    private boolean isUnaryMinus() {
        return previousToken == null
                || previousToken.equals("(")
                || previousToken.isEmpty()
                || isLastOp;
    }


}
