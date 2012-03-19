package city.test;

import city.math.ComplexSolutionException;
import city.math.FirstOrderBivariate;

public class TestFirstOrderBivariate {
	public static void main (String[] args) {
		try {
			System.out.println(new FirstOrderBivariate(19, -3, 1, -9).solve(new FirstOrderBivariate(-4, 9, 15, -5)));
		} catch (ComplexSolutionException e) {
			e.printStackTrace();
		}
	}
}
