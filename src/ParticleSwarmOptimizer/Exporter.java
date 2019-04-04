package ParticleSwarmOptimizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Exporter {

	PrintWriter writer;
	
	public void export(double gBest, ArrayList<Double> gBestLocation, FunctionChoices function, int numberOfParticles, int numberOfIterations, int dimensions, String prefix) {
			
		try {
			File results = new File("results" + "-" + function + "-" + prefix + ".xls");
			boolean checkExists = results.exists();
				
			if(checkExists != true) {
					
				writer = new PrintWriter("results" + "-" + function + "-" + prefix + ".xls", "UTF-8");
				writer.println(function + "\t" + numberOfParticles + "\t" + numberOfIterations);
				writer.print("gBest \tCoordinates \n");
				writer.print(gBest + "\t");
				for(int i = 0; i < dimensions; i++) {
					
					writer.print(gBestLocation.get(i) + "\t");
				}
			    writer.println();				
			    }
				
			else {
					
				FileWriter fileWriter = new FileWriter("results" + "-" + function + "-" + prefix + ".xls", true);
			    writer = new PrintWriter(fileWriter);
			    writer.print(gBest + "\t");
			    for(int i = 0; i < dimensions; i++) {
					
					writer.print(gBestLocation.get(i) + "\t");
				}
			    writer.println();
			}
		} catch (FileNotFoundException e) {
				
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
				
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		writer.close();
	}
	
	public void export(ArrayList<Double> gBest, ArrayList<Integer> iterations, FunctionChoices function, String prefix) {
		
		try {
			File results = new File("gBest results" + "-" + function + "-" + prefix + "txt");
			
			writer = new PrintWriter("gBest results" + "-" + function + "-" + prefix + ".txt", "UTF-8");
			for(int i = 0; i < gBest.size(); i++) {
				
				writer.print("(" + iterations.get(i) + "," + gBest.get(i) + ")");
				writer.println();
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.close();
	}
	
	public void export(Double gBest, ArrayList<Double> gBestLocation, FunctionChoices function, int numberOfParticles, int numberOfIterations, int dimensions, int memory, String prefix) {
		
		try {
			File results = new File("results" + "-" + function + "-" + prefix + "-M" + memory + ".xls");
			boolean checkExists = results.exists();
				
			if(checkExists != true) {
					
				writer = new PrintWriter("results" + "-" + function + "-" + prefix + "-M" + memory + ".xls", "UTF-8");
				writer.println(function + "\t" + numberOfParticles + "\t" + numberOfIterations);
				writer.print("gBest \tCoordinates \n");
				writer.print(gBest + "\t");
				for(int i = 0; i < dimensions; i++) {
					
					writer.print(gBestLocation.get(i) + "\t");
				}
			    writer.println();				
			    }
				
			else {
					
				FileWriter fileWriter = new FileWriter("results" + "-" + function + "-" + prefix + "-M" + memory + ".xls", true);
			    writer = new PrintWriter(fileWriter);
			    writer.print(gBest + "\t");
			    for(int i = 0; i < dimensions; i++) {
					
					writer.print(gBestLocation.get(i) + "\t");
				}
			    writer.println();
			}
		} catch (FileNotFoundException e) {
				
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
				
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		writer.close();
	}
	
	public void export(ArrayList<Double> gBest, ArrayList<Integer> iterations, FunctionChoices function, int memory, String prefix) {
		
		try {
			File results = new File("gBest results" + "-" + function + "-" + prefix + "-M" + memory + ".txt");
			
			writer = new PrintWriter("gBest results" + "-" + function + "-" + prefix + "-M" + memory + ".txt", "UTF-8");
			for(int i = 0; i < gBest.size(); i++) {
				
				writer.print("(" + iterations.get(i) + "," + gBest.get(i) + ")");
				writer.println();
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.close();
	}
}