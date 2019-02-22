package ParticleSwarmOptimizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Exporter {

	PrintWriter writer;
	
	public void export(Double gBest) {
		
		try {
			File results = new File("results.xls");
			boolean checkExists = results.exists();
			
			if(checkExists != true) {
				
				writer = new PrintWriter("results.xls", "UTF-8");
				writer.print(gBest + "\t");
			}
			
			else {
				
				FileWriter fileWriter = new FileWriter("results.xls", true);
			    writer = new PrintWriter(fileWriter);
			    writer.print(gBest + "\t");
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
