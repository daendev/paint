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

/**
 * A JPanel that can be drawn on. Includes drawing tools.
 * @author Dániel Gál
 *
 */
public class DrawingBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int IMAGE_WIDTH = 700;
	private static final int IMAGE_HEIGHT = 350;
	
	private BufferedImage img;
	private Map<String, DrawTool> tools = new HashMap<String, DrawTool>();
	private DrawTool selectedTool;

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
	
	
	/**
	 * Initializes tools
	 * Adds the BufferedImage's Graphics to the tools, so they can manipulate the image.
	 * <p>
	 * Add tools to the drawing board in this function.
	 */
	private void initTools(){
		tools.put("Brush", new Brush(img.getGraphics()));
		tools.put("Pencil", new Pencil(img.getGraphics()));
		tools.put("Oval", new Oval(img.getGraphics()));
	}


    /**
     * Loops through the tools and sets the tools' Graphics to that of the image's.
     */
	private void setToolGraphics(){
		for(DrawTool t : tools.values()){
		    t.setGraphics(img.getGraphics());
        }
	}
	
	
	/**
	 * Get the names of the currently used tools in an array.
	 * @return an array of Strings with the tool names
	 */
	public String[] getToolNameArray(){
		return tools.keySet().toArray(new String[tools.size()]);
	}
	
	
	/**
	 * Select a tool from the currently used tools.
	 * @param t the name of the tool we want to select
	 */
	public void selectTool(String t){
		selectedTool = tools.get(t);
	}
	
	
	/**
	 * Get the currently selected tool.
	 * @return the tool that is currently selected
	 */
	public DrawTool getSelectedTool(){
		return (DrawTool) selectedTool;
	}
	
	
	/**
	 * Getter method for the panel's image.
	 * @return the BufferedImage component of the DrawingBoard
	 */
	public BufferedImage getImage(){
		return img;
	}
	
	
	/**
	 * Setter method for the panel's image.
	 * @param i the BufferedImage to be set
	 */
	public void setImage(BufferedImage i){
		img = i;
		repaint();
		setToolGraphics();
	}
	
	
	/**
	 * Override of the JPanel's paintComponent() method, draws the BufferedImage onto the JPanel.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	
	
	/**
	 * Override of the JPanel's getPreferredSize() method.
	 * <p>
	 * With this override, the JScrollPane will get the size of the BufferedImage and apply the scrollbars
	 * accordingly.
	 */
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(img.getWidth(), img.getHeight());
	}
	
	
	/**
	 * Mouse motion listener class.
	 * <p>
	 * Passes the mouse event to the currently selected tool's performAction() method.
	 * @author Dániel Gál
	 *
	 */
	class motionListener implements MouseMotionListener {

		public void mouseDragged(MouseEvent drag) {
			selectedTool.performDragAction(drag);
			repaint();
		}

		public void mouseMoved(MouseEvent move) {}
	}
	

	/**
	 * Mouse click listener class.
	 * <p>
	 * Passes the mouse event to the currently selected tool's performAction() method.
	 * @author Dániel Gál
	 *
	 */
	class clickListener implements MouseListener {

		public void mouseClicked(MouseEvent click) {
			selectedTool.performClickAction(click);
			repaint();
		}
		
		
		public void mousePressed(MouseEvent press) {
			selectedTool.performPressAction(press);
			repaint();
		}
		
		
		public void mouseReleased(MouseEvent release) {
			selectedTool.performReleaseAction(release);
			repaint();
		}	
		
		
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}			
		
	}

}
