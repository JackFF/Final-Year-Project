package ParticleSwarmOptimizer;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		//GUI gui = new GUI();
		//gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Scanner input = new Scanner(System.in);
		int functionChoice = 0;
		int numberOfParticles = 0;
		int numberOfIterations = 0;
		Functions.FunctionChoices function = null;
		int dimensions = 30;
		int memory = 2;
		
		System.out.println("Welcome to my Particle Swarm Optimizer!");
		System.out.println("You must select each of the following: ");
		System.out.println("Only input integers");
		
		do {
			
			try {
				
				System.out.println("(A) Which function to use: ");
				System.out.println("\t(1) Sphere");
				System.out.println("\t(2) Rosenbrock");
				System.out.println("\t(3) Ackley");
				System.out.println("\t(4) Griewank");
				System.out.println("\t(5) Rastrigin");
				System.out.println("\t(6) Schaffer2D");
				System.out.println("\t(7) Griewank10D");
				System.out.print("\t");
				
				functionChoice = input.nextInt();
				
				if(functionChoice < 1 || functionChoice > 8) {
					
					System.out.println("\nPlease pick a valid choice\n");
					Thread.sleep(1000);
				}
			} catch(InputMismatchException e) {
				
				System.out.println("\nPlease enter an integer\n");
				input.nextLine();
				Thread.sleep(1000);
			}
			
		} while(functionChoice !=1 && functionChoice != 2 && functionChoice != 3 && functionChoice != 4 && functionChoice != 5 && functionChoice != 6 && functionChoice != 7 && functionChoice != 8);
		
		
		do {
			
			try {
				
				System.out.println("(B) The number of particles to use: (Between 5 and 100)");
				System.out.print("\t");
				
				numberOfParticles = input.nextInt();
				
				if(numberOfParticles < 0 || numberOfParticles > 100) {
					
					System.out.println("\nPlease pick a valid choice\n");
					Thread.sleep(1000);
				}
				
			} catch(InputMismatchException e) {
				
				System.out.println("\nPlease enter an integer\n");
				input.nextLine();
				Thread.sleep(1000);
			}
			
		} while(numberOfParticles < 0 || numberOfParticles > 100);
		
		
		do {
			
			try {
				
				System.out.println("(C) The number of iterations: (Between 10 and 500)");
				System.out.print("\t");
				
				numberOfIterations = input.nextInt();
				
				if(numberOfIterations < 10 || numberOfIterations > 100000) {
					
					System.out.println("\nPlease pick a valid choice\n");
					Thread.sleep(1000);
				}
				
			} catch(InputMismatchException e) {
				
				System.out.println("\nPlease enter an integer\n");
				input.nextLine();
				Thread.sleep(1000);
			}
			
		} while(numberOfIterations < 10 || numberOfIterations > 100000);
		
		function = getFunction(functionChoice);
		
		if(functionChoice == 6) {
			
			dimensions = 2;
		}
		
		else if(functionChoice == 7) {
			
			dimensions = 10;
		}
		
		//for(int i = 0; i < 100; i++) {
			
			//Swarm swarm = new Swarm(function, numberOfParticles, numberOfIterations, dimensions);
			//SwarmEMP swarm = new SwarmEMP(function, numberOfParticles, numberOfIterations, dimensions, memory);
		//}
		//Swarm swarm = new Swarm(function, numberOfParticles, numberOfIterations, dimensions);
		//SwarmEMP swarm = new SwarmEMP(function, numberOfParticles, numberOfIterations, dimensions, memory);
		SwarmAWL swarm = new SwarmAWL(function, numberOfParticles, numberOfIterations, dimensions);
	}
	
	public static Functions.FunctionChoices getFunction(int choice) {
		
		if(choice == 1) {
			
			return Functions.FunctionChoices.sphere;
		}
		
		else if(choice == 2) {
			
			return Functions.FunctionChoices.rosenbrock;
		}
		
		else if(choice == 3) {
			
			return Functions.FunctionChoices.ackley;
		}
		
		else if(choice == 4) {
			
			return Functions.FunctionChoices.griewank;
		}
		
		else if(choice == 5) {
			
			return Functions.FunctionChoices.rastrigin;
		}
		
		else if(choice == 6) {
			
			return Functions.FunctionChoices.schaffer2D;
		}
		
		else if(choice == 7) {
			
			return Functions.FunctionChoices.griewank10D;
		}
		
		return null;
	}
}
