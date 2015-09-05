package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.TextController;
import model.PlanesData;
import model.Time;

/**
 * TextView class renders GUI and responds to user's inputs.
 * @author risam
 *
 */
public class TextView implements ActionListener, Observer {

	private TextController controller;
//	private SimulationDataLevel2 data;
	
	private JLabel frequencyLabel; 
	private JTextField frequencyInput;
	private JLabel durationLabel; 
	private JTextField durationInput;
	private JLabel countLabel;
	private JTextField countInput;
	
	private JButton getOverallStatsButton;
	private JButton getDetailsButton; 
	private JButton resetButton; 

	private JTextArea resultArea = new JTextArea(19, 30);
	private JScrollPane resultPane = new JScrollPane(resultArea);
	private JFrame frame;
	private JPanel controlPanel;
	private JPanel setupPanel;
	private JPanel buttonPanel;

	/**
	 * Create a text view with GUI.
	 */
	public TextView() {
		prepareView();
	}
	
	/**
	 * Prepare components for the GUI.
	 */
	private void prepareView() {
		frame = new JFrame();
		controlPanel = new JPanel();
		setupPanel = new JPanel();
		buttonPanel = new JPanel(new GridLayout(3,1));
		
		frequencyLabel = new JLabel("Commercial plane frequency:");
		frequencyInput = new JTextField(5);
		durationLabel = new JLabel("Duration of simulation:");
		durationInput = new JTextField(5);
		countLabel = new JLabel("A number of simulations:");
		countInput = new JTextField(5);
		
		getOverallStatsButton = new JButton("Get Stats");
		getDetailsButton = new JButton("Get Details");
		resetButton = new JButton("Reset Data");
		
		GroupLayout layout = new GroupLayout(setupPanel); 
		setupPanel.setLayout(layout);
		setupPanel.setBorder(BorderFactory.createTitledBorder("Setup"));
		
		buttonPanel.add(getOverallStatsButton);
		buttonPanel.add(getDetailsButton);
		buttonPanel.add(resetButton);

		getOverallStatsButton.addActionListener(this);
		getDetailsButton.addActionListener(this);
		resetButton.addActionListener(this);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(frequencyLabel)
						.addComponent(durationLabel)
						.addComponent(countLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(frequencyInput)
						.addComponent(durationInput)
						.addComponent(countInput))
				
				);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(frequencyLabel)
						.addComponent(frequencyInput))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(durationLabel)
						.addComponent(durationInput))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(countLabel)
						.addComponent(countInput))
				);

		controlPanel.add(setupPanel, "West");
		controlPanel.add(buttonPanel, "Center");
		frame.getContentPane().add(controlPanel, "North");
		frame.getContentPane().add(resultPane, "South");

		frame.setTitle("Air Traffic Simulator");
		frame.setSize(500, 500);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	/**
	 * Respond to button clicks.
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		// Handle Simulate button
		if (source == getOverallStatsButton) {
		
			Double probability = Double.parseDouble(frequencyInput.getText());
			Integer hour = Integer.parseInt(durationInput.getText());
			Integer count = Integer.parseInt(countInput.getText());
			
			if (hour != null && count != null && probability != null) {
				Time simulationDuration = new Time(hour, 0 , 0);			
//				data = SimulationDataLevel2.getInstance();
//				data.addObserver(this);
				controller = TextController.getInstance();
				String stats = controller.getAveragedStats(simulationDuration, count, probability);
				resultArea.append(stats);
			}

		} 
		
		// Handle Get Details button
		else if (source == getDetailsButton){
			String details = controller.getDetails();
			resultArea.append(details);
		}
		
		// Handle Reset button
		else if (source == resetButton) {
			resultArea.setText("");
			controller.resetSimulation();
		} 
	}

	@Override
	public void update(Observable o, Object arg) {
		PlanesData data = (PlanesData) o;
		//resultArea.append(controller.printNewData(data));
	}

}
