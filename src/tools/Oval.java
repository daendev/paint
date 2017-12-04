package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Oval extends PaintingDrawTool {

	private int startX, startY;
	
	public Oval(Graphics g) {
		super(g);
	}

	@Override
	public void performClickAction(MouseEvent e) {}

	@Override
	public void performPressAction(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
	}

	@Override
	public void performReleaseAction(MouseEvent e) {
		g.setColor(Color.BLACK);
		int sizeX = Math.abs(e.getX() - startX);
		int sizeY = Math.abs(e.getY() - startY);
		startX = startX < e.getX() ? startX : e.getX();
		startY = startY < e.getY() ? startY : e.getY();
		g.drawOval(startX, startY, sizeX, sizeY);
	}

}
