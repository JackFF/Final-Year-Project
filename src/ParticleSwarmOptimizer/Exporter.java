package ParticleSwarmOptimizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import ParticleSwarmOptimizer.Functions.FunctionChoices;

public class Exporter {

	PrintWriter writer;
	
	public void export(Double gBest, double x, double y, double z, FunctionChoices function, int numberOfParticles, int numberOfIterations, int dimensions) {
		
		if(dimensions == 1) {
			
			try {
				File results = new File("results" + "-" + function + ".xls");
				boolean checkExists = results.exists();
				
				if(checkExists != true) {
					
					writer = new PrintWriter("results" + "-" + function + ".xls", "UTF-8");
					writer.println(function + "\t" + numberOfParticles + "\t" + numberOfIterations);
					writer.print("gBest \tX-Location \n");
					writer.print(gBest + "\t");
					writer.print(x + "\t");
				    writer.println();
				}
				
				else {
					
					FileWriter fileWriter = new FileWriter("results" + "-" + function + ".xls", true);
				    writer = new PrintWriter(fileWriter);
				    writer.print(gBest + "\t");
				    writer.print(x + "\t");
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
		
		else if(dimensions == 2) {
			
			try {
				File results = new File("results" + "-" + function + ".xls");
				boolean checkExists = results.exists();
				
				if(checkExists != true) {
					
					writer = new PrintWriter("results" + "-" + function + ".xls", "UTF-8");
					writer.println(function + "\t" + numberOfParticles + "\t" + numberOfIterations);
					writer.print("gBest \tX-Location \tY-Location \n");
					writer.print(gBest + "\t");
					writer.print(x + "\t");
				    writer.print(y + "\t");
				    writer.println();
				}
				
				else {
					
					FileWriter fileWriter = new FileWriter("results" + "-" + function + ".xls", true);
				    writer = new PrintWriter(fileWriter);
				    writer.print(gBest + "\t");
				    writer.print(x + "\t");
				    writer.print(y + "\t");
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
		
		else if(dimensions == 3) {
			
			try {
				File results = new File("results" + "-" + function + ".xls");
				boolean checkExists = results.exists();
				
				if(checkExists != true) {
					
					writer = new PrintWriter("results" + "-" + function + ".xls", "UTF-8");
					writer.println(function + "\t" + numberOfParticles + "\t" + numberOfIterations);
					writer.print("gBest \tX-Location \tY-Location \tZ-Location \n");
					writer.print(gBest + "\t");
					writer.print(x + "\t");
				    writer.print(y + "\t");
				    writer.print(z);
				    writer.println();
				}
				
				else {
					
					FileWriter fileWriter = new FileWriter("results" + "-" + function + ".xls", true);
				    writer = new PrintWriter(fileWriter);
				    writer.print(gBest + "\t");
				    writer.print(x + "\t");
				    writer.print(y + "\t");
				    writer.print(z + "\t");
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
	}
}