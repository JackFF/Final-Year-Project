package ParticleSwarmOptimizer;

import java.util.ArrayList;
import java.util.Random;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Swarm {
	
	FunctionChoices function;
	int numberOfParticles;
	int numberOfIterations;
	int dimensions;
	ArrayList<Particle> particles;
	double gBest = Double.POSITIVE_INFINITY;
	Vector gBestLocation = new Vector(0, 0, 0);
	double inertia = 0.729844;
	double social = 1.496180;
	//double inertiaTest = 0.36;
	//double socialTest = 0.75;
	Exporter exp = new Exporter();
	boolean check;
	
	Vector realGBestLocation;

	public Swarm(FunctionChoices function, int numberOfParticles, int numberOfIterations, int dimensions) {
		
		this.function = function;
		this.numberOfParticles = numberOfParticles;
		this.numberOfIterations = numberOfIterations;
		this.dimensions = dimensions;
		particles = new ArrayList<Particle>();
		gBestLocation = new Vector(0, 0, 0);
		execute();
	}

	private void execute() {
		
		//Particle particle;
		
		for(int i = 0; i < numberOfParticles; i++) {
			
			Particle particle = new Particle(function, dimensions);
			particles.add(particle);
			updateGBest(particle);
		}
		
		
		double oldGBest = gBest;
		
		if(dimensions == 1) {
			
			System.out.println("--------------------------Beginning Optimization-------------------------");
            System.out.println("Global Best Value at Iteration " + 0 + ":\t"  + gBest);
            System.out.println("Global Best Location at Iteration " + 0 + ":\t" + "X: " + gBestLocation.getX() + "\n");
		}
		
		else if(dimensions == 2) {
			
			System.out.println("--------------------------Beginning Optimization-------------------------");
            System.out.println("Global Best Value at Iteration " + 0 + ":\t"  + gBest);
            System.out.println("Global Best Location at Iteration " + 0 + ":\t" + "X: " + gBestLocation.getX() + " Y: " + gBestLocation.getY() + "\n");
		}
		
		else if(dimensions == 3) {
			
			System.out.println("--------------------------Beginning Optimization-------------------------");
            System.out.println("Global Best Value at Iteration " + 0 + ":\t"  + gBest);
            System.out.println("Global Best Location at Iteration " + 0 + ":\t" + "X: " + gBestLocation.getX() + " Y: " + gBestLocation.getY() + " Z: " + gBestLocation.getZ() + "\n");
		}
		
		for(int i = 0; i < numberOfIterations; i++) {
			
			//updatePrintout();
			if(gBest < oldGBest) {
				
				check = true;
				
				/*if(dimensions == 1) {
					
					System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
	                System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "X: " + gBestLocation.getX() + "\n");
				}
				
				else if(dimensions == 2) {
					
					System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
	                System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "X: " + gBestLocation.getX() + " Y: " + gBestLocation.getY() + "\n");
				}
				
				else if(dimensions == 3) {
					
					System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
	                System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "X: " + gBestLocation.getX() + " Y: " + gBestLocation.getY() + " Z: " + gBestLocation.getZ() + "\n");
				}*/
				
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
				particles.get(j).updateLocation();
				//System.out.println("Particle " + j + " New position: " + particles.get(j).getLocation());
				
				updateGBest(particles.get(j));
			}
			
			updatePrintout(i, check);
		}
		
		System.out.println("---------------------------RESULT---------------------------");
		
		if(dimensions == 1) {
			
			System.out.println("X: " + gBestLocation.getX());
		    System.out.println("Final Best Evaluation: " + gBest);
		    exp.export(gBest, gBestLocation.getX(), gBestLocation.getY(), gBestLocation.getZ(), function, numberOfParticles, numberOfIterations, dimensions);
		}
		
		else if(dimensions == 2) {
			
			System.out.println("X: " + gBestLocation.getX());
			System.out.println("Y: " + gBestLocation.getY());
		    System.out.println("Final Best Evaluation: " + gBest);
		    exp.export(gBest, gBestLocation.getX(), gBestLocation.getY(), gBestLocation.getZ(), function, numberOfParticles, numberOfIterations, dimensions);
		}
		
		else if(dimensions == 3) {
			
			System.out.println("X: " + gBestLocation.getX());
			System.out.println("Y: " + gBestLocation.getY());
			System.out.println("Z: " + gBestLocation.getZ());
		    System.out.println("Final Best Evaluation: " + gBest);
		    exp.export(gBest, gBestLocation.getX(), gBestLocation.getY(), gBestLocation.getZ(), function, numberOfParticles, numberOfIterations, dimensions);
		}
	}

	private void updatePrintout(int i, boolean check) {
		
		if(check == true) {
			
			if(dimensions == 1) {
				
				System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
	        	System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "X: " + gBestLocation.getX() + "\n");
			}
		
			else if(dimensions == 2) {
			
				System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
				System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "X: " + gBestLocation.getX() + " Y: " + gBestLocation.getY() + "\n");
			}
		
			else if(dimensions == 3) {
			
				System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
				System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "X: " + gBestLocation.getX() + " Y: " + gBestLocation.getY() + " Z: " + gBestLocation.getZ() + "\n");
			}
		}
	}

	private void updateGBest(Particle particle) {
		
		if(particle.getPBest() < gBest) {
			
			gBest = particle.getPBest();
			//System.out.println("New gBest: " + gBest);
			gBestLocation = particle.getPBestLocation();
			//System.out.println("Particles Best Location: " + particle.getPBestLocation());
			//System.out.println("New gBest Location: " + gBestLocation);
			
			realGBestLocation = gBestLocation.clone();
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
		//System.out.println("pBest: " + pBest);
		
		Vector gB = gBestLocation;
		Vector gBest = gB.clone();
		
		Vector cL1 = particle.getLocation();
		Vector currentLocation1 = cL1.clone();
		
		Vector cL2 = particle.getLocation();
		Vector currentLocation2 = cL2.clone();
		
		//System.out.println("cL1: " + currentLocation1);
		////System.out.println("cL2: " + currentLocation2);
		
		Random random = new Random();
		double r1 = random.nextDouble();
		double r2 = random.nextDouble();
		
		//System.out.println("r1: " + r1);
		//System.out.println("r2: " + r2);
		
		Vector newVelocity = oldVelocity;
		////System.out.println("Old Velocity: " + oldVelocity);
		////System.out.println("Old Velocity Clone: " + newVelocity);
		newVelocity.multiply(inertia);
		////System.out.println("New Velocity1: " + newVelocity);
		
		////System.out.println("Cl b4 - pBest: " + currentLocation1);
		//System.out.println("pBest: b4" + pBest);
		pBest.subtract(currentLocation1);
		//currentLocation = particle.getLocation();
		////System.out.println("pBest: a4" + pBest);
		////System.out.println("Cl a4 - pBest: " + currentLocation1);
		//Vector test = particle.getLocation();
		////System.out.println("Test " + test);
		//System.out.println("pBest b4 social: " + pBest);
		pBest.multiply(social);
		//System.out.println("pBest b4 r1: " + pBest);
		pBest.multiply(r1);
		//System.out.println("pBest a4 r1: " + pBest);
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