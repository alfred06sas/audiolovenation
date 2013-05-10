package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import blockage.Gravel;

public class GravelView extends PaintableView{

	@Override
	public void onDraw() {
		Gravel gravel=(Gravel)paintable;
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/gravel.png"));
		} catch (IOException e) {
			System.out.println(e);
		}

		String id=gravel.getActualField().getId();
		String[] sa=id.split("_");
		System.out.println(id);
		int x=Integer.valueOf(sa[0]);
		int y=Integer.valueOf(sa[1]);
		
		x = x * 75;
		y = y * 100;
		y=(int)y/2;
		
		panel.getGraphics().drawImage(img, x, y, null);
	}
}
