package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * Pencil tool for painting squares
 * @author Dániel Gál
 *
 */
public class Pencil extends PaintingDrawTool {

	
	/**
	 * Class constructor
	 * <p>
	 * Sets the default color and size of the square, also the Graphics of the canvas.
	 * @param g the Graphics object that will be painted on
	 */
	public Pencil(Graphics g) {
		super(g);
		color = Color.BLACK;
		size = 20;
	}

	
	/**
	 * When the mouse is clicked or dragged, squares get painted on the canvas.
	 */
	@Override
	public void performClickAction(MouseEvent e) {
		g.setColor(color);
		g.fillRect(e.getX()-size/2, e.getY()-size/2, size, size);
	}


	@Override
	public void performPressAction(MouseEvent e) {}


	@Override
	public void performReleaseAction(MouseEvent e) {}

}
