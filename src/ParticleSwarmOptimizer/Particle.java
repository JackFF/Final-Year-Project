package ParticleSwarmOptimizer;

import java.util.concurrent.ThreadLocalRandom;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Particle {
	
	private Location location;;
	private Velocity velocity;
	private Vector vector;
	private Location pBestLocation;
	private FunctionChoices function;
	double maxRange = 101;
	double minRange = -100;
	double maxVelocity = 11;
	double minVelocity = -10;
	double pBest = Double.POSITIVE_INFINITY;
	double currentFitness;

	public Particle(FunctionChoices functionChoice) {
		
		this.function = functionChoice;
		location = new Location(0, 0, 0);
		velocity = new Velocity(0, 0, 0);
		setPosition();
		setVelocity();
		currentFitness = getCurrentFitness();
		updatePBest();
	}

	private void setPosition() {
		
		double x = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		double y = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		double z = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		
		location.setX(x);
		location.setY(y);
		location.setZ(z);
	}
	
	public Location getPosition() {
		
		return location;
	}
	
	private void setVelocity() {
		
		double x = ThreadLocalRandom.current().nextDouble(minVelocity, maxVelocity);
		double y = ThreadLocalRandom.current().nextDouble(minVelocity, maxVelocity);
		double z = ThreadLocalRandom.current().nextDouble(minVelocity, maxVelocity);
		
		velocity.setX(x);
		velocity.setY(y);
		velocity.setZ(z);
	}
	
	public Velocity getVelocity() {
		
		return velocity;
	}
	
	private double getCurrentFitness() {
		
		if(function == FunctionChoices.testFunction1) {
			
			return Functions.testFunction1(location.getX());
		}
		
		else if(function == FunctionChoices.testFunction2) {
			
			return Functions.testFunction2(location.getX());
		}
		
		return 0;
	}
	
	private void updatePBest() {
		
		if(currentFitness < pBest) {
			
			pBest = currentFitness;
		}
	}
	
	public double getPBest() {
		
		return pBest;
	}
	
	public String toString() {
		
		return "pBest: " + pBest;
	}
}
