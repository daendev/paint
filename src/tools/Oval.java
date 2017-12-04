package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Oval extends PaintingDrawTool {

	private int startX = 0, startY = 0;
	private Graphics temporaryGraphics;
	
	public Oval(Graphics g) {
		super(g);
	}

	@Override
	public void performClickAction(MouseEvent e) {}

	@Override
	public void performPressAction(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		temporaryGraphics = g;
	}

	@Override
	public void performReleaseAction(MouseEvent e) {
		createOval(e);
	}
	
	private void createOval(MouseEvent e){
		g.setColor(color);
		int sizeX = Math.abs(e.getX() - startX);
		int sizeY = Math.abs(e.getY() - startY);
		startX = startX < e.getX() ? startX : e.getX();
		startY = startY < e.getY() ? startY : e.getY();
		if(size<4){
			for(int i=0;i<size;++i)
				g.drawOval(startX+i, startY+i, sizeX-2*i, sizeY-2*i);
			return;
		}
		for(int i=0;i<size;++i){
			g.drawOval(startX+i, startY+i, sizeX-2*i, sizeY-2*i);
			g.drawOval(startX+i, startY+i-1, sizeX-2*i, sizeY-2*i-1);
			g.drawOval(startX+i-1, startY+i, sizeX-2*i-1, sizeY-2*i);
			g.drawOval(startX+i-1, startY+i-1, sizeX-2*i-1, sizeY-2*i-1);
		}
	}

	@Override
	public void performDragAction(MouseEvent e) {
		
	}

}
