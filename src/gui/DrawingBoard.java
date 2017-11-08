package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
		Graphics imageGraphics = img.getGraphics();
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.fillRect(0, 0, img.getWidth(), img.getHeight());
		this.addMouseListener(new clickListener());
		this.addMouseMotionListener(new motionListener());
	}
	
	public Graphics getImageGraphics(){
		return img.getGraphics();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	
	class motionListener implements MouseMotionListener {

		public void mouseDragged(MouseEvent drag) {
			Graphics g = getImageGraphics();
			g.setColor(Color.BLUE);
			g.fillOval(drag.getX()-10, drag.getY()-10, 20, 20);
			repaint();
		}

		public void mouseMoved(MouseEvent move) {}
	}
	
	class clickListener implements MouseListener {

		public void mouseClicked(MouseEvent click) {
			Graphics g = getImageGraphics();
			g.setColor(Color.BLUE);
			g.fillOval(click.getX()-10, click.getY()-10, 20, 20);
			repaint();
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}

}
