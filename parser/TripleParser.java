package expression.parser;

import expression.expression.TripleExpression;

@FunctionalInterface
public interface TripleParser  {
    TripleExpression parse(String expression);
}
