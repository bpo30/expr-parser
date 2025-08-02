package expression.expression;

@FunctionalInterface
@SuppressWarnings("ClassReferencesSubclass")
public interface Expression {

    int evaluate(int x);
}
