package saveload;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveLoadAgent {

	BufferedImage img;
	
	public SaveLoadAgent(BufferedImage i){
		img = i;
	}
	
	public boolean save(String path){
		try {
			FileOutputStream f = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(img);
			out.close();
			return true;
		} catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean load(String path){
		try {
			FileInputStream f = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(f);
			img = (BufferedImage)in.readObject();
			in.close();
			return true;
		} catch(IOException e){
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}
	
}
