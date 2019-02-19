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
	Vector gBestLocation = new Vector(0, 0, 0);
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
				
				//System.out.println("Particle " + j + " Old position: " + particles.get(j).getLocation());
				updateVelocity(particles.get(j));
				particles.get(j).updateLocation();
				//System.out.println("Particle " + j + " New position: " + particles.get(j).getLocation());
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
			System.out.println("New gBest: " + gBest);
			gBestLocation = particle.getPBestLocation();
			System.out.println("New gBest Location: " + gBestLocation);
		}
	}
	
	private void updateVelocity(Particle particle) {
		
		/*Vector oldVelocity = particle.getVelocity();
		Vector pBest = particle.getPBestLocation();
		System.out.println("pBest: " + pBest);
		Vector gBest = gBestLocation;
		Vector currentLocation1 = particle.getLocation();
		Vector currentLocation2 = particle.getLocation();
		System.out.println("cL1: " + currentLocation1);
		System.out.println("cL2: " + currentLocation2);*/
		
		Vector oV = particle.getVelocity();
		Vector oldVelocity = oV.clone();
		
		Vector pB = particle.getPBestLocation();
		Vector pBest = pB.clone();
		////System.out.println("pBest: " + pBest);
		
		Vector gBest = gBestLocation.clone();
		
		Vector cL1 = particle.getLocation();
		Vector currentLocation1 = cL1.clone();
		
		Vector cL2 = particle.getLocation();
		Vector currentLocation2 = cL2.clone();
		
		////System.out.println("cL1: " + currentLocation1);
		////System.out.println("cL2: " + currentLocation2);
		
		Random random = new Random();
		double r1 = random.nextDouble();
		double r2 = random.nextDouble();
		
		Vector newVelocity = oldVelocity;
		////System.out.println("Old Velocity: " + oldVelocity);
		////System.out.println("Old Velocity Clone: " + newVelocity);
		newVelocity.multiply(inertia);
		////System.out.println("New Velocity1: " + newVelocity);
		
		////System.out.println("Cl b4 - pBest: " + currentLocation1);
		////System.out.println("pBest: b4" + pBest);
		pBest.subtract(currentLocation1);
		//currentLocation = particle.getLocation();
		////System.out.println("pBest: a4" + pBest);
		////System.out.println("Cl a4 - pBest: " + currentLocation1);
		Vector test = particle.getLocation();
		////System.out.println("Test " + test);
		pBest.multiply(social);
		pBest.multiply(r1);
		newVelocity.add(pBest);
		////System.out.println("pBest: " + pBest);
		////System.out.println("New Velocity2: " + newVelocity);
		
		////System.out.println("gBestOrig: " + gBestLocation);
		////System.out.println("gBestFake: " + gBest);
		gBest.subtract(currentLocation2);
		////System.out.println("Cl: " + currentLocation2);
		////System.out.println("gBest - Cl: " + gBest);
		gBest.multiply(social);
		////System.out.println("gBest * sT: " + gBest);
		gBest.multiply(r2);
		////System.out.println("gBest * r2: " + gBest);
		////System.out.println("New Velocity b4 add gBest: " + newVelocity);
		newVelocity.add(gBest);
		////System.out.println("New Velocity3: " + newVelocity);
		
		particle.setVelocity(newVelocity);
		
		
	}
}