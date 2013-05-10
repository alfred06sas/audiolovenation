package view;

import javax.swing.JPanel;

import paintable.Paintable;

public abstract class PaintableView{
	Paintable paintable;
	static JPanel panel;
	
	public PaintableView(){
		paintable=new Paintable();
		panel=new JPanel();
	}
	
	public abstract void onDraw();
	
	public void setPaintable(Paintable p){
		paintable=p;
	}

}
