package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import tools.Brush;

public class DrawingBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage img;
	private Brush brush;

	
	public DrawingBoard(){
		img = new BufferedImage(700, 300, BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = img.getGraphics();
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.fillRect(0, 0, img.getWidth(), img.getHeight());
		brush = new Brush(imageGraphics);
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
			brush.performAction(drag);
			repaint();
		}

		public void mouseMoved(MouseEvent move) {}
	}
	
	
	class clickListener implements MouseListener {

		public void mouseClicked(MouseEvent click) {
			brush.performAction(click);
			repaint();
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}

}
