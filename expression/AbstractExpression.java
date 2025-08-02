package expression.expression;

public interface AbstractExpression extends Expression, TripleExpression{

    int evaluate(int a);

    int evaluate(int a, int b, int c);

}
