package ParticleSwarmOptimizer;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class GUI extends JFrame {
	
	private JLabel functionSelectText;
	private JComboBox functionSelect;
	private JLabel particleSelectText;
	SpinnerModel numberOfParticles = new SpinnerNumberModel(5, 5, 100, 1);
	JSpinner numOfParticles = new JSpinner(numberOfParticles);
	private JLabel iterationSelectText;
	SpinnerModel numberOfIterations = new SpinnerNumberModel(10, 10, 500, 1);
	JSpinner numOfIterations = new JSpinner(numberOfIterations);
	private JLabel whiteSpace1, whiteSpace2, whiteSpace3, whiteSpace4;
	private JButton start;
	int functionChoice;
	int numberOfParticlesSelected;
	int numberOfIterationsSelected;
	Functions.FunctionChoices function = null;
	private JLabel results;
	
	private String functions[] = {"tF1", "tF2", "Booth's Function", "Ackley's Function", "Sphere", 
			"Rosenbrock", "Griewank"};

	public GUI() {
		
		super("Particle Swarm Optimizer");
		
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		
		functionSelectText = new JLabel("Please select the function you would like to use: ");
		
		functionSelect = new JComboBox(functions);
		functionSelect.setMaximumRowCount(7);
		
		functionSelect.addItemListener(
				
				new ItemListener() {
					
					public void itemStateChanged(ItemEvent event) {
						
						if(event.getStateChange() == event.SELECTED) {
							
							functionChoice = functionSelect.getSelectedIndex();
							System.out.println(functionChoice);
						}
					}
				});
		
		container.add(functionSelectText);
		container.add(functionSelect);
		
		whiteSpace1 = new JLabel("                                                                   ");
		container.add(whiteSpace1);
		
		particleSelectText = new JLabel("Please select the number of particle you would like to use: ");
		
		//numberOfParticlesSelected = (int) numOfParticles.getValue();
		//System.out.println(numberOfParticlesSelected);
		
		container.add(particleSelectText);
		container.add(numOfParticles);
		
		whiteSpace2 = new JLabel("                                                                   ");
		container.add(whiteSpace2);
		
		iterationSelectText = new JLabel("Please select the number of iterations you would like to use: ");
		
		container.add(iterationSelectText);
		container.add(numOfIterations);
		
		whiteSpace3 = new JLabel("                                                                     "
				+ "                                                                     ");
		container.add(whiteSpace3);
		
		start = new JButton("Start");
		container.add(start);
		
		setSize(500, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start.addActionListener(
				
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						numberOfParticlesSelected = (int) numOfParticles.getValue();
						//System.out.println(numberOfParticlesSelected);
						
						numberOfIterationsSelected = (int) numOfIterations.getValue();
						//System.out.println(numberOfIterationsSelected);
						
						function = getFunction(functionChoice);
						System.out.println(function);
						Swarm swarm = new Swarm(function, numberOfParticlesSelected, numberOfIterationsSelected);
						
						whiteSpace4 = new JLabel("                                                     "
								+ "                                                                    "
								+ "                                                                    ");
						container.add(whiteSpace4);
						
						double gBest = swarm.gBest;
						
						results = new JLabel("gBest: " + gBest);
						container.add(results);
						
						revalidate();
						repaint();
						
						//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				});
	}
	
	public static Functions.FunctionChoices getFunction(int choice) {
		
		if(choice == 0) {
			
			return Functions.FunctionChoices.testFunction1;
		}
		else if(choice == 1) {
			
			return Functions.FunctionChoices.testFunction2;
		}
		
		else if(choice == 2) {
			
			return Functions.FunctionChoices.boothsFunction;
		}
		
		else if(choice == 3) {
			
			return Functions.FunctionChoices.ackleysFunction;
		}
		
		else if(choice == 4) {
			
			return Functions.FunctionChoices.sphere;
		}
		
		else if(choice == 5) {
			
			return Functions.FunctionChoices.rosenbrock;
		}
		
		else if(choice == 6) {
			
			return Functions.FunctionChoices.griewank;
		}
		
		else {
			
			return Functions.FunctionChoices.testFunction1;
		}
	}
}
