package expression.expression;

import java.util.Objects;

public class Const implements AbstractExpression {

    private final int constVal;

    public Const(int constVal) {
        this.constVal = constVal;
    }

    public int evaluate(int a) {
        return constVal;
    }

    public int evaluate(int a, int b, int c) {
        return constVal;
    }

    @Override
    public String toString() {
        return Integer.toString(constVal);
    }

    public int hashCode() {
        return Objects.hashCode(constVal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Const aConst)) return false;
        return constVal == aConst.constVal;
    }
}
