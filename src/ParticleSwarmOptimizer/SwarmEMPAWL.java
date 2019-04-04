package ParticleSwarmOptimizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class SwarmEMPAWL {
	
	FunctionChoices function;
	int numberOfParticles;
	int numberOfIterations;
	int dimensions;
	ArrayList<ParticleEMPAWL> particles;
	double gBest = Double.POSITIVE_INFINITY;
	double gWorst = Double.NEGATIVE_INFINITY;
	ArrayList<Double> gBestLocation;
	ArrayList<Double> gWorstLocation;
	double social1 = 1.845;
	double social2 = 0.205;
	Exporter exp = new Exporter();
	boolean check;
	ParticleEMPAWL particle = new ParticleEMPAWL();
	int memory;
	ArrayList<Double> gBestValues;
	ArrayList<Integer> iterations;
	double constrictionFactor = 0.7298437881;
	int count = 0;
	String prefix;

	public SwarmEMPAWL(FunctionChoices function, int numberOfParticles, int numberOfIterations, int dimensions, int memory, String prefix) {
		
		this.function = function;
		this.numberOfParticles = numberOfParticles;
		this.numberOfIterations = numberOfIterations;
		this.dimensions = dimensions;
		this.memory = memory;
		this.prefix = prefix;
		particles = new ArrayList<ParticleEMPAWL>();
		gBestLocation = particle.setInitial(dimensions);
		gBestValues = new ArrayList<Double>();
		iterations = new ArrayList<Integer>();
		execute();
	}

	private void execute() {
		
		for(int i = 0; i < numberOfParticles; i++) {
			
			ParticleEMPAWL particle = new ParticleEMPAWL(function, dimensions, memory);
			particles.add(particle);
			updateGBest(particle);
			updateGWorst(particle);
		}
		
		
		double oldGBest = gBest;
		double oldGWorst = gWorst;
			
		//System.out.println("--------------------------Beginning Optimization-------------------------");
        //System.out.println("Global Best Value at Iteration " + 0 + ":\t"  + gBest);
        //System.out.println("Global Best Location at Iteration " + 0 + ":\t" + "Coords: " + gBestLocation + "\n");
		
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
			
			if(gWorst > oldGWorst) {
				
				oldGWorst = gWorst;
			}
			
			
			for(int j = 0; j < particles.size(); j++) {
				
				//System.out.println("old pBest = " + particles.get(j).getPBest());
				particles.get(j).updatePBest(particles.get(j));
				particles.get(j).updatePWorst(particles.get(j));
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
		
		//System.out.println("---------------------------RESULT---------------------------");
			
		//System.out.println("Coords: " + gBestLocation);
		//System.out.println("Final Best Evaluation: " + gBest);
		//exp.export(gBest, gBestLocation, function, numberOfParticles, numberOfIterations, dimensions, memory, prefix);
		exp.export(gBestValues, iterations, function, memory, prefix);
	}

	private void updatePrintout(int i, boolean check) {
		
		if(check == true) {
				
			//System.out.println("Global Best Value at Iteration " + (i + 1) + ":\t"  + gBest);
			//System.out.println("Global Best Location at Iteration " + (i + 1) + ":\t" + "Coords: " + gBestLocation + "\n");
			
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
			//System.out.println("gbv: " + gBestValues);
			//System.out.println("iterations: " + iterations);
		}
	}

	private void updateGBest(ParticleEMPAWL particle) {
		
		if(particle.getPBest() < gBest) {
			
			gBest = particle.getPBest();
			//System.out.println("New gBest: " + gBest);
			gBestLocation = particle.getPBestLocation();
			//System.out.println("Particles Best Location: " + particle.getPBestLocation());
			//System.out.println("New gBest Location: " + gBestLocation);
		}
	}
	
	private void updateGWorst(ParticleEMPAWL particle) {
		
		if(particle.getPWorst() > gWorst) {
			
			gWorst = particle.getPWorst();
			//System.out.println("New gBest: " + gBest);
			gWorstLocation = particle.getPWorstLocation();
			//System.out.println("Particles Best Location: " + particle.getPBestLocation());
			//System.out.println("New gBest Location: " + gBestLocation);
		}
	}
	
	private void updateVelocity(ParticleEMPAWL particle) {
		
		ArrayList<Double> chance = new ArrayList<Double>();
		
		for(int i = 0; i < memory; i++) {
			
			chance.add((Math.pow(2, i-1) / (Math.pow(2, memory-1) - 1)));
		}
		
		Random rand = new Random();
		
		int choice = selectRandomWeighted(chance, rand);
		
		//System.out.println("Choices: " + chance);
		//System.out.println("Choice: " + choice);
		
		ArrayList<Double> oV = particle.getVelocity();
		//System.out.println("oV: " + oV);
		ArrayList<Double> oldVelocity = particle.clone(oV);
		//System.out.println("oldVelocity: " + oldVelocity);
		
		ArrayList<Double> pW = particle.getPWorstLocation();
		//System.out.println("pW: " + pW);
		ArrayList<Double> pWorst = particle.clone(pW);
		//System.out.println("pWorst: " + pWorst);
		
		ArrayList<Double> gW = gWorstLocation;
		//System.out.println("gW: " + gW);
		ArrayList<Double> gWorst = particle.clone(gW);
		//System.out.println("gWorst: " + gWorst);
		
		ArrayList<Double> y1 = new ArrayList<Double>();
		
		particle.sortTest(particle);
		
		particle.cutPreviousPositions(particle);
		
		ArrayList<Double> pBestAttempts = particle.getpBestAttempts();
		ArrayList<ArrayList<Double>> listOfPBest = particle.getlistOfpBest();
		
		//System.out.println("pBest Attempts b4: " + pBestAttempts);
		//System.out.println("list of pBest loc b4: " + listOfPBest);
		
		Collections.reverse(pBestAttempts);
		Collections.reverse(listOfPBest);
		
		////System.out.println("pBest Attempts a4: " + pBestAttempts);
		//System.out.println("list of pBest loc a4: " + listOfPBest);
		
		if(count < memory) {
			
			if(listOfPBest.size() == 1) {
				
				y1 = listOfPBest.get(0);
				//System.out.println("if y1: " + y1);
			}
			
			else if(listOfPBest.size() == 2) {
				
				y1 = listOfPBest.get(1);
			}
			
			else if(listOfPBest.size() == 3) {
				
				y1 = listOfPBest.get(2);
			}
			
			else if(listOfPBest.size() == 4) {
				
				y1 = listOfPBest.get(3);
			}
			
			else if(listOfPBest.size() == 5) {
				
				y1 = listOfPBest.get(4);
			}
		}
		
		else {
			
			y1 = listOfPBest.get(choice);
		}
		
		//System.out.println("else y1: " + y1);
		
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
		
		ArrayList<Double> cL4 = particle.getLocation();
		//System.out.println("cL4: " + cL4);
		ArrayList<Double> currentLocation4 = particle.clone(cL4);
		//System.out.println("currentLocation4: " + currentLocation4);
		
		ArrayList<Double> cL5 = particle.getLocation();
		//System.out.println("cL5: " + cL5);
		ArrayList<Double> currentLocation5 = particle.clone(cL5);
		//System.out.println("currentLocation5: " + currentLocation5);
		
		Random random = new Random();
		double r1 = random.nextDouble();
		double r2 = random.nextDouble();
		double r3 = random.nextDouble();
		double r4 = random.nextDouble();
		
		double maxRange = particle.getMaxRange();
		
		//System.out.println("r1: " + r1);
		//System.out.println("r2: " + r2);
		
		ArrayList<Double> newVelocity = particle.clone(oldVelocity);
		
		for(int i = 0; i < dimensions; i++) {
		
			double previousVelocity = newVelocity.get(i);
		
			double p1 = (r1 * social1 * (y1.get(i) - currentLocation1.get(i)));
			double p2 = (r2 * social1 * (gBest.get(i) - currentLocation2.get(i)));
			double p3 = (r3 * social2 * (p1 / (1 + Math.abs(currentLocation3.get(i) - pWorst.get(i)))));
			double p4 = (r4 * social2 * (p2 / (1 + Math.abs(currentLocation4.get(i) - gWorst.get(i)))));
		
			double newVel = constrictionFactor * (previousVelocity + p1 + p2 + p3 + p4);
		
			newVelocity.set(i, newVel);
		}
	
		for(int i = 0; i < dimensions; i++) {
		
			if(newVelocity.get(i) > (maxRange * 2)) {
			
					newVelocity.set(i, (maxRange * 2));
				}
		
			else if(newVelocity.get(i) < 0 - maxRange) {
			
				newVelocity.set(i, ((0 - maxRange) * 2));
			}
		}
	
		for(int i = 0; i < dimensions; i++) {
		
			if(currentLocation5.get(i) + newVelocity.get(i) > maxRange) {
			
				double newPos = maxRange - (currentLocation5.get(i) + newVelocity.get(i) - maxRange);
				currentLocation5.set(i, newPos);
			
				double newVel = 0 - newVelocity.get(i);
				newVelocity.set(i, newVel);
			}
		
			else if(currentLocation5.get(i) + newVelocity.get(i) < (0 - maxRange)) {
			
				double newPos = maxRange + ((-maxRange) - (currentLocation5.get(i) + newVelocity.get(i)));
				currentLocation5.set(i, newPos);
			
				double newVel = 0 - newVelocity.get(i);
				newVelocity.set(i, newVel);
			}
		
			else {
			
				double newPos = currentLocation5.get(i) + newVelocity.get(i);
				currentLocation5.set(i, newPos);
			}
		}
	
		//System.out.println("new currentLocation3: " + currentLocation3);
	
		particle.setVelocity(newVelocity);
		particle.setLocation(currentLocation5);
		
	}

	private static int selectRandomWeighted(ArrayList<Double> chance, Random rand) {
		
		int selected = 0;
		double total = chance.get(0);
		
		for(int i = 1; i < chance.size(); i++) {
			
			double x = chance.get(i);
			total += x;
			
			if(rand.nextDouble() <= (x / total)) {
				
				selected = i;
			}
		}
		
		return selected;
	}
}