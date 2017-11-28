package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import canvas.DrawingBoard;

public class Window extends JFrame implements ActionListener, ChangeListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_BRUSH_SIZE = 20;
	private static final int SLIDER_MIN = 1;
	private static final int SLIDER_MAX = 200;
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
	private JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL,SLIDER_MIN,SLIDER_MAX,DEFAULT_BRUSH_SIZE);
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
	
	public Window(){
		super("Pænt");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(750,500);
		setMinimumSize(new Dimension(750,500));
		
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
		toolList.setSelectedItem("Brush");
		toolPanel.add(toolList);
		toolPanel.add(sizeSlider);
		toolPanel.add(sizeTextField);
		toolList.addActionListener(this);
		sizeTextField.addKeyListener(this);
		sizeSlider.addChangeListener(this);
		

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
		
		addComponentListener(new ComponentAdapter(){
	        public void componentResized(ComponentEvent e){
	            Dimension d=Window.this.getSize();
	            Dimension minD=Window.this.getMinimumSize();
	            if(d.width<minD.width)
	                d.width=minD.width;
	            if(d.height<minD.height)
	                d.height=minD.height;
	            Window.this.setSize(d);
	        }
	    });
	}
	
	public static void main(String[] args){
		Window p = new Window();
		p.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		JComponent src = (JComponent) e.getSource();
		if(src == saveButton) saveButtonPressed();
		else if(src == loadButton) loadButtonPressed();
		else if(src == exportButton) exportButtonPressed();
		else if(src == toolList) toolComboBoxChanged();
		else {
			for(JToggleButton b : colorMap.keySet()){
				if(src == b){
					colorSelection();
					break;
				}
			}
		}
	}
	
	
	public void stateChanged(ChangeEvent e) {
		JComponent src = (JComponent) e.getSource();
		if(src == sizeSlider) sizeSliderChanged();
	}
	
	private void sizeSliderChanged(){
		int size = sizeSlider.getValue();
		sizeTextField.setText(Integer.toString(size));
		board.getSelectedTool().setSize(size);
	}
	
	
	private void sizeTextChanged(){
		try {
			int size = Integer.parseInt(sizeTextField.getText());
			if(size<SLIDER_MIN) size=SLIDER_MIN;
			if(size>SLIDER_MAX) size=SLIDER_MAX;
			board.getSelectedTool().setSize(size);
			sizeSlider.setValue(size);
		} catch(NumberFormatException e){}
	}
	
	
	private void toolComboBoxChanged(){
		String selectedTool = (String) toolList.getSelectedItem();
		board.selectTool(selectedTool);
		colorSelection();
		board.getSelectedTool().setSize(sizeSlider.getValue());
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
				currentDirectory.getAbsolutePath() + "/untitled.png");
		if(path!=null)
			try {
				ImageIO.write(board.getImage(), "PNG", new File(path));
			} catch(IOException e){
				JOptionPane.showMessageDialog(this, "Invalid path", "Saving error", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	private void loadButtonPressed(){
		File currentDirectory = new File("");
		String path = (String) JOptionPane.showInputDialog(
				this,
				"Open from path:",
				"Open file",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				currentDirectory.getAbsolutePath());
		if(path!=null)
			try {
				BufferedImage img = ImageIO.read(new File(path));
				board.setImage(img);
			} catch(IOException e){
				JOptionPane.showMessageDialog(this, "Invalid path", "Opening error", JOptionPane.ERROR_MESSAGE);
			}
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

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		JComponent source = (JComponent) e.getSource();
		if(source == sizeTextField) sizeTextChanged();
	}

	public void keyTyped(KeyEvent e) {

	}
	
}
