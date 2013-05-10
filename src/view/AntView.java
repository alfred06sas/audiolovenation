package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import land.Dir;
import movable.Ant;

public class AntView extends PaintableView{

	@Override
	public void onDraw() {
		Ant ant=(Ant)paintable;
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/ant.png"));
			Dir dir = ant.getDir();
			
			switch (dir) {
			case UP:
				
				break;
			case DOWN:
				dir = Dir.UP;
				break;
			case RIGHT_TOP:
				dir = Dir.LEFT_BOTTOM;
				break;
			case RIGHT_BOTTOM:
				dir = Dir.LEFT_TOP;
				break;
			case LEFT_TOP:
				dir = Dir.RIGHT_BOTTOM;
				break;
			case LEFT_BOTTOM:
				dir = Dir.RIGHT_TOP;
				break;
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		String id=ant.getActualField().getId();
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
