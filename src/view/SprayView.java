package view;

import item.Spray;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SprayView extends PaintableView{

	@Override
	public void onDraw() {
		Spray spray=(Spray)paintable;
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/spray.png"));
		} catch (IOException e) {
			System.out.println(e);
		}

		String id=spray.getActualField().getId();
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
