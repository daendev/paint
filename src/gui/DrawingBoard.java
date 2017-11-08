package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import tools.*;
import javax.swing.JPanel;


public class DrawingBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage img;
	private Map<String, DrawTool> tools = new HashMap<String, DrawTool>();
	private DrawTool selectedTool;

	
	public DrawingBoard(){
		img = new BufferedImage(700, 300, BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = img.getGraphics();
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		tools.put("brush", new Brush(imageGraphics));
		
		this.addMouseListener(new clickListener());
		this.addMouseMotionListener(new motionListener());
		
		selectedTool = tools.get("brush");
	}
	
	
	public void selectTool(String t){
		selectedTool = tools.get(t);
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
			selectedTool.performAction(drag);
			repaint();
		}

		public void mouseMoved(MouseEvent move) {}
	}
	
	
	class clickListener implements MouseListener {

		public void mouseClicked(MouseEvent click) {
			selectedTool.performAction(click);
			repaint();
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}

}
