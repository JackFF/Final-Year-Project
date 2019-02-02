package ParticleSwarmOptimizer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner input = new Scanner(System.in);
		int functionChoice = 0;
		int numberOfParticles = 0;
		int numberOfIterations = 0;
		Functions.FunctionChoices function = null;
		
		System.out.println("Welcome to my Particle Swarm Optimizer!");
		System.out.println("You must select each of the following: ");
		System.out.println("Only input integers");
		
		do {
			
			try {
				
				System.out.println("(A) Which function to use: ");
				System.out.println("\t(1) x^4 - 2(x^3)");
				System.out.println("\t(2) x^7 - 5(x^4) + 7(x^2) + 1");
				System.out.print("\t");
				
				functionChoice = input.nextInt();
				
				if(functionChoice < 1 || functionChoice > 2) {
					
					System.out.println("\nPlease pick a valid choice\n");
					Thread.sleep(1000);
				}
			} catch(InputMismatchException e) {
				
				System.out.println("\nPlease enter an integer\n");
				input.nextLine();
				Thread.sleep(1000);
			}
			
		} while(functionChoice !=1 && functionChoice != 2);
		
		
		do {
			
			try {
				
				System.out.println("(B) The number of particles to use: (Between 5 and 100)");
				System.out.print("\t");
				
				numberOfParticles = input.nextInt();
				
				if(numberOfParticles < 5 || numberOfParticles > 100) {
					
					System.out.println("\nPlease pick a valid choice\n");
					Thread.sleep(1000);
				}
				
			} catch(InputMismatchException e) {
				
				System.out.println("\nPlease enter an integer\n");
				input.nextLine();
				Thread.sleep(1000);
			}
			
		} while(numberOfParticles < 5 || numberOfParticles > 100);
		
		
		do {
			
			try {
				
				System.out.println("(C) The number of iterations: (Between 10 and 500)");
				System.out.print("\t");
				
				numberOfIterations = input.nextInt();
				
				if(numberOfIterations < 10 || numberOfIterations > 500) {
					
					System.out.println("\nPlease pick a valid choice\n");
					Thread.sleep(1000);
				}
				
			} catch(InputMismatchException e) {
				
				System.out.println("\nPlease enter an integer\n");
				input.nextLine();
				Thread.sleep(1000);
			}
			
		} while(numberOfIterations < 10 || numberOfIterations > 500);
		
		function = getFunction(functionChoice);
		Swarm swarm = new Swarm(function, numberOfParticles, numberOfIterations);
	}
	
	public static Functions.FunctionChoices getFunction(int choice) {
		
		if(choice == 1) {
			
			return Functions.FunctionChoices.testFunction1;
		}
		else if(choice == 2) {
			
			return Functions.FunctionChoices.testFunction2;
		}
		
		return null;
	}
}
