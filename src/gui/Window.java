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

import toolkit.PaintingDrawTool;

import canvas.DrawingBoard;

/**
 * Main window of the application.
 * @author Dániel Gál
 *
 */
public class Window extends JFrame implements ActionListener, ChangeListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_BRUSH_SIZE = 20;
	private static final int SLIDER_MIN = 1;
	private static final int SLIDER_MAX = 200;
	// Panels
	private JPanel menuPanel, toolPanel, saveLoadPanel, colorPanel;
	// Buttons
	private JButton saveButton, openButton;
	// Tool options
	private JTextField sizeTextField;
	private JSlider sizeSlider;
	private JComboBox<String> toolList;
	// Color
	private ButtonGroup colorGroup;
	private JToggleButton blackButton, blueButton, yellowButton, whiteButton, greenButton, redButton;
	private HashMap<JToggleButton, Color> colorMap;
	// Board
	private DrawingBoard board;
	
	/**
	 * Class constructor
	 * <p>
	 * Creates a menu on the top of the window and a DrawingBoard in the center.
	 */
	public Window(){
		super("Pænt");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(750,500);
		setMinimumSize(new Dimension(750,500));
		setLayout(new BorderLayout());
		windowResizeFix();
		// Drawing board
		board = new DrawingBoard();
		board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(new JScrollPane(board), BorderLayout.CENTER);
		// Menu
		initMenu();
		add(menuPanel, BorderLayout.NORTH);
	}
	
	
// --------------- INIT FUNCTIONS -----------------------------------------------------------------
	
	
	/**
	 * Initialize JPanels
	 * <p>
	 * Creates basic empty panels with layouts and adds them to the menu.
	 */
	private void initPanels(){
		menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// Tool
		toolPanel = new JPanel(new FlowLayout());
		menuPanel.add(toolPanel);
		// Color
		colorPanel = new JPanel(new GridLayout(2,3,5,5));
		colorPanel.setBorder(BorderFactory.createTitledBorder("Color"));
		menuPanel.add(colorPanel);
		// Save, load
		saveLoadPanel = new JPanel(new GridLayout(2,1,5,5));
		menuPanel.add(saveLoadPanel);
	}
	
	
	/**
	 * Initialize buttons
	 * <p>
	 * Creates all the buttons, places them on the panels and adds ActionListeners to all of them.
	 */
	private void initButtons(){
		// SAVE
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveLoadPanel.add(saveButton);
		// OPEN
		openButton = new JButton("Open");
		openButton.addActionListener(this);
		saveLoadPanel.add(openButton);
		// COLOR
		colorGroup = new ButtonGroup();
		blackButton = new JToggleButton("Black", true);
		colorGroup.add(blackButton);
		colorPanel.add(blackButton);
		blueButton = new JToggleButton("Blue");
		colorGroup.add(blueButton);
		colorPanel.add(blueButton);
		yellowButton = new JToggleButton("Yellow");
		colorGroup.add(yellowButton);
		colorPanel.add(yellowButton);
		whiteButton = new JToggleButton("White");
		colorGroup.add(whiteButton);
		colorPanel.add(whiteButton);
		greenButton = new JToggleButton("Green");
		colorGroup.add(greenButton);
		colorPanel.add(greenButton);
		redButton = new JToggleButton("Red");
		colorGroup.add(redButton);
		colorPanel.add(redButton);
	}
	
	
	/**
	 * Inizialize color selection data structure
	 * <p>
	 * Creates a HashMap with the color JToggleButtons as the keys and the Colors as the values.
	 * Adds ActionListeners to the buttons.
	 */
	private void initColor(){
		colorMap = new HashMap<JToggleButton, Color>();
		colorMap.put(blackButton, Color.BLACK);
		colorMap.put(blueButton, Color.BLUE);
		colorMap.put(yellowButton, Color.YELLOW);
		colorMap.put(whiteButton, Color.WHITE);
		colorMap.put(greenButton, Color.GREEN);
		colorMap.put(redButton, Color.RED);
		for(JToggleButton button : colorMap.keySet()){
			button.addActionListener(this);
		}
	}
	
	
	/**
	 * Initialize tool selection and tool options
	 * <p>
	 * Creates a JComboBox with the tools, a JSlider and a JTextField for changing the tool size.
	 */
	private void initToolOptions(){
		// Panel
		toolPanel.setBorder(BorderFactory.createTitledBorder("Size"));
		// Combo box
		toolList = new JComboBox<String>(board.getToolNameArray());
		toolList.setSelectedItem("Brush");
		toolList.addActionListener(this);
		toolPanel.add(toolList);
		// Slider
		sizeSlider = new JSlider(JSlider.HORIZONTAL,SLIDER_MIN,SLIDER_MAX,DEFAULT_BRUSH_SIZE);
		sizeSlider.addChangeListener(this);
		toolPanel.add(sizeSlider);
		// TextField
		sizeTextField = new JTextField(3);
		sizeTextField.setText(Integer.toString(DEFAULT_BRUSH_SIZE));
		sizeTextField.addKeyListener(this);
		toolPanel.add(sizeTextField);
	}
	
	
	/**
	 * Calls all the initXxx() functions.
	 */
	private void initMenu(){
		initPanels();
		initButtons();
		initColor();
		initToolOptions();
	}
	
	
	/**
	 * This method is making sure that the minimum window size constraint is working on all platforms.
	 * (It was not working on Ubuntu with i3 window manager)
	 */
	private void windowResizeFix(){
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
	
	
// --------------- INIT FUNCTIONS END -------------------------------------------------------------
	
	
	public static void main(String[] args){
		Window p = new Window();
		p.setVisible(true);
	}

	
// --------------- LISTENER INTERFACE OVERRIDE METHODS --------------------------------------------
	
	
	/**
	 * Override of the ActionListener's actionPerformed method.
	 * <p>
	 * Checks which button was pressed and calls the corresponding method.
	 * @see ActionListener
	 */
	public void actionPerformed(ActionEvent e) {
		JComponent src = (JComponent) e.getSource();
		if(src == saveButton) saveButtonPressed();
		else if(src == openButton) openButtonPressed();
		else if(src == toolList) toolComboBoxChanged();
		else colorSelection();
	}
	
	
	/**
	 * Override of the ChangeListener interface's stateChanged method.
	 * <p>
	 * Checks which component was changed and calls the corresponding method.
	 * @see ChangeListener
	 */
	public void stateChanged(ChangeEvent e) {
		JComponent src = (JComponent) e.getSource();
		if(src == sizeSlider) sizeSliderChanged();
	}
	
	
	/**
	 * Override of the KeyListener interface's keyReleased method.
	 * <p>
	 * Checks which component had a key pressed on it and calls the corresponding method.
	 * @see KeyListener
	 */
	public void keyReleased(KeyEvent e) {
		JComponent source = (JComponent) e.getSource();
		if(source == sizeTextField) sizeTextChanged();
	}
	
	
	/**
	 * Override of the KeyListener interface's keyTyped method.
	 * Not used but has to be implemented
	 */
	public void keyTyped(KeyEvent e) {}
	
	
	/**
	 * Override of the KeyListener interface's keyPressed method.
	 * Not used but has to be implemented
	 */
	public void keyPressed(KeyEvent e) {}
	
	
// --------------- LISTENER INTERFACE OVERRIDE METHODS END ----------------------------------------
	
// --------------- CUSTOM ACTION PERFORM METHODS --------------------------------------------------	
	
	
	/**
	 * The method that gets called when the JSlider changes.
	 * <p>
	 * Sets the size of the currently selected tool as well as the value in the JTextField.
	 */
	private void sizeSliderChanged(){
		int size = sizeSlider.getValue();
		sizeTextField.setText(Integer.toString(size));
		((PaintingDrawTool) board.getSelectedTool()).setSize(size);
	}
	
	
	/**
	 * The method that gets called when the JTextField changes.
	 * <p>
	 * Sets the size of the currently selected tool as well as the value in the JSlider.
	 */
	private void sizeTextChanged(){
		try {
			int size = Integer.parseInt(sizeTextField.getText());
			if(size<SLIDER_MIN) size=SLIDER_MIN;
			if(size>SLIDER_MAX) size=SLIDER_MAX;
			((PaintingDrawTool) board.getSelectedTool()).setSize(size);
			sizeSlider.setValue(size);
		} catch(NumberFormatException e){}
	}
	
	
	/**
	 * The method that gets called when the JComboBox changes.
	 * <p>
	 * Selects the tool currently displayed in the JComboBox.
	 * Calls colorSelection() so that the selected tool has the currently selected color
	 * and sets the size to the current size values in the JTextField and the JSlider.
	 */
	private void toolComboBoxChanged(){
		String selectedTool = (String) toolList.getSelectedItem();
		board.selectTool(selectedTool);
		colorSelection();
		((PaintingDrawTool) board.getSelectedTool()).setSize(sizeSlider.getValue());
	}
	
	
	/**
	 * The method that gets called when the Save button is pressed.
	 * <p>
	 * Opens a dialog window where the user can type in a path where they want their image saved.
	 * The BufferedImage gets written to a .PNG file.
	 */
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
				JOptionPane.showMessageDialog(this,
											  "Invalid path",
											  "Saving error",
											  JOptionPane.ERROR_MESSAGE);
			}
	}
	
	
	/**
	 * The method that gets called when the Open button is pressed.
	 * <p>
	 * Opens a dialog window where the user can type in a path of the image they want to open.
	 * The .PNG image gets read into a BufferedImage object
	 * and it is set as the DrawingBoard's BufferedImages component.
	 */
	private void openButtonPressed(){
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
				JOptionPane.showMessageDialog(this,
											  "Invalid path",
											  "Opening error",
											  JOptionPane.ERROR_MESSAGE);
			}
	}


	/**
	 * Color selection method
	 * <p>
	 * Detects which JToggleButton is currently selected and sets the color of
	 * the currently selected tool to that color.
	 */
	public void colorSelection(){
		for(JToggleButton b : colorMap.keySet()){
			if(b.isSelected()){
				((PaintingDrawTool) board.getSelectedTool()).setColor(colorMap.get(b));
				break;
			}
		}
	}
	
// --------------- CUSTOM ACTION PERFORM METHODS END ----------------------------------------------	

	
}
