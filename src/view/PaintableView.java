package view;

import javax.swing.JPanel;

import paintable.Paintable;

public class PaintableView{
	protected Paintable paintable = new Paintable();
	public static JPanel panel=new JPanel();
	
	
	public void onDraw(){}
	
	public void setPaintable(Paintable p){
		paintable=p;
	}

}
