package tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.Assert; 
import org.junit.Test;

public class BrushTest {

	@Test
	public void testBrushConstructor(){
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		Brush brush = new Brush(image.getGraphics());
		Assert.assertEquals(20, brush.getSize());
		Assert.assertEquals(Color.BLACK, brush.getColor());
	}
	
}
