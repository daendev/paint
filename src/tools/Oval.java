package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import toolkit.Shape;

public class Oval extends Shape {
	
	public Oval(BufferedImage i) {
		super(i);
	}
	
	protected void drawShape(MouseEvent e){
		Graphics g = image.getGraphics();
		g.setColor(color);
		int sizeX = Math.abs(e.getX() - startX);
		int sizeY = Math.abs(e.getY() - startY);
		int localStartX = startX < e.getX() ? startX : e.getX();
		int localStartY = startY < e.getY() ? startY : e.getY();
		if(size<4){
			for(int i=0;i<size;++i)
				g.drawOval(localStartX+i, localStartY+i, sizeX-2*i, sizeY-2*i);
			return;
		}
		for(int i=0;i<size;++i){
			g.drawOval(localStartX+i, localStartY+i, sizeX-2*i, sizeY-2*i);
			g.drawOval(localStartX+i, localStartY+i-1, sizeX-2*i, sizeY-2*i-1);
			g.drawOval(localStartX+i-1, localStartY+i, sizeX-2*i-1, sizeY-2*i);
			g.drawOval(localStartX+i-1, localStartY+i-1, sizeX-2*i-1, sizeY-2*i-1);
		}
	}

}
