package paintable;

import view.PaintableView;


public class Paintable {
	public PaintableView paintableView;
	
	public Paintable(){
		paintableView=new PaintableView();
	}

	public void setView(){
		paintableView.setPaintable(this);
	}
	 
	
	public void notifyView(){
		paintableView.onDraw();
	}
	 
	public void setPaintableView(PaintableView pv){
		 paintableView=pv;
	}

}
