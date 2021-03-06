package ParticleSwarmOptimizer;

import java.util.ArrayList;
import java.util.Random;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Swarm {
	
	FunctionChoices function;
	int numberOfParticles;
	int numberOfIterations;
	int dimensions;
	String prefix;
	ArrayList<Particle> particles;
	double gBest = Double.POSITIVE_INFINITY;
	ArrayList<Double> gBestLocation;
	double social = 2.05;
	Exporter exp = new Exporter();
	boolean check;
	Particle particle = new Particle();
	ArrayList<Double> gBestValues;
	ArrayList<Integer> iterations;
	double constrictionFactor = 0.7298437881;
	int count = 0;

	public Swarm(FunctionChoices function, int numberOfParticles, int numberOfIterations, int dimensions, String prefix) {
		
		this.function = function;
		this.numberOfParticles = numberOfParticles;
		this.numberOfIterations = numberOfIterations;
		this.dimensions = dimensions;
		this.prefix = prefix;
		particles = new ArrayList<Particle>();
		gBestLocation = particle.setInitial(dimensions);
		gBestValues = new ArrayList<Double>();
		iterations = new ArrayList<Integer>();
		execute();
	}

	private void execute() {
		
		for(int i = 0; i < numberOfParticles; i++) {
			
			Particle particle = new Particle(function, dimensions);
			particles.add(particle);
			updateGBest(particle);
		}
		
		
		double oldGBest = gBest;
			
		System.out.println("--------------------------Beginning Optimization-------------------------");
        System.out.println("Global Best Value at Iteration " + 0 + ":\t"  + gBest);
        System.out.println("Global Best Location at Iteration " + 0 + ":\t" + "Coords: " + gBestLocation + "\n");
        
        gBestValues.add(gBest);
		iterations.add(0);
		
		for(int i = 0; i < numberOfIterations; i++) {
			
			if(gBest < oldGBest) {
				
				check = true;
				
                oldGBest = gBest;
			}
			
			else {
				
				check = false;
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
				
				//System.out.println("Particle " + j);
				//System.out.println("Particle " + j + " Old position: " + particles.get(j).getLocation());
				updateVelocity(particles.get(j));
				//System.out.println("Particle " + j + " New position: " + particles.get(j).getLocation());
				updateGBest(particles.get(j));
			}
			
			updatePrintout(i, check);
		}
		
		System.out.println("---------------------------RESULT---------------------------");
		
		//System.out.println("Coords: " + gBestLocation);
		System.out.println("Final Best Evaluation: " + gBest);
		exp.export(gBest, gBestLocation, function, numberOfParticles, numberOfIterations, dimensions, prefix);
		exp.export(gBestValues, iterations, function, prefix);
	}

	private void updatePrintout(int i, boolean check) {
		
		if(check == true) {
				
			System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
			System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "Coords: " + gBestLocation + "\n");
			
			double value = gBest;
			
			if((i+1) < 150) {
				
				gBestValues.add(value);
				iterations.add(i + 1);
			}
			
			else if((i+1) < 500) {
				
				if(((i+1) % 2) == 0) {
					
					gBestValues.add(value);
					iterations.add(i + 1);
				}
			}
			
			else if((i+1) < 1000) {
				
				if(((i+1) % 5) == 0) {
					
					gBestValues.add(value);
					iterations.add(i + 1);
				}
			}
			
			else if((i+1) < 5000) {
				
				if(((i+1) % 20) == 0) {
					
					gBestValues.add(value);
					iterations.add(i + 1);
				}
			}
			
			else if((i+1) > 5000) {
				
				if(((i+1) % 50) == 0) {
					
					gBestValues.add(value);
					iterations.add(i + 1);
				}
			}
			
			count++;
		}
	}

	private void updateGBest(Particle particle) {
		
		if(particle.getPBest() < gBest) {
			
			gBest = particle.getPBest();
			//System.out.println("New gBest: " + gBest);
			gBestLocation = particle.getPBestLocation();
			//System.out.println("Particles Best Location: " + particle.getPBestLocation());
			//System.out.println("New gBest Location: " + gBestLocation);
		}
	}
	
	private void updateVelocity(Particle particle) {
		
		ArrayList<Double> oV = particle.getVelocity();
		//System.out.println("oV: " + oV);
		ArrayList<Double> oldVelocity = particle.clone(oV);
		//System.out.println("oldVelocity: " + oldVelocity);
		
		ArrayList<Double> pB = particle.getPBestLocation();
		//System.out.println("pB: " + pB);
		ArrayList<Double> pBest = particle.clone(pB);
		//System.out.println("pBest: " + pBest);
		
		ArrayList<Double> gB = gBestLocation;
		//System.out.println("gB: " + gB);
		ArrayList<Double> gBest = particle.clone(gB);
		//System.out.println("gBest: " + gBest);
		
		ArrayList<Double> cL1 = particle.getLocation();
		//System.out.println("cL1: " + cL1);
		ArrayList<Double> currentLocation1 = particle.clone(cL1);
		//System.out.println("currentLocation1: " + currentLocation1);
		
		ArrayList<Double> cL2 = particle.getLocation();
		//System.out.println("cL2: " + cL2);
		ArrayList<Double> currentLocation2 = particle.clone(cL2);
		//System.out.println("currentLocation2: " + currentLocation2);
		
		ArrayList<Double> cL3 = particle.getLocation();
		//System.out.println("cL3: " + cL3);
		ArrayList<Double> currentLocation3 = particle.clone(cL3);
		//System.out.println("currentLocation3: " + currentLocation3);
		
		Random random = new Random();
		double r1 = random.nextDouble();
		double r2 = random.nextDouble();
		
		double maxRange = particle.getMaxRange();
		
		//System.out.println("r1: " + r1);
		//System.out.println("r2: " + r2);
		
		ArrayList<Double> newVelocity = particle.clone(oldVelocity);
		
		for(int i = 0; i < dimensions; i++) {
			
			double previousVelocity = newVelocity.get(i);
			
			double p1 = (r1 * social * (pBest.get(i) - currentLocation1.get(i)));
			double p2 = (r2 * social * (gBest.get(i) - currentLocation2.get(i)));
			
			double newVel = constrictionFactor * (previousVelocity + p1 + p2);
			
			newVelocity.set(i, newVel);
		}
		
		for(int i = 0; i < dimensions; i++) {
			
			if(newVelocity.get(i) > (maxRange * 2)) {
				
				newVelocity.set(i, (maxRange*2));
			}
			
			else if(newVelocity.get(i) < (0 - maxRange) * 2) {
				
				newVelocity.set(i, ((0 - maxRange) * 2));
			}
		}
		
		for(int i = 0; i < dimensions; i++) {
			
			if(currentLocation3.get(i) + newVelocity.get(i) > maxRange) {
				
				double newPos = maxRange - (currentLocation3.get(i) + newVelocity.get(i) - maxRange);
				currentLocation3.set(i, newPos);
				
				double newVel = 0 - newVelocity.get(i);
				newVelocity.set(i, newVel);
			}
			
			else if(currentLocation3.get(i) + newVelocity.get(i) < (0 - maxRange)) {
				
				double newPos = -maxRange + ((-maxRange) - (currentLocation3.get(i) + newVelocity.get(i)));
				currentLocation3.set(i, newPos);
				
				double newVel = 0 - newVelocity.get(i);
				newVelocity.set(i, newVel);
			}
			
			else {
				
				double newPos = currentLocation3.get(i) + newVelocity.get(i); // particle is within acceptable region											
				currentLocation3.set(i, newPos);
			}
		}
		
		//System.out.println("new currentLocation3: " + currentLocation3);
		
		particle.setVelocity(newVelocity);
		particle.setLocation(currentLocation3);	
	}
}