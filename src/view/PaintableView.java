package view;

import javax.swing.JPanel;

import paintable.Paintable;

public class PaintableView{
	protected Paintable paintable;
	public static JPanel panel;
	
	public PaintableView(){
		paintable=new Paintable();
		panel=new JPanel();
	}
	
	public void onDraw(){}
	
	public void setPaintable(Paintable p){
		paintable=p;
	}

}
