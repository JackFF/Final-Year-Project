package ParticleSwarmOptimizer;

import java.util.ArrayList;
import java.util.Random;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Swarm {
	
	FunctionChoices function;
	int numberOfParticles;
	int numberOfIterations;
	ArrayList<Particle> particles;
	double gBest = Double.POSITIVE_INFINITY;
	Vector gBestLocation;
	double inertia = 0.729844;
	double social = 1.496180;
	double inertiaTest = 0.36;
	double socialTest = 0.75;

	public Swarm(FunctionChoices function, int numberOfParticles, int numberOfIterations) {
		
		this.function = function;
		this.numberOfParticles = numberOfParticles;
		this.numberOfIterations = numberOfIterations;
		particles = new ArrayList<Particle>();
		gBestLocation = new Vector(0, 0, 0);
		execute();
	}

	private void execute() {
		
		//Particle particle;
		
		for(int i = 0; i < numberOfParticles; i++) {
			
			Particle particle = new Particle(function);
			particles.add(particle);
			updateGBest(particle);
		}
		
		
		double oldGBest = gBest;
		System.out.println("--------------------------EXECUTING-------------------------");
        System.out.println("Global Best Evaluation (Iteration " + 0 + "):\t"  + gBest);
		
		for(int i = 0; i < numberOfIterations; i++) {
			
			if(gBest < oldGBest) {
				
				System.out.println("Global Best Evaluation (Iteration " + (i + 1) + "):\t" + gBest);
                oldGBest = gBest;
			}
			
			
			for(int j = 0; j < particles.size(); j++) {
				
				//System.out.println("old pBest = " + particles.get(j).getPBest());
				particles.get(j).updatePBest(particles.get(j));
				//System.out.println("new pBest = " + particles.get(j).getPBest());
				//System.out.println("old gBest: " + gBest);
				updateGBest(particles.get(j));
				//System.out.println("new gBest: " + gBest);
			}
			
			
			for(int j = 0; j < particles.size(); j++) {
				
				System.out.println("Particle " + j + " Old position: " + particles.get(j).getLocation());
				updateVelocity(particles.get(j));
				particles.get(j).updateLocation();
				System.out.println("Particle " + j + " New position: " + particles.get(j).getLocation());
			}
		}
		
		System.out.println("---------------------------RESULT---------------------------");
        System.out.println("x = " + gBestLocation.getX());
        System.out.println("y = " + gBestLocation.getY());
        System.out.println("Final Best Evaluation: " + gBest);
        System.out.println("---------------------------COMPLETE-------------------------");
	}

	private void updateGBest(Particle particle) {
		
		if(particle.getPBest() < gBest) {
			
			gBest = particle.getPBest();
			gBestLocation = particle.getPBestLocation();
		}
	}
	
	private void updateVelocity(Particle particle) {
		
		Vector oldVelocity = particle.getVelocity();
		Vector pBest = particle.getPBestLocation();
		System.out.println("pBest: " + pBest);
		Vector gBest = gBestLocation;
		Vector currentLocation = particle.getLocation();
		System.out.println("cL: " + currentLocation);
		
		Random random = new Random();
		double r1 = random.nextDouble();
		double r2 = random.nextDouble();
		
		Vector newVelocity = oldVelocity;
		System.out.println("Old Velocity: " + oldVelocity);
		newVelocity.multiply(inertiaTest);
		System.out.println("New Velocity1: " + newVelocity);
		
		System.out.println("Cl b4 - pBest: " + currentLocation);
		System.out.println("pBest: b4" + pBest);
		pBest.subtract(currentLocation);
		currentLocation = particle.getLocation();
		System.out.println("pBest: a4" + pBest);
		System.out.println("Cl a4 - pBest: " + currentLocation);
		pBest.multiply(socialTest);
		pBest.multiply(r1);
		newVelocity.add(pBest);
		System.out.println("pBest: " + pBest);
		System.out.println("New Velocity2: " + newVelocity);
		
		System.out.println("gBestOrig: " + gBestLocation);
		System.out.println("gBestFake: " + gBest);
		gBest.subtract(currentLocation);
		System.out.println("Cl: " + currentLocation);
		System.out.println("gBest - Cl: " + gBest);
		gBest.multiply(socialTest);
		gBest.multiply(r2);
		newVelocity.add(gBest);
		System.out.println("New Velocity3: " + newVelocity);
		
		particle.setVelocity(newVelocity);
	}
}