package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/*public class Popimage extends PopupScreen {
	Bitmap bordes;
	Popimage(){
	      super(new Popimage());
	   }
	   class MyManager extends VerticalFieldManager
	   {
	       MyManager(){
	         super();
	       }
	       
	       protected void subpaint(Graphics g){
	    	 bordes = Bitmap.getBitmapResource("rounded-border1.png");  
	          g.drawBitmap(0, 0, 100, 100, bordes, 0, 0);
	          super.subpaint(g);
	       }
	   }
	}*/
public class Popimage extends PopupScreen {
	static VerticalFieldManager img;
	ButtonField close;
public Popimage() {
	super( img = new VerticalFieldManager());
	BitmapField bordes = new BitmapField(Bitmap.getBitmapResource("rounded-border1.png"));
    img.add(bordes);
    close = new ButtonField("Holach");
    close.setChangeListener(

    		new FieldChangeListener()
            {
                public void fieldChanged(Field field, int context)
    			         {
    				          close(); 
    			         }
            });
    		
    		
    img.add(close);
    
}

}