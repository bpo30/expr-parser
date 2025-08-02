package expression.expression;

public class Divide extends BinaryOperation{

    public Divide(AbstractExpression ex1, AbstractExpression ex2) {
        super(ex1, ex2);
    }

    @Override
    protected int calculate(int a, int b) {
        return  a / b;
    }

    @Override
    protected String getOperation() {
        return "/";
    }

    @Override
    public String toString() {
        return "(" + ex1.toString() + " / " + ex2.toString() + ")";
    }
}
