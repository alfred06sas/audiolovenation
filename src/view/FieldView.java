package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import land.Field;

public class FieldView extends PaintableView{

	@Override
	public void onDraw() {
		Field field=(Field)paintable;
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/hatszog.png"));
		} catch (IOException e) {
			System.out.println(e);
		}

		String id=field.getId();
		String[] sa=id.split("_");
		int x=Integer.valueOf(sa[0]);
		int y=Integer.valueOf(sa[1]);
		
		x = x * 75;
		y = y * 100;
		y=(int)y/2;
		if ((x % 2) != 0)
			y = y + 50;
		
		panel.getGraphics().drawImage(img, x, y, null);

	}
}
