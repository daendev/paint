package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import gui.DrawingBoard;

public class PaentWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_BRUSH_SIZE = 20;

	private JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel brushPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel saveLoadExportPanel = new JPanel(new GridLayout(3,1,5,5));
	private JPanel colorPanel = new JPanel(new GridLayout(2,3,5,5));
	
	private JButton saveButton = new JButton("Save");
	private JButton loadButton = new JButton("Load");
	private JButton exportButton = new JButton("Export");
	
	private JTextField brushTextField = new JTextField();
	private JSlider brushSlider = new JSlider(JSlider.HORIZONTAL,1,200,DEFAULT_BRUSH_SIZE);
	
	private ButtonGroup colorGroup = new ButtonGroup();
	private JToggleButton blackButton = new JToggleButton("Black", true);
	private JToggleButton blueButton = new JToggleButton("Blue");
	private JToggleButton yellowButton = new JToggleButton("Yellow");
	private JToggleButton whiteButton = new JToggleButton("White");
	private JToggleButton greenButton = new JToggleButton("Green");
	private JToggleButton redButton = new JToggleButton("Red");
	
	private DrawingBoard board = new DrawingBoard();
	
	
	public PaentWindow(){
		super("Paent");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(650,400);
		
		brushTextField.setText(Integer.toString(DEFAULT_BRUSH_SIZE));
		
		colorGroup.add(blackButton);
		colorGroup.add(blueButton);
		colorGroup.add(yellowButton);
		colorGroup.add(whiteButton);
		colorGroup.add(greenButton);
		colorGroup.add(redButton);
		
		saveLoadExportPanel.add(saveButton);
		saveLoadExportPanel.add(loadButton);
		saveLoadExportPanel.add(exportButton);
		
		brushPanel.add(brushSlider);
		brushPanel.add(brushTextField);
		brushPanel.setBorder(BorderFactory.createTitledBorder("Brush"));
				
		colorPanel.add(blackButton);
		colorPanel.add(blueButton);
		colorPanel.add(yellowButton);
		colorPanel.add(whiteButton);
		colorPanel.add(greenButton);
		colorPanel.add(redButton);
		colorPanel.setBorder(BorderFactory.createTitledBorder("Color"));
		
		menuPanel.add(brushPanel);
		menuPanel.add(colorPanel);
		menuPanel.add(saveLoadExportPanel);
		
		board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		super.setLayout(new BorderLayout());
		super.add(menuPanel, BorderLayout.NORTH);
		super.add(board, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args){
		PaentWindow p = new PaentWindow();
		p.setVisible(true);
	}
	
}
