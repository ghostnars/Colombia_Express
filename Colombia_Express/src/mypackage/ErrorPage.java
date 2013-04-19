package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import estilos.Metodos;

public class ErrorPage extends Metodos implements FieldChangeListener{
	
	Bitmap headImage;
	Bitmap bitmapImg;
	public ErrorPage(){
		
		if (Display.getWidth() == 320)	
		{
			bitmapImg = Bitmap.getBitmapResource("error_320.png");
			
		}
		else if (Display.getWidth() == 360)	
		{
			bitmapImg = Bitmap.getBitmapResource("error_360.png");
			
		}
		else if (Display.getWidth() == 480)	
		{
			bitmapImg = Bitmap.getBitmapResource("error_480.png");
			
		}
		else if (Display.getWidth() == 640)	
		{

			bitmapImg = Bitmap.getBitmapResource("error_640.png");
		}
			
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		
		if (Display.getWidth() == 320)
		{
			headImage = Bitmap.getBitmapResource("titulo_320.png");
		}
		if (Display.getWidth() == 360)
		{
			headImage = Bitmap.getBitmapResource("titulo_360.png");

		}
		if (Display.getWidth() == 480)
		{
			headImage = Bitmap.getBitmapResource("titulo_480.png");
		}	
		if (Display.getWidth() == 640)
		{
			headImage = Bitmap.getBitmapResource("titulo_640.png");

		}
		
		
		BitmapField bitmapImg1 = new BitmapField( headImage, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.GOLD, Color.GOLD,Color.GOLD,Color.GOLD));
		head.add(bitmapImg1);
		//head.setMargin(0, 0, -5, 0);
		setBanner(head);
		

	
		
	}
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		
		
		

		
		
		
		
	}
	public boolean onClose() {
		 TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
	        openScreen(new Menu());
		return true;
	}
}


