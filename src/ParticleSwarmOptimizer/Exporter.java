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
	
	public void export(Double gBest, ArrayList<Double> gBestLocation, FunctionChoices function, int numberOfParticles, int numberOfIterations, int dimensions) {
			
		try {
			File results = new File("results" + "-" + function + ".xls");
			boolean checkExists = results.exists();
				
			if(checkExists != true) {
					
				writer = new PrintWriter("results" + "-" + function + ".xls", "UTF-8");
				writer.println(function + "\t" + numberOfParticles + "\t" + numberOfIterations);
				writer.print("gBest \tCoordinates \n");
				writer.print(gBest + "\t");
				for(int i = 0; i < dimensions; i++) {
					
					writer.print(gBestLocation.get(i) + "\t");
				}
			    writer.println();				
			    }
				
			else {
					
				FileWriter fileWriter = new FileWriter("results" + "-" + function + ".xls", true);
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
	
	public void export(ArrayList<Double> gBest, ArrayList<Integer> iterations, FunctionChoices function) {
		
		try {
			File results = new File("gBest results" + "-" + function + ".xls");
			
			writer = new PrintWriter("gBest results" + "-" + function + ".xls", "UTF-8");
			writer.println(function + "\t");
			writer.print("gBest Value \titeration\n");
			for(int i = 0; i < gBest.size(); i++) {
				
				writer.print(gBest.get(i) + "\t" + iterations.get(i));
				writer.println();
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.close();
		
		/*try {
			File results = new File("results" + "-" + function + ".xls");
			boolean checkExists = results.exists();
				
			if(checkExists != true) {
					
				writer = new PrintWriter("results" + "-" + function + ".xls", "UTF-8");
				writer.println(function + "\t" + numberOfParticles + "\t" + numberOfIterations);
				writer.print("gBest \tCoordinates \n");
				writer.print(gBest + "\t");
				for(int i = 0; i < dimensions; i++) {
					
					writer.print(gBestLocation.get(i) + "\t");
				}
			    writer.println();				
			    }
				
			else {
					
				FileWriter fileWriter = new FileWriter("results" + "-" + function + ".xls", true);
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
			
		writer.close();*/
	}
}