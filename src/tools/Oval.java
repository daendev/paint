package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import canvas.DrawingBoard;

public class Oval extends PaintingDrawTool {

	private int startX = 0, startY = 0;
	private BufferedImage tempImage;
	
	public Oval(BufferedImage i) {
		super(i);
	}

	@Override
	public void performClickAction(MouseEvent e) {}

	@Override
	public void performPressAction(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		tempImage = new BufferedImage(DrawingBoard.IMAGE_WIDTH,
									  DrawingBoard.IMAGE_HEIGHT,
									  BufferedImage.TYPE_INT_RGB);
	}

	@Override
	public void performReleaseAction(MouseEvent e) {
		createOval(e);
	}
	
	private void createOval(MouseEvent e){
		Graphics g = image.getGraphics();
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
