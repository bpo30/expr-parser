package expression.expression;

import java.util.Objects;

public class Variable implements AbstractExpression {
    private final String varName;

    public Variable(String varName) {
        this.varName = varName;
    }

    public int evaluate(int a) {
        return a;
    }

    @Override
    public int evaluate(int a, int b, int c) {
        return switch (varName) {
            case "x" -> a;
            case "y" -> b;
            case "z" -> c;
            default -> throw new IllegalStateException();
        };
    }

    @Override
    public String toString() {
        return varName;
    }

    public int hashCode() {
        return Objects.hashCode(varName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable variable)) return false;
        return Objects.equals(varName, variable.varName);
    }
}