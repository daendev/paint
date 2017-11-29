package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * Brush tool for painting circles
 * @author Dániel Gál
 *
 */
public class Brush extends PaintingDrawTool {

	
	/**
	 * Class constructor
	 * <p>
	 * Sets the default color and size of the circle, also the Graphics of the canvas.
	 * @param g the Graphics object that will be painted on
	 */
	public Brush(Graphics g) {
		super(g);
		color = Color.BLACK;
		size = 20;
	}

	/**
	 * When the mouse is clicked or dragged, circles get painted on the canvas.
	 */
	@Override
	public void performAction(MouseEvent e) {
		g.setColor(color);
		if(size==1) g.fillOval(e.getX(), e.getY(), 1, 2);
		else g.fillOval(e.getX()-size/2, e.getY()-size/2, size, size);
	}
}
