package toolkit;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Abstract DrawTool class, superclass of all tools.
 * <p>
 * All the tools that can be selected and perform a specific action extend from this class.
 * @author Dániel Gál
 *
 */
public abstract class DrawTool {

	protected BufferedImage image;
	
	/**
	 * Class constructor
	 * @param g a Graphics object of an image that can be painted on by the DrawTool
	 */
	public DrawTool(BufferedImage i){
		setImage(i);
	}


	public void setImage(BufferedImage i){
		image = i;
	}
	
	/**
	 * Tool-specific action to be performed on the Graphics. Subclasses have to overwrite this.
	 * @param e the details of the event that triggered the function
	 */
	public abstract void performClickAction(MouseEvent e);
	
	
	public abstract void performPressAction(MouseEvent e);
	
	
	public abstract void performReleaseAction(MouseEvent e);
	
	
	public abstract void performDragAction(MouseEvent e);
	
}