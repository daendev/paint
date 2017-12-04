package tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.Assert; 
import org.junit.Test;

public class PencilTest {

	@Test
	public void testPencilConstructor(){
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		Pencil pencil = new Pencil(image);
		Assert.assertEquals(20, pencil.getSize());
		Assert.assertEquals(Color.BLACK, pencil.getColor());
	}
	
}
