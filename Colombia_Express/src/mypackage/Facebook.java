package mypackage;

import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.browser.field2.BrowserFieldConfig;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class Facebook extends Metodos implements FieldChangeListener  {

	private BitmapButtonField btnHome;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	public Facebook(){
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		 //setTitle("Picture Scroll Field Demo");
		
		if (Display.getWidth() == 320)
		{
			AnchoImagen = 320;	
			AltoImagen = 39;

		}
		if (Display.getWidth() == 360)
		{
			
			AnchoImagen = 360;	
			AltoImagen = 44;

		}
		if (Display.getWidth() == 480)
		{
			
	
		}	
		if (Display.getWidth() == 640)
		{
			
		}
		Bitmap imagenT 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagenT, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		//head.setMargin(0, 0, -5, 0);
		setBanner(head);
		
		BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
	    myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE,BrowserFieldConfig.NAVIGATION_MODE_POINTER);
	    myBrowserFieldConfig.setProperty(BrowserFieldConfig.ENABLE_COOKIES,Boolean.TRUE);
	    BrowserField browserField = new BrowserField(myBrowserFieldConfig);
	    browserField.requestContent("http://m.facebook.com/svIMOVES?refid=46");
	    add(browserField);
    
	}

	public void fieldChanged(Field field, int arg1) {
		// TODO Auto-generated method stub
		if(btnHome== field){
			//Status.show("Cargando...");
			TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
			openScreen(new Menu());
		}
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
