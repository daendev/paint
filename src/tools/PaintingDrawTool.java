package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class PaintingDrawTool extends DrawTool {
	
	protected Color color;
	protected int size;

	public PaintingDrawTool(Graphics g) {
		super(g);
	}

	
	public void setColor(Color c) { color = c; }	
	public void setSize(int s) { size = s; }
	public Color getColor() { return color; }
	public int getSize() { return size;	}
	
	
	@Override
	public abstract void performAction(MouseEvent e);

}
