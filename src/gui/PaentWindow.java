package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import gui.DrawingBoard;

public class PaentWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_BRUSH_SIZE = 20;

	// Panels
	private JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel toolPanel = new JPanel(new FlowLayout());
	private JPanel saveLoadExportPanel = new JPanel(new GridLayout(3,1,5,5));
	private JPanel colorPanel = new JPanel(new GridLayout(2,3,5,5));
	
	// Buttons
	private JButton saveButton = new JButton("Save");
	private JButton loadButton = new JButton("Load");
	private JButton exportButton = new JButton("Export");
	
	// Textfield, slider, combobox
	private JTextField sizeTextField = new JTextField(3);
	private JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL,1,200,DEFAULT_BRUSH_SIZE);
	private JComboBox<String> toolList;
	
	// Color buttons
	private ButtonGroup colorGroup = new ButtonGroup();
	private JToggleButton blackButton = new JToggleButton("Black", true);
	private JToggleButton blueButton = new JToggleButton("Blue");
	private JToggleButton yellowButton = new JToggleButton("Yellow");
	private JToggleButton whiteButton = new JToggleButton("White");
	private JToggleButton greenButton = new JToggleButton("Green");
	private JToggleButton redButton = new JToggleButton("Red");
	private static final HashMap<JToggleButton, Color> colorMap = new HashMap<JToggleButton, Color>();
	
	private DrawingBoard board = new DrawingBoard();
	
	private void initColorMap(){
		colorMap.put(blackButton, Color.BLACK);
		colorMap.put(blueButton, Color.BLUE);
		colorMap.put(yellowButton, Color.YELLOW);
		colorMap.put(whiteButton, Color.WHITE);
		colorMap.put(greenButton, Color.GREEN);
		colorMap.put(redButton, Color.RED);
	}
	
	public PaentWindow(){
		super("Paent");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(750,400);
		
		sizeTextField.setText(Integer.toString(DEFAULT_BRUSH_SIZE));
		
		colorGroup.add(blackButton);
		colorGroup.add(blueButton);
		colorGroup.add(yellowButton);
		colorGroup.add(whiteButton);
		colorGroup.add(greenButton);
		colorGroup.add(redButton);
		
		saveLoadExportPanel.add(saveButton);
		saveLoadExportPanel.add(loadButton);
		saveLoadExportPanel.add(exportButton);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		exportButton.addActionListener(this);
				
		initColorMap();

		toolPanel.setBorder(BorderFactory.createTitledBorder("Size"));
		toolList = new JComboBox<String>(board.getToolNameArray());
		toolPanel.add(toolList);
		toolPanel.add(sizeSlider);
		toolPanel.add(sizeTextField);
		

		colorPanel.add(blackButton);
		colorPanel.add(blueButton);
		colorPanel.add(yellowButton);
		colorPanel.add(whiteButton);
		colorPanel.add(greenButton);
		colorPanel.add(redButton);
		blackButton.addActionListener(this);
		blueButton.addActionListener(this);
		yellowButton.addActionListener(this);
		whiteButton.addActionListener(this);
		greenButton.addActionListener(this);
		redButton.addActionListener(this);
		colorPanel.setBorder(BorderFactory.createTitledBorder("Color"));
		
		menuPanel.add(toolPanel);
		menuPanel.add(colorPanel);
		menuPanel.add(saveLoadExportPanel);
		
		board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		super.setLayout(new BorderLayout());
		super.add(menuPanel, BorderLayout.NORTH);
		super.add(new JScrollPane(board), BorderLayout.CENTER);
	}
	
	public static void main(String[] args){
		PaentWindow p = new PaentWindow();
		p.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		JComponent src = (JComponent) e.getSource();
		if(src == saveButton) saveButtonPressed();
		else if(src == loadButton) loadButtonPressed();
		else if(src == exportButton) exportButtonPressed();
		else {
			for(JToggleButton b : colorMap.keySet()){
				if(src == b){
					colorSelection();
					break;
				}
			}
		}
	}
	
	private void saveButtonPressed(){
		File currentDirectory = new File("");
		String path = (String) JOptionPane.showInputDialog(
				this,
				"Choose a path and filename:",
				"Save file",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				currentDirectory.getAbsolutePath() + "/untitled.ser");
		System.out.println(path);
	}
	
	private void loadButtonPressed(){
		
	}

	private void exportButtonPressed(){
		
	}

	public void colorSelection(){
		for(JToggleButton b : colorMap.keySet()){
			if(b.isSelected()){
				board.getSelectedTool().setColor(colorMap.get(b));
				break;
			}
		}
	}
}
