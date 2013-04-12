package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
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
	
	BitmapField logo;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	public ErrorPage(){
		Bitmap bitmapImg = Bitmap.getBitmapResource("nasty.png");
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(0x425d7b));
		if (Display.getWidth() == 320)
		{
			logo = new BitmapField(Bitmap.getBitmapResource("error_240.png"));
			AnchoImagen = 320;	
			AltoImagen = 39;
		}
		if (Display.getWidth() == 360)
		{
			logo = new BitmapField(Bitmap.getBitmapResource("error_360.png"));
			AnchoImagen = 360;	
			AltoImagen = 44;
		}
		if (Display.getWidth() == 480)
		{
			logo = new BitmapField(Bitmap.getBitmapResource("error_360.png"));
			AnchoImagen = 360;	
			AltoImagen = 44;
		}	
		if (Display.getWidth() == 640)
		{
			
			logo = new BitmapField(Bitmap.getBitmapResource("error_480.png"));
			AnchoImagen = 480;	
			AltoImagen = 58;
		}
		
		
		/*Bitmap imagen 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagen, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		head.setMargin(0, 0, 0, 0);
		add(head);*/
		
		HorizontalFieldManager rowHolder = new HorizontalFieldManager(NO_HORIZONTAL_SCROLL | NO_VERTICAL_SCROLL| Field.FIELD_HCENTER );
		rowHolder.add(logo);
		add(rowHolder);
		
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


