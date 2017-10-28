package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class DrawingBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int brushSize = 20;
	private Color brushColor = Color.BLACK;
	
	public void setBrushSize(int x){
		brushSize = x;
	}
	
	public void setBrushColor(Color c){
		brushColor = c;
	}
	
	

}
