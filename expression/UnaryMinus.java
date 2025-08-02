package expression.expression;

public class UnaryMinus implements AbstractExpression{
    private final AbstractExpression expression;

    public UnaryMinus(AbstractExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int a) {
        return -1 * expression.evaluate(a);
    }

    @Override
    public int evaluate(int a, int b, int c) {
        return -1 * expression.evaluate(a, b, c);
    }

    @Override
    public String toString(){
        return "-(" + expression.toString() + ")";
    }
}
