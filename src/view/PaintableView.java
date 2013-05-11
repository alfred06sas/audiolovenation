package view;

import javax.swing.JPanel;

import paintable.Paintable;

public class PaintableView{
	protected Paintable paintable;
	public static GamePanel panel=new GamePanel();
	
	public PaintableView() {
		paintable=new Paintable();
		
	}
	
	public void onDraw(){}
	
	public void setPaintable(Paintable p){
		paintable=p;
	}

}
