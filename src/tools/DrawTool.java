package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class DrawTool {

	Graphics g;
	
	public DrawTool(Graphics g){
		this.g = g;
	}
	
	public abstract void performAction(MouseEvent e);
	
}