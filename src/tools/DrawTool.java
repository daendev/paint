package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class DrawTool {

	Graphics g;
	String name;
	
	
	public DrawTool(String name, Graphics g){
		this.g = g;
		this.name = name;
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public abstract void performAction(MouseEvent e);
	
}