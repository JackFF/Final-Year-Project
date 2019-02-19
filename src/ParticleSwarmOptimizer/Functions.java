package ParticleSwarmOptimizer;

public class Functions {
	
	public static double testFunction1(double x) {
		
		return Math.pow(x, 4) - 2 * Math.pow(x, 3);
	}
	
	public static double testFunction2(double x) {
		
		return Math.pow(x, 6) - 5 * Math.pow(x, 4) + 7 * Math.pow(x, 2) + 1;
	}
	
	public static double boothsFunction(double x, double y) {
		
		double p1 = Math.pow(x + 2*y - 7, 2);
		double p2 = Math.pow(2*x + y -5, 2);
		
		return p1 + p2;
	}
	
	public static double ackleysFunction (double x, double y) {
		
        double p1 = -20*Math.exp(-0.2*Math.sqrt(0.5*((x*x)+(y*y))));
        double p2 = Math.exp(0.5*(Math.cos(2*Math.PI*x)+Math.cos(2*Math.PI*y)));
        return p1 - p2 + Math.E + 20;
    }
	
	public enum FunctionChoices{
		
		testFunction1,
		testFunction2,
		boothsFunction,
		ackleysFunction
	}
}
