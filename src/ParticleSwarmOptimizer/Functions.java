package ParticleSwarmOptimizer;

import java.util.ArrayList;

public class Functions {
	
	public static double sphere(ArrayList<Double> coords, int dimensions) {
		
		double p1 = 0.0;
		
		for(int i = 0; i < dimensions; i++){
			
			double x = 0;
			x = coords.get(i);
			
			p1 += ((x)*(x));
		}
		
		return p1;
	}
	
	public static double rosenbrock(ArrayList <Double> coords, int dimensions){
		
		double p1 = 0;
		double p2 = 0;
		double p3 = 0;
		
		for(int i = 0; i < dimensions-1; i++) {
			
			double x = coords.get(i);
			double x1 = coords.get(i+1);
			
			p1 = Math.pow(((x * x) - (x1)), 2);
			p2 = Math.pow((x - 1), 2);
			p3 = p3 + (100 * p1) + p2;
		}
		
		return p3;
	}
	
	public static double ackley(ArrayList <Double> coords, int dimensions){
		
		/*return -20.0*Math.exp(-0.2*Math.sqrt(sum1 / ((double )x.length))) + 20
                - Math.exp(sum2 /((double )x.length)) + Math.exp(1.0);*/
		
		double p1 = 0.0;
		double p2 = 0.0;
		double p3 = 0.0;
		
		for(int i = 0; i < dimensions; i++) {
			
			double x = coords.get(i);
			p1 = p1 + Math.pow(x, 2); 
			p2 = p2 + Math.cos(2 * Math.PI * x);
		}
		
		//p3 = -(20 * Math.exp(-0.2 * Math.sqrt(p1 / dimensions))) - (Math.exp(p2 / dimensions)) + 20 + (Math.E);
		p3 = -20 * Math.exp(-0.2 * Math.sqrt(p1 / (double) (dimensions))) + 20 - Math.exp(p2 / (double) (dimensions)) + Math.exp(1.0);
		
		return p3;
	}
	
	public static double griewank(ArrayList <Double> coords, int dimensions){
		
		double p1 = 0.0;
		
		double p2 = 1.0;
		
		for(int i = 0; i < dimensions; i++){
			
			double x  = coords.get(i);
			p1 = p1 + (x*x); 
			p2 = p2 * Math.cos(x/Math.sqrt(i+1)); 
		}
		double p3 = 1 + (p1/4000) - (p2);
		
		return p3;
	}
	
	public static double rastrigin(ArrayList <Double> coords, int dimensions){
		
		double p1 = 0;
		
		for(int i = 0; i < dimensions; i++) {
			
			double x = coords.get(i);
			p1 = p1 + (x * x) - 10 * Math.cos(2 * Math.PI * x) + 10;
		}
		
		return p1;
	}
	
	public static double schaffer2D(ArrayList <Double> coords, int dimensions){
		
		double p1 = 0;
		double p2 = 0;
		double p3 = 0;
		
		double x = coords.get(0);
		double y = coords.get(1);
		
		p1 = Math.pow(Math.sin(Math.sqrt((x * x) + (y * y))), 2)  - 0.5;
		p2 = Math.pow((1 + (0.0001 * ((x * x)+(y * y)))), 2);
		p3 = 0.5 + (p1 / p2);
		
		return p3;
	}
	
	public static double griewank10D(ArrayList <Double> coords, int dimensions){
		
		double p1 = 0.0;
		
		double p2 = 1.0;
		
		for(int i = 0; i < dimensions; i++){
			
			double x  = coords.get(i);
			p1 = p1 + (x*x); 
			p2 = p2 * Math.cos(x/Math.sqrt(i+1)); 
		}
		double p3 = 1 + (p1/4000) - (p2);
		
		return p3;
	}
	
	public enum FunctionChoices{
		
		sphere,
		rosenbrock,
		ackley,
		griewank,
		rastrigin,
		schaffer2D,
		griewank10D
	}
}
