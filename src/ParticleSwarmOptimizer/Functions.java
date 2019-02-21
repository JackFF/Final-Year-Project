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
	
	public static double ackleysFunction(double x, double y) {
		
        double p1 = -20*Math.exp(-0.2*Math.sqrt(0.5*((x*x)+(y*y))));
        double p2 = Math.exp(0.5*(Math.cos(2*Math.PI*x)+Math.cos(2*Math.PI*y)));
        return p1 - p2 + Math.E + 20;
    }
	
	public static double sphere(double x, double y, double z) {
		
        return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
    }
	
	public static double rosenbrock(double x, double y) {
		
        double p1 = Math.pow((1 - x), 2);
        double p2 = Math.pow(x, 2);
        double p3 = (y - p2);
        double p4 = Math.pow(p3, 2);
        double p5 = (p4 * 100);
        double p6 = p1 + p5;
        
        return p6;
    }
	
	public enum FunctionChoices{
		
		testFunction1,
		testFunction2,
		boothsFunction,
		ackleysFunction,
		sphere,
		rosenbrock
	}
}
