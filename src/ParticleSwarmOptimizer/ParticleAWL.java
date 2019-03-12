package ParticleSwarmOptimizer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class ParticleAWL {
	
	private FunctionChoices function;
	private ArrayList<Double> location;
	private ArrayList<Double> velocity;
	double minRange = 0;
	double maxRange = 0;
	private double pBest;
	private ArrayList<Double> pBestLocation;
	private double pWorst;
	private ArrayList<Double> pWorstLocation;
	int dimensions;

	public ParticleAWL(FunctionChoices function, int dimensions) {
		
		this.function = function;
		this.dimensions = dimensions;
		location = setInitialPosition(dimensions);
		//System.out.println("Initial Locations: " + location);
		velocity = setInitial(dimensions);
		pBestLocation = setInitial(dimensions);
		pWorstLocation = setInitial(dimensions);
		pBest = getInitialPBest();
		pWorst = getInitialPBest();
		velocity = setInitialVelocity(dimensions);
	}

	public ParticleAWL() {
		
	}

	public ArrayList<Double> setInitial(int dimensions) {
		
		ArrayList<Double> coords = new ArrayList<Double>();
		
		for(int i = 0; i < dimensions; i++) {
			
			coords.add(0.0);
		}
		
		return coords;
	}

	private ArrayList<Double> setInitialPosition(int dimensions) {
		
		if(function == FunctionChoices.sphere) {
			
			minRange = -5.12;
			maxRange = 5.12;
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			minRange = -2.048;
			maxRange = 2.048;
		}
		
		else if(function == FunctionChoices.ackley) {
			
			minRange = -32;
			maxRange = 32;
		}
		
		else if(function == FunctionChoices.griewank) {
			
			minRange = -600;
			maxRange = 600;
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			minRange = -5.12;
			maxRange = 5.12;
		}
		
		else if(function == FunctionChoices.schaffer2D) {
			
			minRange = -100;
			maxRange = 100;
		}
		
		else if(function == FunctionChoices.griewank10D) {
			
			minRange = -600;
			maxRange = 600;
		}
		
		ArrayList<Double> coords = new ArrayList<Double>();
		
		for(int i = 0; i < dimensions; i++) {
			
			double j = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
			
			coords.add(j);
		}
		
		return coords;
	}
	
	private ArrayList<Double> setInitialVelocity(int dimensions) {
		
		double mnR = 0;
		double mxR = 0;
		
		if(function == FunctionChoices.sphere) {
			
			mnR = -10.24;
			mxR = 10.24;
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			mnR = -(2.048 * 2);
			mxR = (2.048 * 2);
		}
		
		else if(function == FunctionChoices.ackley) {
			
			mnR = -(32 * 2);
			mxR = (32 * 2);
		}
		
		else if(function == FunctionChoices.griewank) {
			
			mnR = -1200;
			mxR = 1200;
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			mnR = -(5.12 * 2);
			mxR = (5.12 * 2);
		}
		
		else if(function == FunctionChoices.schaffer2D) {
			
			mnR = -200;
			mxR = 200;
		}
		
		else if(function == FunctionChoices.griewank10D) {
			
			mnR = -1200;
			mxR = 1200;
		}
		
		ArrayList<Double> coords = new ArrayList<Double>();
		
		for(int i = 0; i < dimensions; i++) {
			
			double j = ThreadLocalRandom.current().nextDouble(mnR, mxR);
			
			coords.add(j);
		}
		
		return coords;
	}

	private double getInitialPBest() {
		
		if(function == FunctionChoices.sphere) {
			
			pBestLocation = location;
			pWorstLocation = location;
			
			return Functions.sphere(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			pBestLocation = location;
			pWorstLocation = location;
			
			return Functions.rosenbrock(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.ackley) {
			
			pBestLocation = location;
			pWorstLocation = location;
			
			return Functions.ackley(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.griewank) {
			
			pBestLocation = location;
			pWorstLocation = location;
			
			return Functions.griewank(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			pBestLocation = location;
			pWorstLocation = location;
			
			return Functions.rastrigin(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.schaffer2D) {
			
			pBestLocation = location;
			pWorstLocation = location;
			
			return Functions.schaffer2D(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.griewank10D) {
			
			pBestLocation = location;
			pWorstLocation = location;
			
			return Functions.griewank10D(getLocation(), dimensions);
		}
		
		else {
			
			return 0;
		}
	}
	
	private double getFitness() {
		
		if(function == FunctionChoices.sphere) {
			
			return Functions.sphere(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			return Functions.rosenbrock(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.ackley) {
			
			return Functions.ackley(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.griewank) {
			
			return Functions.griewank(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			return Functions.rastrigin(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.schaffer2D) {
			
			return Functions.schaffer2D(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.griewank10D) {
			
			return Functions.griewank10D(getLocation(), dimensions);
		}
		
		else {
			
			return 0;
		}
	}
	
	public void updatePBest(ParticleAWL particle) {
		
		double currentFitness = getFitness();
		
		//System.out.println(currentFitness);
		
		if(currentFitness < particle.getPBest()) {
			
			pBest = currentFitness;
			//System.out.println("pBest Ori: " + pBest);
			//pBestLocation = particle.getLocation();
			//System.out.println("pBest Ori Loc: " + pBestLocation);
			particle.updatePBestLocation(particle.getLocation());
		}
	}
	
	public void updatePWorst(ParticleAWL particle) {
		
		double currentFitness = getFitness();
		
		//System.out.println("cF: " + currentFitness);
		
		if(currentFitness > particle.getPWorst()) {
			
			pWorst = currentFitness;
			//System.out.println("pWorst: " + pWorst);
			//pBestLocation = particle.getLocation();
			//System.out.println("pBest Ori Loc: " + pBestLocation);
			particle.updatePWorstLocation(particle.getLocation());
		}
	}
	
	private void updatePBestLocation(ArrayList<Double> arrayList) {
		
		this.pBestLocation = clone(arrayList);
	}
	
	private void updatePWorstLocation(ArrayList<Double> arrayList) {
		
		this.pWorstLocation = clone(arrayList);
	}
	
	public ArrayList<Double> clone(ArrayList<Double> arrayList) {
		
		return new ArrayList<Double>(arrayList);
	}
	
	public double getPBest() {
		
		return pBest;
	}
	
	public double getPWorst() {
		
		return pWorst;
	}
	
	public ArrayList<Double> getPBestLocation() {
		
		return pBestLocation;
	}

	public ArrayList<Double> getLocation() {
		
		return location;
	}

	public ArrayList<Double> getVelocity() {
		
		return velocity;
	}

	public void setVelocity(ArrayList<Double> velocity) {
		
		this.velocity = velocity;
	}

	public double getMaxRange() {
		
		return maxRange;
	}

	public double getMinRange() {

		return minRange;
	}

	public void setLocation(ArrayList<Double> location) {
		
		this.location = location;
	}

	public ArrayList<Double> getPWorstLocation() {

		return pWorstLocation;
	}
}
