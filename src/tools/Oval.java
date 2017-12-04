package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
		// copy buffered image:
		tempImage = deepCopy(image);
		
	}

	@Override
	public void performReleaseAction(MouseEvent e) {
		createOval(e);
	}
	
	private BufferedImage deepCopy(BufferedImage source){
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	private void createOval(MouseEvent e){
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

	@Override
	public void performDragAction(MouseEvent e) {
		image.getGraphics().drawImage(tempImage, 0, 0, null);
		createOval(e);
	}

}
