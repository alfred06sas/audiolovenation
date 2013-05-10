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
			Dir dir = ant.getDir();		
			switch (dir) {
			case UP:
				img = ImageIO.read(new File("images/ant_up.png"));
				break;
			case DOWN:
				img = ImageIO.read(new File("images/ant_down.png"));
				break;
			case RIGHT_TOP:
				img = ImageIO.read(new File("images/ant_top_right.png"));
				break;
			case RIGHT_BOTTOM:
				img = ImageIO.read(new File("images/ant_bottom_right.png"));
				break;
			case LEFT_TOP:
				img = ImageIO.read(new File("images/ant_top_left.png"));
				break;
			case LEFT_BOTTOM:
				img = ImageIO.read(new File("images/ant_bottom_left.png"));
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
