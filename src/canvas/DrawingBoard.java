package canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import tools.*;

import javax.swing.JPanel;


public class DrawingBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int IMAGE_WIDTH = 700;
	private static final int IMAGE_HEIGHT = 350;
	
	private BufferedImage img;
	private Map<String, DrawTool> tools = new HashMap<String, DrawTool>();
	private DrawTool selectedTool;

	
// CONSTRUCTOR
	/**
	 * Class constructor
	 * <p>
	 * Creates a new white drawing board with the default image size values. 
	 * Calls initTools() and selects the Brush tool. 
	 */
	public DrawingBoard(){
		img = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		this.addMouseListener(new clickListener());
		this.addMouseMotionListener(new motionListener());
		
		initTools();
		selectTool("Brush");
	}
	
	
// INIT TOOLS
	/**
	 * Initializes tools
	 * <p>
	 * Add tools to the drawing board in this function.
	 */
	public void initTools(){
		tools.put("Brush", new Brush(getImageGraphics()));
		tools.put("Pencil", new Pencil(getImageGraphics()));
	}
	
	
// GET TOOL NAMES
	/**
	 * Get the names of the currently used tools in an array.
	 * @return an array of Strings with the tool names
	 */
	public String[] getToolNameArray(){
		return tools.keySet().toArray(new String[tools.size()]);
	}
	
	
// SELECT TOOL	
	/**
	 * Select a tool from the currently used tools.
	 * @param t the name of the tool we want to select
	 */
	public void selectTool(String t){
		selectedTool = tools.get(t);
	}
	
	
// GET SELECTED TOOL
	/**
	 * Get the currently selected tool.
	 * @return the tool that is currently selected
	 */
	public PaintingDrawTool getSelectedTool(){
		return (PaintingDrawTool) selectedTool;
	}
	
	
// GET GRAPHICS
	/**
	 * Get the graphics of the BufferedImage.
	 * @return the graphics of the image
	 */
	public Graphics getImageGraphics(){
		return img.getGraphics();
	}
	
	
// IMAGE GETTER AND SETTER
	/**
	 * 
	 * @return
	 */
	public BufferedImage getImage(){
		return img;
	}
	public void setImage(BufferedImage i){
		img = i;
		repaint();
		initTools();
		selectTool("Brush");
	}
	
	
// PAINT COMPONENT
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	
	
// PREFFERRED SIZE OVERRIDE FOR SCROLLPANE
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(img.getWidth(), img.getHeight());
	}
	
	
// MOUSE MOTION
	class motionListener implements MouseMotionListener {

		public void mouseDragged(MouseEvent drag) {
			selectedTool.performAction(drag);
			repaint();
		}

		public void mouseMoved(MouseEvent move) {}
	}
	

// MOUSE CLICK
	class clickListener implements MouseListener {

		public void mouseClicked(MouseEvent click) {
			selectedTool.performAction(click);
			repaint();
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}

}
