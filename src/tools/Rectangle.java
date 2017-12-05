package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import toolkit.Shape;

public class Rectangle extends Shape {

	public Rectangle(BufferedImage i) {
		super(i);
	}

	@Override
	protected void drawShape(MouseEvent e) {
		Graphics g = image.getGraphics();
		g.setColor(color);
		int sizeX = Math.abs(e.getX() - startX);
		int sizeY = Math.abs(e.getY() - startY);
		int localStartX = startX < e.getX() ? startX : e.getX();
		int localStartY = startY < e.getY() ? startY : e.getY();
		for(int i=0;i<size;++i){
			g.drawRect(localStartX+i, localStartY+i, sizeX-2*i-1, sizeY-2*i-1);
		}
	}

}
