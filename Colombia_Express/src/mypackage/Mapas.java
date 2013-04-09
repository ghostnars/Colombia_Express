package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class Mapas extends Metodos implements FieldChangeListener {


		
			private BitmapButtonField btnHome;
			
			int AnchoImagen = 640;
			int AltoImagen = 79;
			int ancho, alto, zoom;
			
			public Mapas() {	
				
				if (Display.getWidth() == 320)
				{
					AnchoImagen = 320;	
					AltoImagen = 39;
					ancho 	= 320;
					alto 	= 260;
					zoom 	= 16;
				}
				if (Display.getWidth() == 360)
				{
					AnchoImagen = 360;	
					AltoImagen = 44;
					ancho 	= 360;
					alto 	= 480;
					zoom 	= 18;
				}
				if (Display.getWidth() == 480)
				{
					AnchoImagen = 480;	
					AltoImagen = 58;
					ancho 	= 480;
					alto 	= 360;
					zoom 	= 17;					
				}	
				if (Display.getWidth() == 480 && Display.getWidth() < Display.getHeight())
				{
					ancho 	= 480;
					alto 	= 630;
					zoom 	= 18;
				}
				
				if (Display.getWidth() == 640)
				{

					ancho 	= 640;
					alto 	= 480;
					zoom 	= 17;
				}
				

				
			Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
				getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
				 //setTitle("Picture Scroll Field Demo");
				
				
				//Barra Titulo
				Bitmap imagenT 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
				BitmapField bitmapImg1 = new BitmapField( imagenT, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
				HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
				head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
				head.add(bitmapImg1);
				//head.setMargin(0, 0, -5, 0);
				setBanner(head);
				
				//Barra Titulo
				
				/*BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
		      	myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE,BrowserFieldConfig.NAVIGATION_MODE_NONE);
		        BrowserField browserField = new BrowserField(myBrowserFieldConfig);
				browserField.requestContent("<iframe width='400' height='300' frameborder='0' scrolling='no' marginheight='0' marginwidth='0' "+
											"src='http://dev.virtualearth.net/embeddedMap/v1/ajax/aerial?zoomLevel=10&center=47.5_-122.5&pushpins=47.5_-122.5'/>");									 	
				add(browserField);
				
				try{
				       
				        Bitmap bitmp = ImageUrl("http://maps.google.com/maps/api/staticmap?size=650x450&zoom=19&maptype=hybrid&%20&markers=size:small%10Ccolor:red%7Clabel:A%7C13.698445,-89.220272&sensor=false");
				        BitmapField fieldDemo = new BitmapField(bitmp, Field.FIELD_HCENTER); 
				        add(fieldDemo);
				}catch(Exception e){
					Dialog.alert("error en el mapa"+e);
				}

		        LabelField hola = new LabelField("HOLACH");
				BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
		        myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE,BrowserFieldConfig.NAVIGATION_MODE_NONE);
		        BrowserField browserField = new BrowserField(myBrowserFieldConfig);
				 browserField.requestContent("");									 	
				add(browserField);*/
				
				
				
				try{    
			        Bitmap bitmp = ImageUrl("http://maps.google.com/maps/api/staticmap?size="+ancho+"x"+alto+"&zoom="+zoom+"&maptype=hybrid&%20&markers=size:small%10Ccolor:red%7Clabel:A%7C13.698445,-89.220272&sensor=false");
			        BitmapField fieldDemo = new BitmapField(bitmp, Field.FIELD_HCENTER); 
			        add(fieldDemo);
				}catch(Exception e){
					Dialog.alert("error en el mapa"+e);
				}
				
				
			}


		
		
	

	public void fieldChanged(Field field, int arg1) {
		// TODO Auto-generated method stub
		if(btnHome== field){
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

