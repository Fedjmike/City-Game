package city.test;

import city.Pair;
import city.math.ComplexSolutionException;
import city.math.Quadratic;

public class TestQuadratic {
    public static void main (String[] args) {
        try {
            //(2x-3)(x+1) = 2x^2-x-3
            System.out.println(Quadratic.solve(2, -1, -3));
            assert(Quadratic.solve(2, -1, -3)
                    .equals(new Pair<Double, Double>(1.5, -1.0)));

            assert(false);

            System.out.println("Passed");
        } catch (AssertionError e) {
            System.out.println("Failed");
        } catch (ComplexSolutionException e) {
            System.out.println("Shit test");
        }
    }
}
