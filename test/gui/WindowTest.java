package gui;

import java.awt.Dimension;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WindowTest {

	Window w;
	
	@Before
	public void setUp(){
		w = new Window();
	}
	
	@Test
	public void testTitle(){
		Assert.assertEquals("Pænt", w.getTitle());
	}
	
	@Test
	public void testMinimumSize(){
		Dimension d = w.getMinimumSize();
		w.setSize(100, 100);
		Assert.assertEquals(d, w.getSize());
	}
}
