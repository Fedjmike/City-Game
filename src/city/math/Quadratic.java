package city.math;

import city.Pair;

public class Quadratic {
	private Quadratic () {}
	
	//TODO: Override clone
	
	/**
	 * Return two real solutions to the equation ax^2+bx+c = 0
	 * 
	 * Returns the larger of the two solutions first.
	 */
	public static Pair<Double, Double> solve (double a, double b, double c)
			throws ComplexSolutionException {
		if (a == 0)
			return new Pair<Double, Double>(-c/b, -c/b);
		
		else {
			double discriminant = b*b-4*a*c;
			
			if (discriminant < 0)
				throw new ComplexSolutionException(discriminant);
			
			else if (discriminant == 0)
				return new Pair<Double, Double>(-b/(2*a), -b/(2*a));
				
			else
				return new Pair<Double, Double>((-b+Math.sqrt(discriminant))/(2*a),
												(-b-Math.sqrt(discriminant))/(2*a));
		}
	}
	
	public static String toString (double a, double b, double c) {
		return a + "x^2 + " + b + "x + " + c;
	}
}
