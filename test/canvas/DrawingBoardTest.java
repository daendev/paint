package canvas;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import org.junit.Assert; 
import org.junit.Before;
import org.junit.Test;
import tools.Brush;
import tools.Pencil;

public class DrawingBoardTest {

	DrawingBoard board;
	
	@Before
	public void setUp(){
		board = new DrawingBoard();
	}
	
	@Test
	public void testImageGetterSetter(){
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		board.setImage(image);
		Assert.assertEquals(image, board.getImage());
	}
	
	@Test
	public void testDefaultToolIsBrush(){
		Assert.assertEquals(Brush.class, board.getSelectedTool().getClass());
	}
	
	@Test
	public void testSelectTool(){
		board.selectTool("Pencil");
		Assert.assertEquals(Pencil.class, board.getSelectedTool().getClass());
	}
	
	@Test
	public void testDefaultImageSize(){
		int w = board.getImage().getWidth();
		int h = board.getImage().getHeight();
		Assert.assertEquals(700,w);
		Assert.assertEquals(350,h);		
	}
	
	@Test
	public void testToolNameArray(){
		String[] array = {"Pencil", "Brush"};
		Assert.assertArrayEquals(array, board.getToolNameArray());
	}
	
	@Test
	public void preferredSizeTest(){
		BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		board.setImage(image);
		board.setPreferredSize(new Dimension(1000,700));
		Assert.assertEquals(new Dimension(500,500), board.getPreferredSize());
	}
}
