package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Brush extends PaintingDrawTool {

	
	public Brush(Graphics g) {
		super("Brush",g);
		color = Color.BLACK;
		size = 20;
	}

	@Override
	public void performAction(MouseEvent e) {
		g.setColor(color);
		g.fillOval(e.getX()-size/2, e.getY()-size/2, size, size);
	}
}
