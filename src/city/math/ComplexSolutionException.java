package city.math;

/**
 * 
 */
public class ComplexSolutionException extends Exception {
    public ComplexSolutionException (double discriminant) {
        super("Complex solution to equation, discriminant: " + discriminant);
    }
}
