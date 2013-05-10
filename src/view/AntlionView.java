package view;

import item.Antlion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import land.Field;

public class AntlionView extends PaintableView{

	@Override
	public void onDraw() {
		Antlion antlion=(Antlion)paintable;
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/antlion.png"));
		} catch (IOException e) {
			System.out.println(e);
		}

		String id=antlion.getActualField().getId();
		String[] sa=id.split("_");
		System.out.println(id);
		int x=Integer.valueOf(sa[1]);
		int y=Integer.valueOf(sa[0]);
		
		x = x * 75;
		y = y * 100;
		y=(int)y/2;
		
		panel.getGraphics().drawImage(img, x, y, null);
	}

}
