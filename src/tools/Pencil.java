package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Pencil extends PaintingDrawTool {

	public Pencil(Graphics g) {
		super(g);
		color = Color.BLACK;
		size = 20;
	}

	@Override
	public void performAction(MouseEvent e) {
		g.setColor(color);
		g.fillRect(e.getX()-size/2, e.getY()-size/2, e.getX()+size, e.getY()+size);
	}

}
