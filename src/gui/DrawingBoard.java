package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawingBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
//	private int brushSize = 20;
//	private Color brushColor = Color.BLACK;
	private BufferedImage img;
	
//	public void setBrushSize(int x){
//		brushSize = x;
//	}
//	
//	public void setBrushColor(Color c){
//		brushColor = c;
//	}
	
	public DrawingBoard(){
		img = new BufferedImage(700, 300, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics imageGraphics = img.getGraphics();
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.fillRect(0, 0, img.getWidth(), img.getHeight());
		imageGraphics.setColor(Color.RED);
		imageGraphics.fillRect(20, 20, 50, 100);
		g.drawImage(img, 0, 0, null);
	}
	

}
