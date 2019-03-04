package ParticleSwarmOptimizer;

import java.util.concurrent.ThreadLocalRandom;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Particle {
	
	private FunctionChoices function;
	private Vector location;
	private Vector velocity;
	double minRange = 0;
	double maxRange = 0;
	double minVelocity = -5;
	double maxVelocity = 6;
	private double pBest;
	private Vector pBestLocation;
	int dimensions;

	public Particle(FunctionChoices function, int dimensions) {
		
		this.function = function;
		this.dimensions = dimensions;
		location = new Vector(0, 0, 0);
		velocity = new Vector(0, 0, 0);
		pBestLocation = new Vector(0, 0, 0);
		setInitialPosition();
		pBest = getInitialPBest();
		////setInitialVelocity(velocity);
		//System.out.println("Velocity:" + velocity);
		////updateLocation();
	}

	private void setInitialPosition() {
		
		if(function == FunctionChoices.testFunction1) {
			
			minRange = -100;
			maxRange = 100;
		}
		
		else if(function == FunctionChoices.testFunction2) {
			
			minRange = -100;
			maxRange = 100;
		}
		
		else if(function == FunctionChoices.boothsFunction) {
			
			minRange = -100;
			maxRange = 100;
		}
		
		else if(function == FunctionChoices.ackleysFunction) {
			
			minRange = -32;
			maxRange = 32;
		}
		
		else if(function == FunctionChoices.sphere) {
			
			minRange = -5.12;
			maxRange = 5.12;
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			minRange = -2.048;
			maxRange = 2.048;
		}
		
		else if(function == FunctionChoices.griewank) {
			
			minRange = -600;
			maxRange = 600;
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			minRange = -5.12;
			maxRange = 5.12;
		}
		
		double x = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		double y = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		double z = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
		
		location.setLocation(x, y, z);
	}
	
	private double getInitialPBest() {
		
		if(function == FunctionChoices.testFunction1) {
			
			pBestLocation = location;
			//System.out.println("Initial x-location: " + location.getX());
			//System.out.println(Functions.testFunction1(location.getX()));
			return Functions.testFunction1(location.getX());
		}
		
		else if(function == FunctionChoices.testFunction2) {
			
			pBestLocation = location;
			return Functions.testFunction2(location.getX());
		}
		
		else if(function == FunctionChoices.boothsFunction) {
			
			pBestLocation = location;
			return Functions.boothsFunction(location.getX(), location.getY());
		}
		
		else if(function == FunctionChoices.ackleysFunction) {
			
			pBestLocation = location;
			return Functions.ackleysFunction(location.getX(), location.getY());
		}
		
		else if(function == FunctionChoices.sphere) {
			
			pBestLocation = location;
			return Functions.sphere(location.getX(), location.getY(), location.getZ());
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			pBestLocation = location;
			return Functions.rosenbrock(location.getX(), location.getY());
		}
		
		else if(function == FunctionChoices.griewank) {
			
			if(dimensions == 1) {
				
				pBestLocation = location;
				return Functions.griewank(location.getX());
			}
			
			else if(dimensions == 2) {
				
				pBestLocation = location;
				return Functions.griewank(location.getX(), location.getY());
			}
			
			else {
				
				pBestLocation = location;
				return Functions.griewank(location.getX(), location.getY(), location.getZ());
			}
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			if(dimensions == 1) {
				
				pBestLocation = location;
				return Functions.rastrigin(location.getX());
			}
			
			else if(dimensions == 2) {
				
				pBestLocation = location;
				return Functions.rastrigin(location.getX(), location.getY());
			}
			
			else {
				
				pBestLocation = location;
				return Functions.rastrigin(location.getX(), location.getY(), location.getZ());
			}
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
		
		else if(function == FunctionChoices.boothsFunction) {
			
			return Functions.boothsFunction(location.getX(), location.getY());
		}
		
		else if(function == FunctionChoices.ackleysFunction) {
			
			return Functions.ackleysFunction(location.getX(), location.getY());
		}
		
		else if(function == FunctionChoices.sphere) {
			
			return Functions.sphere(location.getX(), location.getY(), location.getZ());
		}
		
		else if(function == FunctionChoices.rosenbrock) {
			
			return Functions.rosenbrock(location.getX(), location.getY());
		}
		
		else if(function == FunctionChoices.griewank) {
			
			if(dimensions == 1) {
				
				return Functions.griewank(location.getX());
			}
			
			else if(dimensions == 2) {
				
				return Functions.griewank(location.getX(), location.getY());
			}
			
			else {
				
				return Functions.griewank(location.getX(), location.getY(), location.getZ());
			}
		}
		
		else if(function == FunctionChoices.rastrigin) {
			
			if(dimensions == 1) {
				
				return Functions.rastrigin(location.getX());
			}
			
			else if(dimensions == 2) {
				
				return Functions.rastrigin(location.getX(), location.getY());
			}
			
			else {
				
				return Functions.rastrigin(location.getX(), location.getY(), location.getZ());
			}
		}
		
		else {
			
			return 0;
		}
	}
	
	public void updatePBest(Particle particle) {
		
		double currentFitness = getFitness();
		
		if(currentFitness < particle.getPBest()) {
			
			pBest = currentFitness;
			//System.out.println("pBest Ori: " + pBest);
			//pBestLocation = particle.getLocation();
			//System.out.println("pBest Ori Loc: " + pBestLocation);
			particle.updatePBestLocation(particle.getLocation());
		}
	}
	
	private void updatePBestLocation(Vector location) {
		
		this.pBestLocation = location.clone();
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
