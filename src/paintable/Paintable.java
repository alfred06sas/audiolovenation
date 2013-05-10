package paintable;

import view.PaintableView;


public class Paintable {
	public PaintableView paintableView;
	

	public void setView(){
		paintableView=new PaintableView();
		paintableView.setPaintable(this);
	}
	 
	
	public void notifyView(){
		paintableView.onDraw();
	}
	 
	public void setPaintableView(PaintableView pv){
		 paintableView=pv;
	}

}
