package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import movable.Echidna;

public class EchidnaView extends PaintableView{

	@Override
	public void onDraw() {
		Echidna echidna=(Echidna)paintable;
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/echidna.png"));
		} catch (IOException e) {
			System.out.println(e);
		}

		String id=echidna.getActualField().getId();
		String[] sa=id.split("_");
		int x=Integer.valueOf(sa[1]);
		int y=Integer.valueOf(sa[0]);
		
		x = x * 75;
		y = y * 100;
		y=(int)y/2;
		
		panel.getGraphics().drawImage(img, x, y, null);
	}

}
