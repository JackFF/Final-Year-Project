package ParticleSwarmOptimizer;

import java.util.concurrent.ThreadLocalRandom;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Particle {
	
	private FunctionChoices function;
	private Vector location;
	private Vector velocity;
	double minRange = -100;
	double maxRange = 101;
	double minVelocity = -5;
	double maxVelocity = 6;
	private double pBest;
	private Vector pBestLocation;

	public Particle(FunctionChoices function) {
		
		this.function = function;
		location = new Vector(0, 0, 0);
		velocity = new Vector(0, 0, 0);
		pBestLocation = new Vector(0, 0, 0);
		setInitialPosition();
		pBest = getInitialPBest();
		setInitialVelocity(velocity);
		//System.out.println("Velocity:" + velocity);
		updateLocation();
	}

	private void setInitialPosition() {
		
		double x = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		double y = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		double z = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		
		location.setLocation(x, y, z);
	}
	
	private double getInitialPBest() {
		
		if(function == FunctionChoices.testFunction1) {
			
			pBestLocation = location;
			return Functions.testFunction1(location.getX());
		}
		
		else if(function == FunctionChoices.testFunction2) {
			
			pBestLocation = location;
			return Functions.testFunction2(location.getX());
		}
		
		else {
			
			return 0;
		}
	}
	
	private void setInitialVelocity(Vector velocity) {
		
		velocity.x = ThreadLocalRandom.current().nextDouble(minVelocity, maxVelocity);
		velocity.y = ThreadLocalRandom.current().nextDouble(minVelocity, maxVelocity);
		velocity.z = ThreadLocalRandom.current().nextDouble(minVelocity, maxVelocity);
	}
	
	private double getFitness() {
		
		if(function == FunctionChoices.testFunction1) {
			
			return Functions.testFunction1(location.getX());
		}
		
		else if(function == FunctionChoices.testFunction2) {
			
			return Functions.testFunction2(location.getX());
		}
		
		else {
			
			return 0;
		}
	}
	
	public void updatePBest(Particle particle) {
		
		double currentFitness = getFitness();
		
		if(currentFitness < particle.getPBest()) {
			
			pBest = currentFitness;
			pBestLocation = location;
		}
	}
	
	public double getPBest() {
		
		return pBest;
	}
	
	public Vector getPBestLocation() {
		
		return pBestLocation;
	}

	public Vector getLocation() {
		
		return location;
	}

	public Vector getVelocity() {
		
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		
		this.velocity = velocity;
	}

	public void updateLocation() {
		
		this.location.add(velocity);
	}
}
