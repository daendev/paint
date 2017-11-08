package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Brush extends DrawTool {

	private Color color;
	private int size;
	
	public Brush(Graphics g) {
		super(g);
		color = Color.BLACK;
		size = 20;
	}

	@Override
	public void performAction(MouseEvent e) {
		g.setColor(color);
		g.fillOval(e.getX()-size/2, e.getY()-size/2, size, size);
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void setSize(int s){
		size = s;
	}
	
	public Color getColor(){
		return color;
	}
	
	public int getSize(){
		return size;
	}

}
