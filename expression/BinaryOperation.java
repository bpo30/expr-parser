package expression.expression;

import java.util.Objects;

public abstract class BinaryOperation implements AbstractExpression {

    protected AbstractExpression ex1;
    protected AbstractExpression ex2;

    protected BinaryOperation(AbstractExpression ex1, AbstractExpression ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    protected abstract int calculate(int a, int b);

    public int evaluate(int a) {
        return calculate(ex1.evaluate(a), ex2.evaluate(a));
    }


    public int evaluate(int a, int b, int c) {
        return calculate(ex1.evaluate(a, b, c), ex2.evaluate(a, b, c));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryOperation that)) return false;
        return Objects.equals(ex1, that.ex1)
                && Objects.equals(ex2, that.ex2)
                && Objects.equals(getOperation(), that.getOperation());
    }

    public int hashCode() {
        return Objects.hash(ex1, ex2, getOperation());
    }

    public String toString() {
        return ("(" + ex1.toString() + getOperation() + ex2.toString() + ")");
    }

    protected abstract String getOperation();
}