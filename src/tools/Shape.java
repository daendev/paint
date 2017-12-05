package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class Shape extends PaintingDrawTool {

	protected int startX = 0, startY = 0;
	private BufferedImage tempImage;
	
	public Shape(BufferedImage i) {
		super(i);
	}

	protected abstract void drawShape(MouseEvent e);
	
	private BufferedImage deepCopy(BufferedImage source){
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	@Override
	public void performClickAction(MouseEvent e) {

	}

	@Override
	public void performPressAction(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		tempImage = deepCopy(image);
	}

	@Override
	public void performReleaseAction(MouseEvent e) {
		drawShape(e);
	}

	@Override
	public void performDragAction(MouseEvent e) {
		image.getGraphics().drawImage(tempImage, 0, 0, null);
		drawShape(e);
	}

}
