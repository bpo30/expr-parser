package expression.expression;

@FunctionalInterface
@SuppressWarnings("ClassReferencesSubclass")
public interface TripleExpression {
    int evaluate(int x, int y, int z);
}
