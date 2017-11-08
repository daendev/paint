package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class Tool {

	Graphics g;
	
	public Tool(Graphics g){
		this.g = g;
	}
	
	public abstract void performAction(MouseEvent e);
	
}
