package ParticleSwarmOptimizer;

public class Functions {
	
	public static double testFunction1(double x) {
		
		return Math.pow(x, 4) - 2 * Math.pow(x, 3);
	}
	
	public static double testFunction2(double x) {
		
		return Math.pow(x, 7) - 5 * Math.pow(x, 4) + 7 * Math.pow(x, 2) + 1;
	}
	
	public enum FunctionChoices{
		
		testFunction1,
		testFunction2
	}
}
