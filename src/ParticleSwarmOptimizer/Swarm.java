package ParticleSwarmOptimizer;

import java.util.ArrayList;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Swarm {
	
	FunctionChoices functionChoice;
	int numberOfParticles, numberOfIterations;
	Location gBestLocation;
	ArrayList<Particle> particles;
	double gBest = Double.POSITIVE_INFINITY;
	Location location;
	Velocity velocity;
	Vector vector;

	public Swarm(FunctionChoices function, int numberOfParticles, int numberOfIterations) {
		
		this.functionChoice = function;
		this.numberOfParticles = numberOfParticles;
		this.numberOfIterations = numberOfIterations;
		particles = new ArrayList<Particle>();
		initialiseSwarm();
		execute();
	}
	
	private void initialiseSwarm() {
		
		Particle particle;
		
		for(int i = 0; i < numberOfParticles; i++) {
			
			particle = new Particle(functionChoice);
			particles.add(particle);
		}
		
		//System.out.println(particles.toString());
	}
	
	private void execute() {
		
		for(int i = 0; i < numberOfIterations; i++) {
			
			updatePosition();
			getGBest();
			//getNewVelocity();
			//getNewLocation();
		}
	}
	
	private void updatePosition() {
		
		for(int i = 0; i < particles.size(); i++) {
			
			location = particles.get(i).getPosition();
			//System.out.print("Loca: " + location);
			velocity = particles.get(i).getVelocity();
			//System.out.print("Velo: " + velocity);
			
			vector = new Vector(location, velocity);
			vector.add();
			
			location.setX(vector.getX());
			location.setY(vector.getY());
			location.setZ(vector.getZ());
			
			//System.out.print("Vector X: " + vector.getX() + " Vector Y: " + vector.getY() + " Vector Z: " + vector.getZ());
		}
	}

	private void getGBest() {
		
		for(int i = 0; i < particles.size(); i++) {
			
			if(particles.get(i).getPBest() < gBest) {
				
				
				gBest = particles.get(i).getPBest();
			}
		}
	}
}