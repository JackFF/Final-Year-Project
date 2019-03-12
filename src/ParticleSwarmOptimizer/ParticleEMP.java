package ParticleSwarmOptimizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class ParticleEMP {
	
	private FunctionChoices function;
	private ArrayList<Double> location;
	private ArrayList<Double> velocity;
	private ArrayList<Object> previousPositions;
	private ArrayList<ArrayList<Double>> listOfLists;
	private ArrayList<Double> previousPositionsValues;
	double minRange = 0;
	double maxRange = 0;
	private double pBest;
	private ArrayList<Double> pBestLocation;
	int dimensions;
	int memory;
	
	private ArrayList<ArrayList<Double>> listOfpBest;
	private ArrayList<Double> allPBestAttempts;

	public ParticleEMP(FunctionChoices function, int dimensions, int memory) {
		
		this.function = function;
		this.dimensions = dimensions;
		this.memory = memory;
		location = setInitialPosition(dimensions);
		//System.out.println("Initial Locations: " + location);
		velocity = setInitial(dimensions);
		previousPositions = new ArrayList<Object>();
		previousPositionsValues = new ArrayList<Double>();
		listOfLists = new ArrayList<ArrayList<Double>>();
		//System.out.println("pp: " + previousPositions);
		pBestLocation = setInitial(dimensions);
		pBest = getInitialPBest();
		velocity = setInitialVelocity(dimensions);
		
		listOfpBest = new ArrayList<ArrayList<Double>>();
		//listOfpBest.add(location);
		double x = pBest;
		allPBestAttempts = new ArrayList<Double>();
		//allPBestAttempts.add(x);
	}

	public ParticleEMP() {
		
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
			
			//System.out.println("location: " + location);
			
			//listOfpBest.add(location);
			
			return Functions.sphere(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			pBestLocation = location;
			
			return Functions.rosenbrock(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.ackley) {
			
			pBestLocation = location;
			
			return Functions.ackley(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.griewank) {
			
			pBestLocation = location;
			
			return Functions.griewank(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			pBestLocation = location;
			
			return Functions.rastrigin(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.schaffer2D) {
			
			pBestLocation = location;
			
			return Functions.schaffer2D(getLocation(), dimensions);
		}
		
		else if(function == FunctionChoices.griewank10D) {
			
			pBestLocation = location;
			
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
	
	public void updatePBest(ParticleEMP particle) {
		
		double currentFitness = getFitness();
		particle.previousPositionsValues.add(currentFitness);
		particle.sortPreviousPositions(particle);
		
		particle.allPBestAttempts.add(currentFitness);
		particle.listOfpBest.add(particle.getLocation());
		
		//System.out.println(currentFitness);
		//System.out.println(particle.previousPositionsValues);
		//System.out.println(particle.previousPositions);
		
		if(currentFitness < particle.getPBest()) {
			
			pBest = currentFitness;
			particle.updatePBestLocation(particle.getLocation());
			////previousPositions.add(0, particle.getLocation());
		}
	}
	
	public void updatePreviousPositions(ParticleEMP particleEMP) {
		
		particleEMP.previousPositions.add(particleEMP.getLocation());
		//System.out.println("pp: " + particleEMP.previousPositions);
		
		particleEMP.listOfLists.add(particleEMP.getLocation());
		//System.out.println("lol: " + particleEMP.listOfLists);
	}
	
	private void updatePBestLocation(ArrayList<Double> arrayList) {
		
		this.pBestLocation = clone(arrayList);
	}
	
	public ArrayList<Double> clone(ArrayList<Double> arrayList) {
		
		return new ArrayList<Double>(arrayList);
	}
	
	public double getPBest() {
		
		return pBest;
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

	public void sortPreviousPositions(ParticleEMP particle) {
		
		for(int i = 0; i < particle.previousPositionsValues.size(); i++) {
			
			for(int j = i+1; j < particle.previousPositionsValues.size(); j++) {
				
				if(particle.previousPositionsValues.get(i) > particle.previousPositionsValues.get(j)) {
					
					Collections.swap(particle.previousPositionsValues, i, j);
					Collections.swap(particle.previousPositions, i, j);
					Collections.swap(particle.listOfLists, i, j);
					
					Collections.reverse(particle.previousPositionsValues);
					Collections.reverse(particle.previousPositions);
					Collections.reverse(particle.listOfLists);
				}
			}
		}
	}
	
	public void sortTest(ParticleEMP particle) {
		
		for(int i = 0; i < particle.getpBestAttempts().size(); i++) {
			
			for(int j = i+1; j < particle.getpBestAttempts().size(); j++) {
				
				if(particle.getpBestAttempts().get(i) > particle.getpBestAttempts().get(j)) {
					
					Collections.swap(particle.allPBestAttempts, i, j);
					Collections.swap(particle.listOfpBest, i, j);
				}
			}
		}
	}

	public void cutPreviousPositions(ParticleEMP particle) {
		
		for(int i = memory; i < particle.previousPositionsValues.size(); i++) {
			
			particle.previousPositionsValues.remove(i);
			particle.previousPositions.remove(i);
			particle.listOfLists.remove(i);
			
			//System.out.println("ppV: " + particle.previousPositionsValues);
			//System.out.println("pp: " + particle.previousPositions);
			//System.out.println("lol: " + particle.listOfLists);
			
			particle.allPBestAttempts.remove(i);
			particle.listOfpBest.remove(i);
		}
	}

	public ArrayList<Object> getPreviousPositions() {
		
		return previousPositions;
	}
	
	public ArrayList<ArrayList<Double>> getlOL() {
		
		return listOfLists;
	}

	public ArrayList<Object> cloneObj(ArrayList<Object> arrayList) {

		return new ArrayList<Object>(arrayList);
	}

	public ArrayList<ArrayList<Double>> cloneLOL(ArrayList<ArrayList<Double>> arrayList) {

		return new ArrayList<ArrayList<Double>>(arrayList);
	}
	
	
	
	public ArrayList<ArrayList<Double>> getlistOfpBest() {
		
		return listOfpBest;
	}
	
	public ArrayList<Double> getpBestAttempts() {
		
		return allPBestAttempts;
	}
}