package toolkit;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Abstract PaintingDrawTool class, superclass of every painting tool 
 * @author Dániel Gál
 *
 */
public abstract class PaintingDrawTool extends DrawTool {
	
	protected Color color;
	protected int size;

	
	/**
	 * Class constructor
	 * <p>
	 * Specifies the canvas' Graphics.
	 * @param g the Graphics object that will be painted on
	 */
	public PaintingDrawTool(BufferedImage i) {
		super(i);
	}

	
	/**
	 * Set the color of the tool.
	 * @param c color
	 */
	public void setColor(Color c) { color = c; }	
	
	
	/**
	 * Set the size of the tool.
	 * @param s size (pixels)
	 */
	public void setSize(int s) { size = s;}
	
	
	/**
	 * Get the color of the tool.
	 * @return color
	 */
	public Color getColor() { return color; }
	
	
	/**
	 * Get the size of the tool.
	 * @return size (pixels)
	 */
	public int getSize() { return size;	}
	
	
	/**
	 * Tool-specific action to be performed on the Graphics. Subclasses have to overwrite this.
	 * @param e the details of the event that triggered the function
	 */
	@Override
	public abstract void performClickAction(MouseEvent e);

}
